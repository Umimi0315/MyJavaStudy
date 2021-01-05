package com.atguigu.service;

import com.atguigu.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {

//    @Autowired
    @Resource
    private BookDao bookDao;

    public void save(){
        System.out.println("bookService...正在调用dao帮你保存图书...");
        bookDao.saveBook();
    }
}
