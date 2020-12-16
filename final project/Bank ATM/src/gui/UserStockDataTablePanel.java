package gui;

import account.SecuritiesAccount;
import assets.UserStockData;
import controller.UserStockDataController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class UserStockDataTablePanel extends JPanel {

    private JTable table;
    private UserStockDataModel model;
    private SecuritiesAccount account;
    private JPopupMenu menu;
    private UserStockDataController controller;
    private SellStockListener listener;

    public UserStockDataTablePanel(SecuritiesAccount account){

        this.account = account;
        model = new UserStockDataModel();
        table = new JTable(model);
        menu = new JPopupMenu();
        controller = new UserStockDataController(account.getId());

        JMenuItem sell = new JMenuItem("sell");
        menu.add(sell);

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

        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                new SellStockIntegerTextFrame( model.getValueAt(row, 0), account);
            }
        });

        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border outerBorder = BorderFactory.createTitledBorder("Owned stocks");
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<UserStockData> data){
        model.setData(data);
    }

    public void refresh() throws IOException {
        model.setData(controller.getData());
        model.fireTableDataChanged();
    }

    public void setListener(SellStockListener listener) {
        this.listener = listener;
    }
}
