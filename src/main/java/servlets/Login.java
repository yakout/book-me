package servlets;

import beans.Cart;
import beans.Sale;
import beans.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        if (UserDAO.login(email, pass)) {
            System.out.println("LOGIN SUCCESS");
            /* Get the current user */
            User user = UserDAO.getUser(email);

            Cart cart = new Cart();

            /* get new session or create new if it doesn't exist */
            HttpSession session = request.getSession(false);
            /* adding user to session to access it in other servlets */
            session.setAttribute("user", user);

            session.setAttribute("cart", cart);

            /* send it to welcome page */
            response.sendRedirect("home.jsp");

        } else {
            // TODO
            System.out.println("LOGIN FAILURE");
            response.sendRedirect("home.jsp");
            request.setAttribute("errorMessage", "ERROR!, Wrong email or password.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}