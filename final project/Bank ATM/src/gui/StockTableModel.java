package gui;

import assets.Stock;
import controller.StockDataController;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.util.List;

public class StockTableModel extends AbstractTableModel {

    private List<Stock> db;
    private String[] colNames = {"Symbol", "Company", "Last", "Chng", "% Chng", "Volume", "$ Traded"};
    private StockDataController controller;
    private StockDataEditTablePanel table;


    public StockTableModel(StockDataEditTablePanel table) {
        this.controller = new StockDataController();
        this.table = table;
    }

    public void setData(List<Stock> db) {
        this.db = db;
        this.controller = new StockDataController();
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            controller.setDataAt((String) aValue, rowIndex, columnIndex);
            table.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public Object getValueAt(int rowIndex, int columnIndex) {
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
