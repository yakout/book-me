package model;

import java.sql.*;

/**
 * This should connects to database to get, insert, update or delete data.
 */
public class ModelManager {
    private static String url = "jdbc:mysql://localhost:3306/bookme";
    private static String user = "root";
    private static String pass = "yakout";
    private static ModelManager model;
    private Connection connection;

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
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeQuery(String query) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.execute(query);
            resultSet = statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        return resultSet;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
