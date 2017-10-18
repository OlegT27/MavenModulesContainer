<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="slocale" uri="http://www.springframework.org/tags" %>
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
    <title><slocale:message code="label.ordersPage"/></title>
</head>
<body>
<h1><slocale:message/></h1>


<table>
    <sform:form modelAttribute="orderToAdd" method="post" action="${pageContext.request.contextPath}/add_order">
        <tr>
            <td><label><slocale:message/></label></td>
            <td><sform:input path="name"/></td>
        </tr>
        <tr>
            <td><label><slocale:message/></label></td>
            <td><sform:input path="createDate"/></td>
        </tr>
        <tr>
            <td>
                <sform:button>
                    <slocale:message code="button.submit"/>
                </sform:button>
            <td>
        </tr>
    </sform:form>
    <td>
        <a href="${pageContext.request.contextPath}/back">
            <button><slocale:message code="button.back"/></button>
        </a>
    </td>
</table>


<table>
    <caption><slocale:message code="label.ordersList"/></caption>
    <c:forEach var="order" items="${ordersList}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.name}</td>
            <td>${order.createDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
