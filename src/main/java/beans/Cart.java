package beans;

import model.SalesDAO;

import java.util.ArrayList;

public class Cart {

    ArrayList<Sale> cart;

    public Cart(){
        cart = new ArrayList<>();
    }

    public void addSale(Sale sale){
        cart.add(sale);
    }

    public boolean removeSale(int index){
        return cart.remove(index) != null;
    }

    public void clearCart(){
        cart.clear();
    }

    public boolean checkOut(String creditCard, String expiryDate){
        return SalesDAO.checkout(cart,creditCard,expiryDate);
    }

}
