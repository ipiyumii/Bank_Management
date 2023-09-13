package bank_management.UI.user;

import Models.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class ActivityPanel extends JPanel {
    Account user;
    public ActivityPanel(Account user){
        this.user = user;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JLabel recentActivity = new JLabel("Recent Activity");
        recentActivity.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(recentActivity);
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
}
