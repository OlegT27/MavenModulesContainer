<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<h2>Hello World!</h2>
Time <%
    SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy HH:mm:ss");
    Calendar calendar = Calendar.getInstance();
    out.println(sdf.format(calendar.getTime()));
%>
</body>
</html>
