<%@ page import="beans.Cart" %>
<%@ page import="beans.Sale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if ( session != null && session.getAttribute("cart") != null) {
        Integer ISBN = Integer.parseInt(request.getParameter("ISBN"));
        ((Cart) session.getAttribute("cart")).removeSale(ISBN);

        request.setAttribute("successMessage", "Item deleted from cart Successfully!");
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    } else {
        request.setAttribute("errorMessage", "You must login first!");
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
%>
