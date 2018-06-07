<%@ page import="beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->

    <link href="css/shop-homepage.css" rel="stylesheet">
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script>
        $(document).ready(function() {
            // error alert auto close.
            $("#error-alert").fadeTo(2000, 500).slideUp(500, function(){
                $("#error-alert").slideUp(500);
            });
        })
    </script>


</head>
<body>

<% if(request.getAttribute("errorMessage") != null) { %>

<div class="alert alert-danger" id="error-alert">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Error!</strong> ${errorMessage}
</div>

<% } %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Book Me</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <% if (session.getAttribute("user") != null) { %>

                <li class="nav-item active">
                    <a href="#" class="nav-link">Home</a>
                    <span class="sr-only">(current)</span>
                </li>
                <li class="nav-item">
                    <a href="profile.jsp" class="nav-link">
                        <%= ((User) session.getAttribute("user")).getfName() %>
                        <%= ((User) session.getAttribute("user")).getlName() %>
                    </a>
                </li>

                <li class="nav-item">
                    <a href="logout" class="nav-link">Logout</a>
                </li>
                <%
                    response.sendRedirect("index.jsp");
                } else { %>

                <li class="nav-item">
                    <a href="login.jsp" class="nav-link">Login</a>
                </li>
                <% } %>

            </ul>
        </div>
    </div>
</nav>

<div class="col-md-6 offset-md-3">

    <br class="mb-5">

<!-- form card register -->
<div class="card card-outline-secondary">
    <div class="card-header">
        <h3 class="mb-0">Sign Up</h3>
    </div>
    <div class="card-body">
        <form class="form" role="form" autocomplete="off" method="post" action="/register">
            <div class="form-group">
                <label for="inputEmail3">Email</label>
                <input type="email" class="form-control" id="inputEmail3" name="email" placeholder="Email" required>
            </div>

            <div class="form-group">
                <label for="inputPassword3">Password</label>
                <input type="password" class="form-control" id="inputPassword3" name="pass" placeholder="Password" required>
            </div>

            <div class="form-group">
                <label for="inputName1">First Name</label>
                <input type="text" class="form-control" id="inputName1" name="FName" placeholder="First name" required>
            </div>

            <div class="form-group">
                <label for="inputName2">Last Name</label>
                <input type="text" class="form-control" id="inputName2" name="LName" placeholder="Last name" required>
            </div>

            <div class="form-group">
                <label for="inputName3">Phone Number</label>
                <input type="text" class="form-control" id="inputName3" name="PhoneNumber"
                       placeholder="(+20) 112 345 6789" required>
            </div>

            <div class="form-group">
                <label for="inputName4">Shipping Address</label>
                <input type="text" class="form-control" id="inputName4" name="ShippingAddress"
                       placeholder="10 Mohammed Salah St." required>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-success btn-lg float-right">Register</button>
            </div>

        </form>

    </div>
</div>
<!-- /form card register -->
</div>


</body>
</html>
