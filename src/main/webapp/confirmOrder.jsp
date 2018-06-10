<%@ page import="beans.User" %>
<%@ page import="beans.Order" %>
<%@ page import="model.OrderDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("user") != null && ((User)session.getAttribute("user")).isManager()) {
        Order order = new Order();
        order.setISBN(Integer.parseInt(request.getParameter("ISBN")));
        order.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        order.setOrderID(request.getParameter("order_id"));

        if (OrderDAO.confirmOrder(order)) {
            request.setAttribute("successMessage", "Confirmed!");
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Confirmation failed!");
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        }
    } else {
        request.setAttribute("errorMessage", "Unauthorized access!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>