<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/10/14
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="hello">hello</a><br/>
  <a href="handle01?username=tomcat">handle01</a><br/>
  <form action="book" method="post">
    书名：<input type="text" name="bookName"/><br/>
    作者：<input type="text" name="author" /><br/>
    价格：<input type="text" name="price" /><br/>
    库存：<input type="text" name="stock" /><br/>
    销量：<input type="text" name="sales" /><br/>
    <hr/>
    省：<input type="text" name="address.province">
    市：<input type="text" name="address.city">
    街道：<input type="text" name="address.street"><br/>
    <input type="submit">
  </form>
  </body>
</html>
