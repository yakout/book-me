package model;

import beans.Book;
import beans.BookCategory;
import beans.Sale;
import beans.User;
import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * Responsible for getting any statistics on sales for the last 3 months.
 *
 */
public class SalesDAO {

    /**
     * The total sales for books in the previous month.
     */
    public static Double getTotalSales() {

        // not tested
        String query = "SELECT  SUM(Sale.copies * price) "
                + " FROM (Sale NATURAL JOIN Book) "
                + " WHERE "
                + " YEAR(Sale.sale_date) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) "
                + " AND MONTH(Sale.sale_date) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH);";

        Double total_sales = 0.0;
        ResultSet result = null;

        try {
            result = ModelManager.getInstance().executeQuery(query);
            total_sales = result.getDouble(1);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return total_sales;
        }
        return total_sales;
    }

    /**
     * The top 5 customers who purchase the most purchase amount in descending order for the last three months.
     */
    public static ArrayList<User> getTopFiveCustomers() {
        ArrayList<User> top_five = new ArrayList<>();

        // not tested
        String query = "SELECT User.* , SUM(Sale.copies) AS sum_copies, "
                + " SUM(Sale.copies * Book.price) AS sum_paid "
                + " FROM (Sale NATURAL JOIN Book NATURAL JOIN User)"
                + " WHERE " + " YEAR(sale_date) >= YEAR(CURRENT_DATE - INTERVAL 3 MONTH) "
                + " AND MONTH(sale_date) >= MONTH(CURRENT_DATE - INTERVAL 3 MONTH) "
                + " AND YEAR(sale_date) < YEAR(CURRENT_DATE) "
                + " AND MONTH(sale_date) < MONTH(CURRENT_DATE) "
                + " GROUP BY User.user_id "
                + " ORDER BY sum_paid DESC"
                + " LIMIT 5 ;";

        ResultSet result = null;
        try {
            result = ModelManager.getInstance().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return top_five;
        }

        try {

            while (result.next()){
                String email,  fName,  lName,  phoneNumber,  shippingAddress;

                int sum_copies , sum_paid;

                email = result.getString("email");
                fName = result.getString("first_name");
                lName = result.getString("last_name");
                phoneNumber = result.getString("phone_number");
                shippingAddress = result.getString("shipping_address");
                sum_copies = result.getInt("sum_copies");
                sum_paid = result.getInt("sum_paid");

                User u = new User(email,fName,lName,phoneNumber,shippingAddress);
                u.setSum_copies(sum_copies);
                u.setSum_paid(sum_paid);

                top_five.add(u);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return top_five;

    }


    /**
     * The top 10 selling books for the last three months.
     */
    public static ArrayList<Book> getTopTenBooks() {
        ArrayList<Book> top_ten = new ArrayList<>();

        // not tested
        String query = "SELECT Book.* , SUM(Sale.copies) AS sum_copies "
                + " FROM (Book NATURAL JOIN Sale)"
                + " GROUP BY Book.ISBN "
                + " ORDER BY sum_copies DESC"
                + " LIMIT 10 ;";


        ResultSet result = null;
        try {
            result = ModelManager.getInstance().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return top_ten;
        }

        try {
            while (result.next()) {
                Book book = new Book();
                book.setISBN(result.getInt("ISBN"));
                book.setTitle(result.getString("title"));
                book.setPublisherName(result.getString("publisher"));
                book.setCategory(BookCategory.valueOf(result.getString("category")));
                book.setPrice(result.getDouble("price"));
                book.setThreshold(result.getInt("threshold"));
                book.setNumberOfCopies(result.getInt("copies"));
                book.setNumberOfSalesCopies(result.getInt("sum_copies"));
                top_ten.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return top_ten;
    }

    public static void checkout(@NotNull ArrayList<Sale> sales, String user_id) {
        ArrayList<Boolean> status = new ArrayList<>();

        for (int i = 0; i < sales.size(); i++) {
            String query = "INSERT INTO Sale VALUES "
                + "("
                + " UUID() " + " , "
                + "'" + user_id + "'" + " , "
                + sales.get(i).getISBN() + " , "
                + "NOW()" + " , "
                + sales.get(i).getCopies()
                + ");";

            System.out.println("checkout query: " + query);
            try {
                ModelManager.getInstance().executeQuery(query);
                status.add(i, true);
            } catch (SQLException e) {
                status.add(i, false);
            }
        }

        for (int i = sales.size() - 1; i >= 0; i--) {
            if (status.get(i)) {
                sales.remove(i);
            }
        }
    }



}
