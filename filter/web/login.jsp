<%--
  Created by IntelliJ IDEA.
  User: xaver
  Date: 22.12.2019
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login user</title>
</head>
<body>
<div style="color: red">
    <%= request.getParameter("message") == null ? "" : request.getParameter("message")  %>
</div>
<form method="post" action="./login">
    <div>Name</div>
    <input type="text" name="name" value=""><br>
    <div>Password</div>
    <input type="text" name="password" value=""><br>
    <input type="submit" value="Login"><br>
</form>
</body>
</html>
