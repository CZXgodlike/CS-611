package gui;

import account.AccountFactory;
import account.CustomerAccount;
import account.SecuritiesAccount;
import assets.AccountInformation;
import controller.AssociateAccountController;
import controller.CustomerAccountInformationController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class AssociateAccountTablePanel extends JPanel {

    private JTable table;
    private AssociateAccountModel model;
    private String userName;
    private AssociateAccountController controller;
    private AccountFactory factory;
    private CustomerAccount account;

    public AssociateAccountTablePanel(String userName, CustomerMainFrame prev){
        model = new AssociateAccountModel();
        table = new JTable(model);
        controller = new AssociateAccountController();
        this.userName = userName;

        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border outerBorder = BorderFactory.createTitledBorder("Account Overview");
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new BorderLayout());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);
                String accountType = (String) table.getModel().getValueAt(row, 0);
                String id = (String) table.getModel().getValueAt(row, 1);


                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    factory = new AccountFactory(accountType, id);
                    try {
                        account = factory.createAccount();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    if(accountType.equalsIgnoreCase("security account")){
                        if(account instanceof SecuritiesAccount){
                            try {
                                new SecurityAccountMainFrame((SecuritiesAccount) account);
                                //prev.dispose();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    } else {
                        new AccountWindow(account, prev);
                    }
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<AccountInformation> data){model.setData(data);}

    public void refresh() throws IOException {
        model.setData(controller.getData(userName));
        model.fireTableDataChanged();
    }

}
