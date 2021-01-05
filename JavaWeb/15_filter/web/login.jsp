<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/4/20
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    这是登录页面。login.jsp页面<br/>
    <form action="http://localhost:8080/15_filter/loginServlet" method="get">
        用户名：<input type="text" name="username"/><br/>
        密  码:<input type="password" name="password" />
        <input type="submit" />
    </form>
</body>
</html>
