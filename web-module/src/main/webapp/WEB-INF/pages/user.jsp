<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
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
<h1>Update user</h1>
<table>
    <s:form modelAttribute="userToUpdate" method="post" action="${pageContext.request.contextPath}/update_user">
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
        <tr>
            <td><s:hidden path="id" value="${param.id}"/></td>
        </tr>
    </s:form>
    <td>
        <a href="/back">
            <button>Back</button>
        </a>
    </td>
</table>
</body>
</html>
