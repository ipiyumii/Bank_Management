package bank_management.UI.user;

import Models.Account;
import bank_management.UI.admin.AccountsPanel;
import bank_management.db.AccountDbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboardFrame extends JFrame {
    private Account user;
    private JPanel contentPanel;

    public UserDashboardFrame(Account user){
        this.user = user;
        setTitle("User Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(750,350);

        SidebarPanel sidebarPanel = new SidebarPanel(user);
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
        } else if ("Transfer".equals(command)) {
            contentPanel = new TransferPanel(user);
        }else if("Transfer History".equals(command)){
            contentPanel = new HistoryPanel(user);
        }else if("Recent Activity".equals(command)){
            contentPanel = new ActivityPanel(user);
        }

        add(contentPanel,BorderLayout.CENTER);
        revalidate();
        repaint();
    }


}
