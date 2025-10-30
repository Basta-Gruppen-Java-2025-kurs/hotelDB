import Helpers.TextMenu;
import database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTestData {
    public static void createDataIfNotExists() {
        final String[] QUERIES =  new String[]
                { """
                  INSERT INTO Customers (name, city, email) VALUES
                  ('John Romero', 'Colorado Springs', 'jromero@id.com'),
                  ('Rick Rubin', 'New York', 'rick@hose.com'),
                  ('George Fisher', 'Baltimore', 'cg@corps.com'),
                  ('Tatiana Shmayluk', 'Donetsk', 'tatiana@jinjer.com'),
                  ('Eva Korman', 'Stockbridge', 'eva@rolo.com'),
                  ('Kyree Vibrant', 'Toronto', 'kyree@hpf.com');
                  """, """
                  INSERT INTO Rooms (type, price, number, available) VALUES
                  ('Single', 800, 101, TRUE),
                  ('Single', 800, 102, TRUE),
                  ('Single', 800, 103, TRUE),
                  ('Single', 800, 104, TRUE),
                  ('Double', 1400, 105, TRUE),
                  ('Double', 1400, 106, TRUE),
                  ('Double', 1450, 107, TRUE),
                  ('Suite', 2000, 108, FALSE),
                  ('Suite', 2000, 109, TRUE),
                  ('Single', 800, 201, TRUE),
                  ('Single', 800, 202, FALSE),
                  ('Single', 800, 203, TRUE),
                  ('Single', 800, 204, TRUE),
                  ('Double', 1400, 205, TRUE),
                  ('Double', 1400, 206, TRUE),
                  ('Double', 1450, 207, TRUE),
                  ('Suite', 2000, 208, TRUE),
                  ('Single', 800, 301, TRUE),
                  ('Double', 800, 302, TRUE),
                  ('Double', 800, 303, TRUE),
                  ('Double', 800, 304, TRUE),
                  ('Suite', 1400, 305, TRUE),
                  ('Suite', 1400, 306, TRUE),
                  ('Suite', 1450, 307, TRUE);
                  """, """
                  INSERT INTO Bookings (room_id, customer_id) VALUES
                  (8, 3),
                  (11, 5);
                  """};
        if (testDataExists()) {
            System.out.println("Test data already exists");
            return;
        }
        for (String query: QUERIES) {
            try (Connection conn = Database.getConnection();
                 Statement statement = conn.createStatement()) {
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println("Error creating test data: " + e);
            }
        }
        System.out.println("Test data created.");
    }

    public static void createDatabaseIfNotExists() {
        final String[] QUERIES =  new String[]
                { "CREATE DATABASE IF NOT EXISTS hotelDB;",
                  "USE hotelDB;",
                  "CREATE TABLE IF NOT EXISTS Rooms (room_id INT AUTO_INCREMENT PRIMARY KEY, type VARCHAR(50), price DOUBLE, number INT, available BOOL);",
                  "CREATE TABLE IF NOT EXISTS Customers (customer_id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), city VARCHAR(50), email VARCHAR(100));",
                  """
                  CREATE TABLE IF NOT EXISTS Bookings (
                  booking_id INT AUTO_INCREMENT PRIMARY KEY,
                  room_id INT NOT NULL,
                  customer_id INT NOT NULL,
                  start_date DATE,
                  end_date DATE,
                  FOREIGN KEY (room_id) REFERENCES Rooms (room_id),
                  FOREIGN KEY (customer_id) REFERENCES Customers (customer_id));
                  """};
        for (String query: QUERIES) {
            try (Connection conn = Database.getConnection();
                 Statement statement = conn.createStatement()) {
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println("Error creating database: " + e);
            }
        }
    }

    public static boolean testDataExists() {
        final String[] CHECK_QUERIES = new String[] {
                "SELECT COUNT(*) howMany FROM Customers; -- 06",
                "SELECT COUNT(*) howMany FROM Rooms; -- 24"
        };
        createDatabaseIfNotExists();
        try(Connection conn = Database.getConnection()) {
            for(String query: CHECK_QUERIES) {
                try (Statement s = conn.createStatement()) {
                    int checkNumber = Integer.parseInt(query.substring(query.length()-2));
                    ResultSet rs = s.executeQuery(query);
                    if (!rs.next() || rs.getInt("howMany") < checkNumber) {
                        return false;
                    }
                } catch (SQLException e) {
                    System.out.println("Error checking test data: " + e);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting database connection: " + e);
        }
        return true;
    }

    public static void dropDatabase() {
        final String[] QUERIES = new String[] { "DROP TABLE Bookings;", "DROP TABLE Customers;", "DROP TABLE Rooms;" };
        if (!TextMenu.yesNoQuestion("Are you sure you want to drop the database? There's no undo.")) {
            return;
        }
        try ( Connection conn = Database.getConnection() ) {
            for (String query: QUERIES) {
                try (Statement statement = conn.createStatement()) {
                    statement.execute(query);
                } catch (SQLException e) {
                    System.out.println("Error dropping a table: " + e);
                }
            }
            System.out.println("Database dropped");
        } catch (SQLException e) {
            System.out.println("Error getting connection: " + e);
        }
    }
}
