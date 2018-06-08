package servlets;

import beans.Book;
import beans.BookCategory;
import beans.Cart;
import model.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "home", urlPatterns = "/home")
public class Home extends HttpServlet {
    /**
     *  Used for check-out for the books by the user.
     *
     * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.checkOut();
        cart.clearCart();
        /* return to the home page. */
        response.sendRedirect("home.jsp");
    }

    /**
     *
     *  Used for getting the wanted book due to search.
     * */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get parameters
        Integer ISBN = Integer.parseInt(req.getParameter("ISBN"));
        String title = req.getParameter("title");
        String publisherName = req.getParameter("publisherName");
        String authorName = req.getParameter("authorName") ;
        BookCategory category = BookCategory.valueOf(req.getParameter("category"));

        //call for search
        ArrayList<Book> books = BookDAO.find(ISBN,title,publisherName,category,authorName, null);

        //send the books to the User Interface.
        req.getSession().setAttribute("search_books",books);

    }
}
