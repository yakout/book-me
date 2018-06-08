package beans;

import model.SalesDAO;

import java.util.ArrayList;

public class Cart {
    private String user_id;
    ArrayList<Sale> cart;

    public Cart(String user_id) {
        this.user_id = user_id;
        cart = new ArrayList<>();
    }

    /**
     * add sale item to shopping cart.
     * @param sale
     */
    public void addSale(Sale sale) {
        // check if sale item already in cart
        for (Sale s : cart) {
            // if sale item already in cart increase it's copies
            if (s.getISBN() == sale.getISBN()) {
                s.setCopies(s.getCopies() + sale.getCopies());
                return;
            }
        }
        // if sale item not in cart add it.
        cart.add(sale);
    }

    /**
     * remove sale from cart.
     * @param ISBN
     * @return
     */
    public boolean removeSale(int ISBN) {
        for (Sale sale : cart) {
            if (sale.getISBN() == ISBN) {
                cart.remove(sale);
                return true;
            }
        }
        return false;
    }

    /**
     * clear cart.
     */
    public void clearCart(){
        cart.clear();
    }

    /**
     * get the current session cart.
     * @return
     */
    public ArrayList<Sale> getCart() {
        return cart;
    }

    /**
     * compute total price.
     * @return
     */
    public int getTotalPrice() {
        int sum = 0;
        for (Sale sale : cart) {
            sum += sale.getCopies() * sale.getPrice();
        }
        return sum;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * check out items.
     * @return
     */
    public void checkOut() {
        SalesDAO.checkout(cart, user_id);
    }

}
