/**
 * Window displaying the accounts associate to the customer
 */
package gui;

import controller.AssociateAccountController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CustomerMainFrame extends JFrame {

    private AssociateAccountTablePanel tablePanel;
    private AssociateAccountController controller;
    private CustomerToolBar toolBar;

    public CustomerMainFrame(String userName){

        super("Accounts");

        tablePanel = new AssociateAccountTablePanel(userName, this);
        controller = new AssociateAccountController();
        toolBar = new CustomerToolBar(userName, this);


        tablePanel.setData(controller.getData(userName));

        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(tablePanel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.SOUTH);
    }

    public void refresh() throws IOException {
        tablePanel.refresh();
    }
}
