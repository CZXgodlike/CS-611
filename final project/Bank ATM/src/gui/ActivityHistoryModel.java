package gui;

import assets.ActivityHistory;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ActivityHistoryModel extends AbstractTableModel {

    private List<ActivityHistory> db;
    private String[] colNames = {"Activity type", "Date", "Amount", "Currency type", "Status", "Associate account"};

    public ActivityHistoryModel(){
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ActivityHistory history = db.get(rowIndex);

        return switch (columnIndex){
            case 0 -> history.getActivityType();
            case 1 -> history.getDate();
            case 2 -> history.getAmount();
            case 3 -> history.getCurrencyType();
            case 4 -> history.getStatus();
            case 5 -> history.getTargetID();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setData(List<ActivityHistory> db) {
        this.db = db;
    }
}
