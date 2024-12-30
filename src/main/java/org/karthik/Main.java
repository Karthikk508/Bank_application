package org.karthik;

import org.karthik.service.BankingDao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {

    static Scanner scanner;

    public static void main(String[] args) throws SQLException {

        initiate();

    }
    public static void initiate() throws SQLException {

        scanner = new Scanner(System.in);

        while(true){

            System.out.println("""
                    Welcome to KBank :
      
                    1) Register
                    2) Login
                    3) Exit
                    """);

            System.out.println("Enter your option between 1 and 3 : ");

            int next = scanner.nextInt();
            boolean flag = true;

            switch (next){
                case 1 -> register();
                case 2 -> login();
                case 3 -> flag = false;
            }
            if(!flag){
                break;
            }
        }


    }

    private static void login() {
    }

    public static void register() throws SQLException {

        System.out.println("Enter account number : ");
        String acntNo = scanner.next();
        System.out.println("Enter date of birth in 'yyyy-MM-dd' format");
        String dob = scanner.next();


        BankingDao obj = new BankingDao();
        obj.register(acntNo,dob);
    }
}
