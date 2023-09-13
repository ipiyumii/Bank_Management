package bank_management.UI.user;

import Models.Account;
import Models.RecentActivity;
import Models.User;
import bank_management.UI.authentication.AuthenticationFrame;
import bank_management.core.Store;
import bank_management.db.AccountDbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class SidebarPanel extends JPanel {
    private JLabel userName;
    private JLabel accoutnNo;
    private Account user;

    public SidebarPanel(Account user){
        this.user = user;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.lightGray);

        userName = new JLabel();
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);

        accoutnNo = new JLabel();
        accoutnNo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnHome = new JButton(("Home"));
        btnHome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnTransfer = new JButton(("Transfer"));
        btnTransfer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnHistory = new JButton(("Transfer History"));
        btnHistory.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnActivity = new JButton(("Recent Activity"));
        btnActivity.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnLogout = new JButton("Log Out");
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var frame = new AuthenticationFrame();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                RecentActivity logoutActivity = new RecentActivity("Logout", new Date());
                Store.recentActivitiesStack.push(logoutActivity);
            }
        });

        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(userName);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(accoutnNo);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(btnHome);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(btnTransfer);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(btnHistory);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(btnActivity);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(Box.createVerticalGlue());
        add(btnLogout);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        setPreferredSize(new Dimension(150,300));


    }

    public void getUserData(String uName){
        userName.setText(uName);

        int accNo = new AccountDbUtil(user).getAccountNumber(uName);
        accoutnNo.setText(String.valueOf(accNo));
    }
}
