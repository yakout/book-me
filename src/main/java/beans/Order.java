package beans;

/**
 * Created by ahmedyakout on 5/4/18.
 */
public class Order {
    private int orderID;
    private int ISBN;
    private int quantity;

    // GETTERS
    public int getISBN() {
        return ISBN;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getQuantity() {
        return quantity;
    }


    // SETTERS
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

