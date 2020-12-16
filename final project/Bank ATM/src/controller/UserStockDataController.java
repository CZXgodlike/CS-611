package controller;


import assets.ActivityHistory;
import assets.UserStockData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserStockDataController extends DataController {

    public UserStockDataController(String userName){
        dataPath = "data/UserStockData/" + userName + ".csv";
    }

    @Override
    public List<UserStockData> getData() throws IOException {
        return readData();
    }

    private List<UserStockData> readData() throws IOException {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(dataPath));
        } catch (FileNotFoundException e) {
            return null;
        }
        List<UserStockData> dataList = new ArrayList<>();
        String row;
        //csvReader.readLine(); //skip the first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            dataList.add(new UserStockData(Arrays.asList(data)));
        }
        csvReader.close();

        return dataList;
    }

}
