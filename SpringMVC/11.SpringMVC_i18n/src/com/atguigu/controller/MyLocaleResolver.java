package com.atguigu.controller;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    /**
     * 解析返回locale
     * @param httpServletRequest
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {

        Locale locale=null;
        String localeStr = httpServletRequest.getParameter("locale");
        //如果带了locale参数就用参数指定的区域信息，如果没带就用请求头的
        if (localeStr!=null&&!"".equals(localeStr)){
            locale=new Locale(localeStr.split("_")[0],localeStr.split("_")[1]);
        }else {
            locale=httpServletRequest.getLocale();
        }

        return locale;
    }

    /**
     * 修改locale
     * @param httpServletRequest
     * @param httpServletResponse
     * @param locale
     */
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        throw new UnsupportedOperationException(
                "Cannot change HTTP accept header - use a different locale resolution strategy");
    }
}
