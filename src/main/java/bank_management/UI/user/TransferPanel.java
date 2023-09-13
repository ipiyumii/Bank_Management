package bank_management.UI.user;

import Models.Account;
import Models.TransactionRequest;
import bank_management.core.Store;
import bank_management.core.TransactionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class TransferPanel extends JPanel {
    private Account user;
    private JTextField amountTf;
    private JTextField receiverAccNoTF;
    private JTextField senderUserNameTf;
    public TransferPanel(Account user){
        this.user = user;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel sendMoney = new JLabel("Send Money");
        sendMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(sendMoney);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel senderAccPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel senderLabel = new JLabel("Your user Name");
        senderLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        senderAccPanel.add(senderLabel);

        senderUserNameTf = new JTextField();
        senderUserNameTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        senderUserNameTf.setPreferredSize(new Dimension(200, senderUserNameTf.getPreferredSize().height)); // Set a preferred width of 200 pixels
        senderAccPanel.add(senderUserNameTf);
        senderAccPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(senderAccPanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel receiverAccNoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel receiverAccNo = new JLabel(("Receiver"));
        receiverAccNo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        receiverAccNoPanel.add(receiverAccNo);

        receiverAccNoTF = new JTextField();
        receiverAccNoTF.setAlignmentX(Component.CENTER_ALIGNMENT);
        receiverAccNoTF.setPreferredSize(new Dimension(200, receiverAccNoTF.getPreferredSize().height)); // Set a preferred width of 200 pixels
        receiverAccNoPanel.add(receiverAccNoTF);
        receiverAccNoPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(receiverAccNoPanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel amount = new JLabel(("Amount"));
        amount.setAlignmentX(Component.CENTER_ALIGNMENT);
        amountPanel.add(amount);

        amountTf = new JTextField();
        amountTf.setAlignmentX(Component.CENTER_ALIGNMENT);
        amountTf.setPreferredSize(new Dimension(200, amountTf.getPreferredSize().height)); // Set a preferred width of 200 pixels
        amountPanel.add(amountTf);
        amountPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        add(amountPanel);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setAlignmentX(Component.RIGHT_ALIGNMENT);
        add(btnTransfer);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));

        btnTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTransferAction();
            }
        });

    }
    public void btnTransferAction(){
        String senderUserName = senderUserNameTf.getText();
        int transaction_amount =Integer.parseInt( amountTf.getText());
        String receiverAccNo = receiverAccNoTF.getText();
        try{
            TransactionManager transactionManager = new TransactionManager(user);
            transactionManager.addPendingTransactions(senderUserName,transaction_amount,receiverAccNo);
        }catch (Exception ex){

        }
        senderUserNameTf.setText("");
        amountTf.setText("");
        receiverAccNoTF.setText("");
    }

    public  void setSender(){

    }

}

