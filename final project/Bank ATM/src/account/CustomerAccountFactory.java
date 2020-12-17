/*
 * Class used for generating an object of customer account
 */


package account;

import assets.CustomerAccountInformation;
import com.opencsv.exceptions.CsvException;
import controller.CustomerAccountInformationController;

import java.io.IOException;

public class CustomerAccountFactory {

    private CustomerAccountInformationController controller;
    private String type;
    private String id;

    public CustomerAccountFactory(String type, String id){
        this.type = type;
        this.id = id;

        if(type.equalsIgnoreCase("Checking account")){
            controller = new CustomerAccountInformationController("checking");
        } else if(type.equalsIgnoreCase("Saving account")){
            controller = new CustomerAccountInformationController("saving");
        } else {
            controller = new CustomerAccountInformationController("security");
        }
    }

    public CustomerAccountFactory(String id){
        this.id = id;
    }

    public CustomerAccount createAccount() throws IOException {
        CustomerAccountInformation data = controller.getAccountData(id);
        if(type.equalsIgnoreCase("checking account")){
            CheckingAccount account = new CheckingAccount(data.getCustomerName(), Double.parseDouble(data.getBalance()), data.getCurrencyType());
            account.setId(id);
            return account;
        } else if(type.equalsIgnoreCase("saving account")){
            SavingAccount account = new SavingAccount(data.getCustomerName(), Double.parseDouble(data.getBalance()), data.getCurrencyType());
            account.setId(id);
            return account;
        } else{
            SecuritiesAccount account = new SecuritiesAccount(data.getCustomerName(), Double.parseDouble(data.getBalance()), data.getCurrencyType());
            account.setId(id);
            return account;
        }
    }

    private void setController(String type){
        controller = new CustomerAccountInformationController(type);
    }

    public CustomerAccount findAccount() throws IOException {
        setController("saving");
        CustomerAccountInformation data = controller.getAccountData(id);
        if(data != null){
            SavingAccount account = new SavingAccount(data.getCustomerName(), Double.parseDouble(data.getBalance()), data.getCurrencyType());
            account.setId(id);
            return account;
        }

        setController("checking");
        data = controller.getAccountData(id);
        if(data != null){
            CheckingAccount account = new CheckingAccount(data.getCustomerName(), Double.parseDouble(data.getBalance()), data.getCurrencyType());
            account.setId(id);
            return account;
        }

        setController("security");
        data = controller.getAccountData(id);
        if(data != null){
            SecuritiesAccount account = new SecuritiesAccount(data.getCustomerName(), Double.parseDouble(data.getBalance()), data.getCurrencyType());
            account.setId(id);
            return account;
        }

        return null;
    }

}
