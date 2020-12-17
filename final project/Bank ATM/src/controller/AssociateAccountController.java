/**
 * Class represents the controller of associate account csv file
 */
package controller;

import assets.AccountInformation;
import assets.ActivityHistory;
import assets.AssociateAccountInformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssociateAccountController {

    private String dataPath;

    public AssociateAccountController(){
        dataPath = "data/customerData.csv";
    }

    public List<AccountInformation> getData(String userName){
        List<AccountInformation> data = new ArrayList<>();

        try {
            if(getAssociateAccounts(userName) == null){
                return null;
            }
            for(String account: getAssociateAccounts(userName)){
                data.add(new AccountInformation(account));
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean hasAccount(String userName, String id) throws IOException{
        for(String ID: getAssociateAccounts(userName)){
            if(id.equals(ID.substring(1))){
                return true;
            }
        }
        return false;
    }

    private List<String> getAssociateAccounts(String userName) throws IOException {
        List<AssociateAccountInformation> data = readData();
        String accounts = null;
        List<String> associateAccounts;

        for(AssociateAccountInformation information: data){
            if(information.getUserName().equals(userName)){
                accounts = information.getAssociateAccounts();
                break;
            }
        }

        if(accounts == null){
            return null;
        } else {
            associateAccounts = Arrays.asList(accounts.split(";"));
            return associateAccounts;
        }
    }

    private List<AssociateAccountInformation> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(dataPath));
        List<AssociateAccountInformation> associateAccountInformationList = new ArrayList<>();
        String row;

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if(data.length <= 2){
                continue;
            }
            associateAccountInformationList.add(new AssociateAccountInformation(Arrays.asList(data)));
        }
        csvReader.close();

        return associateAccountInformationList;
    }

    public String getUserName(String id) throws IOException{
        for(AssociateAccountInformation information: readData()){
            if(hasAccount(information.getUserName(), id)){
                return information.getUserName();
            }
        }
        return null;
    }
}
