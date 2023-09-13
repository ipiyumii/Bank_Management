
package Models;

import java.util.Date;

public class RecentActivity {
    private String activityType;
    private Date timestamp;

    public RecentActivity() {
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getActivityType() {
        return activityType;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    public RecentActivity(String activityType, Date timestamp){
        this.activityType = activityType;
        this.timestamp = timestamp;
    }
    
     
}
