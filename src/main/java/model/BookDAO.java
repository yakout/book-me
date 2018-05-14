package model;

import beans.Book;
import com.sun.istack.internal.NotNull;

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

    @
    /**
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
                + newBook.getNumberOfCopies() + " , "
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
     *
     */
    public static void modifyBook() {

    }

    /**
     * The user can search for a book by ISBN, and title. The user can search for books of a specific Category,
     * author or publisher.
     */
    public static void searchBook() {

    }

    public static void findByTitle() {

    }

    public static void findByISBN() {

    }

    public static void findByAuthor() {

    }

    public static void findByCategory() {

    }

    public static void findByPublisher() {

    }

}
