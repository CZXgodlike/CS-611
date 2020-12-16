package Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public FileReader(){}

    public List<List<String>> readTable(String pathname){
        Scanner sc = null;
        List<List<String>> table = new ArrayList<>();

        try {
            sc = new Scanner(new File(pathname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lineNumber = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if(s.equals("")){
                continue;
            }
            ArrayList<String> S;
            if (lineNumber == 0){
                S = new ArrayList<String>(Arrays.asList(s.split("/")));
            }
            else {
                S = new ArrayList<String>(Arrays.asList(s.split("\\s+")));
            }
            lineNumber++;
            table.add(S);
        }
        return table;
    }

    public StringBuilder readGraphic(String pathname) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(pathname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            sb.append(s);
            sb.append('\n');
        }
        return sb;
    }
}
