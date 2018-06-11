<%@ page import="beans.User" %>
<%@ page import="beans.Sale" %>
<%@ page import="beans.Cart" %>
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
                        <a href="index.jsp" class="nav-link"><i class="fa fa-home"></i> Home</a>
                        <span class="sr-only">(current)</span>
                    </li>

                    <li class="nav-item">
                        <a href="profile.jsp" class="nav-link">
                            <i class="fa fa-user"></i>
                            <%= ((User) session.getAttribute("user")).getfName() %>
                            <%= ((User) session.getAttribute("user")).getlName() %>
                        </a>
                    </li>

                    <% if (session.getAttribute("user") != null && ((User)session.getAttribute("user")).isManager()) {%>
                    <li class="nav-item">
                        <a href="statistics.jsp" class="nav-link"><i class="fa fa-bar-chart"></i> Statistics</a>
                    </li>

                    <li class="nav-item">
                        <a href="orders.jsp" class="nav-link"><i class="fa fa-book"></i> Orders</a>
                    </li>
                    <% } %>

                    <li class="nav-item active">
                        <a class="nav-link cart-item-count" href="cart.jsp">
                            <span class="fa fa-shopping-cart"></span> Shopping Cart</a>
                    </li>

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
                    <% for (Sale sale : ((Cart) session.getAttribute("cart")).getCart()) {%>
                    <tr>
                        <td><%=sale.getSale_name()%></td>
                        <td><%=sale.getCopies()%></td>
                        <td><%=sale.getPrice()%></td>
                        <td><%=sale.getPrice() * sale.getCopies()%></td>
                        <td>
                            <form action="removeFromCart.jsp" class="form">
                                <input type="hidden" value="<%=sale.getISBN()%>" name="ISBN">
                                <input type="submit" class="btn btn-danger" value="Remove Item"/>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td><a href="clearCart.jsp" class="btn btn-danger">Clear Cart</a></td>
                        <td><a href="checkout.jsp" class="btn btn-success">Check Out</a></td>
                        <td>Total</td>
                        <td class="text-right" id="total-value">
                            <strong><%=((Cart)session.getAttribute("cart")).getTotalPrice()%></strong></td>
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
            <p class="m-0 text-center text-white">Copyright &copy; BookMe.com 2018</p>
        </div>
        <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
