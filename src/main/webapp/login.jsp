<%@ page import="beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
            // validation example for Login form
            $("#btnLogin").click(function (event) {

                var form = $("#loginForm");

                if (form[0].checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                // if validation passed form
                // would post to the server here

                form.addClass('was-validated');
            });

            // error alert auto close
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

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Book Me</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a href="register.jsp" class="nav-link">Register</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="col-md-6 offset-md-3">

    <br class="mb-5">

    <!-- form card register -->
    <div class="card card-outline-secondary">
        <div class="card-header">
            <h3 class="mb-0">Log in</h3>
        </div>
        <div class="card-body">
            <form class="form" role="form" autocomplete="off" id="loginForm" novalidate="" method="post" action="/login">

                <div class="form-group">
                    <label for="inputEmail3">Email</label>
                    <input type="email" class="form-control" id="inputEmail3" name="email" placeholder="Email" required>
                    <div class="invalid-feedback">Please enter your email</div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3">Password</label>
                    <input type="password" class="form-control" id="inputPassword3" name="pass" placeholder="Password" required>
                    <div class="invalid-feedback">Please enter a password</div>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnLogin">Log in</button>
                </div>

            </form>

        </div>
    </div>
    <!-- /form card register -->
</div>

<!--

<form action="/login" method="post">
    Enter User Email <input type="text" name="email"><br>
    Enter Password <input type="password" name="pass"><br>
    <input type="submit" value="login">
</form>

-->

</body>
</html>
