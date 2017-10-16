<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: olego
  Date: 16.10.2017
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add order</h1>
<table>
    <s:form modelAttribute="orderToAdd" method="post" action="${pageContext.request.contextPath}/add_order">
        <tr>
            <td><s:label path="name">NAME</s:label></td>
            <td><s:input path="name"/></td>
        </tr>
        <tr>
            <td><s:label path="createDate">DATE</s:label></td>
            <td><s:input path="createDate"/></td>
        </tr>
        <> <s:hidden path="userId"/>
        <tr>
            <s:button>PUSH</s:button>
        </tr>
    </s:form>
    <td>
        <a href="${pageContext.request.contextPath}/back">
            <button>BACK</button>
        </a>
    </td>
</table>
<table>
    Orders
    <c:forEach var="order" items="${ordersList}">
        <p>${order}</p>
    </c:forEach>
</table>
</body>
</html>
