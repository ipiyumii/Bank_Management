package bank_management.core;

import Models.Account;
import Models.RecentActivity;

import java.util.Date;
import java.util.Stack;

public class ActivityManager {
    private Account user;
    public ActivityManager(Account user){
        this.user = user;
    }

    private void getRecentActivity() {
        Stack<RecentActivity> recentActivities = Store.recentActivitiesStack;

        while (!recentActivities.isEmpty()) {
            RecentActivity activity = recentActivities.pop();
            String activity_type = activity.getActivityType();
            Date time_stamp = activity.getTimestamp();

        }
    }

}
