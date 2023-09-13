package Models;

import java.sql.Date;

public class Transaction {
    private int accountNo;
    private String receiver;

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public Transaction(int accountNo, String receiver, double amount, Date date) {
        this.accountNo = accountNo;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private double amount;
    private Date date;
}
