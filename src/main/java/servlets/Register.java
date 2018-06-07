package servlets;

import beans.User;
import model.UserDAO;
import services.PasswordEncryptionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "register", urlPatterns = "/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                request.getParameter("email"),
                request.getParameter("FName"),
                request.getParameter("LName"),
                request.getParameter("PhoneNumber"),
                request.getParameter("ShippingAddress")
        );

        user.setManager(false);

        PasswordEncryptionService pw = new PasswordEncryptionService();
        byte[] encryptedPwd = null;
        byte[] salt = null;

        try {
            salt = pw.generateSalt();
            encryptedPwd = pw.getEncryptedPassword(request.getParameter("pass"), salt);
            user.setSalt(salt);
            user.setEncryptedPassword(encryptedPwd);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        if (UserDAO.register(user)) {
            System.out.println("REGISTER SUCCESS");
            log("REGISTER SUCCESS");
            //response.sendRedirect("welcome.jsp");
            request.setAttribute("successMessage", "Registration successful. You can sign in now.");
            /* forward request to login servlet to handle the login process. */
//            response.sendRedirect("home.jsp");
            request.getRequestDispatcher("/login").forward(request,response);
        } else {
            System.out.println("REGISTER FAILURE");
            log("REGISTER FAILURE");
            request.setAttribute("errorMessage", "ERROR!, in register. ");
            response.sendRedirect("register.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
