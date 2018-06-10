
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.User" %>
<%@ page import="beans.Book" %>
<%@ page import="model.BookDAO" %>
<html>

<head>
    <title>Book Edit</title>

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

                <li class="nav-item">
                    <a href="logout" class="nav-link">Logout</a>
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
            <h3 class="mb-0">Book Edit</h3>
        </div>
        <div class="card-body">
            <form class="form" role="form" autocomplete="off" method="post" action="/BookUpdate">
                <% Book book = BookDAO.findByISBN(Integer.parseInt(request.getParameter("ISBN")));%>

                <input type="hidden" value="edit" name="action">
                <input type="hidden" value="<%=book.getISBN()%>" name="oldISBN">
                <input type="hidden" value="<%=book.getNumberOfCopies()%>" name="originalCopies">

                <div class="form-group">
                    <label for="ISBN">ISBN</label>
                    <input type="text" class="form-control"
                           value="<%=book.getISBN()%>" id="ISBN" name="ISBN" placeholder="Book ISBN" required>
                </div>

                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" class="form-control" value="<%=book.getTitle()%>" id="title" name="title"
                           placeholder="Book Title" required>
                </div>

                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="text" class="form-control" value="<%=book.getPrice()%>" id="price" name="price"
                           placeholder="Book Price">
                </div>

                <div class="form-group">
                    <label for="publisher">Publisher</label>
                    <input type="text" class="form-control" value="<%=book.getPublisherName()%>" id="publisher"
                           name="publisher"
                           placeholder="Book Publisher" required>
                </div>

                <div class="form-group">
                    <label for="pub_year">Publication Year</label>
                    <input type="text" class="form-control" value="<%=book.getPublicationYear()%>" id="pub_year"
                           name="pub_year"
                           placeholder="Publication Year" required>
                </div>

                <div class="form-group">
                    <label for="threshold">Threshold</label>
                    <input type="text" class="form-control" value="<%=book.getThreshold()%>"  id="threshold"
                           name="threshold"
                           placeholder="Book Threshold" required>
                </div>

                <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" class="form-control" value="<%=book.getCategory().toString()%>" id="category"
                           name="category" placeholder="Book Category" required>
                </div>

                <%  int count = 0;
                    for (String author : BookDAO.getBookAuthors(book.getISBN())) {%>
                <div class="form-group">
                    <label for="<%=author%>">Author <%=++count%></label>
                    <input type="text" class="form-control" value="<%=author%>" id="<%=author%>" name="<%=author%>">
                </div>
                <% } %>

                <p>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample"
                            aria-expanded="false" aria-controls="collapseExample">
                        Add Author
                    </button>
                </p>

                <div class="collapse" id="collapseExample">
                    <div class="card card-body">
                        <div class="form-group">
                            <label for="category">Author Name</label>
                            <input type="text" class="form-control" value="" id="newAuthor"
                                   name="newAuthor" placeholder="Author Name">
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-lg float-right">Update</button>
                </div>

            </form>

        </div>
    </div>
    <!-- /form card register -->
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
