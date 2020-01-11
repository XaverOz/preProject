<%--
  Created by IntelliJ IDEA.
  model.User: xaver
  Date: 14.12.2019
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>User list</title>
  </head>
  <body>
    <table>
      <tr>
        <td>
          Name
        </td>
        <td>
          Age
        </td>
      </tr>
      <c:forEach items="${users}" var="user">
        <tr>
          <td>
              ${user.name}
          </td>
          <td>
              ${user.age}
          </td>
          <td>
            <a href="./edit?id=${user.id}">Edit</a>
          </td>
          <td>
            <a href="./delete?id=${user.id}">Delete</a>
          </td>
        </tr>
      </c:forEach>
    </table>
    <a href="./add">Add</a>
  </body>
</html>
