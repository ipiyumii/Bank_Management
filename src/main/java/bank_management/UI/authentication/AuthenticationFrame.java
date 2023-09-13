package bank_management.UI.authentication;

import Models.Account;
import bank_management.UI.admin.AccountsPanel;
import bank_management.UI.admin.HomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Account user;
    public AuthenticationFrame(){

        setTitle("Authentication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(750,350);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginPanel login = new LoginPanel(cardLayout,mainPanel);
        RegisterPanel registration = new RegisterPanel(cardLayout,mainPanel);

        mainPanel.add(login,"loginPanel");
        mainPanel.add(registration,"registerPanel");

        add(mainPanel);

        cardLayout.show(mainPanel,"loginPanel");
    }

}
