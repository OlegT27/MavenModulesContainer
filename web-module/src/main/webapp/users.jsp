<%@ page import="java.util.List" %>
<%@ page import="com.company.db.DatabaseObject" %><%--
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
<form action="/users" method="post"><p>
    <p>
        <input type="text" placeholder="Name" name="user_name">
    </p>
    <p>
        <input type="text" placeholder="Surname" name="user_sname">
    </p>
    <p>
        <input type="text" placeholder="Patronymic" name="user_patr">
    </p>
    <p>
        <input type="date" placeholder="Date of birth" name="user_patr">
    </p>
    <p>
        <input type="submit" title="Submit">
    </p>
</form>
<table>
    <caption>Users List</caption>
    <tr>
        <th>user_id</th>
        <th>user_name</th>
        <th>user_surname</th>
        <th>user_patr</th>
        <th>user_bdate</th>
    </tr>
    <%
        List<DatabaseObject> viewData = (List) request.getAttribute("viewData");
        for (DatabaseObject obj : viewData) {%>
    <tr>
        <td><%=obj.getId()%></td>
        <td><%=obj.getName()%></td>
        <td><%=obj.getSurname()%></td>
        <td><%=obj.getPatron()%></td>
        <td><%=obj.getBirthDate()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
