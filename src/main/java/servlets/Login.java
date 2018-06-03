package servlets;

import beans.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ahmedyakout on 5/2/18.
 */
@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        if (UserDAO.login(email, pass)) {
            /* Get the current user */
            User user = UserDAO.getUser(email);
            /* get new session or create new if it doesn't exist */
            HttpSession session = request.getSession();
            /* adding user to session to access it in other servlets */
            session.setAttribute("user",user);
            /* send it to welcome page */
            response.sendRedirect("welcome.jsp");

        } else {
            response.sendRedirect("index.jsp");
            request.setAttribute("errorMessage", "ERROR!, Wrong email or password.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}