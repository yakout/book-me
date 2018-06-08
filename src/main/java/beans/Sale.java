package beans;

import java.sql.Date;

public class Sale {
    private int ISBN;
    private int copies;
    private Double price;
    private String sale_name;


    public Sale() { }

    public Sale(String user_id, int ISBN, Date sale_date, int copies) {
        this.ISBN = ISBN;
        this.copies = copies;
    }

    public Sale(String sale_id, String user_id, int ISBN, Date sale_date, int copies) {
        this.ISBN = ISBN;
        this.copies = copies;
    }

    public int getCopies() { return copies; }

    public void setCopies(int copies) { this.copies = copies; }

    public int getISBN() { return ISBN; }

    public void setISBN(int ISBN) { this.ISBN = ISBN; }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public String getSale_name() {
        return sale_name;
    }

    public void setSale_name(String sale_name) {
        this.sale_name = sale_name;
    }
}
