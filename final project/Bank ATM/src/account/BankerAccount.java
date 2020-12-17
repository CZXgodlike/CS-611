package account;

/**
* Class representing the banker account
*/
import assets.*;
import services.StockMarket;

import java.io.File;
import java.util.*;

public class BankerAccount extends Account {
    
    protected ArrayList<Customer> customers; // A list of all customers
    
    /** Constructor */
    public BankerAccount(){
        super();
        customers = new ArrayList<Customer>();
    }
    
    public void getDailyReport(Date date){
        
    }
    
    /** Checkup a specific customer */
//    public void checkUp(Customer customer){
//        List<Account> accounts = customer.getAccounts();
//        // display each account that customer has
//        for(Account a: accounts){
//            if(a instanceof CustomerAccount) ((CustomerAccount) a).display();
//        }
//    }
    
    /** Update price of a stock */
    public void updatePrice(Stock stock){
        // GUI DISPLAY
        String newPrice = "GUI_INPUT";
        stock.setLast(newPrice);
    }
    
    /** Add/remove/update stock from all stocks */
    public void updateStockList(StockMarket market){
        market.displayStocks();
        // USE GUI BUTTON TO SELECT OPTION ADD/REMOVE/UPDATE
        int opt = 0; //GUI_INPUT;
        if(opt == 1){
            // USE BUTTON TO GET NEW STOCK
            Stock newStock = new Stock(new ArrayList<>());//GUI_INPUT;
            market.add(newStock);
        }else if(opt == 2){
            // USE BOTTON TO SELECT STOCK TO BE REMOVED
            int stockId = 0; //GUI_INPUT;
            market.remove(stockId);
        }else{
            // USE BOTTON TO SELECT STOCK TO BE UPDATE
            int stockId = 0; //GUI_INPUT;
            updatePrice(market.get(stockId));
        }
    }
}
