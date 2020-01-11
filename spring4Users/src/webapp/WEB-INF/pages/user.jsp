<%--
  Created by IntelliJ IDEA.
  User: xaver
  Date: 27.12.2019
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User info</title>
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
        <tr>
            <td>
                    ${user.name}
            </td>
            <td>
                    ${user.age}
            </td>

        </tr>
</table>
<jsp:include page="logout.jsp" />
</body>
</html>
