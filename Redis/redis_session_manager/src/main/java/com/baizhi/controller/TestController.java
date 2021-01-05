package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("test")
//使用redis的session管理     注意：当session中数据发生变化时必须将session中变化的数据同步到redis中
public class TestController {

    @RequestMapping("test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> list = (List<String>) request.getSession().getAttribute("list");
        if (list==null){
            list=new ArrayList<>();

        }
        list.add("xxxxx");
        request.getSession().setAttribute("list", list);//每次session变化都要同步session
        response.getWriter().println("size:"+list.size());
        response.getWriter().println("sessionid:"+request.getSession().getId());
    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request){
        //退出登录
        request.getSession().invalidate();//失效
    }
}
