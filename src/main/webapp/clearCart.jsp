<%@ page import="beans.Cart" %>
<%@ page import="beans.Sale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if ( session != null && session.getAttribute("cart") != null) {
        ((Cart) session.getAttribute("cart")).clearCart();
        request.setAttribute("successMessage", "Cart cleared Successfully!");
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    } else {
        request.setAttribute("errorMessage", "You must login first!");
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
%>
