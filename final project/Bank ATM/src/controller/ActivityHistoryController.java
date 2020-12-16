package controller;

import assets.ActivityHistory;
import assets.Stock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityHistoryController extends DataController{

    public ActivityHistoryController(String path){
        super(path);
    }

    @Override
    public List<ActivityHistory> getData() throws IOException {
        return readData();
    }

    private List<ActivityHistory> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(dataPath));
        List<ActivityHistory> activityHistories = new ArrayList<>();
        String row;
        //csvReader.readLine(); //skip the first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            activityHistories.add(new ActivityHistory(Arrays.asList(data)));
        }
        csvReader.close();

        return activityHistories;
    }
}
