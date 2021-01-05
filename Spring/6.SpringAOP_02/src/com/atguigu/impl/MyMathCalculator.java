package com.atguigu.impl;

import com.atguigu.inter.Calculator;
import org.springframework.stereotype.Service;

@Service
public class MyMathCalculator /*implements Calculator*/ {
    public int add(int i,double j){
        System.out.println("方法内部执行");
        return 0;
    }

    //@Override
    public int add(int i, int j) {
        int result=i+j;
        System.out.println("方法内部执行");
        return result;
    }

   //@Override
    public int sub(int i, int j) {
        int result=i-j;
        System.out.println("方法内部执行");
        return result;
    }

    //@Override
    public int mul(int i, int j) {
        int result=i*j;
        System.out.println("方法内部执行");
        return result;
    }

    //@Override
    public int div(int i, int j) {
        int result=i/j;
        System.out.println("方法内部执行");
        return result;
    }
}
