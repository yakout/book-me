<%@ page import="model.BookDAO" %>
<%@ page import="beans.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.BookCategory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if ( session != null && session.getAttribute("user") != null) {
        String title = request.getParameter("title").isEmpty() ? null : request.getParameter("title");
        String ISBN = request.getParameter("ISBN").isEmpty() ? null : request.getParameter("ISBN");
        String author = request.getParameter("author").isEmpty() ? null : request.getParameter("author");
        String category = request.getParameter("category").isEmpty() ? null : request.getParameter("category");
        String publisher = request.getParameter("publisher").isEmpty() ? null : request.getParameter("publisher");
        String pub_year = request.getParameter("pub_year").isEmpty() ? null : request.getParameter("pub_year");

        ArrayList<Book> results = new ArrayList<>();
        BookCategory bookCategory = null;
        Integer ISBN_INT = null;
        try {
            if (category != null) {
                bookCategory = BookCategory.valueOf(category);
            }
        } catch (IllegalArgumentException e) {
        }

        try {
            if (ISBN != null) {
                ISBN_INT = Integer.parseInt(ISBN);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        results.addAll(BookDAO.find(ISBN_INT, title, publisher, bookCategory, author, pub_year));

        request.setAttribute("searchResults", results);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } else {
        request.setAttribute("errorMessage", "You must login first!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>
