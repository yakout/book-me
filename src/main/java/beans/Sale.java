package beans;

import java.sql.Date;

public class Sale {

    private String sale_id;
    private String user_id;
    private int ISBN;
    private Date sale_date;
    private int copies;


    public Sale() { }

    public Sale(String user_id, int ISBN, Date sale_date, int copies) {
        this.user_id = user_id;
        this.ISBN = ISBN;
        this.sale_date = sale_date;
        this.copies = copies;
    }

    public Sale(String sale_id, String user_id, int ISBN, Date sale_date, int copies) {
        this.sale_id = sale_id;
        this.user_id = user_id;
        this.ISBN = ISBN;
        this.sale_date = sale_date;
        this.copies = copies;
    }

    public int getCopies() { return copies; }

    public void setCopies(int copies) { this.copies = copies; }

    public String getSale_id() { return sale_id; }

    public void setSale_id(String sale_id) { this.sale_id = sale_id; }

    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public int getISBN() { return ISBN; }

    public void setISBN(int ISBN) { this.ISBN = ISBN; }

    public Date getSale_date() { return sale_date; }

    public void setSale_date(Date sale_date) { this.sale_date = sale_date; }
}
