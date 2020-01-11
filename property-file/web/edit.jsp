<%--
  Created by IntelliJ IDEA.
  model.User: xaver
  Date: 14.12.2019
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>

<form method="post" action="./edit">
    <input type="hidden" name="id" value="${user.id}"><br>
    <input type="text" name="name" value="${user.name}"><br>
    <input type="number" name="age" value="${user.age}" min="10" max="100"><br>
    <input type="submit" value="Save"><br>
</form>
</body>
</html>
