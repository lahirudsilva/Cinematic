/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lahiru De silva
 */
public class DAO {
    private static Connection connection = Database_Connection.getConnection();

    public DAO() {

    }

    public boolean checkUsers(String email) {
        boolean st = false;
        try{
            PreparedStatement ps = connection.prepareStatement ("select * from user where email=?");
         
         ps.setString(1, email);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return st;
    }
    
    /*register*/
    public boolean Register(User user) {
         boolean st = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(email,firstName,lastName,password,dob,phNumber,role) values( ?, ?, ?, ?, ?, ?,?)");
            //Parameters start with 1
            preparedStatement.setString(1, user.email);
            preparedStatement.setString(2, user.firstName);
            preparedStatement.setString(3, user.lastName);
            preparedStatement.setString(4, user.password);
            preparedStatement.setString(5, user.dob);
            preparedStatement.setString(6, user.phonenumber);
            preparedStatement.setString(7, user.role);
            preparedStatement.executeUpdate();
            
            
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    public String authenticateUser(User user) {
         try {

            /* Statement to be executed to get all users */
            PreparedStatement statement = connection.prepareStatement("select email, password, role from user");
            ResultSet results = statement.executeQuery();

            while (results.next()) {

                //From each result obtained a user object is created with the credentials, this is then cross referenced with user entered credentials (stored in user object recieved in arguments)
                // Once found type of user is returned
                User selectedUser = new User(results.getString("email"), results.getString("password"), results.getString("role"));

                // If the user exists on the database
                if (user.getEmail().equals(selectedUser.getEmail()) && user.getPassword().equals(selectedUser.getPassword())) {

                    //According to the type of user return the user type as string
                    if (selectedUser.getRole().equals("admin")) {
                        return "admin";
                    } else if (selectedUser.getRole().equals("member")) {
                        return "member";
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        // If user is not found return "invalid"
        return "invalid";
    }
    
    
    
}
