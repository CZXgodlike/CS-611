/**
 * Window for entering the number of stocks customer wants to buy
 */
package gui;

import account.SecuritiesAccount;

import javax.swing.*;

public class SellStockIntegerTextFrame extends JFrame {

    private SellStockIntegerTextPanel panel;

    public SellStockIntegerTextFrame(String id, SecuritiesAccount account){
        super();

        panel = new SellStockIntegerTextPanel(id, account);

        panel.setListener(new SellStockListener() {
            @Override
            public void sellStock(String id, int number, SecuritiesAccount account) {

                if(account.sell(id, number)){
                    JOptionPane.showMessageDialog(new JFrame(), "Sold successfully!");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Failed to sell!", "Oops!", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

        add(panel);

        setVisible(true);
        setSize(450,200);
    }
}
