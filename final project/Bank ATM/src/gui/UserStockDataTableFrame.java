package gui;

import account.SecuritiesAccount;
import controller.UserStockDataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class UserStockDataTableFrame extends JFrame {

    private UserStockDataTablePanel tablePanel;
    private UserStockDataController controller;
    private RefreshToolBar toolBar;

    public UserStockDataTableFrame(SecuritiesAccount account) throws IOException {
        tablePanel = new UserStockDataTablePanel(account);
        controller = new UserStockDataController(account.getId());
        toolBar = new RefreshToolBar();

        setSize(750, 500);
        setVisible(true);

        tablePanel.setData(controller.getData());

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
