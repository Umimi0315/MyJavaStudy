package com.atguigu.mybatis.test;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import com.atguigu.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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
            Employee employee = employeeMapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }
    @Test
    public void test02() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }


}
