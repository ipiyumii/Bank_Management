
package Models;

import bank_management.UI.user.HistoryPanel;

public class Account{
    private int accID;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String firstName;
    private String lastName;

    public int getAccountNo() {
        return accountNo;
    }
    private int accountNo;
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    private double balance;
    String userName;
    private String password;
    
    public Account( String firstName, String lastName, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
    public Account(String userName){
        this.userName = userName;
    }

    public Account(String firstName, String lastName, String userName, int accountNo,double accBal){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.accountNo = accountNo;
        this.balance = accBal;
    }
}
