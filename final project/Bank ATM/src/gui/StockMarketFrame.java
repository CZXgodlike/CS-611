/**
 * Window for displaying stock price
 */
package gui;

import account.SecuritiesAccount;
import controller.StockDataController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StockMarketFrame extends JFrame {
    private StockDisplayTablePanel tablePanel;
    private StockDataController controller;
    private RefreshToolBar toolBar;

    public StockMarketFrame(SecuritiesAccount account) throws IOException {
        super("Stock Market");

        tablePanel = new StockDisplayTablePanel(account);
        controller = new StockDataController();
        toolBar = new RefreshToolBar();

        tablePanel.setData(controller.getData());

        setSize(750, 500);
        setVisible(true);

        add(toolBar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        toolBar.setRefreshListener(new RefreshListener() {
            @Override
            public void refreshEventOccurred() throws IOException {
                tablePanel.refresh();
            }
        });
    }
}
