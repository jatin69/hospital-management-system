/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.MyDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jatin
 */
public class DBconnect {

    private static final String URL = "jdbc:mysql://localhost/hospital";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection conn;

    public static Connection connectToDatabase() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return conn;
    }
}
