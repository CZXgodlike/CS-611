/**
 * Daily report window
 */
package gui;

import controller.DailyReportController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DailyReportFrame extends JFrame {

    private DailyReportController controller;
    private DailyReportTablePanel tablePanel;

    public DailyReportFrame() throws IOException {
        super("Daily report");

        tablePanel = new DailyReportTablePanel();
        controller = new DailyReportController();

        tablePanel.setData(controller.getData());

        setSize(750, 500);
        setVisible(true);

        add(tablePanel, BorderLayout.CENTER);

    }
}
