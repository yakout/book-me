<%@ page import="beans.User" %>
<%@ page import="model.BookDAO" %>
<%@ page import="beans.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>

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

                    <li class="nav-item">
                        <a class="nav-link cart-item-count" href="cart.jsp" data-cesta-feira-items-count>
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

            <div class="col-lg-3">

                <h1 class="my-4">Book Me</h1>
                <div class="list-group">
                    <% for(String category : BookDAO.getCategories()) {%>
                        <a href="#" class="list-group-item">
                            <%= category %>
                        </a>
                    <% } %>
                </div>

            </div>
            <!-- /.col-lg-3 -->

            <div class="col-lg-9">

                <div class="my-4"></div>

                <div class="row">

                    <%for (Book book : BookDAO.getBooks(20) ) {%>
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="#">
                                                <%= book.getTitle() %>
                                            </a>
                                        </h4>
                                        <h5>
                                            <%= book.getPrice() + "$" %>
                                        </h5>
                                        <p class="card-text">
                                            <strong>Category: </strong><%= book.getCategory().toString() %>
                                            <br>
                                            <strong>Book Authors: </strong>
                                            <br>
                                            <%-- TODO add authors and other book's details --%>
                                        </p>
                                    </div>
                                    <div class="card-footer">
                                        <form action="" class="form" data-cesta-feira-form>
                                            <div class="form-group">
                                                <input type="number" min="1" value="1" class="form-control" name="quantity" data-cesta-feira-attribute placeholder="Quantity">
                                            </div>
                                            <input type="hidden" value="Product 4" name="product_name" data-cesta-feira-attribute>
                                            <input type="hidden" value="2.02" name="unity_price" data-cesta-feira-attribute>
                                            <input type="hidden" value="other" name="item_type" data-cesta-feira-attribute>
                                            <input type="hidden" value="4" data-cesta-feira-item-id />
                                            <input type="submit" class="btn btn-primary" value="Add to Cart"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                <% } %>

                </div>
                <!-- /.row -->

            </div>
            <!-- /.col-lg-9 -->

        </div>
        <!-- /.row -->

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