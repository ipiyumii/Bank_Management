
package com.bankManagement.db;

import Models.Accounts;
import Models.IAccount;
import Models.TransactionRequest;
import Models.User;
import Models.recentActivity;
import com.mycompany.bank_management.AdminDashboard;
import  com.mycompany.bank_management.Constants;
import com.mycompany.bank_management.UserDashboard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class DBUtil {

    private Connection connection;
    private PreparedStatement statement;

    public DBUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String db_url = "jdbc:mysql://localhost:3306/Bank_Management";
            String db_useR = "root";
            String db_pwd = "root";

            this.connection = DriverManager.getConnection(db_url, db_useR, db_pwd);
            System.out.println(this.connection);
            //Connection.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(UserDashboard, "Connection failed: " + ex.getMessage());
           System.out.println("Connection failed:"+ex.getMessage());
        }
    }

    public void insertAccounts(String firstName, String lastName, String userName, String password, int account_no) {
        String query = "INSERT INTO Accounts\n"
                + "(FirstName,LastName,UserName,Password,Account_No)\n"
                + "VALUES(?,?,?,?,?);";
        try {
            statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, userName);
            statement.setString(4, password);
            statement.setInt(5, account_no);
            
            statement.executeUpdate();
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<IAccount> selectAllAccounts() {
        List<IAccount> accounts = new ArrayList<>();
        String query = "SELECT * Accounts";// select all from 
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int accID = resultSet.getInt("Acc_ID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String userName = resultSet.getString("UserName");
                String password = resultSet.getString("Password");
                Accounts account = new Accounts(accID,firstName,lastName,userName,password); //constructor of the Accounts class
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return accounts;
    }
    
     public List<IAccount> selectAccount(String userName, String password) {
       List<IAccount> accounts = new ArrayList<>();
       String query = "SELECT * FROM Accounts WHERE UserName=? AND Password=?";
       ResultSet resultSet = null;
       
        try {
            statement = connection.prepareStatement(query);  
            statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userName);
            statement.setString(2, password);
             
            resultSet = statement.executeQuery();
            
            
            
             if (resultSet.next()) {
                if (userName.startsWith("AD")) {
                    // User is an admin, show the adminDashboard
                    var adminDashboard = new AdminDashboard();
                    adminDashboard.setTitle("Admin Dashboard");
                    adminDashboard.setLocationRelativeTo(null);
                    adminDashboard.setVisible(true);
                } else {
                    // User is not an admin, show the userDashboard
                    var userDashboard = new UserDashboard();
                    userDashboard.setTitle("User Dashboard");
                    userDashboard.setLocationRelativeTo(null);
                    userDashboard.setVisible(true);
                    
                    //add login activity to the recent
                    recentActivity loginActivity = new recentActivity("Login", new Date());
                    Constants.recentActivitiesStack.push(loginActivity);
                    
//                    UserActivity logoutActivity = new UserActivity("Logout", new Date());
//                    recentActivitiesStack.push(logoutActivity);
            }
                
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password");
        }

        connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return accounts;    
     }

    public List<User> getAccountData() {
        List<User> users = new ArrayList<>();
        String query = "SELECT\n" +
                            "    Accounts.UserName,\n" +
                            "    Accounts.Account_No,\n" +
                            "    Account_balance.Account_balance\n" +
                            "FROM\n" +
                            "    Accounts\n" +
                            "JOIN\n" +
                            "    Account_balance ON Accounts.UserName = Account_balance.UserName;";     //join
        
        ResultSet resultSet = null;
        
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int accNo = resultSet.getInt("Account_No");
                String userName = resultSet.getString("UserName");
                Double account_balance = resultSet.getDouble("Account_balance");

                User user = new User(accNo,userName,account_balance);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }
    
    public double getAccountBalance(int acc_no){
        String query = "SELECT\n" +
                "    ab.Account_balance\n" +
                "FROM\n" +
                "    Accounts a\n" +
                "JOIN\n" +
                "    Account_balance ab ON a.UserName = ab.UserName\n" +
                "WHERE\n" +
                "    a.Account_No = ?; ";
        
        ResultSet resultSet = null;
        double accountBalance = 0.0;
        
        try{
            statement = connection.prepareStatement(query);
            //statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,  acc_no);
            resultSet = statement.executeQuery();
            
             if (resultSet.next()) {
            // Retrieve the Account_balance column as a double
            accountBalance = resultSet.getDouble("Account_balance");
        }

        }catch (SQLException e) {
        e.printStackTrace();
    } finally {
    }
    
    return accountBalance;
        
    }

     public void insertTransaction(int accountNo, double transactionAmount) {
        String query = "INSERT INTO Transactions (`Date`,Account_No, Amount, Transaction_Date) VALUES (CURRENT_TIMESTAMP, ?, ?)";
        
        try{
            statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, accountNo);
            statement.setDouble(2, transactionAmount);
            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

;

   

}

