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

<a href="${ctp}/test01">test01</a>

</body>
</html>
