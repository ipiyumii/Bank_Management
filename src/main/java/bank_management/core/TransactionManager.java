package bank_management.core;

import Models.Account;
import Models.TransactionRequest;
import bank_management.db.AccountDbUtil;

import java.util.LinkedList;

public class TransactionManager {
    private Account user;
    public TransactionManager(Account user){
        this.user = user;
    }
    private void getTransactionHistory(){
        LinkedList<TransactionRequest> transactionHistory = Store.approvedTransactions;

        while(!transactionHistory.isEmpty()){
            TransactionRequest request = transactionHistory.poll();
            int account_no = request.getAccountNo();
            double transaction_amount = request.getTransactionAmount();
        }
    }

//    public void displayTransactionRequest(){
//        accountNoTextField.setText(String.valueOf(accountNo));
//        transactionAmountTextField.setText(String.valueOf(transactionAmount));
//        }

//    public void approveTansactionLogic(){
//        // Handle approval logic here
//        TransactionRequest approvedRequest = pendingTransactions.poll();
//
//        if (approvedRequest != null) {
//            DBUtil dbUtil = new DBUtil();
//            double accountBalance = dbUtil.getAccountBalance(accountNo);
//
//            if(accountBalance > transactionAmount ){
//                // Add the approved request to the linked list(trans history)
//                Store.approvedTransactions.add(request);
//
//                //insert to database
//                dbUtil.insertTransaction( accountNo, transactionAmount);
//
//                // For a completed transaction add to the stack
//                recentActivity transactionActivity = new recentActivity("Transaction", new Date());
//                Store.recentActivitiesStack.push(transactionActivity);
//            }
//            else{
//                // do not aprove message account balance in not sufficient
//            }
//        }
//    }
    private TransactionRequest approvePendingTransaction(){
        return Store.pendingTransactions.poll();
    }
    public void addPendingTransactions(String senderUserName, int transaction_amount, String receiverAccNo) throws Exception {
//        if (new AccountManager().getAccountBalance(account_No) < transaction_amount){
//            throw new Exception("insufficient balance");
//        }
        if(new AccountDbUtil(user).getAccBalance(senderUserName) < transaction_amount){
            throw new Exception("insufficient balance");
        }
        TransactionRequest request = new TransactionRequest(senderUserName, transaction_amount,receiverAccNo);
        Store.pendingTransactions.add(request);
    }

    private void addApprovedTransaction(){

    }

    public TransactionRequest getTransactionRequests() {
        if (!Store.pendingTransactions.isEmpty()) {
            return Store.pendingTransactions.poll();
        }else {
            return null;
        }
    }
}




