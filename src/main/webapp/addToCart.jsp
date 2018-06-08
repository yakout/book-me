<%@ page import="beans.Cart" %>
<%@ page import="beans.Sale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if ( session != null && session.getAttribute("cart") != null) {
        Sale sale = new Sale();

        sale.setCopies(Integer.parseInt(request.getParameter("quantity")));
        sale.setISBN(Integer.parseInt(request.getParameter("ISBN")));
        sale.setPrice(Double.parseDouble(request.getParameter("price")));
        sale.setSale_name(request.getParameter("name"));

        ((Cart) session.getAttribute("cart")).addSale(sale);
        request.setAttribute("successMessage", "Item Added to cart Successfully!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } else {
        request.setAttribute("errorMessage", "You must login first!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>
