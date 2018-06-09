package beans;

import java.util.List;

public class User {
    private String userID;
    private byte[] encryptedPassword;
    private byte[] salt;
    private String email;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String shippingAddress;
    private boolean isManager;

    /**
     *  will be used by Statistics.
     * */
    int sum_copies;
    int sum_paid;

    public int getSum_paid() {
        return sum_paid;
    }

    public void setSum_paid(int sum_paid) {
        this.sum_paid = sum_paid;
    }

    public int getSum_copies() {
        return sum_copies;
    }

    public void setSum_copies(int sum_copies) {
        this.sum_copies = sum_copies;
    }


    public User() {

    }

    public User(String email, String fName, String lName, String phoneNumber, String shippingAddress) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.shippingAddress = shippingAddress;
    }


    // GETTERS
    public String getUserID() {
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


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setUserID(String userID) {
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
                + "encryptedPassword: " + encryptedPassword + "\n"
                + "salt: " + salt + "\n"
                + "phoneNumber: " + phoneNumber + "\n"
                + "shippingAddress: " + shippingAddress + "\n"
                + "isManager: " + isManager + "\n";
    }
}
