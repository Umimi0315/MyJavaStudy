package com.atguigu.servlet;

import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * 属性的自动注入
 *
 *  @Autowired:Spring会自动的为这个属性赋值;
 *              一定是去容器中找到这个属性对应的组件
 */
@Controller
public class BookServlet {

    //自动装配，自动的为这个属性赋值
    //@Qualifier:指定一个名作为id，让Spring别使用变量名作为id
    @Qualifier("bookService")
    @Autowired(required = false)
    private BookService bookServiceExt2;

    public void doGet(){
        System.out.println("BookServlet...."+bookServiceExt2);
        bookServiceExt2.save();
    }

    /**
     * 方法上有@Autowired的话；
     * 1）、这个方法也会在Bean创建的时候自动运行
     * 2)、这个方法上的每一个参数都会自动注入值
     */
    @Autowired
    public void hahaha(BookDao bookDao,@Qualifier("bookServiceExt") BookService bookService){
        System.out.println("Spring运行了这个方法...."+bookDao+"===>"+bookService);
    }

}
