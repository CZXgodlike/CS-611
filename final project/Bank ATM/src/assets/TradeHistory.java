package assets;

import java.util.List;

public class TradeHistory {

    private String tradingType;
    private String date;
    private String amount;
    private String currencyType;
    private String stockID;

    public TradeHistory(List<String> data){
        tradingType = data.get(0);
        date = data.get(1);
        amount = data.get(2);
        currencyType = data.get(3);
        stockID = data.get(4);
    }

    public TradeHistory() {
    }

    public String getTradingType() {
        return tradingType;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getStockID() {
        return stockID;
    }
}
