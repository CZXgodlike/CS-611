package gui;

import account.SecuritiesAccount;
import assets.Stock;
import controller.StockDataController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class StockDisplayTablePanel extends JPanel {

    private JTable table;
    private StockDisplayModel tableModel;
    private StockDataController controller;
    private JPopupMenu menu;

    public StockDisplayTablePanel(SecuritiesAccount account){
        tableModel = new StockDisplayModel();
        table = new JTable(tableModel);
        controller = new StockDataController();
        menu = new JPopupMenu();

        JMenuItem buy = new JMenuItem("buy");
        menu.add(buy);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // Select row
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);

                // Show popup menu
                if(e.getButton() == MouseEvent.BUTTON3){
                    menu.show(table, e.getX(), e.getY());
                }
            }
        });

        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                new BuyStockIntegerTextFrame(tableModel.getValueAt(row, 0), account);
            }
        });

        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border outerBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
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

}
