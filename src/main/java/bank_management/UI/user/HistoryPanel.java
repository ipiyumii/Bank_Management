package bank_management.UI.user;

import Models.Account;
import Models.Transaction;
import bank_management.db.TransactionDbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class HistoryPanel extends JPanel {
    private Account user;
    private JTable table;
    public HistoryPanel(Account user){
        this.user = user;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JLabel currentBalance = new JLabel("Account Balance");
        currentBalance.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(currentBalance);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        addTable();
        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        setPreferredSize(new Dimension(350,300));
    }
    private void addTable(){
        List<Transaction> transactions =  new TransactionDbUtil().selectTransactions(user);

        String[] columnNames ={"Account No","Receiver","Amount","date"};
        Object[][] data = new Object[transactions.size()][4];

        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            data[i][0] = transaction.getAccountNo();
            data[i][1] = transaction.getReceiver();
            data[i][2] = transaction.getAmount();
            data[i][3] = transaction.getDate();
        }

        DefaultTableModel model = new DefaultTableModel(data,columnNames){
            @Override
            public Class<?> getColumnClass(int column) {return getValueAt(0,column).getClass();}
        };

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void displayApprovedTransactions(){
        List<Transaction> transactions =  new TransactionDbUtil().selectTransactions(user);


        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Transaction transaction : transactions) {
            Object[] rowData = {
                    transaction.getAccountNo(),
                    transaction.getReceiver(),
                    transaction.getAmount(),
                    transaction.getDate()
            };
            model.addRow(rowData);
        }
    }
}
