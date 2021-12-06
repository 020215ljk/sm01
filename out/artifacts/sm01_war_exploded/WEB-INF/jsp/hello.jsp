<%--
  Created by IntelliJ IDEA.
  User: 刘静y1
  Date: 2021/11/26
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello，${sessionScope.name}!</h1>
<h1>年龄：${requestScope.age}${sessionScope.age}</h1>
<h1>生日：${sessionScope.bir}</h1>
<h1>${requestScope.msg}</h1>


<div>
    <form action="${pageContext.request.contextPath}/param1/upload" enctype="multipart/form-data" method="post">
        上传头像1：<input type="file" name="images">
        上传头像2：<input type="file" name="images">
        上传头像3：<input type="file" name="images">
                <input type="submit" value="点击上传">
    </form>
</div>
</body>
</html>
