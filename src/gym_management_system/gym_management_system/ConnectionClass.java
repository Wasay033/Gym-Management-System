package gym_management_system;
import java.sql.*;

public class ConnectionClass {
    Connection con;
    Statement stm;

    ConnectionClass() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gym", "root", "admin");
            stm = con.createStatement();
            System.out.println("Connection established successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ConnectionClass();
        // Keep the program running to view error messages
        try {
            Thread.sleep(5000); // Sleep for 5 seconds to allow viewing the console output
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
