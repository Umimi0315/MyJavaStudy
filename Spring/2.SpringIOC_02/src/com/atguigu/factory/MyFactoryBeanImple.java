package com.atguigu.factory;

import com.atguigu.bean.Book;
import org.springframework.beans.factory.FactoryBean;

import java.util.UUID;

/**
 * 实现了FactoryBean接口的类是Spring可以认识的工厂类
 * Spring会自动调用工厂方法创建实例
 *
 * 1.编写一个FactoryBean的实现类
 * 2.在Spring配置文件中注册
 */
public class MyFactoryBeanImple implements FactoryBean<Book> {

    /**
     * getObject:工厂方法;
     *      返回创建的对象
     * @return
     * @throws Exception
     */
    @Override
    public Book getObject() throws Exception {
        System.out.println("MyFactoryBeanImple帮你创建对象...");
        Book book = new Book();
        book.setBookName(UUID.randomUUID().toString());
        return book;
    }

    /**
     *返回创建的对象的类型
     * Spring会自动调用这个方法来确认创建的对象是什么类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }

    /**
     * isSingleton:是单例？
     * false：不是单例
     * true：是单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
