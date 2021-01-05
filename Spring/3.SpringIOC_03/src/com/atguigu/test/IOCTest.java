package com.atguigu.test;

import com.atguigu.service.BookService;
import com.atguigu.servlet.BookServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用Spring的单元测试；
 * 1、导包：Spring单元测试包spring-test-4.0.0.RELEASE.jar
 * 2、@ContextConfiguration(locations = "")使用它来指定Spring的配置文件的位置
 * 3、@RunWith指定用哪种驱动进行单元测试，默认就是Junit
 *      @RunWith(SpringJUnit4ClassRunner.class)
 *      使用Spring的单元测试模块来执行标了@Test注解的测试方法;
 *      以前@Test注解只是有Junit执行
 * 好处：我们不用ioc.getBean()获取组件了，直接Autowired组件，Spring为我们自动装配
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IOCTest {

//    ApplicationContext ioc= new ClassPathXmlApplicationContext("applicationContext.xml");
    ApplicationContext ioc=null;

    @Autowired
    BookServlet bookServlet;

    @Autowired
    BookService bookService;

    @Test
    public void test03(){
        System.out.println(bookServlet);
        System.out.println(bookService);
    }

    @Test
    public void test02(){
        BookServlet bookServlet = ioc.getBean(BookServlet.class);
        bookServlet.doGet();
    }
    /**
     * java.lang.NoClassDefFoundError:
     * org/springframework/aop/TargetSource
     *
     * 使用注释加入到容器中的组件，和使用配置加入到容器中的组件行为都是默认一样的：
     * 1、组件的id。默认就是组件的类名首字母小写
     *          @Repository("bookdaohaha")
     *          public class BookDao {
     * 2、组件的作用域，默认就是单例的；
     *          @Scope(value = "prototype")
     */
    @Test
    public void test01(){
        Object bean = ioc.getBean("bookdaohaha");
        Object bean2 = ioc.getBean("bookdaohaha");
        System.out.println(bean==bean2);

    }

}
