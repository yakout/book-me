package beans;

import java.util.List;

/**
 * Created by ahmedyakout on 5/4/18.
 */
public class User {
    private int userID;
    private String password;
    private byte[] encryptedPassword;
    private byte[] salt;
    private String email;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String shippingAddress;
    private boolean isManager;

    private List<Book> shoppingCart;

    public User() {

    }

    public User(String email, String password, String fName, String lName, String phoneNumber, String shippingAddress) {
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.shippingAddress = shippingAddress;
    }


    // GETTERS
    public int getUserID() {
        return userID;
    }

    public byte[] getEncryptedPassword() {
        return encryptedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public boolean isManager() {
        return isManager;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }



    // SETTERS
    public void setEmail(String email) {
        this.email = email;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setEncryptedPassword(byte[] encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setManager(boolean manager) {
        this.isManager = manager;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    @Override
    public String toString() {
        return "email: " + email + "\n"
                + "fName: " + fName + "\n"
                + "lName: " + lName + "\n"
                + "password: " + password + "\n"
                + "encryptedPassword: " + encryptedPassword + "\n"
                + "phoneNumber: " + phoneNumber + "\n"
                + "shippingAddress: " + shippingAddress + "\n"
                + "isManager: " + isManager + "\n";
    }
}
