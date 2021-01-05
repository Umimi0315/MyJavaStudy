package com.atguigu.dao;

import com.atguigu.bean.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends BaseDao<Book> {
    @Override
    public void save() {
        System.out.println("BookDao...保存图书...");
    }
}
