<%--
  Created by IntelliJ IDEA.
  User: ahmedyakout
  Date: 5/2/18
  Time: 2:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/Login" method="post">
    Enter User Email <input type="text" name="email"><br>
    Enter Password <input type="password" name="pass"><br>
    <input type="submit" value="login">
</form>

<% if(request.getAttribute("errorMessage") != null) { %>
<p>${errorMessage}</p>
<% } %>

</form>
</body>
</html>