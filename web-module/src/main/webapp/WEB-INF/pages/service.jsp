<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olego
  Date: 14.10.2017
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>IN Progress</h1>
<h2>${param.id}</h2>
<h3>${userToUpdate}</h3>
<c:forEach var="order" items="${ordersList}">
    <h4>${order}</h4>
</c:forEach>

</body>
</html>
