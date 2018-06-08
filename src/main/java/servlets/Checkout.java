package servlets;

import beans.Cart;
import beans.Sale;
import model.SalesDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialStruct;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "checkout", urlPatterns = "/checkout")
public class Checkout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO process checkout.
        String firstname = request.getParameter("firstname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String cardname = request.getParameter("cardname");
        String cardnumber = request.getParameter("cardnumber");
        String expmonth = request.getParameter("expmonth");
        String expyear = request.getParameter("expyear");
        String cvv = request.getParameter("cvv");

        System.out.println("CHECKOUT ...");
        if (!firstname.isEmpty() && !email.isEmpty() && !address.isEmpty() && !city.isEmpty() && !state.isEmpty() &&
                !zip.isEmpty() && !cardname.isEmpty() && !cardnumber.isEmpty() && !expmonth.isEmpty() && !expyear
                .isEmpty() && !cvv.isEmpty()) {
            System.out.println("CHECKOUT SUCCESS");

            Cart cart = ((Cart) request.getSession().getAttribute("cart"));
            cart.checkOut();
            if (!cart.getCart().isEmpty()) {
                request.setAttribute("errorMessage", "These items failed to checkout, they may be not available " +
                        "at the moment!");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            } else {
                request.setAttribute("successMessage", "Checkout Done");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            System.out.println("CHECKOUT FAIL");
            request.setAttribute("errorMessage", "Please fill the empty slots");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
