<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
  Created by IntelliJ IDEA.
  User: olego
  Date: 26.09.2017
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="users/add" method="post" name="addUser">
    <p>
        <label for="name"> Name</label>
        <input type="text" placeholder="Name" id="name" name="user_name" required>
    </p>
    <p>
        <label for="sname">Surname</label>
        <input type="text" placeholder="Surname" id="sname" name="user_sname" required>
    </p>
    <p>
        <label for="pname">Patronymic</label>
        <input type="text" placeholder="Patronymic"id="pname" name="user_patr" required>
    </p>
    <p>
        <label for="bdate">Date of birth</label>
        <input type="text" placeholder="yyyy-mm-dd" id="bdate"name="user_date" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" >
    </p>
    <p>
        <input type="submit" title="Submit" value="Submit">
    </p>
</form>


<form action="/users" method="get" id="listUser">
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
                <form action="users/delete" method="post" id="delUser">

                    <td><c:out value="${user.getName()}"/></td>
                    <td><c:out value="${user.getSurname()}"/></td>
                    <td><c:out value="${user.getPatron()}"/></td>
                    <td><c:out value="${user.getBirthDate()}"/></td>
                    <td><input type="submit" value="delete" form="delUser"></td>
                    <td><input type=hidden value="${user.getId()}" name="idToDelete" readonly ></td>
                </form>
            </tr>
        </c:forEach>

    </table>
</form>


</body>
</html>
