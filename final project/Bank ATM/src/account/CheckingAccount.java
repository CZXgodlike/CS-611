/**
 * Class represents a checking account
 */
package account;

import assets.CustomerAccountInformation;
import com.opencsv.CSVWriter;
import controller.CustomerAccountInformationController;
import utils.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CheckingAccount extends CustomerAccount{

    public CheckingAccount(String accountName, double amount, String currencyType){
        super(accountName, amount, currencyType);
    }

    public CheckingAccount(String accountName){
        super(accountName,0.0,"USD");
    }

    public CheckingAccount(){
        this("");
    }

    public void open(){
        // Add to the specific customer's checking, saving or security account
        super.open(this);
        File checkingAccountFile = ReadFileUtil.getPathToAccountData("checkingAccounts");
        if(checkingAccountFile.exists()){
            try {
                CSVReader reader = new CSVReader(new FileReader(getCustomerData().getAbsolutePath()));
                List<String[]> data = reader.readAll();
                boolean customerExists = false;
                for(String[] d: data){
                    if(d[0].equalsIgnoreCase(this.name)){
                        customerExists = true;
                    }
                }
                if(customerExists){
                    WriteFileUtil.writeLine(checkingAccountFile.getPath(), id +"," + balance + "," + currencyType);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){
        // Add to the specific customer's checking, saving or security account
        super.close(this);
        File checkingAccountFile = ReadFileUtil.getPathToAccountData("checkingAccounts");
        if(checkingAccountFile.exists()){
            WriteFileUtil.removeLineFromCustomerAccountData(checkingAccountFile.getAbsolutePath(), this);
        }
    }

    
    public void transfer(CustomerAccount otherAccount, double amount){
        super.transfer(otherAccount,amount);
    }

    public void updateBalance(double value) throws IOException, CsvException {
        balance = balance + value;
        System.out.println("Checking balance" + balance);
        File currAccountFile = ReadFileUtil.getPathToAccountData("checkingAccounts");
        List<String[]> data = new CSVReader(new FileReader(currAccountFile)).readAll();
        for(String[] d: data){
            if(d[0].equals(this.id)){
                d[1] = "" + (balance);
            }
        }
        WriteFileUtil.writeFile(currAccountFile, data);
    }

//    @Override
//    public void display() {
//        // display info in GUI
//    }

    public String getBalance() {
        CustomerAccountInformationController customerInfoController = new CustomerAccountInformationController("checking");
        try {
            List<CustomerAccountInformation> info = customerInfoController.getData();
            for(CustomerAccountInformation i: info){
                if(i.getId().equalsIgnoreCase("" + this.id)){
                    return i.getBalance();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void loan(double amt) {
        try {
            updateBalance(-amt);
            addToTransactionHistory("loan",amt,this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }
}
