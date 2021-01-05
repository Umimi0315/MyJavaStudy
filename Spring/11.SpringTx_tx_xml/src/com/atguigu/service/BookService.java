package com.atguigu.service;

import com.atguigu.dao.BookDao;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BookService {

    @Autowired
    BookDao bookDao;


    public void checkout(String username,String isbn) {
        bookDao.updateStock(isbn);

        int price=bookDao.getPrice(isbn);

        bookDao.updateBalance(username, price);

        int i=10/0;

    }

    public void updatePrice(String isbn,int price){
        bookDao.updatePrice(isbn, price);
        //int div=10/0;
    }

    public int getPrice(String isbn){
       return bookDao.getPrice(isbn);
    }

    public void mulTx(){
        checkout("Tom", "ISBN-001");
        updatePrice("ISBN-002", 998);
    }

}
