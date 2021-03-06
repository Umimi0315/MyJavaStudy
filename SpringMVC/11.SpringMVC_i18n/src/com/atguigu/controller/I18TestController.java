package com.atguigu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class I18TestController {
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/tologinpage")
    public String tologinPage(@RequestParam(value = "locale",defaultValue = "zh_CN") String localeStr, Locale locale, HttpSession session){
        System.out.println(locale);
        String welcomeInfo = messageSource.getMessage("welcomeInfo", null, locale);
        System.out.println(welcomeInfo);

  /*      Locale l=null;
        //如果带了locale参数就用参数指定的区域信息，如果没带就用请求头的
        if (localeStr!=null&&!"".equals(localeStr)){
            l=new Locale(localeStr.split("_")[0],localeStr.split("_")[1]);
        }else {
            l=locale;
        }

        session.setAttribute(SessionLocaleResolver.class.getName()+".LOCALE", l);
*/
        return "login";
    }
}
