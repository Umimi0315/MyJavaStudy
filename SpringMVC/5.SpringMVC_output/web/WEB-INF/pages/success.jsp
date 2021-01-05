<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/10/15
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello</h1>
pageContext:${pageScope.msg}<br/>
request:${requestScope.msg}<br/>
session:${sessionScope.msg}---${sessionScope.haha}<br/>
application:${applicationScope.msg}<br/>
</body>
</html>
