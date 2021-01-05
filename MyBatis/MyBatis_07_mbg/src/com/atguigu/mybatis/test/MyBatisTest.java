package com.atguigu.mybatis.test;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.bean.EmployeeExample;
import com.atguigu.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testMbg() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void testMyBatis3Simple() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.selectByExample(null);
            for (Employee employee : employees) {
                System.out.println(employee.getId());
            }
        } finally {
            openSession.close();
        }
    }

    @Test
    public void testMyBatis3() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //xxxxExample就是封装查询条件的
            //1、查询所有
            //List<Employee> emps = mapper.selectByExample(null);
            //2、查询员工名字中有e字母的，和员工性别是1的
            //封装员工查询条件的example

            EmployeeExample example=new EmployeeExample();
            EmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andLastNameLike("%e%");
            criteria.andGenderEqualTo("1");

            EmployeeExample.Criteria criteria2 = example.createCriteria();
            criteria2.andEmailLike("%e%");
            example.or(criteria2);

            List<Employee> employees = mapper.selectByExample(example);

            for (Employee employee : employees) {
                System.out.println(employee.getId());
            }
        } finally {
            openSession.close();
        }
    }


}
