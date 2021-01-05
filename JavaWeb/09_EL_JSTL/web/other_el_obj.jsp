<%--
  Created by IntelliJ IDEA.
  User: niexiaohan
  Date: 2020/4/12
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    输出请求参数username的值：${ param.username }<br/>
    输出请求参数password的值：${ param.password }<br/>
    ${param.hobby}<br/>
    输出请求参数username的值：${paramValues.username[0]}<br/>
    输出请求参数username的值：${paramValues.hobby[0]}<br/>
    输出请求参数username的值：${paramValues.hobby[1]}<br/>
    <hr/>
    输出请求头【User-Agent】的值：${header["User-Agent"]}<br/>
    输出请求头【User-Agent】的值：${header.Connection}<br/>
    输出请求头【User-Agent】的值：${headerValues["User-Agent"][0]}<br/>
    <hr/>
    获取Cookie的名称：${ cookie.JSESSIONID.name }<br/>
    获取Cookie的值：${ cookie.JSESSIONID.value }<br/>
    <hr/>
    输出&lt;Context-param&gt;的username的值：${initParam.username}<br/>
    输出&lt;Context-param&gt;的url的值：${initParam.url}<br/>

</body>
</html>
