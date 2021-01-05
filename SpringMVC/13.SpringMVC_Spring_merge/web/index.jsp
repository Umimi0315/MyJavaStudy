<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/10/31
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>
<body>

<a href="${ctp}/handle01?i=10">test-哈哈</a><br/>
<a href="${ctp}/handle02?username=admin">handle02</a><br/>
<a href="${ctp}/handle03">handle03</a><br/>
<a href="${ctp}/handle04">handle04</a><br/>
</body>
</html>
