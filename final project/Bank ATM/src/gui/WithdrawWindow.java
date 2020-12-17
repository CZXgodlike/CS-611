/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Withdraw window for BankATM
 */

package gui;

import utils.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import account.*;

public class WithdrawWindow extends JFrame {
    private JFrame frame = new JFrame("Withdraw Money");
    private JPanel panel = new JPanel();
    private JLabel withdrawLabel = new JLabel("Amount withdraw:");
    private JTextField withdrawText = new JTextField();
    private JButton confirmButton = new JButton("confirm");
    private JButton backButton = new JButton("back");

    private CustomerAccount curAccount;
    private JFrame prevWin;
    private AccountWindow window;
    
    public WithdrawWindow(CustomerAccount curAccount, JFrame prevWin, AccountWindow window){
        this.curAccount = curAccount;
        this.prevWin = prevWin;
        this.window = window;
        this.withdrawText.setDocument(new NumericTextControl());
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
        }else if (curAccount instanceof SavingAccount){
            panel.add(new JLabel("Saving Account "+curAccount.getId()));
        }else{
            panel.add(new JLabel("Securities Account "+curAccount.getId()));
        }
        panel.add(new JLabel("Current Balance: "+curAccount.getAmount()+" "+curAccount.getCurrencyType()));
    }
    
    private void placeComponents(JPanel panel){
        // Withdraw money
        withdrawLabel.setBounds(30, 30, 80, 25);
        panel.add(withdrawLabel);
        withdrawText.setBounds(105, 30, 165, 25);
        panel.add(withdrawText);
        // confirm button
        confirmButton.setBounds(25, 100, 80, 25);
        panel.add(confirmButton);
        // back buttom
        backButton.setBounds(190, 100, 80, 25);
        panel.add(backButton);
    }
    
    public void initListener(){
        confirmButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                double money = Double.parseDouble(withdrawText.getText());
                // check if enough money
                if(Double.parseDouble(curAccount.getBalance())<money){
                    JOptionPane.showMessageDialog(null,"Don't have enough money in account.");
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Withdraw "+money+" from current account.");
                    // add to account object
                    curAccount.withdraw(money);
                    frame.dispose();
                    window.refresh();
                }
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
