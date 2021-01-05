package com.atguigu.view;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 自定义视图
 */
public class MyView implements View {
    /**
     * 返回的数据的内容类型
     * @return
     */
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("之前保存的数据："+map);
        httpServletResponse.setContentType("text/html");
        List<String > vn = (List<String>) map.get("video");
        httpServletResponse.getWriter().write("哈哈<h1>即将展现精彩内容</h1>");
        for (String s : vn) {
            httpServletResponse.getWriter().write("<a>下载"+s+".avi</a><br/>");
        }
        httpServletResponse.getWriter().write(""
        +"<script>"
                +"bar aEle=document.getElementsByTagName('a');"
                +"aEle.onclick=function(){"
                +"alert('想下载吗？交学费')"
                +"}"
                +"</script>"
        );
    }
}
