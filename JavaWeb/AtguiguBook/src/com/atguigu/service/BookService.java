package com.atguigu.service;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    void deleteBookById(Integer id);
    void updateBook(Book book);
    Book queryBookById(Integer id);
    List<Book> queryBooks();
}
