package com.atguigu.view;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class MyMeiNvViewResolver implements ViewResolver, Ordered {

    private Integer order=0;

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        //根据视图名返回视图对象
        /**
         * meinv:/gaoqing    meinv:/dama
         * forward:/login.jsp
         */
        if (s.startsWith("meinv:")){
            return new MyView();
        }else{
            //如果不能处理，返回null即可
            return null;
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
    //改变视图解析器的优先级
    public void setOrder(Integer order){
        this.order=order;
    }
}
