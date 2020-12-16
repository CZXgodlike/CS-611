package gui;

import assets.CustomerAccountInformation;
import assets.Stock;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CustomerAccountInformationModel extends AbstractTableModel {

    private List<CustomerAccountInformation> db;
    private String[] colNames = {"ID", "Balance", "Currency Type"};

    public CustomerAccountInformationModel(){}

    public void setData(List<CustomerAccountInformation> db) {
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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CustomerAccountInformation information = db.get(rowIndex);

        return switch (columnIndex){
            case 0 -> information.getId();
            case 1 -> information.getBalance();
            case 2 -> information.getCurrencyType();
            default -> null;
        };
    }
}
