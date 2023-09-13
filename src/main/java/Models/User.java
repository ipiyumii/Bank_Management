
package Models;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private double account_balance;
    private int account_no;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    
    public User(String firstName, String lastName, String userName, String password,double account_balance, int Account_no) {
    }
    
    public User(int accNo, String userName, double account_balance){
        this.account_balance = account_balance;
        this.account_no = accNo;
        this.userName = userName;
    }
    public User(String userName){
        this.userName = userName;
    }


}
