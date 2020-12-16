package controller;

import assets.Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerStockDataController extends DataController{

    public CustomerStockDataController(){
        dataPath = "data/customerStockData";
    }

    private List<Stock> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("data/stockData.csv"));
        List<Stock> stocks = new ArrayList<>();
        String row;
        //csvReader.readLine(); //skip the first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            stocks.add(new Stock(Arrays.asList(data)));
        }
        csvReader.close();

        return stocks;
    }

    @Override
    public Object getData() throws IOException {
        return null;
    }
}
