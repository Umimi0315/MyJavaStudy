package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        //验证码，检查是否正确，写死，要求验证码为：abcde
        if ("abcde".equalsIgnoreCase(code)){
            if (userService.existUsername(username)){
                System.out.println("用户名["+username+"]已存在！");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else {
                userService.registUser(new User(null, username, password, email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            }

        }else {
            System.out.println("验证码["+code+"]错误！");
           request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
