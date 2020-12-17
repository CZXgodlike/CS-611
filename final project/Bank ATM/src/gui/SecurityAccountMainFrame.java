/**
 * Security account main frame
 */
package gui;

import account.SecuritiesAccount;
import controller.UserStockDataController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SecurityAccountMainFrame extends JFrame {
    private UserStockDataTablePanel tablePanel;
    private UserStockDataController controller;
    private RefreshToolBar toolBar;
    private SecurityAccountToolBar buttonToolBar;

    public SecurityAccountMainFrame(SecuritiesAccount account) throws IOException {

        tablePanel = new UserStockDataTablePanel(account);
        controller = new UserStockDataController(account.getId());
        toolBar = new RefreshToolBar();
        buttonToolBar = new SecurityAccountToolBar(account);

        setSize(750, 500);
        setVisible(true);

        tablePanel.setData(controller.getData());

        add(toolBar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonToolBar, BorderLayout.SOUTH);

        toolBar.setRefreshListener(new RefreshListener() {
            @Override
            public void refreshEventOccurred() throws IOException {
                tablePanel.refresh();
                buttonToolBar.refresh();
            }
        });
    }
}
