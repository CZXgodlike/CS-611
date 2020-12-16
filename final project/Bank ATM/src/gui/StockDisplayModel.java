package gui;

import assets.Stock;
import controller.StockDataController;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StockDisplayModel extends AbstractTableModel {

    private List<Stock> db;
    private String[] colNames = {"Symbol", "Company", "Last", "Chng", "% Chng", "Volume", "$ Traded"};

    public StockDisplayModel(){
    }

    public void setData(List<Stock> db) {
        this.db = db;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Stock stock = db.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> stock.getSymbol();
            case 1 -> stock.getCompany();
            case 2 -> stock.getLast();
            case 3 -> stock.getChange();
            case 4 -> stock.getChange_percentage();
            case 5 -> stock.getVolume();
            case 6 -> stock.getTraded();
            default -> null;
        };
    }
}
