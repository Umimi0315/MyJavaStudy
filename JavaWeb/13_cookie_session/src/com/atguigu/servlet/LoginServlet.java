package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if ("wzg168".equals(username)&&"123456".equals(password)){
            //登录成功
            Cookie cookie=new Cookie("username",username);
            cookie.setMaxAge(60*60*24*7);//当前cookie一周内有效
            response.addCookie(cookie);
            System.out.println("登录成功");
        }else {
            //登录失败
            System.out.println("登录失败");
        }
    }
}
