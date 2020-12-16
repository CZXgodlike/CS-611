package gui;

import assets.Stock;
import com.opencsv.exceptions.CsvException;
import controller.StockDataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class TablePanel extends JPanel {

    private JTable table;
    private StockTableModel tableModel;
    private JPopupMenu popupMenu;
    private StockDataListener listener;
    private StockDataController controller;

    public TablePanel(){

//        tableModel = new StockTableModel(this);
        table = new JTable(tableModel);
        popupMenu = new JPopupMenu();
        controller = new StockDataController();

        JMenuItem deleteRow = new JMenuItem("delete");
        popupMenu.add(deleteRow);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // Select row
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);

                // Show popup menu
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });

        deleteRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if(listener != null){
                    try {
                        listener.rowDeleted(row);
                    } catch (IOException | CsvException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridBagLayout());

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Stock> db){
        tableModel.setData(db);
    }

    public void refresh() throws IOException {
        setData(controller.getData());
        tableModel.fireTableDataChanged();
    }

    public void setStockDataListener(StockDataListener stockDataListener) {
        this.listener = stockDataListener;
    }
}
