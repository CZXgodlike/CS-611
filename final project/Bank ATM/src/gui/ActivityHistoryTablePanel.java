package gui;

import assets.ActivityHistory;
import controller.ActivityHistoryController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class ActivityHistoryTablePanel extends JPanel {

    private JTable table;
    private ActivityHistoryModel model;

    public ActivityHistoryTablePanel(){
        model = new ActivityHistoryModel();
        table = new JTable(model);

        Border innerBorder = BorderFactory.createTitledBorder("Activity history");
        Border outerBorder = BorderFactory.createEmptyBorder(0, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<ActivityHistory> data){
        model.setData(data);
    }
}
