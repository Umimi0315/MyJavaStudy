<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/11/1
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    <%--国际化的区域信息是决定国际化显示的要素--%>
    <fmt:message key="welcomeInfo"/>
</h1>
<form action="">
    <fmt:message key="uername"/>：<input type="text" name="username"/><br/>
    <fmt:message key="password"/>：<input type="text" name="password"/><br/>
    <input type="submit" value="<fmt:message key="loginBtn"/>"/><br/>
</form>
<%--如果点击超链接切换国际化--%>
<a href="tologinpage?locale=zh_CN">中文</a>|<a href="tologinpage?locale=en_US">English</a>
</body>
</html>
