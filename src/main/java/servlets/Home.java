package servlets;

import beans.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "home", urlPatterns = "/home")
public class Home extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.checkOut("","");
        cart.clearCart();
        /* return to the home page. */
        response.sendRedirect("welcome.jsp");
    }
}
