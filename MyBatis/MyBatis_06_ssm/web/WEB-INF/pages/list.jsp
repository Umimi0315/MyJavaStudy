<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/11/6
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
</head>
<body>
    <table>
        <tr>
            <th>id</th>
            <th>lastName</th>
            <th>email</th>
            <th>gender</th>
        </tr>
        <c:forEach items="${allEmps}" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
