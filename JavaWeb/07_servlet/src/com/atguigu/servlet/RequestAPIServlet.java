package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        i. getRequestURI() 获取请求的资源路径
        System.out.println("URI =>"+req.getRequestURI());
//        ii.getRequestURL() 获取请求的统一资源定位符（绝对路径）
        System.out.println("URL =>"+req.getRequestURL());
//        iii. getRemoteHost() 获取客户端的 ip 地址
        /**
         * 在IDEA中，使用localhost访问时，得到的客户端ip地址是===>>>127.0.0.1
         * 在IDEA中，使用127.0.0.1访问时，得到的客户端ip地址是===>>>127.0.0.1
         * 在IDEA中，使用真实的ip访问时，得到的客户端ip地址是===>>>真实的客户端ip地址
         */
        System.out.println("客户端ip地址 =>"+req.getRemoteHost());
//        iv.getHeader() 获取请求头
        System.out.println("请求头User-Agent ==>>"+req.getHeader("User-Agent"));
//        vii. getMethod() 获取请求的方式 GET 或 POST
        System.out.println("请求的方式 ==>>"+req.getMethod());
    }
}
