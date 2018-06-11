package model;

import java.sql.*;

/**
 * This should connects to database to get, insert, update or delete data.
 */
public class ModelManager {
    private static String url = "jdbc:mysql://localhost:3306/bookme";
    private static String user = "root";
//    private static String pass = "admin"; // essam
    private static String pass = "yakout"; // yakout
    private static ModelManager model;
    private Connection connection;
    static private int pagecount = 20;

    private ModelManager() {
        startConnection();
    }

    public static synchronized ModelManager getInstance()
    {
        if (model == null) {
            model = new ModelManager();
        }
        return model;
    }

    public void startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        return statement.getResultSet();
    }

    static public int getPagecount() {
        return pagecount;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
