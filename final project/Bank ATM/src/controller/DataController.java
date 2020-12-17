/**
 * Super class of controllers
 */
package controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class DataController {

    protected String dataPath;

    public DataController(){}

    public DataController(String dataPath){
        this.dataPath = dataPath;
    }

    public void deleteData(int row) throws IOException, CsvException {
        CSVReader reader2 = new CSVReader(new FileReader(dataPath));
        List<String[]> allElements = reader2.readAll();
        allElements.remove(row);
        FileWriter sw = new FileWriter(dataPath);
        CSVWriter writer = new CSVWriter(sw, CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        writer.writeAll(allElements);
        writer.close();
    }

    public abstract Object getData() throws IOException;


}
