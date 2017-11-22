<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="slocale" uri="http://www.springframework.org/tags" %>

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
                    success: function (data) {
                        if (data.status === 'fail') {
                            var errors = data.errorMessages;
                            $.each(errors, function (key, value) {
//                                console.log(key + "-" + value);
                                $("#" + key + "-error").text(value);
                            });
                        }
                    }
                });
            }

            dialog = $("#submit-form").dialog({
                autoOpen: false,
                modal: true,
            });
            dialog.find("form").on("submit", function (event) {
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
        <sform:form modelAttribute="user">
            <tr>
                <td><label><slocale:message code="label.lastName"/></label></td>
                <td><sform:input path="sname" id="sname"/></td>
                <td><label id="sname-error"/></td>
                    <%--<sform:errors path="sname" id="sname-error"/>--%>
            </tr>
            <tr>
                <td><label><slocale:message code="label.firstName"/></label></td>
                <td><sform:input path="name" id="name"/></td>
                <td><label id="name-error"/></td>
                    <%--<sform:errors path="name" id="name-error"/>--%>
            </tr>
            <tr>
                <td><label><slocale:message code="label.patron"/></label></td>
                <td><sform:input path="patr" id="patr"/></td>
                <td><label id="patr-error"/></td>
                    <%--<sform:errors path="patr" id="patr-error"/>--%>
            </tr>
            <tr>
                <td><label><slocale:message code="label.birthDate"/></label></td>
                <td><sform:input path="bdate" id="bdate"/></td>
                    <%--<sform:errors path="bdate" id="bdate-error"/>--%>
                <td><label id="bdate-error"/></td>
            </tr>
            <tr>
                <td>
                    <button id="create-user" type="submit"><slocale:message code="button.submit"/></button>
                </td>
            </tr>
        </sform:form>
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
