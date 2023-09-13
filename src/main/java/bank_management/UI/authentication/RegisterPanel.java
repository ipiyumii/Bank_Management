package bank_management.UI.authentication;

import Models.Account;
import bank_management.UI.Util;
import bank_management.UI.user.UserDashboardFrame;
import bank_management.core.AccountManager;
import bank_management.db.AccountDbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class RegisterPanel extends JPanel {
    private JTextField fNameTf;
    private JTextField lastNameTf;
    private JTextField userNameTf;
    private JTextField passwordTf;
    private JTextField verifyPasswordTf;



    public RegisterPanel(CardLayout cardLayout, JPanel mainPanel ){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        JLabel title = new JLabel("Registration");
        title.setFont(titleFont);
        title.setForeground(Color.darkGray);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel firstNamePanel = new JPanel();

        JLabel fName  = new JLabel("User Name");
        fName.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstNamePanel.add(fName);

        fNameTf = new JTextField();
        fNameTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        fNameTf.setPreferredSize(new Dimension(200, fNameTf.getPreferredSize().height));
        firstNamePanel.add(fNameTf);
        firstNamePanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(firstNamePanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel lastNamePanel = new JPanel();

        JLabel lastName  = new JLabel("Last Name");
        lastName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lastNamePanel.add(lastName);

        lastNameTf = new JTextField();
        lastNameTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        lastNameTf.setPreferredSize(new Dimension(200, lastNameTf.getPreferredSize().height));
        lastNamePanel.add(lastNameTf);
        lastNamePanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(lastNamePanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel userNamePanel = new JPanel();

        JLabel userName  = new JLabel("User Name");
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNamePanel.add(userName);

        userNameTf = new JTextField();
        userNameTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameTf.setPreferredSize(new Dimension(200, userNameTf.getPreferredSize().height));
        userNamePanel.add(userNameTf);
        userNamePanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(userNamePanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel passwordPanel = new JPanel();

        JLabel password  = new JLabel("Password");
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.add(password);

        passwordTf = new JTextField();
        passwordTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordTf.setPreferredSize(new Dimension(200, passwordTf.getPreferredSize().height));
        passwordPanel.add(passwordTf);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(passwordPanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel verifyPasswordPanel = new JPanel();

        JLabel VerifyPassword  = new JLabel("Verify");
        VerifyPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        verifyPasswordPanel.add(VerifyPassword);

        verifyPasswordTf = new JTextField();
        verifyPasswordTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        verifyPasswordTf.setPreferredSize(new Dimension(200, verifyPasswordTf.getPreferredSize().height));
        verifyPasswordPanel.add(verifyPasswordTf);
        verifyPasswordPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(verifyPasswordPanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JButton btnBack = new JButton("Back");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(btnBack);
        btnBack.setFont(buttonFont);
        btnBack.setMargin(new Insets(10, 20, 10, 20));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"loginPanel");
            }
        });


        JButton btnRegister = new JButton("Register");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(btnRegister);
        btnRegister.setFont(buttonFont);
        btnRegister.setMargin(new Insets(10, 20, 10, 20));
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account userRegister = userRegistration();
                if(userRegister != null){
                    var userDashboard = new UserDashboardFrame(userRegister);
                    userDashboard.setLocationRelativeTo(null);
                    userDashboard.setVisible(true);
                }
            }
        });

        add(buttonPanel);
        setPreferredSize(new Dimension(500,300));
    }

    public Account userRegistration(){
        String firstName = fNameTf.getText();
        String lastName = lastNameTf.getText();
        String userName = userNameTf.getText();
        String password = passwordTf.getText();
        String verifyPw = verifyPasswordTf.getText();
        Account user = new Account(userName);
        if(password.equals(verifyPw)){
            try {
                Account account = new Account(firstName,lastName,userName,password);
                new AccountManager(user).addAccount(account);
                return user;
            }catch (RuntimeException ex){
                //JOptionPane.showMessageDialog(this,"fail to insert user ");
            }
        }else {
            JOptionPane.showMessageDialog(this,"Password doesn't match ");
        }
        return null;
    }


}
