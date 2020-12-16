package controller;

import assets.Stock;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import gui.AddStockEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockDataController extends DataController{

    //private StockDatabase db = new StockDatabase();

    public StockDataController(){
        dataPath = "data/stockData/stockData.csv";
    }

    public List<Stock> getData() throws IOException {
        return readData();
    }

    private List<Stock> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("data/stockData/stockData.csv"));
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

    public void addData(AddStockEvent e) throws IOException {
        String symbol = e.getSymbol();
        String company = e.getCompany();
        String last = e.getLast();
        String change = e.getChange();
        String change_percentage = e.getChange_percentage();
        String volume = e.getVolume();
        String traded = e.getTraded();

//        Stock stock = new Stock(symbol, company, last, change, change_percentage, volume, traded);
//        db.addStock(stock);

        FileWriter csvWriter = new FileWriter("data/stockData/stockData.csv", true);
        csvWriter.append(symbol);
        csvWriter.append(",");
        csvWriter.append(company);
        csvWriter.append(",");
        csvWriter.append(last);
        csvWriter.append(",");
        csvWriter.append(change);
        csvWriter.append(",");
        csvWriter.append(change_percentage);
        csvWriter.append(",");
        csvWriter.append(volume);
        csvWriter.append(",");
        csvWriter.append(traded);
        csvWriter.append("\n");

        csvWriter.flush();
        csvWriter.close();
    }

    public void setDataAt(String value, int row, int col) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(dataPath));
        List<String[]> csvBody = null;
        try {
            csvBody = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
// get CSV row column  and replace with by using row and column
        assert csvBody != null;
        csvBody.get(row)[col] = value;
        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter(dataPath), CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }

    public String getCurrentPrice(String id) throws IOException {
        for(Stock stock: readData()){
            if(id.equals(stock.getSymbol())){
                return stock.getLast();
            }
        }
        return null;
    }

    public Stock getStock(String id) throws IOException {
        for(Stock stock: readData()){
            if(id.equals(stock.getSymbol())){
                return stock;
            }
        }
        return null;
    }

}
