<%--
  Created by IntelliJ IDEA.
  User: 刘静y1
  Date: 2021/12/1
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/user/param1">
    姓名：<input type="text" name="username">
    年龄：<input type="text" name="age">
    生日：<input type="date" name="birthday">
    城市：<input type="text" name="address.city">
    街道：<input type="text" name="address.street">
    电话：<input type="text" name="address.phone">
    <input type="submit" value="提交">
</form>
</body>
</html>