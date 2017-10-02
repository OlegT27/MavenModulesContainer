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
</body>
</html>
