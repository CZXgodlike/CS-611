/**
 * Class used for storing a line of customer information from csv
 */
package assets;

import java.util.List;

public class CustomerAccountInformation {

    private String id;
    private String balance;
    private String currencyType;
    private String customerName;

    public CustomerAccountInformation(List<String> data){
        id = data.get(0);
        balance = data.get(1);
        currencyType = data.get(2);
    }

    public String getId() {
        return id;
    }

    public String getBalance() {
        return balance;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
}
