package servlets;

import beans.User;
import model.UserDAO;
import services.PasswordEncryptionService;
import sun.security.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "profile" , urlPatterns = "/profile")
public class Profile extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getParameter("action") != null && request.getParameter("action").equals("promote")) {
            if (UserDAO.promoteUser(request.getParameter("email"))) {
                request.setAttribute("successMessage", "Promotion Done.");
                request.getRequestDispatcher("promoteUser.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Promotion Failed.");
                request.getRequestDispatcher("promoteUser.jsp").forward(request, response);
            }
            return;
        }

        User new_user = new User(
                request.getParameter("email"),
                request.getParameter("FName"),
                request.getParameter("LName"),
                request.getParameter("PhoneNumber"),
                request.getParameter("ShippingAddress")
        );

        String old_password = request.getParameter("old_pass");
        String new_password = request.getParameter("new_pass");

        PasswordEncryptionService pw = new PasswordEncryptionService();

        User old_user = (User) request.getSession().getAttribute("user");

        try {
            if (pw.authenticate(old_password, old_user.getEncryptedPassword(), old_user.getSalt())) {
                if (!new_password.isEmpty()) {
                    new_user.setEncryptedPassword(pw.getEncryptedPassword(new_password, old_user.getSalt()));
                }
                System.out.println("Old Password matched.");
            } else {
                System.out.println("Old Password did not match.");
                request.setAttribute("errorMessage", "Wrong password.");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return;
        }

        new_user.setUserID(old_user.getUserID());
        new_user.setManager(old_user.isManager());

        if (UserDAO.updateUser(new_user)) {
            System.out.println("UPDATE PROFILE SUCCESS.");
            request.getSession().setAttribute("user", new_user);
            request.setAttribute("successMessage", "Updated profile successfully.");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            System.out.println("UPDATE PROFILE FAIL.");
            request.setAttribute("errorMessage", "Updating profile failed.");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }
}
