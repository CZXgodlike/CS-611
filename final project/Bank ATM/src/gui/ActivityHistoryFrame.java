package gui;

import controller.ActivityHistoryController;

import javax.swing.*;
import java.io.IOException;

public class ActivityHistoryFrame extends JFrame {

    private ActivityHistoryTablePanel tablePanel;
    private ActivityHistoryController controller;

    public ActivityHistoryFrame(String id) throws IOException {
        super("Activity history");

        String path = "data/UserActivityData/" + id + ".csv";
        //System.out.println(path);
        tablePanel = new ActivityHistoryTablePanel();
        controller = new ActivityHistoryController(path);

        tablePanel.setData(controller.getData());

        setSize(750, 500);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(tablePanel);
    }
}
