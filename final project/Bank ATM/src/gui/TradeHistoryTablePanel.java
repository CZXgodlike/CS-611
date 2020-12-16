package gui;

import assets.TradeHistory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class TradeHistoryTablePanel extends JPanel{

    private TradeHistoryModel model;
    private JTable table;

    public TradeHistoryTablePanel(){
        model = new TradeHistoryModel();
        table = new JTable(model);

        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border outerBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<TradeHistory> data){
        model.setData(data);
    }
}
