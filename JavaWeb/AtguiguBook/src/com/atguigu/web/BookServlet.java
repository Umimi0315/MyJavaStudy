package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数封装成book对象
        Book book= WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //调用BookService.addBook()保存图书
        bookService.addBook(book);
        //跳到图书列表页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数id，图书编号
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        //调用bookService.deleteBookById（）删除图书
        bookService.deleteBookById(id);
        //重定向回图书列表管理页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数图书编号
        int id=WebUtils.parseInt(request.getParameter("id"), 0);
        //2 调用bookService.queryBookById查询图书
        Book book=bookService.queryBookById(id);
        //3 保存图书到Request域中
        request.setAttribute("book", book);
        //4 请求转发到pages/manager/book_edit.jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到Request域中
        request.setAttribute("books", books);

        //3 请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
}
