<%--
  Created by IntelliJ IDEA.
  User: ahmedyakout
  Date: 5/5/18
  Time: 4:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="register" method="post">
    Email <input type="text" name="email"><br>
    First Name <input type="text" name="FName"><br>
    Last Name <input type="text" name="LName"><br>
    Phone Number<input type="text" name="PhoneNumber"><br>
    Shipping Address <input type="text" name="ShippingAddress"><br>
    Password <input type="password" name="pass"><br>
    <input type="submit" value="login">
</form>

<% if(request.getAttribute("errorMessage") != null) { %>
<p>${errorMessage}</p>
<% } %>

</body>
</html>
