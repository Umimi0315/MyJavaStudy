package com.atguigu.proxy;

import com.atguigu.inter.Calculator;
import com.atguigu.utils.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 帮Calculator.java生成代理对象的类
 */
public class CalculatorProxy {

    /**
     * 为传入的参数对象创建一个动态代理对象
     * @param calculator
     * @return
     *
     * Calculator calculator：被代理对象；（宝宝
     * 返回的：宋喆
     */
    public static Calculator getProxy(Calculator calculator){

        //方法执行器。帮我们目标对象执行目标方法
        InvocationHandler h=new InvocationHandler() {
            /**
             *
             * @param proxy:代理对象：给jdk使用，任何时候都不要动这个对象
             * @param method：当前将要执行的目标对象的方法
             * @param args：这个方法调用时外界传入的参数值
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //利用反射执行目标方法
                //目标方法执行后的返回值
//                System.out.println("这是动态代理将要帮你执行方法...");

                Object result = null;
                try {
                    LogUtils.logStart(method,args);
                    result = method.invoke(calculator, args);
                    LogUtils.logReturn(method, result);
                } catch (Exception e) {
                    LogUtils.logException(method,e);
                }finally {
                    LogUtils.logEnd(method);
                }

                //返回值返回出去外界才能真正拿到执行后的返回值
                return result;
            }
        };
        Class<?>[] interfaces=calculator.getClass().getInterfaces();
        ClassLoader loader=calculator.getClass().getClassLoader();

        //Proxy为目标对象创建代理对象;
        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);

        return (Calculator) proxy;
    }
}
