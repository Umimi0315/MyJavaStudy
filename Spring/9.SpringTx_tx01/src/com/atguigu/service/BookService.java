package com.atguigu.service;

import com.atguigu.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookService {
    @Autowired
    BookDao bookDao;

    /**
     * 结账：传入哪个用户买了哪本书
     * @param username
     * @param isbn
     */
    @Transactional
    public void checkout(String username,String isbn){
        //1.减库存
        bookDao.updateStock(isbn);

        int price=bookDao.getPrice(isbn);

        //2、减余额
        bookDao.updateBalance(username, price);
    }

}
