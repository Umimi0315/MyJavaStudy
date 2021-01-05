package com.atguigu.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class ValidateAspect {

    public static void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("[VaAspect-前置]【"+name+"】方法开始执行，用的参数列表是【"+ Arrays.asList(args)+"】");
    }

    public static void logReturn(JoinPoint joinPoint,Object result){
        System.out.println("[VaAspect-返回]【"+joinPoint.getSignature().getName()+"】方法正常执行完成，执行结果是【"+result+"】");
    }

    public static void logException(JoinPoint joinPoint,Exception exception){

        System.out.println("[VaAspect-异常]【"+joinPoint.getSignature().getName()+"】方法执行出现异常了，异常信息是:【"+exception+"】这个异常已经通知测试小组进行排查");

    }

    public static void logEnd(JoinPoint joinPoint){
        System.out.println("[VaAspect-后置]【"+joinPoint.getSignature().getName()+"】方法最终结束了");
    }
}
