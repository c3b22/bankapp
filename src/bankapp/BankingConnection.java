package bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankingConnection {
    public static Connection connection() {
        String URL = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "Prem1613#";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BankingConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
