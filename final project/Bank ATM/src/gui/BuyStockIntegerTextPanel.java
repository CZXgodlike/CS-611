package gui;

import account.SecuritiesAccount;
import utils.NumericTextControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyStockIntegerTextPanel extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JButton confirmButton;
    private BuyStockListener listener;

    public BuyStockIntegerTextPanel(String id, SecuritiesAccount account){
        label = new JLabel("Please enter the number of stock (" + id + ") you want to buy:");
        textField = new JTextField(10);
        confirmButton = new JButton("Confirm");

        textField.setDocument(new NumericTextControl());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(textField.getText());

                if(listener != null){
                    try {
                        listener.buyStock(id, number, account);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);

        add(label, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0, 0, 0, 5);
        add(textField, gc);

        gc.gridy = 7;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 0, 0, 0);
        add(confirmButton, gc);
    }

    public void setListener(BuyStockListener listener) {
        this.listener = listener;
    }
}
