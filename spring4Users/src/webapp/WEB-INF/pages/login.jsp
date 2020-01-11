<%--
  Created by IntelliJ IDEA.
  User: xaver
  Date: 26.12.2019
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form method="post" action="/login">
    <div>Name</div>
    <input name="name"/><br>
    <div>Password</div>
    <input name="password"/><br>
    <input type="submit" value="Login"/>
</form>

</body>
</html>
