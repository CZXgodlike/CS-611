/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * GUI to diplay and take action in an customer account
 */

package gui;

import utils.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import account.*;

public class AccountWindow extends JFrame {

    private JFrame frame = new JFrame("Account Summary");
    private JPanel panel = new JPanel();
    private JButton depButton = new JButton("Deposit");
    private JButton wdButton = new JButton("Withdraw");
    private JButton transButton = new JButton("Transfer");
    private JButton actButton = new JButton("View Activity");
    private JButton secButton = new JButton("Open Security Account");
    private JButton loanButton = new JButton("Loan");
    private JButton backButton = new JButton("Back");
    private JLabel balanceLable;
    private AccountWindow thisWindow;

    private CustomerAccount curAccount;
    private CustomerMainFrame prevWin;

    public AccountWindow(CustomerAccount curAccount, CustomerMainFrame prevWin){
        this.curAccount = curAccount;
        this.prevWin = prevWin;
        thisWindow = this;
        initListener();
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 450);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        displayInfo(curAccount);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void displayInfo(CustomerAccount curAccount){
        if (curAccount instanceof CheckingAccount){
            //System.out.println(curAccount instanceof CheckingAccount);
            JLabel idLable = new JLabel("Checking Account: "+curAccount.getId());
            idLable.setBounds(25, 10, 400, 25);
            panel.add(idLable);
            loanButton.setBounds(150, 110, 130, 25);
            panel.add(loanButton);
        }else if (curAccount instanceof SavingAccount){
            //System.out.println(curAccount instanceof SavingAccount);
            JLabel idLable = new JLabel("Saving Account: "+curAccount.getId());
            idLable.setBounds(25, 10, 400, 25);
            panel.add(idLable);
            if(Double.parseDouble(curAccount.getBalance())>5000){
                secButton.setBounds(150, 110, 130, 25);
                panel.add(secButton);
            }
        }else{
            JLabel idLable = new JLabel("Security Account: "+curAccount.getId());
            idLable.setBounds(25, 10, 400, 25);
            panel.add(idLable);
        }
        balanceLable = new JLabel("Current Balance: "+curAccount.getBalance()+" "+curAccount.getCurrencyType());
        balanceLable.setBounds(25, 20, 400, 25);
        panel.add(balanceLable);
    }

    private void placeComponents(JPanel panel){
        // deposit button
        depButton.setBounds(25, 50, 80, 25);
        panel.add(depButton);
        // withdraw buttom
        wdButton.setBounds(25, 80, 80, 25);
        panel.add(wdButton);
        // trans buttom
        transButton.setBounds(25, 110, 80, 25);
        panel.add(transButton);
        // act button
        actButton.setBounds(150, 50, 130, 25);
        panel.add(actButton);
    }

    public void initListener(){
        depButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                //setVisible(false);
                new DepositWindow(curAccount, frame, thisWindow);
            }
        });

        wdButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                new WithdrawWindow(curAccount, frame, thisWindow );
            }
        });

        loanButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                new LoanWindow(curAccount, frame, thisWindow );
            }
        });

        transButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                new TransferWindow(curAccount, frame, thisWindow);
            }
        });

        actButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //frame.dispose();
                try {
                    new ActivityHistoryFrame(curAccount.getId());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        secButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                new OpenSecAccountWindow(prevWin, curAccount.getName());
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

    public void refresh(){
        balanceLable.setText("Current Balance: "+curAccount.getBalance()+" "+curAccount.getCurrencyType());
        balanceLable.setBounds(25, 20, 400, 25);
    }


}

