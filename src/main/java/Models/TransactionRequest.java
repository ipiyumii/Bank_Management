
package Models;

public class TransactionRequest {
    private int accountNo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;
    private String receiver;
    private double transactionAmount;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public TransactionRequest(String userName, int transactionAmount,String receiver) {
        this.userName = userName;
        this.transactionAmount = transactionAmount;
        this.receiver = receiver;
    }
    public TransactionRequest(int accountNo,double transactionAmount){
        this.accountNo = accountNo;
        this.transactionAmount = transactionAmount;
    }

}
