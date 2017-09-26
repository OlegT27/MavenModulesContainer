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
    <label><%=request.getAttribute("textValue")%></label>
    </p>
    <p>
        <input type="text" placeholder="Enter something right here" name="textValue">
    </p>
    <p>
        <input type="submit" title="Submit">
    </p>
</form>
</body>
</html>
