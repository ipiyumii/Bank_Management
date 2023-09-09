
package Models;

public class User extends Accounts {
    private double account_balance;
    private int account_no;

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public int getAccount_no() {
        return account_no;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    public double getAccount_balance() {
        return account_balance;
    }
    
    public User(int accID, String firstName, String lastName, String userName, String password,double account_balance, int Account_no) {
        super(accID, firstName, lastName, userName, password);
    }
    
    public User(int accNo, String userName, double account_balance){
        super(userName);
        this.account_balance = account_balance;
        this.account_no = accNo;
    }
    public String getUserName() {
        return userName;
    }
}
