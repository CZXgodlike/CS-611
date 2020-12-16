package controller;

import assets.DailyTransaction;
import assets.Stock;
import utils.LocalDateUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyReportController extends DataController{

    private LocalDateUtil dateUtil;

    public DailyReportController(){
        dateUtil = new LocalDateUtil();
        String date = dateUtil.getDate();
        dataPath = "data/dailyReport/" + date + ".csv";
    }

    @Override
    public List<DailyTransaction> getData() throws IOException {
        return readData();
    }

    private List<DailyTransaction> readData() throws IOException {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(dataPath));
        } catch (FileNotFoundException e) {
            return null;
        }
        List<DailyTransaction> transactions = new ArrayList<>();
        String row;
        //csvReader.readLine(); //skip the first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            transactions.add(new DailyTransaction(Arrays.asList(data)));
        }
        csvReader.close();

        return transactions;
    }

}
