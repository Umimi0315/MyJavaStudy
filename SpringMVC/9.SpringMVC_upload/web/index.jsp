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
<%--
1）、文件上传：
    1、文件上传表单准备：enctype="multipart/form-data"
    2、导入fileupload；
        commons-io-2.0.jar
        commons-fileupload-1.2.1.jar
    3、javaWeb:
        object=new FileItemDiskFactory();
        ServletFileUpload upload=new ServletFileUpload(object);

        List<FileItem> items=upload.parseRequest(upload);
        for(FileItem item:items){
            if(item.isField()){
                //普通项
            else{
                //文件项
                IOUtils.copy();//文件保存
            }
        }
    4、只要在SpringMVC配置文件中，编写一个配置，配置文件上传解析器(MultipartResolver)
    5、处理文件上传请求
        在处理器方法上写一个
            @RequestParam("headerimg")MultipartFile file,封装当前文件的信息,可以直接保存
--%>
${msg}
<form action="${ctp}/upload" method="post" enctype="multipart/form-data">
    用户头像：<input type="file" name="headerimg" /><br/>
    用户头像：<input type="file" name="headerimg" /><br/>
    用户头像：<input type="file" name="headerimg" /><br/>
    用户头像：<input type="file" name="headerimg" /><br/>
    用户头像：<input type="file" name="headerimg" /><br/>
    用户名：<input type="text" name="username" /><br/>
    <input type="submit" />
</form>


</body>
</html>
