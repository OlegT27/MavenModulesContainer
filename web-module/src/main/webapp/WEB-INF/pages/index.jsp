<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
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
</head>
<body>
<table>
    <s:form modelAttribute="userToAdd" method="post" action="/webapp/add">
        <tr>
            <td><s:label path="surname">SURNAME</s:label></td>
            <td><s:input path="surname"/></td>
        </tr>
        <tr>
            <td><s:label path="name">NAME</s:label></td>
            <td><s:input path="name"/></td>
        </tr>
        <tr>
            <td><s:label path="patron">PATRONYMIC</s:label></td>
            <td><s:input path="patron"/></td>
        </tr>
        <tr>
            <td><s:label path="birthDate">BIRTH DATE</s:label></td>
            <td><s:input path="birthDate" title="YYYY-MM-DD"/></td>
        </tr>
        <tr>
            <td><s:button>Push User</s:button></td>
        </tr>
    </s:form>
</table>
<table>
    <caption>Users List</caption>
    <c:forEach var="user" items="${usersList}">
        <s:form modelAttribute="userToDel" action="/webapp/delete" method="post">

            <tr>
                <s:hidden path="id" value="${user.id}"/>

                <td><a href="${pageContext.request.contextPath}/update?id=${user.id}">${user}</a></td>
                <td><s:button>Delete</s:button></td>
                <td></td>
            </tr>

        </s:form>
    </c:forEach>

</table>
</body>
</html>
