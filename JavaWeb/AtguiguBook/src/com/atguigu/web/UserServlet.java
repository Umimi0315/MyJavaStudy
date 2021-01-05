package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class UserServlet extends BaseServlet {

    UserService userService=new UserServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //调用userService.login()登录处理业务
        User loginUser=userService.login(new User(null, username, password, null));
        //如果等于null，说明登录失败
        if (loginUser==null){
            //把错误信息和回显的表单项信息，保存到request域中
            request.setAttribute("msg", "用户名或密码错误！");
            request.setAttribute("username", username);

            //跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }else {
            //登录成功
            //跳到成功页面login_success.html
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        User user=WebUtils.copyParamToBean(request.getParameterMap(), new User());

        //验证码，检查是否正确，写死，要求验证码为：abcde
        if ("abcde".equalsIgnoreCase(code)){
            if (userService.existUsername(username)){

                //把错误信息和回显的表单信息保存到request域中
                request.setAttribute("msg", "用户名已存在！");
                request.setAttribute("username", username);
                request.setAttribute("email", email);

                System.out.println("用户名["+username+"]已存在！");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else {
                userService.registUser(new User(null, username, password, email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            }

        }else {
            //把错误信息和回显的表单信息保存到request域中
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);

            System.out.println("验证码["+code+"]错误！");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this, request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
