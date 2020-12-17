/**
 * Abstract class for the three kinds of customer accounts:
 * saving account, checking account and security account
 */
package account;

import assets.Customer;
import utils.*;
import assets.Stock;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public abstract class CustomerAccount extends Account {

    protected double balance;
    protected String currencyType;
    protected String name;

    public CustomerAccount(String accountName, double amount, String currencyType){
        super();
        this.name = accountName;
        this.balance = amount;
        this.currencyType = currencyType;
    }

    public CustomerAccount(){
        this("",0,"USD");
    }

    public String getName(){ return this.name;}
        
    public abstract void open();

    protected void open(CustomerAccount acctType){
        File toEdit = getCustomerData();
        String startingSymbol = getSymbolForAccount(acctType);
        try {
            CSVReader reader = new CSVReader(new FileReader(toEdit));
            List<String[]> data = reader.readAll();;
            for(String[] d: data){
                if(d[0].equals(this.name)){
                    String accounts = d[2];
                    if(accounts.length() == 0){
                        accounts += startingSymbol + id;
                    } else{
                        accounts += ";" + startingSymbol + id;
                    }
                    d[2] = accounts;
                }
            }
            WriteFileUtil.writeFile(toEdit, data);
            File newFile = ReadFileUtil.getPathToUserDataPath("UserActivityData",this.id + "");
            newFile.createNewFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }
    private boolean validatePassword(String password){
        File tempFile = getCustomerData();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(tempFile.getAbsolutePath()))) {

            List<String[]> info = new ArrayList<>();
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                info.add(data);
            }
            csvReader.close();
            if(info.size() == 0){
                System.out.println("There is no information for this account, but it exists!");
                return false;
            }
            if(password.equals(info.get(0)[1])){
                return true;
            }
            else{
                return false;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public abstract void close();

    public void close(CustomerAccount acct){
        // Removes the account referenced in CustomerData.csv
        String searchSymbol = getSymbolForAccount(acct);
        File customerData = getCustomerData();
        try {
            List<String[]> data = new CSVReader(new FileReader(customerData)).readAll();
            for(String[] d: data){
                if(d[0].equalsIgnoreCase(this.name)){
                    String[] accounts = d[2].split(";");
                    String newData = "";
                    for(String account: accounts){
                        if(!account.startsWith(searchSymbol)){
                            if(newData.length() == 0){
                                newData+= account;
                            } else{
                                newData+= ";" + account;
                            }
                        }
                    }
                    d[2] = newData;
                }
            }
            WriteFileUtil.writeFile(customerData, data);

            // Remove the files associated with account
            File newFile = ReadFileUtil.getPathToUserDataPath("UserActivityData",this.id + "");
            newFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public String getSymbolForAccount(Account account){
        if(account instanceof CustomerAccount) {
            CustomerAccount acct = (CustomerAccount) account;
            if (acct instanceof CheckingAccount) {
                return "C";
            } else if (acct instanceof SavingAccount) {
                return "A";
            } else if (acct instanceof SecuritiesAccount) {
                return "S";
            }
        }
        return "";
    }

    // Bad method do not use, use the other overloaded transfer
    public void transfer(Account acct, double amount, int addOrSub){
        File customerData = this.getCustomerData();
        try {
            CSVReader reader = new CSVReader(new FileReader(customerData));
            List<String[]> info = reader.readAll();
            if(info.size() >= 1){
                String pathToAcctInfo = info.get(0)[2];
                String currPathToAcctInfo = pathToAcctInfo + "\\" + this.name + ".csv";
                reader = new CSVReader(new FileReader(currPathToAcctInfo));
                info = reader.readAll();
                double currentAcctBalance = Double.parseDouble(info.get(0)[1]);
                currentAcctBalance += amount*addOrSub;
                info.get(0)[1] = currentAcctBalance + "";
                CSVWriter writer = new CSVWriter(new FileWriter(currPathToAcctInfo));
                writer.writeAll(info);
                String targetPathToAcctInfo = pathToAcctInfo + "\\" + acct.id + ".csv";
                reader = new CSVReader(new FileReader(targetPathToAcctInfo));
                info = reader.readAll();
                currentAcctBalance = Double.parseDouble(info.get(0)[1]);
                currentAcctBalance += -(amount*addOrSub);
                info.get(0)[1] = currentAcctBalance + "";
                writer = new CSVWriter(new FileWriter(targetPathToAcctInfo));
                writer.writeAll(info);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }

    public void addBalance(double amount){
        try {
            this.updateBalance(amount);
        } catch (IOException | CsvException e){
            e.printStackTrace();
        }
    }
    
    public void subBalance(double amount){
        try {
            this.updateBalance(-amount);
        } catch (IOException | CsvException e){
            e.printStackTrace();
        }
    }

    public abstract void updateBalance(double valueToAdd) throws IOException, CsvException;
    
    /** transfer money to another account */
    public void transfer(CustomerAccount otherAccount, double amount){
        try{
            if(otherAccount != null && exists(otherAccount)) {
                if(ReadFileUtil.checkIfEnoughBalance(this, amount)){
                    double convertedAmount = amount;
                    if(!otherAccount.currencyType.equalsIgnoreCase(this.currencyType)){
                        CurrencyUtil currencyUtil = CurrencyUtil.getCurrencyUtil();
                        convertedAmount = currencyUtil.convert(this.currencyType,amount, otherAccount.getCurrencyType());
                    }
                    updateBalance(-amount);
                    otherAccount.updateBalance(convertedAmount);

                    // Add log to transaction history
                    addToTransactionHistory("transfer",amount,this);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }
    
    public File getCustomerData(){
        Path pathAbsolute = Paths.get("../../data/customerData.csv");
        Path pathBase = Paths.get("../../");
        Path pathRelative = pathBase.relativize(pathAbsolute);
        return new File(pathRelative.toUri());
    }

    public abstract String getBalance();

    public String getCurrencyType() {
        return currencyType;
    }

    public double getAmount() {
        return balance;
    }

    public void deposit(double amount){
        try {
            updateBalance(amount);
            addToTransactionHistory("deposit",amount,this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(double amount){
        try {
            if(ReadFileUtil.checkIfEnoughBalance(this, amount)){
                updateBalance(-amount);
                addToTransactionHistory("withdraw",amount,this);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(CustomerAccount acc){
        String fileName;
        if(acc instanceof CheckingAccount){
            fileName = "checkingAccounts";
        } else if(acc instanceof SavingAccount){
            fileName = "savingAccounts";
        } else{
            fileName = "securityAccounts";
        }
        return exists(fileName, acc.getId());
    }

    public boolean exists(String fileName, String accID){
        boolean exists = false;
        File checking = ReadFileUtil.getPathToAccountData(fileName);
        try {
            List<String[]> checkingData = new CSVReader(new FileReader(checking)).readAll();
            for(String[] data: checkingData){
                if(data[0].equals(accID)){
                    exists = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        return exists;

    }

    public void addToTransactionHistory(String transactionType, double amount, CustomerAccount otherAccount){
        String contentToAdd = transactionType + "," + LocalDateUtil.getDate() + "," + amount + "," + this.currencyType + ",approved," + this.getId();
        String contentToAddDailyReport = transactionType + "," + amount + "," + this.currencyType + ",approved" + this.id;
        if(transactionType.equalsIgnoreCase("transfer")){
            contentToAdd += "," + otherAccount.getId();
            contentToAddDailyReport += "," + otherAccount.getId();
        }
        WriteFileUtil.writeLine(ReadFileUtil.getPathToUserDataPath("UserActivityData", this.id + "").getAbsolutePath(), contentToAdd);

        // Add to daily report
        if(!ReadFileUtil.getPathToUserDataPath("dailyReport", LocalDateUtil.getDate()).exists()){
            try {
                ReadFileUtil.getPathToUserDataPath("dailyReport", LocalDateUtil.getDate()).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        WriteFileUtil.writeLine(ReadFileUtil.getPathToUserDataPath("dailyReport", LocalDateUtil.getDate()).getAbsolutePath(), contentToAddDailyReport);
    }

    public abstract void loan(double amt);
}
