package model;

import beans.Book;
import beans.BookCategory;
import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ahmedyakout on 5/4/18.
 *
 * Responsible for:
 *     1. add new books.
 *     2. modify existing books.
 *     3. search for books.
 *
 */
public class BookDAO {


    /**
     * To add a new book to the online store.
     * @param newBook : the new book we want to add into our store.
     */
    public static void addNewBook(@NotNull Book newBook) {
        /**
         * adding the new book.
         */
        String query = "INSERT INTO BOOK VALUES"
                + "( "
                + newBook.getISBN() + " , "
                + "'" + newBook.getTitle() + "'" + " , "
                + "'" + newBook.getPublisherName() + "'" + " , "
                + "'" + newBook.getCategory() + "'" + " , "
                + newBook.getPrice() + " , "
                + newBook.getThreshold() + " , "
                + newBook.getNumberOfCopies()
                + " );" ;

        ModelManager.getInstance().executeQuery(query);

        /**
         * adding the book authors associated with the new book.
         */
        for(String authorName : newBook.getAuthorsNames()){
            query = "INSERT INTO AUTHOR VALUES"
                    + "( "
                    + "'" + authorName + "'" + " , "
                    + newBook.getISBN()
                    + " );" ;
            ModelManager.getInstance().executeQuery(query);
        }
    }

    /**
     * For updating an existing book, the user first searches for the book then he does the required update.
     * For a given book, the user can update the quantity in stock when a copy or more of the book is sold.
     * The user cannot update the quantity of a book if this update will cause the quantity of a book in stock to be
     * negative.
     * @param updatedBook : the modified book attributes
     */
    public static void modifyBook(@NotNull Book updatedBook) {
        /**
         * update the existing book.
         */
        String query = "UPDATE BOOK SET"
                + "title = " + "'" + updatedBook.getTitle() + "'" + " , "
                + "publisher = " + "'" + updatedBook.getPublisherName() + "'" + " , "
                + "category = " + "'" + updatedBook.getCategory() + "'" + " , "
                + "price = " + updatedBook.getPrice() + " , "
                + "threshold = " + updatedBook.getThreshold() + " , "
                + "copies = " + updatedBook.getNumberOfCopies()
                + "where ISBN = " + updatedBook.getISBN() + ";" ;

        ModelManager.getInstance().executeQuery(query);
    }


    /**
     * The user can search for a book by ISBN, and title. The user can search for books of a specific Category,
     * author or publisher.
     */


    /**
     * find only one book or nothing by searching by title.
     * @param title : the title we search by
     * @return the matched book.
     */
    public static Book findByTitle(@NotNull String title) {
        String query = "SELECT FROM BOOK"
                        + "WHERE TITLE = " + "'" + title + "'" + ";";

        ResultSet resultSet = ModelManager.getInstance().executeQuery(query);
        return buildBook(resultSet);
    }

    /**
     * find only one book or nothing by searching by ISBN.
     */
    public static void findByISBN() {

    }

    /**
     * find many books by searching by Author Name.
     */
    public static void findByAuthor() {

    }

    /**
     * find many books by searching by category.
     */
    public static void findByCategory() {

    }

    /**
     * find many books by searching by publisher Name.
     */
    public static void findByPublisher() {

    }


    private static Book buildBook(@NotNull ResultSet rs){
        Book book = new Book();
        try {
            book.setISBN(Integer.parseInt(rs.getString("ISBN")));
            book.setTitle(rs.getString("title"));
            book.setPublisherName(rs.getString("publisher"));
            book.setCategory(BookCategory.valueOf(rs.getString("category")));
            book.setPrice(Integer.parseInt(rs.getString("price")));
            book.setThreshold(Integer.parseInt(rs.getString("threshold")));
            book.setNumberOfCopies(Integer.parseInt(rs.getString("copies")));
        } catch (SQLException | NullPointerException e){
            book = null;
            e.printStackTrace();
        } finally {
            return book;
        }
    }

}
