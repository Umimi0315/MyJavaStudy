package com.atguigu.test;

import java.lang.reflect.Method;

public class UserServletTest {
    public void regist(){
        System.out.println("调用了regist()方法");
    }
    public void login(){
        System.out.println("调用了login()方法");
    }
    public void updateUser(){
        System.out.println("调用了updateUser()方法");
    }
    public void updateUserPassword(){
        System.out.println("调用了updateUserPassword()方法");
    }

    public static void main(String[] args) {
        String action="regist";
        try {
            Method method = UserServletTest.class.getDeclaredMethod(action);
            method.invoke(new UserServletTest());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
