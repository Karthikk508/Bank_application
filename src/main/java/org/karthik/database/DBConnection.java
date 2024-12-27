package org.karthik.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    private static final Connection connection;

   static {

       String url = "jdbc:mysql://localhost:3306/bankapplicationdata";
       String username = "root";
       String password = "Kannan@26";

       try {
           connection = DriverManager.getConnection(url,username,password);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


   }

    public static Connection getConnection() throws SQLException {
       
        if(connection == null){
            System.out.println("Not connected to database ");
            throw new SQLException();
        }
        return connection;
    }

}
