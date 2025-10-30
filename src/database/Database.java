package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static final String URL_ROOT = "jdbc:mysql://localhost:3306/";
    public static final String USER = System.getenv("MYSQL_USER");
    public static final String PASSWORD = System.getenv("MYSQL_PASSWORD");
    public static final String DB_NAME = "hotelDB";

    static {
        try( Connection conn = DriverManager.getConnection(URL_ROOT, USER, PASSWORD);
             Statement statement = conn.createStatement() ) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Database \033[1m" + DB_NAME + "\033[0m ready");
        } catch (SQLException e) {
            System.out.println("Failed to create database \033[1m" + DB_NAME + "\033[0m : " + e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url_db = URL_ROOT + DB_NAME;
        return DriverManager.getConnection(url_db,USER, PASSWORD);
    }
}
