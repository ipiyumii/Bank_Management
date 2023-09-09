
package Models;

public class TransactionRequest {
    private int accountNo;
    private int transactionAmount;

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public TransactionRequest(int accountNo, int transactionAmount) {
        this.accountNo = accountNo;
        this.transactionAmount = transactionAmount;
    }
}
