package bank_management.db;

import Models.Account;
import Models.IAccount;
import Models.Transaction;
import Models.TransactionRequest;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDbUtil extends DBUtil{
    public void insertTransaction(TransactionRequest request) {
        String query = "INSERT INTO Transactions (UserName,Receiver,Amount,`Date`) VALUES (?,?,?,CURRENT_TIMESTAMP)";

        try{
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, request.getUserName());
            statement.setString(2,request.getReceiver());
            statement.setDouble(3, request.getTransactionAmount());
            statement.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException("fail to inset account",e);
        }finally {
            closeConnection();
        }
    }

    public List<Transaction> selectTransactions(Account uname){
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT T.Account_No, T.Receiver, T.Amount, T.Date\n" +
                "FROM Transactions AS T\n" +
                "INNER JOIN Accounts AS A ON T.Account_No = A.Account_No\n" +
                "WHERE A.UserName = ?;";
        ResultSet resultSet;

        try {
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, String.valueOf(uname));
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                int accountNo = resultSet.getInt("Account_No");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                Date date = resultSet.getDate("Date");
                Transaction transaction = new Transaction(accountNo,receiver,amount,date);
                transactions.add(transaction);
            }
        }catch (SQLException ex){
            throw new RuntimeException("fail to get transactions",ex);
        }finally {
            closeConnection();
        }
        return transactions;
    }

}
