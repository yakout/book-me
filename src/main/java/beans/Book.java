package beans;

import java.util.ArrayList;

public class Book {
    private int ISBN;
    private String title;
    private String publisherName;
    private String publicationYear;
    private Double price;
    private BookCategory category;
    private int threshold;
    private int numberOfCopies;
    private ArrayList<String> authorsNames;
    private int numberOfSalesCopies;


    // default constructor if we don't know the values yet
    public Book() {
        price = 10d;
        threshold = 0;
        numberOfCopies = 0;
        publicationYear = "";
        numberOfSalesCopies = 0;
    }

    // GETTERS
    public int getISBN() {
        return ISBN;
    }

    public BookCategory getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getPublicationYear() {
        return publicationYear;
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

    public int getNumberOfSalesCopies() {
        return numberOfSalesCopies;
    }

    // SETTERS
    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
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

    public void setNumberOfSalesCopies(int numberOfSalesCopies) {
        this.numberOfSalesCopies = numberOfSalesCopies;
    }
}
