package servlets;

import beans.Cart;
import beans.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        if (UserDAO.login(email, pass)) {
            System.out.println("LOGIN SUCCESS");
            /* Get the current user */
            User user = UserDAO.getUser(email);

            Cart cart = new Cart(user.getUserID());
            /* get new session or create new if it doesn't exist */
            HttpSession session = request.getSession(true);
            /* adding user to session to access it in other servlets */
            session.setAttribute("user", user);
            session.setAttribute("cart", cart);

            /* send it to welcome page */
            request.setAttribute("successMessage", "Success.");
            response.sendRedirect("index.jsp");
        } else {
            System.out.println("LOGIN FAILURE");
            System.out.println("email: " + email);

            request.setAttribute("errorMessage", "Wrong email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}