package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CurrencyUtil {

    private CurrencyUtil(){}

    public static CurrencyUtil getCurrencyUtil(){
        return new CurrencyUtil();
    }

    public double convert(String currency1, double value, String currency2) throws IOException, CsvException {
        // Returns the value in the currency of the second currency
        File conversionRates = ReadFileUtil.getPathToConversionData();
        CSVReader reader = new CSVReader(new FileReader(conversionRates));
        List<String[]> data = reader.readAll();
        double rateCurrency1 = 0.0;
        double rateCurrency2 = 0.0;
        for(String[] d: data){
            if(d[0].equalsIgnoreCase(currency1)){
                rateCurrency1 = Double.parseDouble(d[1]);
            }
            if(d[0].equalsIgnoreCase(currency2)){
                rateCurrency2 = Double.parseDouble(d[1]);
            }
        }
        return value*(rateCurrency2/rateCurrency1);
    }
}
