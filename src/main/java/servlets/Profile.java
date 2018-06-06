package servlets;

import beans.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profile" , urlPatterns = "/profile")
public class Profile extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         *
         * Used to update the profile attributes.
         *
         * */
        User user = (User) req.getSession().getAttribute("user");
        if(UserDAO.updateUser(user)){
            resp.sendRedirect("home.jsp");
        }else{
            req.setAttribute("errorMessage", "ERROR!, Didn't update the Profile");
            resp.sendRedirect("profile.jsp");
        }
    }
}
