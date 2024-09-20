package vn.atstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBConnectMySQL {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "251108";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ltw";

    public static Connection getDatabaseConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Database Driver not found.", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the SQLException
        }
    }

    // Test chương trình
    public static void main(String[] args) {
        try {
            System.out.println(DBConnectMySQL.getDatabaseConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra mật khẩu
    public static boolean matchesPassword(String inputPassword, String storedPassword) {
        String encodedInputPassword = encodePassword(inputPassword);
        return encodedInputPassword.equals(storedPassword);
    }

    // Phương thức mã hóa mật khẩu
    public static String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
