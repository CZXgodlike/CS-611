/**
 * Class used for storing a line of stock owned by user from csv
 */
package assets;

import controller.StockDataController;

import java.io.IOException;
import java.util.List;

public class UserStockData {

    private String id;
    private String name;
    private String boughtPrice;
    private String owned;
    private String currentPrice;
    private StockDataController controller;

    public UserStockData(List<String> data) throws IOException {
        controller = new StockDataController();
        id = data.get(0);
        name = data.get(1);
        boughtPrice = data.get(2);
        owned = data.get(3);
        currentPrice = controller.getCurrentPrice(id);
    }

    public UserStockData() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBoughtPrice() {
        return boughtPrice;
    }

    public String getOwned() {
        return owned;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }
}
