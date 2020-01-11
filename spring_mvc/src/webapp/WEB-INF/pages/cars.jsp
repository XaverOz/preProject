<%--
  Created by IntelliJ IDEA.
  model.User: xaver
  Date: 14.12.2019
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:if test="${empty locale}">
    <c:set var="locale" value="en" scope="session" />
</c:if>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Car list</title>
</head>
<body>
<fmt:message key="label.cars"/>
<table>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>
                    ${car.model}
            </td>
            <td>
                    ${car.manufacturer}
            </td>
            <td>
                    ${car.carClass}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>