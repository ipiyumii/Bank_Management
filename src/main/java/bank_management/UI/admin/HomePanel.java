package bank_management.UI.admin;

import Models.Account;
import Models.TransactionRequest;
import bank_management.core.Store;
import bank_management.core.TransactionManager;
import bank_management.db.TransactionDbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class HomePanel extends JPanel {
    private JTextField senderTf;
    private JTextField amountTf;
    private JTextField receiverTf;
    private Account user;
    public HomePanel(Account user){
        this.user = user;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        JLabel title = new JLabel("Home");
        title.setFont(titleFont);
        title.setForeground(Color.darkGray);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel requestPanel = new JPanel();

        senderTf = new JTextField();
        senderTf.setAlignmentX(Component.LEFT_ALIGNMENT);
        senderTf.setPreferredSize(new Dimension(100, senderTf.getPreferredSize().height)); // Set a preferred width of 200 pixels
        requestPanel.add(senderTf);

        receiverTf = new JTextField();
        receiverTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        requestPanel.setPreferredSize(new Dimension(100, receiverTf.getPreferredSize().height)); // Set a preferred width of 200 pixels
        requestPanel.add(receiverTf);


        amountTf = new JTextField();
        amountTf.setAlignmentX(Component.RIGHT_ALIGNMENT);
        amountTf.setPreferredSize(new Dimension(100, amountTf.getPreferredSize().height)); // Set a preferred width of 200 pixels
        requestPanel.add(amountTf);

        JButton btnApprove = new JButton("Approve");
        requestPanel.add(btnApprove);
        JButton btnCancel = new JButton("Cancel");
        requestPanel.add(btnCancel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(title);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(requestPanel);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        setPreferredSize(new Dimension(400,300));

        btnApprove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    btnApproveAction();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTransactionRequests();
            }
        });
    }

    public TransactionRequest getTransactionRequests(){
        try {
            TransactionRequest request = new TransactionManager(user).getTransactionRequests();

            senderTf.setText(request.getUserName());
            receiverTf.setText(request.getReceiver());
            amountTf.setText(String.valueOf(request.getTransactionAmount()));
            return request;

        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"No Transaction Requests");
            return null;
        }

    }

    public void btnApproveAction(){
        TransactionRequest request = getTransactionRequests();
        Store.approvedTransactions.add(request);
        new TransactionDbUtil().insertTransaction(request);
        senderTf.setText("");
        receiverTf.setText("");
        amountTf.setText("");
    }


}
