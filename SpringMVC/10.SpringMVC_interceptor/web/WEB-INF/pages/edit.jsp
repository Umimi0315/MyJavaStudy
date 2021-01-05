<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/10/19
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>员工修改</title>
</head>
<body>
<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>
<form:form action="${ctp}/emp/${employee.id}" modelAttribute="employee" method="post">
    <input name="_method" value="put" type="hidden">
    <input name="id" value="${employee.id}" type="hidden">
    email:<form:input path="email"/><br/>
    gender:&nbsp;&nbsp;&nbsp;
        男：<form:radiobutton path="gender" value="1"/>&nbsp;&nbsp;&nbsp;
        女：<form:radiobutton path="gender" value="0"/><br/>
    dept:
        <form:select path="department.id" items="${depts}" itemLabel="departmentName" itemValue="id"></form:select>
    <input type="submit" value="修改">

</form:form>

</body>
</html>
