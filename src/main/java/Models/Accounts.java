
package Models;

public class Accounts implements IAccount{
    private int accID;
    private String firstName;
    private String lastName;
    String userName;
    private String password;
    
    public Accounts(int accID,String firstName,String lastName,String userName,String password){
        this.accID = accID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
    public Accounts(String userName){
        this.userName = userName;
    }
}
