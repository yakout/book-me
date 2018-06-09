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
    public static ArrayList<Sale> getTotalSales() {

        String query = "SELECT Sale.copies, Sale.ISBN, Sale.sale_date,"
                + " User.first_name, User.last_name "
                + " FROM (Sale NATURAL JOIN User) "
                + " WHERE " + " YEAR(Sale.sale_date) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) "
                + " AND MONTH(Sale.sale_date) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH);";

        ArrayList<Sale> total_sales = new ArrayList<>();
        ResultSet result = null;
        try {
            result = ModelManager.getInstance().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return total_sales;
        }


        try {
            while (result.next()){
                String user_first_name,user_last_name;
                Date sale_date;
                int copies , ISBN;

                copies = result.getInt(1);
                ISBN = result.getInt(2);
                sale_date = result.getDate(3);
                user_first_name = result.getString(4);
                user_last_name = result.getString(5);



                Sale s = new Sale(ISBN,copies);
                s.setUser_first_name(user_first_name);
                s.setUser_last_name(user_last_name);
                s.setSale_date(sale_date);

                total_sales.add(s);

            }
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
        String query = "SELECT User.* , SUM(Sale.copies * Book.price) AS sum_cost , SUM(Sale.copies) AS sum_copies"
                + " FROM (Sale NATURAL JOIN User NATURAL JOIN Book)"
                + " WHERE " + " YEAR(sale_date) = YEAR(CURRENT_DATE - INTERVAL 3 MONTH) "
                + " AND MONTH(sale_date) = MONTH(CURRENT_DATE - INTERVAL 3 MONTH) "
                + " GROUP BY User.user_id "
                + " ORDER BY sum_cost DESC"
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

                int sum_copies, sum_cost;

                email = result.getString("email");
                fName = result.getString("first_name");
                lName = result.getString("last_name");
                phoneNumber = result.getString("phone_number");
                shippingAddress = result.getString("shipping_address");
                sum_copies = result.getInt("sum_copies");
                sum_cost = result.getInt("sum_cost");

                User u = new User(email,fName,lName,phoneNumber,shippingAddress);
                u.setSum_cost(sum_cost);
                u.setSum_copies(sum_copies);

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
            while (result.next()){
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
