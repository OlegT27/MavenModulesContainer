<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="slocale" uri="http://www.springframework.org/tags" %>
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
    <title><slocale:message code="label.updateUser"/></title>
</head>
<body>
<h1><slocale:message code="label.updateUser"/> ${userToUpdate}</h1>


<table>
    <sform:form modelAttribute="userToUpdate" method="post" action="${pageContext.request.contextPath}/update_user">
        <tr>
            <td><label><slocale:message code="label.lastName"/></label></td>
            <td><sform:input path="surname"/></td>
        </tr>
        <tr>
            <td><label><slocale:message code="label.firstName"/></label></td>
            <td><sform:input path="name"/></td>
        </tr>
        <tr>
            <td><label><slocale:message code="label.patron"/></label></td>
            <td><sform:input path="patron"/></td>
        </tr>
        <tr>
            <td><label><slocale:message code="label.birthDate"/></label></td>
            <td><sform:input path="birthDate" title="YYYY-MM-DD"/></td>
        </tr>
        <tr>
            <td><sform:button><slocale:message code="button.submit"/></sform:button></td>
        </tr>
        <tr>
            <td><sform:hidden path="id" value="${param.id}"/></td>
        </tr>
    </sform:form>


    <td>
        <a href="/back">
            <button><slocale:message code="button.back"/></button>
        </a>
    </td>
</table>
</body>
</html>
