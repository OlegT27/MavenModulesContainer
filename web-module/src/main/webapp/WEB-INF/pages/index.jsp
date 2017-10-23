<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="slocale" uri="http://www.springframework.org/tags" %>
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
    <title><slocale:message code="label.title"/></title>
</head>
<body>

<table>
    <sform:form modelAttribute="userToSubmit" method="post" action="${pageContext.request.contextPath}/add">
        <tr>
            <td><label><slocale:message code="label.lastName"/></label></td>
            <td><sform:input path="surname"/></td>
            <td><sform:errors path="surname"/></td>
        </tr>
        <tr>
            <td><label><slocale:message code="label.firstName"/></label></td>
            <td><sform:input path="name"/></td>
            <td><sform:errors path="name"/></td>
        </tr>
        <tr>
            <td><label><slocale:message code="label.patron"/></label></td>
            <td><sform:input path="patron"/></td>
            <td><sform:errors path="patron"/></td>
        </tr>
        <tr>
            <td><label><slocale:message code="label.birthDate"/></label></td>
            <td><sform:input path="birthDate" title="YYYY-MM-DD"/></td>
            <td><sform:errors path="birthDate"/></td>
        </tr>
        <tr>
            <td><sform:button><slocale:message code="button.submit"/></sform:button></td>
        </tr>
    </sform:form>
</table>

<a href="${pageContext.request.contextPath}/users">
<table>
    <caption><slocale:message code="label.listTitle"/></caption>
    <c:forEach var="user" items="${usersList}">
        <sform:form id="userForm" modelAttribute="currentUser" action="${pageContext.request.contextPath}/delete"
                    method="post">
            <tr>
                <sform:hidden path="id" value="${user.id}"/>
                <td><a href="${pageContext.request.contextPath}/update?id=${user.id}">${user}</a></td>
                <td>
                    <sform:button>
                        <slocale:message code="button.delete"/>
                    </sform:button>
                </td>
                <td>
                    <sform:button formaction="${pageContext.request.contextPath}/orders">
                        <slocale:message code="button.orders"/>
                    </sform:button>
                </td>
            </tr>
        </sform:form>
    </c:forEach>
</table>
</a>
</body>
</html>
