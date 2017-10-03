<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<h2>Hello World!</h2>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    Calendar calendar = Calendar.getInstance();

%>
<p>Date <%=sdf.format(calendar.getTime())%></p>
<p><a href="web-module/users">Check out my UberMegaServlet!</a></p>
</body>
</html>
