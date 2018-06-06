package model;

import beans.Order;
import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xpath.internal.operations.Mod;

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
    public static void placeOrder(@NotNull Order newOrder) {
        /**
         *
         *  Inserting new Order.
         *
         * */
        String query = "INSERT INTO Order VALUES"
                + "( "
                + "'"  + newOrder.getOrderID() + "'" + " , "
                + newOrder.getISBN() + " , "
                + newOrder.getQuantity() + " , "
                + " );" ;

        ModelManager.getInstance().executeQuery(query);

    }

    /**
     *
     * The user can confirm an order when receiving the ordered quantity from the book’s publisher;
     * the quantity of the book in store automatically increases with the quantity specified in the order.
     * Assume that deleting the order means that the order is received from publisher
     *
     */
    public static void confirmOrder(@NotNull Order confirmedOrder) {
        /**
         *
         * Deleting the confirmed order.
         *
         * */

        String query = "DELETE FROM Order"
                        + " WHERE " + " order_id "
                        + " = " + confirmedOrder.getOrderID() + " ; ";

        ModelManager.getInstance().executeQuery(query);
    }

}
