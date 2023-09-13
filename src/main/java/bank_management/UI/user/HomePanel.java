package bank_management.UI.user;

import Models.Account;
import bank_management.UI.authentication.LoginPanel;
import bank_management.db.AccountDbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class HomePanel extends JPanel {
    private Account user;
    public HomePanel(Account user){
        this.user = user;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        Font titleFont = new Font("Arial", Font.BOLD, 24);

        JLabel title = new JLabel("Home");
        title.setFont(titleFont);
        title.setForeground(Color.darkGray);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel currentBalance = new JLabel("");
        currentBalance.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(title);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(currentBalance);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        addTable();
        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        setPreferredSize(new Dimension(350,300));
    }
    private void addTable(){
        String[] columnNames ={"Date","Account No","Amount"};
        Object[][] data ={
                {"Alice", "$100", "Verify"},
                {"Bob", "$200", "Verify"},
                {"Carol", "$300", "Verify"}
        };

        DefaultTableModel model = new DefaultTableModel(data,columnNames){
            @Override
            public Class<?> getColumnClass(int column) {return getValueAt(0,column).getClass();}
        };

        JTable table = new JTable((model));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void getUserName(){

    }
}



