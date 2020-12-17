/**
 * Class used for storing a line of activity history from csv
 */
package assets;

import java.util.List;

public class ActivityHistory {

    private String activityType;
    private String date;
    private String amount;
    private String currencyType;
    private String status;
    private String targetID;

    public ActivityHistory(List<String> data){
        activityType = data.get(0);
        date = data.get(1);
        amount = data.get(2);
        currencyType = data.get(3);
        status = data.get(4);
        targetID = data.get(5);
    }

    public String getActivityType() {
        return activityType;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getStatus() {
        return status;
    }

    public String getTargetID() {
        return targetID;
    }
}
