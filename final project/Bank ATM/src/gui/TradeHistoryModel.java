package gui;

import assets.TradeHistory;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TradeHistoryModel extends AbstractTableModel {

    private List<TradeHistory> data;
    private String[] colNames = {"Trading type", "Date", "Profit", "Currency type", "Stock ID"};

    public TradeHistoryModel(){}

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TradeHistory history = data.get(rowIndex);

        return switch (columnIndex){
            case 0 -> history.getTradingType();
            case 1 -> history.getDate();
            case 2 -> history.getAmount();
            case 3 -> history.getCurrencyType();
            case 4 -> history.getStockID();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setData(List<TradeHistory> data) {
        this.data = data;
    }
}
