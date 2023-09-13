
package bank_management.core;

import Models.*;

import java.util.*;

public class Store {
    public static List<IAccount> accountList = new ArrayList<>();
    
    public static Queue<TransactionRequest> pendingTransactions = new LinkedList<>(); //queue
    
    public static LinkedList<TransactionRequest> approvedTransactions = new LinkedList<TransactionRequest>(); //history

    public static HashMap<Integer, Account> accounts = new HashMap<>();

    public static Stack<RecentActivity> recentActivitiesStack = new Stack<>();
//    public static void Populate() {
//        DBUtil dbUtil = new DBUtil();
//        
//        dbUtil.close();
//
//    }


}
