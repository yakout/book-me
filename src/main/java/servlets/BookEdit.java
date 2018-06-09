package servlets;

import beans.Book;
import beans.BookCategory;
import model.BookDAO;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedReader;
import java.util.ArrayList;

@WebServlet(name = "BookEdit", urlPatterns = "/editBook")
public class BookEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Book book = new Book();

        String oldISBN = request.getParameter("oldISBN");
        String ISBN = request.getParameter("ISBN");
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String publisher = request.getParameter("publisher");
        String pub_year = request.getParameter("pub_year");
        String threshold = request.getParameter("threshold");
        String category = request.getParameter("category");
        String originalCopies = request.getParameter("originalCopies");
        String newAuthor = request.getParameter("newAuthor");

        try {
            int isbn_int = Integer.parseInt(ISBN);
            book.setISBN(isbn_int);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "ISBN must be an integer value");
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
            return;
        }

        book.setTitle(title);

        try {
            Double price_double = Double.parseDouble(price);
            book.setPrice(price_double);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Price must be Double value");
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
            return;
        }

        book.setPublisherName(publisher);
        book.setPublicationYear(pub_year);


        try {
            int threshold_int = Integer.parseInt(threshold);
            book.setThreshold(threshold_int);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Threshold must be an integer value");
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
            return;
        }

        try {
            book.setCategory(BookCategory.valueOf(category));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Category doesn't exits, make sure it's in the form " +
                    "[A-Z][a-z]+ 'e.g Art'");
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
            return;
        }

        book.setNumberOfCopies(Integer.parseInt(originalCopies));

        if (!BookDAO.modifyBook(book, Integer.parseInt(oldISBN))) {
            request.setAttribute("errorMessage", "Updating book info failed.");
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
        } else {
            request.setAttribute("successMessage", "Updated book info successfully.");
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
