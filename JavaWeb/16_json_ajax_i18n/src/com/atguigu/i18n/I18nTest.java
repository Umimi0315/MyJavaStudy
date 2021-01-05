package com.atguigu.i18n;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nTest {
    @Test
    public void testLocale(){
        //获取系统默认的语言国家信息
//        Locale locale=Locale.getDefault();
//        System.out.println(locale);

//        for (Locale availableLocale:Locale.getAvailableLocales()) {
//            System.out.println(availableLocale);
//        }
        //获取中文，中国的常量的Locale对象
        System.out.println(Locale.CHINA);
        //获取英文，美国的常量的Locale对象
        System.out.println(Locale.US);


    }
    @Test
    public void testI18n(){

        //得到我们需要的Locale对象
        Locale locale = Locale.CHINA;
        //通过指定的basename和locale对象，读取相应的配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);

        System.out.println("username:"+bundle.getString("username"));
        System.out.println("password:"+bundle.getString("password"));
        System.out.println("age:"+bundle.getString("age"));
        System.out.println("sex:"+bundle.getString("sex"));

    }

}
