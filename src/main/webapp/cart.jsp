<%@ page import="beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="vendor/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet"/>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">
</head>
<body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
            <a class="navbar-brand" href="#">Book Me</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <% if (session.getAttribute("user") != null) { %>

                    <li class="nav-item">
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

                    <li class="nav-item" active>
                        <a class="nav-link cart-item-count" href="cart.jsp"
                           data-cesta-feira-items-count>
                            <span class="fa fa-shopping-cart"></span> Shopping Cart</a>
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

    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-sm-12 col-md-10 col-md-offset-1">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Total</th>
                        <th> </th>
                    </tr>
                    </thead>
                    <tbody id="cart-items">
                    </tbody>
                    <tfoot>
                    <tr>
                        <td><a href="javascript:;" class="btn btn-danger" data-cesta-feira-clear-basket>Clear Cart</a></td>
                        <td>  </td>
                        <td>Total</td>
                        <td class="text-right" id="total-value"><strong>$0</strong></td>
                        <td>  </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
    <!-- /.container -->

    
    <!-- Footer -->
    <footer class="py-5 bg-dark">
        <div class="container">
            <p class="m-0 text-center text-white">Copyright &copy; BookMe.com 2017</p>
        </div>
        <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
