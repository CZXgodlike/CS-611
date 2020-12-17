/**
 * Bottom toolbar of the banker account main window
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BankerToolBar extends JPanel {

    private JButton updateStockButton;
    private JButton dailyReportButton;

    public BankerToolBar(){
        updateStockButton = new JButton("Update Stock");
        dailyReportButton = new JButton("Daily Report");

        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(updateStockButton);
        add(dailyReportButton);

        updateStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new StockDataManagementMainFrame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        dailyReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DailyReportFrame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
