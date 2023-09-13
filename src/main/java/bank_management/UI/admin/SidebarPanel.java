package bank_management.UI.admin;

import bank_management.UI.authentication.AuthenticationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class SidebarPanel extends JPanel {
    public SidebarPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.lightGray);

        JLabel username = new JLabel("user name");
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnHome = new JButton("Home");
        btnHome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAccounts = new JButton(("Accounts"));
        btnAccounts.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnLogout = new JButton("Log Out");

        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(username);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(btnHome);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(btnAccounts);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(Box.createVerticalGlue());
        add(btnLogout);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var frame = new AuthenticationFrame();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        setPreferredSize(new Dimension(100,300));
    }
}
