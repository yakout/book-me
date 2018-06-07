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
 *
 * Created by ahmed yakout on 5/4/18.
 *
 * Responsible for:
 *      1. register user.
 *      2. login user.
 *      3.
 *
 */
public class UserDAO {
    /**
     *
     */
    public static boolean login(String email, String pass) {
        boolean status = false;
        PasswordEncryptionService pw = new PasswordEncryptionService();
        ResultSet rs = ModelManager.getInstance().executeQuery(
                " SELECT password, salt from User where email = "
                        + "'" + email + "'"
                        + ";"
        );
        
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
        PasswordEncryptionService pw = new PasswordEncryptionService();

        user.setManager(false);

        byte[] encryptedPwd = null;
        byte[] salt = null;

        try {
            salt = pw.generateSalt();
            encryptedPwd = pw.getEncryptedPassword(user.getPassword(), salt);
            user.setSalt(salt);
            user.setEncryptedPassword(encryptedPwd);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        // check if the email is already registered.
        ResultSet rs = ModelManager.getInstance().executeQuery(
                " SELECT * FROM User WHERE email = " + "'" + user.getEmail() + "'" + ";");

        try {
            if (!rs.isBeforeFirst()) {
                // email is not registered.
                 PreparedStatement pst = ModelManager.getInstance().getConnection().prepareStatement(
                        "INSERT INTO USER VALUES (UUID()," + "? , ? , ? , ? , ? , ?, ?, ?)");

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

//                ModelManager.getInstance().executeQuery(
//                        "INSERT INTO USER VALUES ("
//                                + "UUID()" + ","
//                                + "'" + user.getEmail() + "',"
//                                + "'" + new String(user.getEncryptedPassword()) + "',"
//                                + "'" + new String(user.getSalt()) + "',"
//                                + "'" + user.getfName() + "',"
//                                + "'" + user.getlName() + "',"
//                                + "'" + user.getPhoneNumber() + "',"
//                                + "'" + user.getShippingAddress() + "',"
//                                + (user.isManager() ? "'1'" : "'0'")
//                                + ");"
//                );
//                status = true;
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
     *  This should return the user associated with this email
     *  */
    public static User getUser(String email){
        //TODO
        return null;
    }

    public static boolean updateUser(User user){
        //TODO
        return false;
    }
}
