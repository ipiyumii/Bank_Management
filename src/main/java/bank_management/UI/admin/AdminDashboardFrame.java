package bank_management.UI.admin;

import Models.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardFrame extends JFrame {
    private JPanel contentPanel;
    private Account user;
    public AdminDashboardFrame(Account user){
        this.user = user;
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(750,350);

        SidebarPanel sidebarPanel = new SidebarPanel();
        add(sidebarPanel,BorderLayout.WEST);

        contentPanel = new HomePanel(user);

        add(contentPanel,BorderLayout.CENTER);

        for (Component comp: sidebarPanel.getComponents()){
            if(comp instanceof JButton){
                ((JButton) comp).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateContent(e.getActionCommand());
                    }
                });
            }
        }
    }

    public void updateContent(String command) {

        remove(contentPanel);

        if ("Home".equals(command)) {
            contentPanel = new HomePanel(user);
        } else if ("Accounts".equals(command)) {
            contentPanel = new AccountsPanel(user);
        }

        add(contentPanel,BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
