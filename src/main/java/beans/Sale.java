package beans;

import java.sql.Date;

public class Sale {
    private int ISBN;
    private int copies;
    private Double price;
    private String sale_name;


    /**
     *  will be used by statistics page.
     * */
    private Date sale_date;

    /**
     *  the user made that sale.
     * */
    private String user_first_name;
    private String user_last_name;


    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public Sale() { }

    public Sale(int ISBN, int copies) {
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
