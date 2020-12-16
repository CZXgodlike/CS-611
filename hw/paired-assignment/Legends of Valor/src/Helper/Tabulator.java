package Helper;

import java.util.ArrayList;
import java.util.List;

public class Tabulator {

    private List<List<String>> table;

    public Tabulator(){}

    public Tabulator(List<List<String>> table){
        this.table = table;
    }

    public void printTable(int spacing) {
        List<Integer> maxLengths = findMaxLengths();

        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[43m");
        sb.append("\u001B[1;90m");
        for (int i = 0; i < table.size(); i++){
            if(i == 1){
                sb.append("\u001B[30m");
            }
            for (int j = 0; j < table.get(0).size(); j++){
                String currentValue = table.get(i).get(j);
                sb.append(currentValue);
                sb.append(" ".repeat(maxLengths.get(j) - currentValue.length() + spacing));
            }
            sb.append('\n');
            if(i == 0){
                sb.append("-".repeat(sb.length() - spacing - 12));
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }

    private List<Integer> findMaxLengths() {
        ArrayList<Integer> maxLengths = new ArrayList<>();
        for(int i = 0; i < table.get(0).size(); i++){
            int maxLength = 0;
            for (List<String> strings : table) {
                String value = strings.get(i);
                if (value.length() > maxLength) {
                    maxLength = value.length();
                }
            }
            maxLengths.add(maxLength);
        }
        return maxLengths;
    }
}
