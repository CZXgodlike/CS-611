/*
package gui;

import controller.AssociateAccountController;

import javax.swing.*;
import java.awt.*;

public class AssociateAccountFrame extends JFrame {

    private AssociateAccountTablePanel tablePanel;
    private AssociateAccountController controller;

    public AssociateAccountFrame(String userName){

        super("Accounts");

        tablePanel = new AssociateAccountTablePanel(userName, this);
        controller = new AssociateAccountController();

        tablePanel.setData(controller.getData(userName));

        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(tablePanel, BorderLayout.CENTER);
    }

}
*/
