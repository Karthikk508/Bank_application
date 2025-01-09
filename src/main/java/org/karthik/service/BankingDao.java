package org.karthik.service;

import org.karthik.beans.User;
import org.karthik.database.DBConnection;
import java.sql.*;
import java.util.Objects;
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

        ps.setString(1, acntNo);
        ps.setString(2, dob);

        ResultSet rs = ps.executeQuery();
        // ResultSetMetaData resultSetMetaData = ps.getMetaData();


        if (rs.next()) {

            scanner = new Scanner(System.in);

            System.out.println("account found");

            if (rs.getString(2) != null) {

                System.out.println("Account already registered");
            } else {

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

                ps1.setString(1, user.getUserId());
                ps1.setString(2, user.getAcntId());
                ps1.setString(3, user.getUsername());
                ps1.setString(4, user.getPassword());
                ps1.setString(5, user.getFirstName());
                ps1.setString(6, user.getLastName());
                ps1.setString(7, user.getContact());
                ps1.setString(8, user.getEmail());
                ps1.setString(9, user.getAddress());

                ps1.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement(updateUserId);
                ps2.setString(1, user.getUserId());
                ps2.setString(2, acntNo);


                ps2.executeUpdate();

            }
        } else {
            System.out.println("Enter a valid account number and dob ");
        }
    }

//----------------------------------------------------------------------------------------------------------------------


    public void login(String userName, String password) throws SQLException {

        String query = "SELECT * FROM userdetails WHERE username = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, userName);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            String username = rs.getString(3);
            String pword = rs.getString(4);
            String accountId = rs.getString("acntId");


            if (userName.equals(username) && password.equals(pword)) {

                while (true) {

                    System.out.println(
                            """
                                    1) view balance
                                    2) deposits
                                    3) transfer to bank account
                                    4) quick transfer
                                    5) exit"""
                    );

                    scanner = new Scanner(System.in);

                    boolean flag = false;

                    int n = scanner.nextInt();

                    switch (n) {

                        case 1 -> viewBalance(accountId);
                        case 2 -> deposits();
                        case 3 -> transfer(accountId);
                        case 4 -> quickTransfer();
                        case 5 -> flag = true;
                    }

                    if (flag) break;
                }
            } else {
                System.out.println("Incorrect cerdentials");
            }

        } else {
            System.out.println("User not registered");
        }

    }
// ---------------------------------------------------------------------------------------------------------------------


    private void viewBalance(String acntId) {

        String balance = getBalanceUsingAcntId(acntId);

        if(balance != null){
            System.out.println("Your account balance is : " + balance);
        }
        else{
            System.out.println("Error in fetching account balance");
        }

        scanner = new Scanner(System.in);
        System.out.println("Enter 1 to exit");
        int n = scanner.nextInt();
        if (n == 1) return;
        viewBalance(acntId);
    }

    //------------------------------------------------------------------------------------------------------------------

    private void transfer(String acntId) {

        String receiverAcNo = ""; long phNo; boolean flag = true;

        while(flag){

            System.out.println("""
                
                1) Transfer using accountNumber
                2) Transfer using phoneNumber
                3) Cancel transaction \n""");
            
            int choice = scanner.nextInt();
            
            if(choice == 1){
                
                System.out.println("Enter receiver account number : ");
                receiverAcNo = scanner.next();
                
                if(!validate(receiverAcNo)){
                    System.err.println("Receiver account number not found !!!!!!!");
                    System.out.println("Re-enter account number : ");
                    receiverAcNo = scanner.next();

                    if(!validate(receiverAcNo)){
                        System.out.println("Receiver account number not found !!!!!!!");
                        System.out.println("Transaction cancelled");
                        return;
                    }
                }
                flag = false;
            }
            else if(choice == 2){

                System.out.println("Enter receiver phone number : ");
                phNo = scanner.nextLong();

                if(!validate(phNo)){
                    System.err.println("Receiver phone number not found !!!!!!!");
                    System.out.println("Re-enter phone number : ");
                    phNo = scanner.nextLong();

                    if(!validate(phNo)){
                        System.out.println("Receiver phone number not found !!!!!!!");
                        System.out.println("Transaction cancelled");
                        return;
                    }
                }
                else{

                    try{

                        String query = "select * from accountdetails where ContactNumber = "+phNo+";";

                        Connection con = DBConnection.getConnection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        if(!rs.next()){
                            System.out.println("Error...");
                            return;
                        }

                        receiverAcNo = rs.getString("AcntNo");

                        if(receiverAcNo == null) System.out.println("Error in fetching account number");

                        flag = false;

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
            else {
                System.out.println("Bye...");
                return;
            }
        }

        System.out.println("Enter the amount you want to transfer : ");
        long amount = scanner.nextLong();
        String senderAccountBalance = getBalanceUsingAcntId(acntId);
        String senderAccountNo = getAccountNumber(acntId);
        long balance;

        if(senderAccountBalance != null) {
            balance = Long.parseLong(senderAccountBalance);
        }
        else {
            System.err.println("Error in fetching account balance");
            return;
        }

        while(amount > balance){

            System.out.println("""
                    Insufficient balance
                    
                    1) Re-enter amount
                    2) exit""");

            int choice = scanner.nextInt();

            if(choice == 1){
                amount = scanner.nextLong();
            }
            else if(choice == 2){
                return;
            }
            else{
                System.out.println("Enter a valid input :( ");
            }
        }

        // Updating the balance ;

        balance -= amount;

        try{
            String query = "update accountdetails set acBalance = '"+balance+"' where AcntNo = "+senderAccountNo+";";
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            int check = st.executeUpdate(query);

            if(check != 1) System.out.println("Not updated in sending side");

            long receiver = Long.parseLong(Objects.requireNonNull(getBalanceUsingAcntNo(receiverAcNo)));
            receiver += amount;
            query = "update accountdetails set acBalance = "+receiver+" where AcntNo = "+receiverAcNo+";";
            st.executeUpdate(query);

            con.close();

            System.out.println("Transaction completed !! :)");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    private String getBalanceUsingAcntNo(String receiverAcNo) {

        String query = "SELECT * FROM accountdetails WHERE AcntNo = ?";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);


            ps.setString(1, receiverAcNo);

            try {

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getString("acBalance");
                } else {
                    System.out.println("Account not found : Error in getBalance.");
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        catch (SQLException e) {
            System.out.println("Error in fetching data from accountdetails table " + e.getMessage());
        }

        return null;

    }

    private String getAccountNumber(String acntId) {

        String query = "SELECT * FROM accountdetails WHERE AcntId = ?";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);


            ps.setString(1, acntId);

            try {

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getString("AcntNo");
                } else {
                    System.out.println("Account not found : Error in getBalance.");
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        catch (SQLException e) {
            System.out.println("Error in fetching data from accountdetails table " + e.getMessage());
        }

        return null;
    }

    private String getBalanceUsingAcntId (String acntId){


        String query = "SELECT * FROM accountdetails WHERE AcntId = ?";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);


            ps.setString(1, acntId);

            try {

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getString("acBalance");
                } else {
                    System.out.println("Account not found : Error in getBalance.");
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        catch (SQLException e) {
            System.out.println("Error in fetching data from accountdetails table " + e.getMessage());
        }

        return null;

    }

    private boolean validate(String receiverAcNo) {

        String query = "select * from accountdetails where AcntNo = "+receiverAcNo+";";

        try {
            Connection con = DBConnection.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validate(long phNo) {

        String query = "select * from accountdetails where ContactNumber = '"+phNo+"';";

        try {
            Connection con = DBConnection.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void quickTransfer() {

        System.out.println("quickTransfer");
    }



    private void deposits() {
    }


}
