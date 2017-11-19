<%--suppress ALL --%>
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
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title><slocale:message code="label.title"/></title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            var dialog, form;

            function addUser() {
                var adduser = {
                    name: $("#name").val(),
                    sname: $("#sname").val(),
                    patr: $("#patr").val(),
                    bdate: $("#bdate").val(),
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/add",
                    data: JSON.stringify(adduser),
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                });
            }

            dialog = $("#submit-form").dialog({
                autoOpen: false,
                modal: true,
                close: function () {
                    alert("close");
                }
            });
            form = dialog.find("form").on("submit", function (event) {
                event.preventDefault();
                addUser();
            });

            $("#get-form").button().on("click", function () {
                dialog.dialog("open");
            });
        });
    </script>
</head>
<body>
<div id="submit-form">
    <table>
        <form method="post">
            <tr>
                <td><label><slocale:message code="label.lastName"/></label></td>
                <td><input type="text" id="sname"/></td>
                <sform:errors path="sname"/>
            </tr>
            <tr>
                <td><label><slocale:message code="label.firstName"/></label></td>
                <td><input type="text" id="name"/></td>
                <sform:errors path="name"/>
            </tr>
            <tr>
                <td><label><slocale:message code="label.patron"/></label></td>
                <td><input type="text" id="patr"/></td>
                <sform:errors path="patr"/>
            </tr>
            <tr>
                <td><label><slocale:message code="label.birthDate"/></label></td>
                <td><input type="text" id="bdate"/></td>
                <sform:errors path="bdate"/>
            </tr>
            <tr>
                <td>
                    <button id="create-user" type="submit"><slocale:message code="button.submit"/></button>
                </td>
            </tr>
        </form>
    </table>
</div>

<button id="get-form"><slocale:message code="button.userform"/></button>

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
