<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/5/3
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    System.out.println("target.jsp页面执行了");
    System.out.println("target.jsp线程:"+Thread.currentThread().getName());
    System.out.println("target.jsp "+request.getParameter("username"));
%>

</body>
</html>
