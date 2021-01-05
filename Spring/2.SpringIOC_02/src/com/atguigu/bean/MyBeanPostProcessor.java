package com.atguigu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 1）、编写后置处理器的实现类
 * 2)、将后置处理器注册在配置文件中
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * postProcessBeforeInitialization
     *      初始化前调用
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization...["+s+"]bean将要调用初始化方法了，这个bean是这样["+o+"]");

        //返回传入的bean
        return o;
    }

    /**
     * postProcessAfterInitialization
     *      初始化方法之后调用
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization...["+s+"]bean初始化方法调用完了....AfterInitialization");
        //初始化之后返回的bean，返回的是什么，容器中保存的就是什么
        return o;
    }
}
