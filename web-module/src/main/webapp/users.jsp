<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: olego
  Date: 26.09.2017
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="add" method="post" name="addUser">
    <table>
        <tr>
            <c:if test="${dataNotValid == true}">
                <font color="red"><c:out value="Fields are empty or invalid data format submitted"/></font>
            </c:if>
        </tr>
        <tr>
            <td><label for="name"> Name</label></td>
            <td><input type="text" placeholder="Name" id="name" name="user_name"></td>
        </tr>
        <tr>
            <td><label for="sname">Surname</label></td>
            <td><input type="text" placeholder="Surname" id="sname" name="user_sname"></td>

        </tr>
        <tr>
            <td><label for="pname">Patronymic</label></td>
            <td><input type="text" placeholder="Patronymic" id="pname" name="user_patr"></td>
        </tr>
        <tr>
            <td><label for="bdate">Date of birth</label></td>
            <td><input type="text" placeholder="yyyy-mm-dd" id="bdate" name="user_date"
                       pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"></td>
        </tr>
        <tr>
            <td><input type="submit" title="Submit" value="Submit">
            </td>
        </tr>
    </table>
</form>

<form action="/" method="get" id="listUser">
    <table>
        <caption>Users List</caption>
        <tr>
            <th>user_name</th>
            <th>user_surname</th>
            <th>user_patr</th>
            <th>user_bdate</th>
        </tr>

        <c:forEach var="user" items="${userList}">
            <tr>

                <td><c:out value="${user.getName()}"/></td>
                <td><c:out value="${user.getSurname()}"/></td>
                <td><c:out value="${user.getPatron()}"/></td>
                <td><c:out value="${user.getBirthDate()}"/></td>
                <td><input type="submit" value="delete" formaction="delete" formmethod="post"></td>
                <td><input type=hidden value="${user.getId()}" name="userId" readonly></td>
            </tr>
        </c:forEach>

    </table>
</form>
</body>
</html>
