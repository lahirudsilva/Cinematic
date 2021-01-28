/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lahiru De silva
 */
public class Database_Connection {

    private static Database_Connection databaseObject;
    //initializing a private static Database object

    public static Database_Connection getInstance() {
        //static method which returns a Database object
        if (databaseObject == null) {
            databaseObject = new Database_Connection();
        }
        return databaseObject;
        //return a database object
    }

    public static Connection getConnection() {
        try {
            String  URL = "jdbc:mysql://localhost:3306/cinematic?useSSL=false"; //databse url
            String username = "root"; //database username
            String password = null; //databse password
            
           Class.forName("com.mysql.jdbc.Driver"); //setting the classname of the jdbc driver
            Connection con = DriverManager.getConnection(URL, username, password);
            return con;//initializing and returning a Connection object to the Database = "cinematic"
        } catch (Exception e) {
            System.out.println("Database connection error " + e.getMessage());
            return null;
        }
    }
}
