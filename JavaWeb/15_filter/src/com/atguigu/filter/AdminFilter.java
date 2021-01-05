package com.atguigu.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    public AdminFilter() {
        System.out.println("1.Filter构造器方法AdminFilter()");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("2.Filter的init(FilterConfig filterConfig)初始化方法");

//        1、获取 Filter 的名称 filter-name 的内容
        System.out.println("filter-name的值是:"+filterConfig.getFilterName());
//        2、获取在 web.xml 中配置的 init-param 初始化参数
        System.out.println("初始化参数username的值是:"+filterConfig.getInitParameter("username"));
        System.out.println("初始化参数url的值是:"+filterConfig.getInitParameter("url"));
//        3、获取 ServletContext 对象
        System.out.println(filterConfig.getServletContext());

    }

    /**
     * 专门用于拦截请求过滤响应.可以做权限检查
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("3.Filter的doFilter()过滤方法");
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpSession session=httpServletRequest.getSession();
        Object user=session.getAttribute("user");
        //如果等于null，说明还没有登录
        if (user==null){
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            return;
        }else {
            //让程序继续往下访问用户的目标资源
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        System.out.println("4.Filter的destroy()销毁方法");

    }
}
