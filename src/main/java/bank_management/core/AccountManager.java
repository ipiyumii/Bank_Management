package bank_management.core;

import Models.Account;
import bank_management.UI.Util;
import bank_management.db.AccountDbUtil;

import javax.swing.*;
import java.util.List;

public class AccountManager {
    private Account user;
    public AccountManager(Account user){
        this.user = user;
    }

    public void addAccount(Account account){
        account.setAccountNo(Util.generate8DigitRandomNumber());
        account.setBalance(10000);
        new AccountDbUtil(user).insertAccounts(account.getFirstName(), account.getLastName(), account.getUserName(), account.getPassword(),account.getAccountNo(),account.getBalance());

        Store.accounts.put(account.getAccountNo(),account);
    }

    public Account getAccount(int accNo){
        return Store.accounts.get(accNo);
    }
    public List<Account> getAccountList(){
        return Store.accounts.values().stream().toList();
    }

    public void updateAccountList(){
        Store.accounts.clear();
        List<Account> accounts = new AccountDbUtil(user).selectAllAccounts();
        for (Account account:accounts) {
            Store.accounts.put(account.getAccountNo(),account);
        }
    }
    public double getAccountBalance(int accNo){
        updateAccountList();
        return Store.accounts.get(accNo).getBalance();
    }
}
