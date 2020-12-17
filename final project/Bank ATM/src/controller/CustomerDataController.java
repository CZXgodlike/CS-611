/**
 * Class represents the controller of customer data csv file
 */
package controller;

import assets.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerDataController extends DataController{

    public CustomerDataController(){
        dataPath = "data";
    }

    private List<Stock> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("customerData.csv"));
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
