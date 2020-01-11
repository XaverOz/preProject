<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>

<form method="post" action="./add">
    <div>Name</div>
    <input type="text" name="name" value="usrtst"><br>
    <div>Age</div>
    <input type="number" name="age" value="10" min="10" max="100"><br>
    <input type="submit" value="Add"><br>
</form>
</body>
</html>
