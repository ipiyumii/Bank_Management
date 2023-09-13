package bank_management.UI.authentication;

import Models.Account;
import Models.User;
import bank_management.UI.user.HistoryPanel;
import bank_management.UI.user.SidebarPanel;
import bank_management.UI.user.TransferPanel;
import bank_management.db.AccountDbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class LoginPanel extends JPanel {
   // private Account user;
    private JTextField userNameTf;
    private JPasswordField pwdfield;
    public LoginPanel(CardLayout cardLayout, JPanel mainPanel ){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        JLabel title = new JLabel("Log In to ABC Bank");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.DARK_GRAY);
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        title.setFont(titleFont);
        add(title);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel userNamePanel = new JPanel();

        JLabel userName = new JLabel(("User Name"));
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNamePanel.add(userName);

        userNameTf = new JTextField();
        userNameTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameTf.setPreferredSize(new Dimension(200, userNameTf.getPreferredSize().height)); // Set a preferred width of 200 pixels
        userNamePanel.add(userNameTf);
        userNamePanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(userNamePanel);

       add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel passwordPanel = new JPanel();

        JLabel password = new JLabel(("Password"));
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.add(password);

        pwdfield = new JPasswordField();
        pwdfield.setAlignmentX(Component.CENTER_ALIGNMENT);
        pwdfield.setPreferredSize(new Dimension(200, pwdfield.getPreferredSize().height)); // Set a preferred width of 200 pixels
        passwordPanel.add(pwdfield);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(passwordPanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JButton signIn = new JButton("Sign In");
        signIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signIn.setFont(buttonFont);
        signIn.setMargin(new Insets(10, 20, 10, 20));
        buttonPanel.add(signIn);

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSignInClick();
            }
        });

        JButton signUp = new JButton("Sign Up");
        signUp.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(signUp);
        signUp.setFont(buttonFont);
        signUp.setMargin(new Insets(10, 20, 10, 20));
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"registerPanel");
            }
        });

        add(buttonPanel);
        setPreferredSize(new Dimension(500,300));
    }

    public void btnSignInClick(){
        String userName = userNameTf.getText();
        char[] passwordChars = pwdfield.getPassword();
        String password = new String(passwordChars);
        Account user = new Account(userName);

        AccountDbUtil accDb = new AccountDbUtil(user);
        accDb.verifyAccount(userName,password);

    }
}
