package com.atguigu.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 集中处理所有异常
 *
 * 1、集中处理所有异常的类加入到ioc容器中
 * 2.@ControllerAdvice专门处理异常的类
 */
@ControllerAdvice
public class MyJiZhongExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public ModelAndView handleException01(Exception exception){
        System.out.println("全局的：handleException02..."+exception);
        ModelAndView modelAndView = new ModelAndView("myerror");
        modelAndView.addObject("ex", exception);
        //视图解析器拼串
        return modelAndView;
    }

//    @ExceptionHandler(value = {Exception.class})
//    public ModelAndView handleException02(Exception exception){
//        System.out.println("全局的：handleException02..."+exception);
//        ModelAndView modelAndView = new ModelAndView("myerror");
//        modelAndView.addObject("ex", exception);
//        //视图解析器拼串
//        return modelAndView;
//    }


}
