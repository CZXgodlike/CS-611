package assets;

import java.util.List;

public class DailyTransaction {

    private String type;
    private String amount;
    private String currencyType;
    private String status;
    private String actionID;
    private String associateID;

    public DailyTransaction(List<String> data){
        type = data.get(0);
        amount = data.get(1);
        currencyType = data.get(2);
        status = data.get(3);
        actionID = data.get(4);
        associateID = data.get(5);
    }

    public DailyTransaction() {
    }

    public String getType() {
        return type;
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

    public String getActionID() {
        return actionID;
    }

    public String getAssociateID() {
        return associateID;
    }
}
