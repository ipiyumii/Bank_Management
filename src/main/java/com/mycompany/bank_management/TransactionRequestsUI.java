
package com.mycompany.bank_management;

import Models.TransactionRequest;
import Models.recentActivity;
import com.bankManagement.db.DBUtil;
import static com.mycompany.bank_management.Constants.approvedTransactions;
import static com.mycompany.bank_management.Constants.pendingTransactions;
import static com.mycompany.bank_management.Constants.recentActivitiesStack;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransactionRequestsUI extends javax.swing.JFrame {
    private int accountNo;
    private double transactionAmount;
    TransactionRequest request;
    
    public TransactionRequestsUI() {
        initComponents();
        populateRequests();
    }
    
    private void populateRequests() {
        getTransactionRequests();
        displayTransactionRequestDemo();
    }
    private void getTransactionRequests(){
          // Assuming Constants.pendingTransactions is accessible here
        //Queue<TransactionRequest> pendingTransaction = Constants.pendingTransactions;
        
        while (!pendingTransactions.isEmpty()) { 
            if (!pendingTransactions.isEmpty()) {
                // Retrieve the first transaction request from the queue
                request = pendingTransactions.poll();

                 // Extract account number and transaction amount from the request 
                accountNo = request.getAccountNo();
                transactionAmount = request.getTransactionAmount();
                
                //displayTransactionRequest();
            } else {
                System.out.println("No pending transactions.");
            }
        }
    }
    
    private void displayTransactionRequest(){
         JPanel transactionPanel = new JPanel(new FlowLayout());
         JTextField accountNoTextField = new JTextField(10); // Adjust the column size as needed
         JTextField transactionAmountTextField = new JTextField(10);
         
         // Set text fields to non-editable
        accountNoTextField.setEditable(false);
        transactionAmountTextField.setEditable(false);
        
        accountNoTextField.setText(String.valueOf(accountNo));
        transactionAmountTextField.setText(String.valueOf(transactionAmount));
        
        JButton approveButton = new JButton("Approve");

           // Attach an ActionListener to the button to handle the approval action
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle approval logic here
                 //TransactionRequest approvedRequest = pendingTransactions.poll();
         
            //if (approvedRequest != null) {
                DBUtil dbUtil = new DBUtil();
                double accountBalance = dbUtil.getAccountBalance(accountNo);
                
                if(accountBalance > transactionAmount ){
                     // Add the approved request to the linked list(trans history)
                    approvedTransactions.add(request);
                    
                    //insert to database
                    dbUtil.insertTransaction( accountNo, transactionAmount);   
                    
              // For a completed transaction add to the stack
                recentActivity transactionActivity = new recentActivity("Transaction", new Date());
                recentActivitiesStack.push(transactionActivity);
                }   
                else{
                    // do not aprove message account balance in not sufficient 
                }
           }
              // }
        });
        
        // Add components to the JPanel
        transactionPanel.add(new JLabel("Account Number: "));
        transactionPanel.add(accountNoTextField);
        transactionPanel.add(new JLabel("Transaction Amount: "));
        transactionPanel.add(transactionAmountTextField);
        transactionPanel.add(approveButton);
        
//        mainJpanel.add(transactionPanel);
//        mainJpanel.revalidate();
//        mainJpanel.repaint();
    }
    
        private void displayTransactionRequestDemo(){
         JPanel transactionPanel = new JPanel(new FlowLayout());
         JTextField accountNoTextField = new JTextField(10); // Adjust the column size as needed
         //JTextField transactionAmountTextField = new JTextField(10);
         
         // Set text fields to non-editable
        accountNoTextField.setEditable(false);
        //transactionAmountTextField.setEditable(false);
        
         accountNoTextField.setText(String.valueOf(10));
        //transactionAmountTextField.setText(String.valueOf(10000));
    
        // JButton approveButton = new JButton("Approve");

           // Attach an ActionListener to the button to handle the approval action
//        approveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Handle approval logic here
//                 //TransactionRequest approvedRequest = pendingTransactions.poll();
//         
//            //if (approvedRequest != null) {
//                DBUtil dbUtil = new DBUtil();
//                double accountBalance = dbUtil.getAccountBalance(accountNo);
//                
//                if(accountBalance > transactionAmount ){
//                     // Add the approved request to the linked list(trans history)
//                    approvedTransactions.add(request);
//                    
//                    //insert to database
//                    dbUtil.insertTransaction( accountNo, transactionAmount);   
//                    
//              // For a completed transaction add to the stack
//                //recentActivity transactionActivity = new recentActivity("Transaction", new Date());
//                //recentActivitiesStack.push(transactionActivity);
//                }   
//                else{
//                    // do not aprove message account balance in not sufficient 
//                }
//           }
//              // }
//        });
        
        // Add components to the JPanel
        transactionPanel.add(new JLabel("Account Number: "));
        transactionPanel.add(accountNoTextField);
        transactionPanel.add(new JLabel("Transaction Amount: "));
        
        //transactionPanel.add(transactionAmountTextField);
        //transactionPanel.add(approveButton);
        
        //mainJpanel.add(transactionPanel);
        jpanelMain.add(new JLabel("test"));
        jpanelMain.revalidate();
        jpanelMain.repaint();
    }

//     private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {   
//         //remove the request from the queue and assing it approvedRequest variable
//         TransactionRequest approvedRequest = pendingTransactions.poll();
//         
//         if (approvedRequest != null) {
//            // Add the approved request to the linked list
//            approvedTransactions.add(approvedRequest);
//        }
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        Test = new javax.swing.JButton();
        jpanelMain = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Big Caslon", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 153));
        jLabel1.setText("Transaction Request");

        btnLogout.setText("Log Out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        Test.setText("Test");
        Test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(Test, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Test))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpanelMainLayout = new javax.swing.GroupLayout(jpanelMain);
        jpanelMain.setLayout(jpanelMainLayout);
        jpanelMainLayout.setHorizontalGroup(
            jpanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpanelMainLayout.setVerticalGroup(
            jpanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jpanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        Login_form logform =  new Login_form();
        logform.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void TestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestActionPerformed
        displayTransactionRequestDemo();
    }//GEN-LAST:event_TestActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransactionRequestsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionRequestsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionRequestsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionRequestsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionRequestsUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Test;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jpanelMain;
    // End of variables declaration//GEN-END:variables
}
