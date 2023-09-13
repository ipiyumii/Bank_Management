package bank_management.db;

import Models.Account;
import Models.IAccount;
import Models.RecentActivity;
import Models.User;
import bank_management.UI.admin.AdminDashboardFrame;
import bank_management.UI.user.UserDashboardFrame;
import bank_management.core.Store;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountDbUtil extends DBUtil{
    private Account user;
    public AccountDbUtil(Account user){
        this.user = user;
    }

        public void insertAccounts(String firstName, String lastName, String userName, String password, int account_no, double balance) {
            String query = "INSERT INTO Accounts\n"
                    + "(Account_No,FirstName,LastName,UserName,Password,Account_Balance)\n"
                    + "VALUES(?,?,?,?,?,?);";
            try {
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setInt(1, account_no);
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setString(4, userName);
                statement.setString(5, password);
                statement.setDouble(6,balance);
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("fail to inset account",e);
            }finally {
                closeConnection();

            }
       }
        public List<Account> selectAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM Accounts";
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String userName = resultSet.getString("UserName");
                int accountNo = resultSet.getInt("Account_No");
                double accountBalance = resultSet.getDouble("Account_Balance");
                Account account = new Account(firstName,lastName,userName,accountNo,accountBalance);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException("fail to get Account data");
        } finally {
           closeConnection();
        }
        return accounts;
    }

     public List<IAccount> verifyAccount(String userName, String password) {
       List<IAccount> accounts = new ArrayList<>();
       String query = "SELECT * FROM Accounts WHERE UserName=? AND Password=?";
       ResultSet resultSet = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userName);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

             if (resultSet.next()) {
                if (userName.startsWith("AD")) {
                    var adminDashboard = new AdminDashboardFrame(user);
                    adminDashboard.setLocationRelativeTo(null);
                    adminDashboard.setVisible(true);
                } else {
                    var userDashboard = new UserDashboardFrame(user);
                    userDashboard.setLocationRelativeTo(null);
                    userDashboard.setVisible(true);

                    //add login activity to the recent
                    RecentActivity loginActivity = new RecentActivity("Login", new Date());
                    Store.recentActivitiesStack.push(loginActivity);


                }
             }else {
                 JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
             }
        } catch (SQLException e) {
            throw new RuntimeException("fail to get Account login info");
        } finally {
            closeConnection();
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
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int accNo = resultSet.getInt("Account_No");
                String userName = resultSet.getString("UserName");
                double account_balance = resultSet.getDouble("Account_balance");

                User user = new User(accNo,userName,account_balance);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("fail to get account data n balance");
        } finally {
            closeConnection();
        }
        return users;
    }

        public double selectAccountBalance(String userName){
            String query = "SELECT\n" +
                    "    ab.Account_balance\n" +
                    "FROM\n" +
                    "    Accounts a\n" +
                    "JOIN\n" +
                    "    Account_balance ab ON a.UserName = ab.UserName\n" +
                    "WHERE\n" +
                    "    a.UserName = ?; ";

            ResultSet resultSet = null;
            double accountBalance = 0.0;

            try{
                PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1,  userName);
                resultSet = statement.executeQuery();

                 if (resultSet.next()) {
                // Retrieve the Account_balance column as a double
                    accountBalance = resultSet.getDouble("Account_balance");
            }

            }catch (SQLException e) {
                  throw new RuntimeException("fail to get account balance");
              } finally {
                  closeConnection();
                  }

            return accountBalance;

    }

    public double getAccBalance(String senderUserName){
            String query = "SELECT Account_Balance FROM Accounts WHERE userName = ?";
            ResultSet resultSet;
            int accountBalance = 0;
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, senderUserName);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                accountBalance = resultSet.getInt("Account_balance");
                return accountBalance;
            }
        }catch (SQLException e) {
            throw new RuntimeException("fail to get account balance");
        } finally {
            closeConnection();
        }

        return accountBalance;
    }

    public int getAccountNumber(String uName){
            String query = "SELECT Account_No FROM Accounts WHERE UserName = ?;\n";
            ResultSet resultSet;
            int accountNo = 0;
            try {
                PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1,uName);
                resultSet = statement.executeQuery();

                if(resultSet.next()){
                    accountNo = resultSet.getInt("Account_No");
                }
            }catch (SQLException e){
                throw new RuntimeException("fail to get account no");
            }finally {
                closeConnection();
            }
            return accountNo;
    }
}
