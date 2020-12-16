/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Login window for BankATM
 */

package gui;

import utils.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import account.*;

public class TransferWindow extends JFrame {
    private JFrame frame = new JFrame("Transfer Money");
    private JPanel panel = new JPanel();
    private JLabel transLabel = new JLabel("Amount Transfer:");
    private JTextField transText = new JTextField();
    private JLabel aAccLabel = new JLabel("Transfer to (Account ID):");
    private JTextField aAccText = new JTextField();
    private JButton confirmButton = new JButton("confirm");
    private JButton backButton = new JButton("back");
    private JButton submitButton = new JButton("Submit");

    private CustomerAccount curAccount;
    private JFrame prevWin;
    private AccountWindow window;
    
    public TransferWindow(CustomerAccount curAccount, JFrame prevWin, AccountWindow window){
        this.curAccount = curAccount;
        this.prevWin = prevWin;
        this.window = window;
        this.transText.setDocument(new NumericTextControl());
        initListener();
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 200);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        displayInfo(curAccount);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void displayInfo(CustomerAccount curAccount){
        if (curAccount instanceof CheckingAccount){
            panel.add(new JLabel("Checking Account "+curAccount.getId()));
        }else{
            panel.add(new JLabel("Saving Account "+curAccount.getId()));
        }
        panel.add(new JLabel("Current Balance: "+curAccount.getAmount()+" "+curAccount.getCurrencyType()));
    }
    
    private void placeComponents(JPanel panel){
        // Transfer money
        transLabel.setBounds(30, 30, 80, 25);
        panel.add(transLabel);
        transText.setBounds(105, 30, 165, 25);
        panel.add(transText);
        // Transfer money
        aAccLabel.setBounds(30, 60, 80, 25);
        panel.add(aAccLabel);
        aAccText.setBounds(105, 60, 165, 25);
        panel.add(aAccText);
        // confirm button
        confirmButton.setBounds(25, 100, 80, 25);
        panel.add(confirmButton);
        // back buttom
        backButton.setBounds(190, 100, 80, 25);
        panel.add(backButton);
        // Need to add submit button
    }
    
    public void initListener(){
        confirmButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String money = transText.getText();
                String id = aAccText.getText();
                CustomerAccount account = null;
                try {
                    account = new AccountFactory(id).findAccount();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
//                JOptionPane.showMessageDialog(null,"Transfered "+money+" to account"+aAcc);
                // add to account object
                frame.dispose();
                curAccount.transfer(account, Double.parseDouble(money));
                window.refresh();
                prevWin.setVisible(true);
            }
        });
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                prevWin.setVisible(true);
            }
        });
    }
}
