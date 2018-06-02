package beans;

import java.util.ArrayList;

/**
 * Created by ahmedyakout on 5/4/18.
 */
public class Book {
    private int ISBN;
    private String title;
    private String publisherName;
    private String publisherDate;
    private float price;
    private BookCategory category;
    private int threshold;
    private int numberOfCopies;
    private ArrayList<String> authorsNames;


    // default constructor if we don't know the values yet
    public Book(){
        price = 10;
        threshold = 0;
        numberOfCopies = 0;
        publisherDate = "";
    }

    // GETTERS
    public int getISBN() {
        return ISBN;
    }

    public BookCategory getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getPublisherDate() {
        return publisherDate;
    }

    public String getTitle() {
        return title;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public ArrayList<String> getAuthorsNames() {
        return authorsNames;
    }

    // SETTERS
    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPublisherDate(String publisherDate) {
        this.publisherDate = publisherDate;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setAuthorsNames(ArrayList<String> authorsNames) {
        this.authorsNames = authorsNames;
    }
}
