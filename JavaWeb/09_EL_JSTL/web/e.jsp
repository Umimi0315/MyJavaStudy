<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/4/12
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
//    1、值为 null 值的时候，为空
    request.setAttribute("emptyNull",null);
//    2、值为空串的时候，为空
    request.setAttribute("emptyStr","");
//    3、值是 Object 类型数组，长度为零的时候
    request.setAttribute("emptyArr",new Object[]{});
//    4、list 集合，元素个数为零
    List<String> list=new ArrayList<>();
    request.setAttribute("emptyList",list);
//    5、map 集合，元素个数为零
    Map<String ,Object> map=new HashMap<String ,Object>();
    request.setAttribute("emptyMap",map);
%>
    ${empty emptyNull}<br/>
    ${empty emptyStr}<br/>
    ${empty emptyArr}<br/>
    ${empty emptyList}<br/>
    ${empty emptyMap}<br/>

    <hr>
${12==12?"国哥帅呆了":"国哥又骗人了"}

</body>
</html>
