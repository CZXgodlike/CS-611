package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

    public LocalDateUtil(){}

    public static String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_dd_yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

}
