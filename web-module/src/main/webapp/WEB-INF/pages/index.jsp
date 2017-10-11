<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olego
  Date: 11.10.2017
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <form method="post">
        <input type="submit">
    </form>

    <c:forEach var="user" items="${usersList}">
        <tr><c:out value="${user.name}"/></tr>
    </c:forEach>

</head>
<body>

</body>
</html>
