package com.atguigu.mybatis.test;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * 1.接口式编程
 * 原生：          Dao         =====>     DaoImpl
 * mybatis:       Mapper      =====>     xxMapper.xml
 * 2.SqlSession代表和数据库的一次会话;用完必须关闭;
 * 3.SqlSession和connection一样，都是非线程安全。每次使用都应该获取新的对象
 * 4.mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 *      (将接口和xml进行绑定)
 *    EmployeeMapper empMapper =  sqlSession.getMapper(EmployeeMapper.class);
 *5.两个重要的配置文件：
 *      mybatis的全局配置文件，包含数据库连接池信息，事务管理器信息等..系统运行环境信息
 *      sql映射文件：保存了每一个sql语句的映射信息
 *                      将sql抽取出来。
 *
 */
public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 1.根据xml配置文件(全局配置文件)创建一个SqlSessionFactory对象
     *      有数据源一些运行环境信息
     * 2.sql映射文件，配置了每一个sql，以及sql的封装规则等。
     * 3.将sql映射文件注册在全局配置文件中
     * 4.写代码：
     *      1)根据全局配置文件得到SqlSessionFactory
     *      2）使用sqlSession工厂，获取到sqlSession对象，使用它来执行增删改查
     *          一个sqlSession就是代表和数据库的一次会话，用完关闭
     *      3)使用sql唯一标识告诉mybatis执行哪个sql，sql都是保存在sql映射文件中的
     */
    @Test
    public void test(){
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SQLSession实例，能直接执行已经映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();

        //sql唯一标识： Unique identifier matching the statement to use.
        //执行sql要用的参数:A parameter object to pass to the statement.

        try {
            Employee employee = openSession.selectOne("com.atguigu.mybatis.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test01() throws Exception{
        //1获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            //3获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Page<Object> page = PageHelper.startPage(1, 5);
            List<Employee> emps = employeeMapper.getEmps();
            //传入要连续显示多少页
            PageInfo<Employee> info = new PageInfo<>(emps, 5);
            for (Employee emp : emps) {
                System.out.println(emp);
            }
           /* System.out.println("当前页码："+page.getPageNum());
            System.out.println("总记录数："+page.getTotal());
            System.out.println("每页的记录数："+page.getPageSize());
            System.out.println("总页码："+page.getPages());*/
            ///xxx
            System.out.println("当前页码："+info.getPageNum());
            System.out.println("总记录数："+info.getTotal());
            System.out.println("每页的记录数："+info.getPageSize());
            System.out.println("总页码："+info.getPages());
            System.out.println("是否是第一页："+info.isIsFirstPage());
            System.out.println("连续显示的页码:");
            int[] nums = info.getNavigatepageNums();
            for (int i=0;i<nums.length;i++){
                System.out.println(nums[i]);
            }

        } finally {
            openSession.close();
        }
    }

    @Test
    public void testBatch() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //可以执行批量操作的sqlSession
        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        long start=System.currentTimeMillis();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            for (int i=0;i<10000;i++){
                mapper.addEmp(new Employee(UUID.randomUUID().toString().substring(0, 5),"B", "1"));
            }
            openSession.commit();
            long end = System.currentTimeMillis();
            //批量:(预编译sql一次===>设置参数===>10000次===>执行（1次）)4596
            //非批量：(预编译sql=设置参数=执行)==>10000  10200
        } finally {
            openSession.close();
        }

    }

    /**
     * 默认mybatis在处理枚举对象的时候保存的是枚举的名字：EnumTypeHandler
     * 改变使用：EnumOrdinalTypeHandler
     * @throws Exception
     */
    @Test
    public void testEnum() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee=new Employee("testEnum", "enum@atguigu.com", "1");
//            mapper.addEmp(employee);
//            System.out.println("保存成功:"+employee.getId());
//            openSession.commit();
            Employee empById = mapper.getEmpById(16);
            System.out.println(empById.getEmpStatus());
        } finally {
            openSession.close();
        }

    }


}
