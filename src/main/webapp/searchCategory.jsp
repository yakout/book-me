<%@ page import="model.BookDAO" %>
<%@ page import="beans.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.BookCategory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String category = request.getParameter("category");

    ArrayList<Book> results = new ArrayList<>();
    BookCategory bookCategory = null;
    try {
        if (category != null) {
            bookCategory = BookCategory.valueOf(category);
        }
    } catch (IllegalArgumentException e) { }

    if (bookCategory != null) {
        results.addAll(BookDAO.findByCategory(bookCategory, session.getAttribute("offset") == null ? 0 :
                (Integer) session.getAttribute("offset")));
    }
    request.setAttribute("searchResults", results);
    request.getRequestDispatcher("index.jsp").forward(request, response);
%>
