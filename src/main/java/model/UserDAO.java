package model;

import beans.User;
import services.PasswordEncryptionService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Responsible for:
 *      1. register user.
 *      2. login user.
 *      3. get user as bean.
 *      4. update user.
 *      5. promote user.
 */
public class UserDAO {
    /**
     *
     */
    public static boolean login(String email, String pass) {
        boolean status = false;
        PasswordEncryptionService pw = new PasswordEncryptionService();
        ResultSet rs = null;
        try {
            rs = ModelManager.getInstance().executeQuery(
                    " SELECT password, salt from User where email = "
                            + "'" + email + "'"
                            + ";"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try {
            while(rs.next()) {
                try {
                    if(pw.authenticate(pass, rs.getBytes("password"), rs.getBytes("salt"))) {
                        System.out.println("CORRECT PASSWORD");
                        status = true;
                    } else {
                        System.out.println(
                                "WRONG PASSWORD: "
                                        + Arrays.toString(rs.getBytes("password"))
                                        + " with "
                                        + Arrays.toString(rs.getBytes("salt"))
                        );
                        status = false;
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
                    e.printStackTrace();
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }


    /**
     *
     */
    public static boolean register(User user) {
        boolean status = false;

        // check if the email is already registered.
        ResultSet rs = null;
        try {
            rs = ModelManager.getInstance().executeQuery(
                    " SELECT * FROM User WHERE email = " + "'" + user.getEmail() + "'" + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try {
            if (!rs.isBeforeFirst()) {
                // email is not registered.
                 PreparedStatement pst = ModelManager.getInstance().getConnection().prepareStatement(
                        "INSERT INTO User VALUES (UUID()," + "? , ? , ? , ? , ? , ?, ?, ?)");

                pst.setString(1, user.getEmail());
                pst.setBytes(2, user.getEncryptedPassword());
                pst.setBytes(3, user.getSalt());
                pst.setString(4, user.getfName());
                pst.setString(5, user.getlName());
                pst.setString(6, user.getPhoneNumber());
                pst.setString(7, user.getShippingAddress());
                pst.setString(8, user.isManager() ? "1" : "0");

                if (pst.executeUpdate() == 1) {
                    status = true;
                }
            } else {
                // TODO email is already registered
                status = false;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     * This should return the user associated with the given email
     * @param email
     * @return User bean
     */
    public static User getUser(String email) {
        User user = new User();

        String query = "SELECT * FROM User WHERE EMAIL = '" + email + "';";
        ResultSet rs = null;
        try {
            rs = ModelManager.getInstance().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try {
            if (rs.next()) {
                System.out.println(rs.getString("first_name"));
                user.setfName(rs.getString("first_name"));
                user.setlName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUserID(rs.getString("user_id"));
                user.setManager(rs.getInt("is_manager") == 1);
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setShippingAddress(rs.getString("shipping_address"));
                user.setEncryptedPassword(rs.getBytes("password"));
                user.setSalt(rs.getBytes("salt"));
            }
            rs.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * update user with the same associated id in the user bean with new info in the given bean.
     * @param user
     * @return true if success.
     */
    public static boolean updateUser(User user) {
        try {
            PreparedStatement pst = ModelManager.getInstance().getConnection().prepareStatement(
                    "UPDATE User SET email = ?, password = ?, first_name = ?, last_name = ?," +
                            "phone_number = ?, shipping_address = ?, is_manager = ? WHERE user_id = ?;");
            pst.setString(1, user.getEmail());
            pst.setBytes(2, user.getEncryptedPassword());
            pst.setString(3, user.getfName());
            pst.setString(4, user.getlName());
            pst.setString(5, user.getPhoneNumber());
            pst.setString(6, user.getShippingAddress());
            pst.setString(7, user.isManager() ? "1" : "0");
            pst.setString(8, user.getUserID());

            if (pst.executeUpdate() == 1) {
                return true;
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean promoteUser(String email) {
        String query = "update user set is_manager = 1 where email = '" + email + "';";
        try {
            ModelManager.getInstance().executeQuery(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
