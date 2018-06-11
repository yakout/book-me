<%@ page import="beans.User" %>
<%@ page import="beans.Sale" %>
<%@ page import="beans.Cart" %>
<%@ page import="model.OrderDAO" %>
<%@ page import="beans.Order" %>
<%@ page import="model.ModelManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>

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
        <a class="navbar-brand" href="index.jsp">Orders</a>
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

                <li class="nav-item">
                    <a href="logout" class="nav-link">Logout</a>
                </li>

                <li class="nav-item active">
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
    <br>
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Book Name</th>
                    <th scope="col">Book ISBN</th>
                    <th scope="col">Quantity</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <% int count = 0;
                    for (Order order : OrderDAO.getOrders(session.getAttribute("offset") == null ? 0 :
                            (Integer) session.getAttribute("offset"))) {%>
                <tr>
                    <th scope="row"><%=count++%></th>
                    <td><%=order.getBook_name()%></td>
                    <td><%=order.getISBN()%></td>
                    <td><%=order.getQuantity()%></td>
                    <td>
                        <form action="confirmOrder.jsp">
                            <input type="hidden" name="order_id" value="<%=order.getOrderID()%>">
                            <input type="hidden" name="ISBN" value="<%=order.getISBN()%>">
                            <input type="hidden" name="quantity" value="<%=order.getQuantity()%>">
                            <input type="submit" class="btn btn-success" value="Confirm">
                        </form>
                    </td>
                </tr>
                <% }
                    // if there are no results reset decrement back the offset.
                    if (count == 0) {
                        session.setAttribute("offset", (Integer) session.getAttribute("offset") -
                                ModelManager.getPagecount());
                    }
                %>
                </tbody>
            </table>
            <nav aria-label="Books Navigation">
                <ul class="pagination justify-content-center">
                    <form action="pagination.jsp">
                        <input type="hidden" name="from" value="orders">
                        <input class="btn btn-dark" type="submit" name="action" value="< Previous">
                        <input class="btn btn-dark" type="submit" name="action" value="Next >">
                    </form>
                </ul>
            </nav>
        </div>
    </div>
    <br>
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
