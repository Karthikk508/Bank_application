package org.karthik.service;

import org.karthik.database.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class BankingDao {

    // 1) register using account number and dob :

    public void register(String acntNo, String dob) throws SQLException {

        String query = "select * from accountdetails where acntno = ? and dob = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1,acntNo);
        ps.setString(2,dob);


        ResultSet rs = ps.executeQuery();
        ResultSetMetaData resultSetMetaData = ps.getMetaData();


        if(rs != null){

            System.out.println("account found");

            while (rs.next()){

                for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
                    System.out.println(rs.getString(i));
                }
            }


        }
        else {
            System.out.println("Enter a valid account number and dob");
        }



    }

}
