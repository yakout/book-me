package servlets;

import beans.BookCategory;
import model.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is for editing or adding new books.
 */
@WebServlet(name = "BookUpdate", urlPatterns = "/BookUpdate")
public class BookUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        beans.Book book = new beans.Book();

        Boolean update = request.getParameter("action").equals("edit");
        String oldISBN = request.getParameter("oldISBN");
        String ISBN = request.getParameter("ISBN");
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String publisher = request.getParameter("publisher");
        String pub_year = request.getParameter("pub_year");
        String threshold = request.getParameter("threshold");
        String category = request.getParameter("category");
        String originalCopies = request.getParameter("originalCopies");
        String copies = request.getParameter("copies");
        String newAuthor = request.getParameter("newAuthor");

        try {
            int isbn_int = Integer.parseInt(ISBN);
            book.setISBN(isbn_int);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "ISBN must be an integer value");
            if (update) {
                request.getRequestDispatcher("editBook.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("addNewBook.jsp").forward(request, response);
            }
            return;
        }

        book.setTitle(title);

        try {
            Double price_double = Double.parseDouble(price);
            book.setPrice(price_double);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Price must be Double value");
            if (update) {
                request.getRequestDispatcher("editBook.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("addNewBook.jsp").forward(request, response);
            }
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
            if (update) {
                request.getRequestDispatcher("editBook.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("addNewBook.jsp").forward(request, response);
            }
            return;
        }

        try {
            book.setCategory(BookCategory.valueOf(category));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Category doesn't exits, make sure it's in the form " +
                    "[A-Z][a-z]+ 'e.g Art'");
            if (update) {
                request.getRequestDispatcher("editBook.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("addNewBook.jsp").forward(request, response);
            }
            return;
        }

        if (update) {
            book.setNumberOfCopies(Integer.parseInt(originalCopies));

            ArrayList<String> authors = new ArrayList<>();
            for (String author : BookDAO.getBookAuthors(Integer.parseInt(oldISBN))) {
                String edited_author = request.getParameter(author);
                if (!edited_author.isEmpty()) {
                    authors.add(edited_author);
                }
            }

            if (!newAuthor.isEmpty()) {
                authors.add(newAuthor);
            }

            if (!BookDAO.modifyBook(book, Integer.parseInt(oldISBN), authors)) {
                request.setAttribute("errorMessage", "Updating book info failed.");
                request.getRequestDispatcher("editBook.jsp").forward(request, response);
            } else {
                request.setAttribute("successMessage", "Updated book info successfully.");
                request.getRequestDispatcher("editBook.jsp").forward(request, response);
            }
        } else {
            // Adding new book.
            try {
                book.setNumberOfCopies(Integer.parseInt(copies));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Copies must be an integer value");
                request.getRequestDispatcher("addNewBook.jsp").forward(request, response);
            }

            ArrayList<String> authors = new ArrayList<>();

            String author1 = request.getParameter("author1");
            String author2 = request.getParameter("author2");
            String author3 = request.getParameter("author3");
            String author4 = request.getParameter("author4");
            String author5 = request.getParameter("author5");
            String author6 = request.getParameter("author6");

            if (!author1.isEmpty()) authors.add(author1);
            if (!author2.isEmpty()) authors.add(author2);
            if (!author3.isEmpty()) authors.add(author3);
            if (!author4.isEmpty()) authors.add(author4);
            if (!author5.isEmpty()) authors.add(author5);
            if (!author6.isEmpty()) authors.add(author6);

            book.setAuthorsNames(authors);

            if (!BookDAO.addNewBook(book)) {
                request.setAttribute("errorMessage", "Adding book failed.");
                request.getRequestDispatcher("addNewBook.jsp").forward(request, response);
            } else {
                request.setAttribute("successMessage", "Added book successfully.");
                request.getRequestDispatcher("addNewBook.jsp").forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
