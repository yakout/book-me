package model;

import beans.User;
import services.PasswordEncryptionService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
                " SELECT password, salt from user where email = "
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
                " SELECT * FROM USER WHERE email = " + "'" + user.getEmail() + "'" + ";");

        try {
            if (!rs.isBeforeFirst()) {
                // email is not registered.
                ModelManager.getInstance().executeQuery(
                        "INSERT INTO USER VALUES ("
                                + "UUID()" + ","
                                + "'" + user.getEmail() + "',"
                                + "'" + user.getfName() + "',"
                                + "'" + user.getlName() + "',"
                                + "'" + new String(user.getEncryptedPassword()) + "',"
                                + "'" + new String(user.getSalt()) + "',"
                                + "'" + user.getPhoneNumber() + "',"
                                + "'" + user.getShippingAddress() + "',"
                                + (user.isManager() ? "'0'" : "'1'")
                                + ");"
                );
                status = true;
            } else {
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
