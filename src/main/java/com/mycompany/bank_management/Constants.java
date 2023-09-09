
package com.mycompany.bank_management;

import Models.IAccount;
import Models.TransactionRequest;
import Models.recentActivity;
import com.bankManagement.db.DBUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Constants {
    public static List<IAccount> accountList = new ArrayList<>();
    
    public static Queue<TransactionRequest> pendingTransactions = new LinkedList<>(); //queue
    
    public static LinkedList<TransactionRequest> approvedTransactions = new LinkedList<>(); //history
    
    public static Stack<recentActivity> recentActivitiesStack = new Stack<>();
//    public static void Populate() {
//        DBUtil dbUtil = new DBUtil();
//        
//        dbUtil.close();
//
//    }

}
