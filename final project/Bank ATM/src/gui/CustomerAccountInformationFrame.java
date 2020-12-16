package gui;

import controller.CustomerAccountInformationController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CustomerAccountInformationFrame extends JFrame {

    private CustomerAccountInformationController savingController;
    private CustomerAccountInformationController checkingController;
    private CustomerAccountInformationController securityController;
    private JTabbedPane tabbedPane;
    private CustomerAccountInformationTablePanel savingTable;
    private CustomerAccountInformationTablePanel checkingTable;
    private CustomerAccountInformationTablePanel securityTable;

    public CustomerAccountInformationFrame() throws IOException {
        super("Customer Accounts Information");

        savingController = new CustomerAccountInformationController("saving");
        checkingController = new CustomerAccountInformationController("checking");
        securityController = new CustomerAccountInformationController("security");
        tabbedPane = new JTabbedPane();
        savingTable = new CustomerAccountInformationTablePanel();
        checkingTable = new CustomerAccountInformationTablePanel();
        securityTable = new CustomerAccountInformationTablePanel();

        savingTable.setData(savingController.getData());
        checkingTable.setData(checkingController.getData());
        securityTable.setData((securityController.getData()));

        savingTable.setCheckUpListener(new CheckUpListener() {
            @Override
            public void checkUp(int row) throws IOException {
                new ActivityHistoryFrame(savingController.getData().get(row).getId());
            }
        });


        tabbedPane.add("Saving accounts", savingTable);
        tabbedPane.add("Checking accounts", checkingTable);
        tabbedPane.add("Security accounts", securityTable);

        add(tabbedPane, BorderLayout.CENTER);

        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
