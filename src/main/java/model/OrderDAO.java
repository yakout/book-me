package model;

import beans.Order;
import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * Responsible for:
 *    1. place order.
 *    2. confirm order.
 */
public class OrderDAO {

    /**
     *
     * An order with constant quantity is placed only when the quantity of a book drops from above a given threshold
     * (the minimum quantity in stock) to below the given threshold.
     *
     */
    public static boolean placeOrder(@NotNull Order newOrder) {
        /**
         *  Inserting new Order.
         * */
        String query = "INSERT INTO `Order` VALUES"
                + "(UUID(), "
                + newOrder.getISBN() + ", "
                + newOrder.getQuantity() + ");" ;
        try {
            ModelManager.getInstance().executeQuery(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The user can confirm an order when receiving the ordered quantity from the book’s publisher;
     * the quantity of the book in store automatically increases with the quantity specified in the order.
     * Assume that deleting the order means that the order is received from publisher
     *
     */
    public static boolean confirmOrder(@NotNull Order confirmedOrder) {
        /**
         * Deleting the confirmed order.
         * */
        String query = "DELETE FROM `Order`"
                        + " WHERE " + " order_id "
                        + " = '" + confirmedOrder.getOrderID() + "';";

        try {
            ModelManager.getInstance().executeQuery(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static ArrayList<Order> getOrders(Integer offset) {
        String query = "SELECT `Order`.* , title FROM `Order` NATURAL JOIN `Book` LIMIT "
                + ModelManager.getPagecount()
                + " OFFSET " + offset + ";";

        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet rs = ModelManager.getInstance().executeQuery(query);
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getString("order_id"));
                order.setISBN(rs.getInt("ISBN"));
                order.setQuantity(rs.getInt("quantity"));
                order.setBook_name(rs.getString("title"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

}
