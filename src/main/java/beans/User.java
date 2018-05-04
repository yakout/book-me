package beans;

import java.util.List;

/**
 * Created by ahmedyakout on 5/4/18.
 */
public class User {
    private int UserID;
    private String password;
    private String email;
    private String FName;
    private String LName;
    private String PhoneNumber;
    private String shippingAddress;
    private boolean isManager;

    private List<Book> shoppingCart;


    // GETTERS
    public int getUserID() {
        return UserID;
    }

    public String getEmail() {
        return email;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }



    // SETTERS
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }


    // ****************************************
    // ************** OPERATIONS **************
    // ****************************************

    /**
     *
     * Search for books by any of the book’s attributes.
     *
     */
    public void searchBook() {

    }

    /**
     * Add books to a shopping cart
     */
    public void addItem() {

    }

    /**
     *
     * View the items in the cart
     *
     */
    public void viewItems() {

    }

    /**
     *
     * View the individual and total prices of the books in the cart
     *
     */
    public void viewPrice() {

    }

    /**
     *
     * Remove items from the cart
     *
     */
    public void removeItem() {

    }

    /**
     *
     * Checkout a shopping cart.
     *
     * - The customer is then required to provide a credit card number and its expiry date.
     *   This transaction is completed successfully if the credit card information is appropriate.
     *
     * - The book’s quantities in the store are updated according to this transaction.
     *
     */
    public void checkout() {

    }

    /**
     *
     * Logout of the system
     * This will remove all the items in the current cart.
     *
     */
    public void logout() {

    }


}
