package gui;

import assets.AccountInformation;
import assets.AssociateAccountInformation;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AssociateAccountModel extends AbstractTableModel {

    private List<AccountInformation> db;
    private String[] colNames = {"Account type", "Account ID"};

    @Override
    public int getRowCount() {
        if(db == null){
            return 0;
        }
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AccountInformation information = db.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> information.getType();
            case 1 -> information.getId();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setData(List<AccountInformation> db) {
        this.db = db;
    }
}
