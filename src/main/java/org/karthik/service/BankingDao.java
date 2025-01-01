package org.karthik.service;

import org.karthik.beans.User;
import org.karthik.database.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class BankingDao {

    static Scanner scanner;
    static int count = 1;

    // 1) register using account number and dob :

    public void register(String acntNo, String dob) throws SQLException {

        String query = "SELECT * FROM accountdetails WHERE acntno = ? and dob = ?";
        String insertQuery = "INSERT INTO userdetails VALUES (?,?,?,?,?,?,?,?,?)";
        String updateUserId = "UPDATE accountdetails SET userid = ? where acntno = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1,acntNo);
        ps.setString(2,dob);

        ResultSet rs = ps.executeQuery();
        ResultSetMetaData resultSetMetaData = ps.getMetaData();


        if(rs.next()){

            scanner = new Scanner(System.in);

            System.out.println("account found");

            if(rs.getString(2) != null){

                System.out.println("Account already registered");
            }
            else{

                System.out.println("Enter your username : ");
                String username = scanner.next();
                System.out.println("Enter your password : ");
                String pword = scanner.next();


                User user = new User();

                user.setAcntId(rs.getString(1));
                user.setUserId(String.valueOf(count++));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setContact(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setUsername(username);
                user.setPassword(pword);


                PreparedStatement ps1 = con.prepareStatement(insertQuery);

                ps1.setString(1,user.getUserId());
                ps1.setString(2,user.getAcntId());
                ps1.setString(3,user.getUsername());
                ps1.setString(4,user.getPassword());
                ps1.setString(5,user.getFirstName());
                ps1.setString(6,user.getLastName());
                ps1.setString(7,user.getContact());
                ps1.setString(8,user.getEmail());
                ps1.setString(9,user.getAddress());

                ps1.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement(updateUserId);
                ps2.setString(1,user.getUserId());
                ps2.setString(2,acntNo);


                ps2.executeUpdate();

            }
        }
        else {
            System.out.println("Enter a valid account number and dob ");
        }
    }

    public void login(String userName, String password) throws SQLException {

        String query = "SELECT * FROM userdetails WHERE username = ? and pword = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1,userName);
        ps.setString(2,password);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            String username = rs.getString(3);
            String pword = rs.getString(4);


            if(userName.equals(username) && password.equals(pword)){
                System.out.println("Login successful");
            }
            else{
                System.out.println("Incorrect cerdentials");
            }

        }
        else{
            System.out.println("User not registered");
        }

    }
}
