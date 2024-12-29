package org.karthik.service;

import org.karthik.beans.User;
import org.karthik.database.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class BankingDao {

    static Scanner scanner;

    // 1) register using account number and dob :

    public void register(String acntNo, String dob) throws SQLException {

        String query = "select * from accountdetails where acntno = ? and dob = ?";
        String insertQuery = "insert into accountdetails values (?,?,?,?,?,?,?,?,?)";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1,acntNo);
        ps.setString(2,dob);

        ResultSet rs = ps.executeQuery();
        ResultSetMetaData resultSetMetaData = ps.getMetaData();


        if(rs.next()){

            System.out.println("account found");

            System.out.println("Enter your username : ");
            String username = scanner.next();
            System.out.println("Enter your password : ");
            String pword = scanner.next();


            User user = new User();
            user.setUserId(rs.getString(1));
            user.setUserId(rs.getString(2));
            user.setUserId(rs.getString(3));
            user.setUserId(rs.getString(4));
            user.setUserId(rs.getString(5));
            user.setUserId(rs.getString(6));
            user.setUserId(rs.getString(7));
            user.setUserId(rs.getString(8));
            user.setUserId(rs.getString(9));

            PreparedStatement psI = con.prepareStatement(insertQuery);

            psI.executeUpdate();





        }
        else {
            System.out.println("Enter a valid account number and dob ");
        }



    }

}
