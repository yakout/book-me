package beans;

public class Order {
    private String orderID;
    private int ISBN;
    private int quantity;

    // GETTERS
    public int getISBN() {
        return ISBN;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    // SETTERS
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

