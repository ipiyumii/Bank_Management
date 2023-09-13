
package bank_management.UI;

import Models.Account;
import bank_management.UI.admin.AdminDashboardFrame;
import bank_management.UI.authentication.AuthenticationFrame;
import bank_management.UI.user.UserDashboardFrame;
import bank_management.core.AccountManager;
import bank_management.mycompany.bank_management.Login_form;

public class BankManagement {

    public static void main(String[] args) {
       // new AccountManager().updateAccountList();
        var frame = new AuthenticationFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
