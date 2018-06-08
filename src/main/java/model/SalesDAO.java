package model;

import beans.Book;
import beans.BookCategory;
import beans.Sale;
import beans.User;
import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * Responsible for getting any statistics on sales for the last 3 months.
 *
 */
public class SalesDAO {

    /**
     * The total sales for books in the previous month.
     */
    public static int getTotalSales() {

        String query = "SELECT SUM(copies) FROM Sale"
                + " WHERE " + " YEAR(sale_date) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) "
                + " AND MONTH(sale_date) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) ;";

        ResultSet result = null;
        try {
            result = ModelManager.getInstance().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int total_sales = -1;

        try {
            total_sales = result.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return total_sales;
    }

    /**
     * The top 5 customers who purchase the most purchase amount in descending order for the last three months.
     */
    public static ArrayList<User> getTopFiveCustomers() {
        ArrayList<User> top_five = new ArrayList<>();
        String query = "SELECT User.* , SUM(copies) AS sum_copies "
                + " FROM Sale , User"
                + " WHERE " + " YEAR(sale_date) = YEAR(CURRENT_DATE - INTERVAL 3 MONTH) "
                + " AND MONTH(sale_date) = MONTH(CURRENT_DATE - INTERVAL 3 MONTH) "
                + " AND Sale.user_id = User.user_id "
                + " GROUP BY User.user_id "
                + " ORDER BY sum_copies DESC"
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
                byte[] password;

                email = result.getString("email");
                password = result.getBytes("password");
                fName = result.getString("first_name");
                lName = result.getString("last_name");
                phoneNumber = result.getString("phone_number");
                shippingAddress = result.getString("shipping_address");

                User u = new User(email,fName,lName,phoneNumber,shippingAddress);
                u.setEncryptedPassword(password);

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

        String query = "SELECT Book.* , SUM(copies) AS sum_copies "
                + " FROM Sale , Book"
                + " WHERE "
                + " Sale.ISBN = Book.ISBN "
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
            while (result.next()){
                Book book = new Book();
                book.setISBN(result.getInt("ISBN"));
                book.setTitle(result.getString("title"));
                book.setPublisherName(result.getString("publisher"));
                book.setCategory(BookCategory.valueOf(result.getString("category")));
                book.setPrice(result.getDouble("price"));
                book.setThreshold(result.getInt("threshold"));
                book.setNumberOfCopies(result.getInt("copies"));

                top_ten.add(book);

            }

        }catch (SQLException e){
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
