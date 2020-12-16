package account;

import assets.CustomerAccountInformation;
import assets.Stock;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import utils.LocalDateUtil;
import utils.ReadFileUtil;
import utils.WriteFileUtil;
import controller.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SecuritiesAccount extends CustomerAccount {

    public SecuritiesAccount(String accountName, double amount, String currencyType){
        super(accountName, amount, currencyType);
    }

    public SecuritiesAccount(String accountName){
        super(accountName, 0.0,"USD");
    }

    public SecuritiesAccount(){
        this("");
    }

    public boolean buy(String stockID, int numToBuy){
        StockDataController stockData = new StockDataController();
        try {
            Stock stockToBuy = stockData.getStock(stockID);
            Double stockPrice = Double.parseDouble(stockToBuy.getLast());
            Double totalStockPrice = stockPrice*numToBuy;
            if(balance < totalStockPrice){
                // Not enough balance to buy the intended amount of stock
                Double amountMissing = totalStockPrice - balance;
                return false;
            } else{
                // update balance in account
                updateBalance(-totalStockPrice);

                // Run code to add the stock to the files
                File userStockData = ReadFileUtil.getPathToUserDataPath("UserStockData", this.id+"");
                String stockContents = stockID + "," + stockToBuy.getCompany() + "," + stockToBuy.getLast() + "," + numToBuy;
                WriteFileUtil.writeLine(userStockData.getAbsolutePath(),stockContents);

                // Add to trade history
                updateTradeHistory("buy",-totalStockPrice,stockID);

                return true;
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sell(String stockID, int numToSell){
        // check if they even have the stock to be sold and amount to sell and run code to deal with both cases
        // This is written under the assumption that the user can have multiple entries for the stock in the csv file
        // because the stock could have been bought at different prices at different times
        StockDataController stockData = new StockDataController();
        try {
            Stock stockToSell = stockData.getStock(stockID);
            File userStockData = ReadFileUtil.getPathToUserDataPath("UserStockData", this.id+"");
            List<String[]> data = new CSVReader(new FileReader(userStockData)).readAll();
            boolean sold;
            int amtUserHas = 0;
            for(String[] d: data){
                // If the user has it they can sell and also count to see if they have enough to sell
                if(d[0].equalsIgnoreCase(stockID)){
                    amtUserHas+= Integer.parseInt(d[3]);
                }
            }
            // If they don't have enough of the stock to sell then return false immediately
            if(numToSell > amtUserHas){
                sold = false;
            } else{
                for(String[] d: data){
                    // break out of the loop if we have no more to sell
                    if(numToSell <= 0){
                        break;
                    }
                    // If the user has it they can sell
                    if(d[0].equalsIgnoreCase(stockID)){
                        int currentNumStock = Integer.parseInt(d[3]);
                        if(currentNumStock <= numToSell){
                            numToSell = numToSell - currentNumStock;
                            d[3] = 0+"";
                        } else{
                            d[3] = currentNumStock - numToSell + "";
                        }
                    }
                }
                // Update values in UserStockData
                WriteFileUtil.writeFile(userStockData,data);
                for(String[] d: data) {
                    if (Integer.parseInt(d[3]) == 0) {
                        WriteFileUtil.removeLineFromStockDataWhenNoneLeft(userStockData.getAbsolutePath());
                    }
                }


                // Update balance in account
                updateBalance(Double.parseDouble(stockToSell.getLast())*numToSell);

                // Add to trade history
                updateTradeHistory("sell",Double.parseDouble(stockToSell.getLast())*numToSell,stockID);

                sold = true;
            }

            return sold;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void displayOpenPositions(){

    }

    public void checkProfits(){

    }

    public void display(){
        // Connect to the GUI
    }

    public void updateBalance(double value) throws IOException, CsvException {
        balance = balance + value;
        System.out.println("security balance" + balance);
        File currAccountFile = ReadFileUtil.getPathToAccountData("securityAccounts");
        List<String[]> data = new CSVReader(new FileReader(currAccountFile)).readAll();
        for(String[] d: data){
            if(d[0].equals(this.id)){
                d[1] = "" + (balance);
            }
        }
        WriteFileUtil.writeFile(currAccountFile, data);
    }

    @Override
    public void open() {
        super.open(this);
        File securityAccountFile = ReadFileUtil.getPathToAccountData("securityAccounts");
        if(securityAccountFile.exists()){
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
                    WriteFileUtil.writeLine(securityAccountFile.getPath(), id +"," + balance + "," + currencyType);
                }
                File newFile = ReadFileUtil.getPathToUserDataPath("TradingHistory",this.id + "");
                newFile.createNewFile();
                newFile = ReadFileUtil.getPathToUserDataPath("UserStockData",this.id + "");
                newFile.createNewFile();
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
        super.close(this);
        File securityAccountFile = ReadFileUtil.getPathToAccountData("securityAccounts");
        if(securityAccountFile.exists()){
            WriteFileUtil.removeLineFromCustomerAccountData(securityAccountFile.getAbsolutePath(), this);
        }
        File deleteUserStock = ReadFileUtil.getPathToUserDataPath("UserStockData",this.id + "");
        deleteUserStock.delete();
        File deleteTradeHistory = ReadFileUtil.getPathToUserDataPath("TradingHistory",this.id + "");
        deleteTradeHistory.delete();
    }

    public void updateTradeHistory(String buyOrSell, double value, String stockID){
        File tradingHistory = ReadFileUtil.getPathToUserDataPath("TradingHistory", this.id+"");
        String tradeContents = buyOrSell+ "," + LocalDateUtil.getDate() + "," + value + "," + this.currencyType + "," + stockID;
        WriteFileUtil.writeLine(tradingHistory.getAbsolutePath(),tradeContents);
    }

    public String getBalance() {
        CustomerAccountInformationController customerInfoController = new CustomerAccountInformationController("security");
        try {
            List<CustomerAccountInformation> info = customerInfoController.getData();
            for(CustomerAccountInformation i: info){
                if(i.getId().equalsIgnoreCase("" + this.id)){
                    //System.out.println("ture");
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
        // do nothing
    }
}
