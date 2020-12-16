package controller;

import assets.CustomerAccountInformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerAccountInformationController extends DataController{


    public CustomerAccountInformationController(String accountType) {
        if(accountType.equals("saving")){
            dataPath = "data/AccountData/savingAccounts.csv";
        } else if (accountType.equals("checking")){
            dataPath = "data/AccountData/checkingAccounts.csv";
        } else {
            dataPath = "data/AccountData/securityAccounts.csv";
        }
    }


    @Override
    public List<CustomerAccountInformation> getData() throws IOException {
        return readData();
    }

    public List<CustomerAccountInformation> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(dataPath));
        List<CustomerAccountInformation> information = new ArrayList<>();
        String row;
        //csvReader.readLine(); //skip the first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            information.add(new CustomerAccountInformation(Arrays.asList(data)));
        }
        csvReader.close();

        return information;
    }



    public CustomerAccountInformation getAccountData(String id) throws IOException {
        AssociateAccountController controller = new AssociateAccountController();

        for(CustomerAccountInformation data: getData()){
            if(data.getId().equals(id)){
                data.setCustomerName(controller.getUserName(id));
                return data;
            }
        }
        return null;
    }
}
