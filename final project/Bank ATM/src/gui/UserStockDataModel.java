package gui;

import assets.UserStockData;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserStockDataModel extends AbstractTableModel {

    private List<UserStockData> data;
    private String[] colNames = {"Stock ID", "Stock name", "Bought price", "Current Price", "Owned"};

    public UserStockDataModel(){
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public int getRowCount() {
        if(data == null){
            return 0;
        }
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        UserStockData stockData = data.get(rowIndex);

        return switch (columnIndex){
            case 0 -> stockData.getId();
            case 1 -> stockData.getName();
            case 2 -> stockData.getBoughtPrice();
            case 3 -> stockData.getCurrentPrice();
            case 4 -> stockData.getOwned();
            default -> null;
        };
    }

    public void setData(List<UserStockData> data) {
        this.data = data;
    }
}
