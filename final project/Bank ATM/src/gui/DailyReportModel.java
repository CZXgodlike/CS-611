package gui;

import assets.DailyTransaction;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DailyReportModel extends AbstractTableModel {

    private List<DailyTransaction> data;
    private String[] colNames = {"Transaction type", "Amount", "Currency type", "Status", "Action account ID", "Associate account ID"};

    public DailyReportModel(){}



    @Override
    public int getRowCount() {
        if(data == null){
            return 0;
        }
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DailyTransaction transaction = data.get(rowIndex);

        return switch (columnIndex){
            case 0 -> transaction.getType();
            case 1 -> transaction.getAmount();
            case 2 -> transaction.getCurrencyType();
            case 3 -> transaction.getStatus();
            case 4 -> transaction.getActionID();
            case 5 -> transaction.getAssociateID();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setData(List<DailyTransaction> data) {
        this.data = data;
    }
}
