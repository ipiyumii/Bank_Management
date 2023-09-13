package Models;

public class ApprovedTransactions {
    private int accountNo;
    private double transactionAmount;

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

    public ApprovedTransactions(int accountNo, double transactionAmount) {
        this.accountNo = accountNo;
        this.transactionAmount = transactionAmount;
    }
}
