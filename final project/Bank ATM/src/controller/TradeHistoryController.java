/**
 * Class represents the controller of trading history csv file
 */
package controller;

import assets.Stock;
import assets.TradeHistory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TradeHistoryController extends DataController{

    public TradeHistoryController() {
    }

    public TradeHistoryController(String accountID){
        dataPath = "data/TradingHistory/" + accountID + ".csv";
    }

    @Override
    public List<TradeHistory> getData() throws IOException {
        return readData();
    }

    private List<TradeHistory> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(dataPath));
        List<TradeHistory> histories = new ArrayList<>();
        String row;
        //csvReader.readLine(); //skip the first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            histories.add(new TradeHistory(Arrays.asList(data)));
        }
        csvReader.close();

        return histories;
    }
}
