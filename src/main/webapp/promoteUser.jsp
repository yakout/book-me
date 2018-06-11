
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.User" %>
<html>

<head>
    <title>User Promotion</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="vendor/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet"/>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">

    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script>
        $(document).ready(function() {
            // TODO validation for update profile form

            // error alert auto close
            $("#error-alert").fadeTo(2000, 500).slideUp(500, function(){
                $("#error-alert").slideUp(500);
            });

            // success alert auto close
            $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
                $("#success-alert").slideUp(500);
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

<% if(request.getAttribute("successMessage") != null) { %>
<div class="alert alert-success" id="success-alert">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Success!</strong> ${successMessage}
</div>
<% } %>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Book Me</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <% if (session.getAttribute("user") != null) { %>

                <li class="nav-item">
                    <a href="index.jsp" class="nav-link">Home</a>
                    <span class="sr-only">(current)</span>
                </li>

                <li class="nav-item">
                    <a href="profile.jsp" class="nav-link">
                        <%= ((User) session.getAttribute("user")).getfName() %>
                        <%= ((User) session.getAttribute("user")).getlName() %>
                    </a>
                </li>

                <% if (session.getAttribute("user") != null && ((User)session.getAttribute("user")).isManager()) {%>
                <li class="nav-item">
                    <a href="statistics.jsp" class="nav-link"><i class="fa fa-bar-chart"></i> Statistics</a>
                </li>

                <li class="nav-item">
                    <a href="orders.jsp" class="nav-link"><i class="fas fa-book"></i> Orders</a>
                </li>
                <% } else {%>

                <li class="nav-item">
                    <a class="nav-link cart-item-count" href="cart.jsp" data-cesta-feira-items-count>
                        <span class="fa fa-shopping-cart"></span> Shopping Cart</a>
                </li>

                <% } %>

                <li class="nav-item">
                    <a href="logout" class="nav-link"><i class="fa fa-fw fa-sign-out"></i>Logout</a>
                </li>

                <% } else {	%>

                <li class="nav-item">
                    <a href="login.jsp" class="nav-link">Login</a>
                </li>

                <li class="nav-item">
                    <a href="register.jsp" class="nav-link">Register</a>
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
            <h3 class="mb-0">Promote User</h3>
        </div>
        <div class="card-body">
            <form class="form" role="form" autocomplete="off" method="post" action="/profile">

                <input type="hidden" value="promote" name="action">

                <div class="form-group">
                    <label for="email">User Email</label>
                    <input type="text" class="form-control"
                           value="" id="email" name="email" placeholder="email" required>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-lg float-right">Promote</button>
                </div>

            </form>

        </div>
    </div>
    <!-- /form card register -->
    <br>
</div>

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; BookMe.com 2018</p>
    </div>
    <!-- /.container -->
</footer>

</body>
</html>
