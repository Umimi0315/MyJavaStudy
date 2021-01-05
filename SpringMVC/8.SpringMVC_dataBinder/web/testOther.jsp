<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/10/30
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        pageContext.setAttribute("ctp", request.getContextPath());
    %>
</head>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<body>
<form action="${ctp}/test02" method="post">
    <input name="username" value="tomcat"/>
    <input name="password" value="123456"/>
    <input type="submit"/>
</form>
<a href="${ctp}/testRequestBody">ajax发送json数据</a>
</body>
<script type="text/javascript">
    $("a:first").click(function () {
        //点击发送ajax请求，请求带的数据是json
        var emp={lastName:"张三",email:"aaa@aa.com",gender:0};
        //json对象
        var empStr=JSON.stringify(emp);
        $.ajax({
            url:'${ctp}/testRequestBody',
            type:"POST",
            data:empStr,
            contentType:"application/json",
            success:function (data) {
                alert(data);
            }
        });
        return false;
    });
</script>
</html>
