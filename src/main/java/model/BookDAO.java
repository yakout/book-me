package model;

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
     * this should take book attributes + threshold.
     */
    public static void addNewBook() {

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
