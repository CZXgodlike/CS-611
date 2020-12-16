package gui;

import assets.CustomerAccountInformation;
import controller.CustomerAccountInformationController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class CustomerAccountInformationTablePanel extends JPanel {
    private JTable table;
    private CustomerAccountInformationModel model;
    private JPopupMenu popupMenu;
    private CheckUpListener listener;

    public CustomerAccountInformationTablePanel(){
        model = new CustomerAccountInformationModel();
        table = new JTable(model);
        popupMenu = new JPopupMenu();

        JMenuItem checkUp = new JMenuItem("check up");
        popupMenu.add(checkUp);

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

        checkUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if(listener != null){
                    try {
                        listener.checkUp(row);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border outerBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<CustomerAccountInformation> db){
        model.setData(db);
    }

//    public void refresh() throws IOException {
//        setData(controller.getData());
//        model.fireTableDataChanged();
//    }

    public void setCheckUpListener(CheckUpListener listener) {
        this.listener = listener;
    }

}
