<%--
  Created by IntelliJ IDEA.
  User: ahmedyakout
  Date: 6/7/18
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="/login" method="post">
    Enter User Email <input type="text" name="email"><br>
    Enter Password <input type="password" name="pass"><br>
    <input type="submit" value="login">
</form>

<% if(request.getAttribute("errorMessage") != null) { %>
<p>${errorMessage}</p>
<% } %>

</body>
</html>
