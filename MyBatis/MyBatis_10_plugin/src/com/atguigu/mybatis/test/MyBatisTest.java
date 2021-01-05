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
     * 1、获取sqlSessionFactory对象：
     *      解析文件的每一个信息保存在Configuration中，返回包含Configuration的DefaultSqlSessionFactory
     *      注意：【MappedStatement】：代表一个增删改查的详细信息
     * 2、获取sqlSession对象
     *      返回一个DefaultSQLSession对象，包含Executor和Configuration；
     *      这一步会创建Executor对象;
     * 3、获取接口的代理对象（MapperProxy）
     *      getMapper，使用MapperProxyFactory创建一个MapperProxy的代理对象
     *      代理对象里面包含了，DefaultSqlSession（Executor）
     * 4、执行增删改查方法
     *
     *
     * 总结：
     *      1、根据配置文件（全局，sql映射）初始化出Configuration对象
     *      2、创建一个DefaultSqlSession对象
     *          他里面包含Configuration以及
     *          Executor（根据全局配置文件中的defaultExecutorType创建出对应的Executor）
     *      3、DefaultSQLSession.getMapper（）：拿到Mapper接口对应的MapperProxy对象
     *      4、MapperProxy里面有（DefaultSqlSession）；
     *      5、执行增删改查方法：
     *              1）、调用DefaultSqlSession的增删改查（Executor）；
     *              2）、会创建一个StatementHandler对象。
     *                  （同时也会创建出ParameterHandler和ResultHandler）
     *              3）、调用StatementHandler预编译参数以及设置参数值；
     *                  使用ParameterHandler的增删改查方法
     *              4）、调用StatementHandler的增删改查方法
     *              5)、ResultSetHandler封装结果
     *      注意：
     *          四大对象每个创建的时候都有一个interceptorChain.pluginAll（parameterHandler）；
     * @throws Exception
     */
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

    /**
     * 插件原理
     * 在四大对象创建的时候
     * 1、每个创建出来的对象不是直接返回的，而是
     *      interceptorChain.pluginAll(parameterHandler)
     * 2、获取到所有的Interceptor（拦截器）（插件需要实现的接口）
     *      调用interceptor.plugin（target）；返回target包装后的对象
     * 3、插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面）
     *      我们的插件可以为四大对象创建出代理对象；
     *      代理对象就可以拦截到四大对象的每一个执行;
     * public Object pluginAll(Object target){
     *     for(Interceptor interceptor:interceptors){
     *         target=interceptor.plugin(target);
     *     }
     *     return target;
     * }
     */
    /**
     * 插件编写：
     * 1、编写Interceptor的实现类
     * 2、使用@Intercepts注解完成插件签名
     * 3、将写好的插件注册到全局配置文件中
     *
     */
    @Test
    public void testPlugin(){

    }


}
