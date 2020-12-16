package assets;

import java.util.List;

public class Stock {

    private String symbol;
    private String company;
    private String last;
    private String change;
    private String change_percentage;
    private String volume;
    private String traded;

    public Stock(String symbol, String company, String last,
                 String change, String change_percentage, String volume, String traded){
        this.symbol = symbol;
        this.company = company;
        this.last = last;
        this.change = change;
        this.change_percentage = change_percentage;
        this.volume = volume;
        this.traded = traded;
    }

    public Stock(List<String> data){
        symbol = data.get(0);
        company = data.get(1);
        last = data.get(2);
        change = data.get(3);
        change_percentage = data.get(4);
        volume = data.get(5);
        traded = data.get(6);
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChange_percentage() {
        return change_percentage;
    }

    public void setChange_percentage(String change_percentage) {
        this.change_percentage = change_percentage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTraded() {
        return traded;
    }

    public void setTraded(String traded) {
        this.traded = traded;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
