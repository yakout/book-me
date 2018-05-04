package model;

import java.sql.*;

/**
 * This should connects to database to get, insert, update or delete data.
 */
public class ModelManager {
    private static String url = "jdbc:mysql://localhost:3306/library";
    private static String user = "root";
    private static String pass = "yakout";
    private static ModelManager model;
    private Connection connection;

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
            e.printStackTrace();
        }
    }

    public void executeQuery(String query) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            for (int i  = 0; i < 10; i++) {
                if (resultSet.next()) {
                    System.out.print(resultSet.getString("BOOK_ID") + " | ");
                    System.out.print(resultSet.getString("TITLE") + " | ");
                    System.out.println(resultSet.getString("PUBLISHER_NAME"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
