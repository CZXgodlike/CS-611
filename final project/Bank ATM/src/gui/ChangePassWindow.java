/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Change password window for BankATM
 */

package gui;

import utils.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import assets.*;

public class ChangePassWindow extends JFrame {
    private JFrame frame = new JFrame("Reset Password");
    private JPanel panel = new JPanel();
    private JLabel passLabel = new JLabel("New password:");
    private JPasswordField passText = new JPasswordField();
    private JLabel passLabel2 = new JLabel("Retype password:");
    private JPasswordField passText2 = new JPasswordField();
    private JButton submitButton = new JButton("Confirm");
    private JButton backButton = new JButton("Back");
    
    private CustomerMainFrame prevWin;
    private Customer customer;
    
    public ChangePassWindow(CustomerMainFrame prevWin, Customer c){
        this.prevWin = prevWin;
        this.customer = c;
        initListener();
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 200);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        // Enter password
        panel.setLayout(null);
        passLabel.setBounds(25, 30, 100, 25);
        panel.add(passLabel);
        passText.setBounds(135, 30, 165, 25);
        panel.add(passText);
        // Re enter pass
        panel.setLayout(null);
        passLabel.setBounds(25, 60, 100, 25);
        panel.add(passLabel2);
        passText.setBounds(135, 60, 165, 25);
        panel.add(passText2);
        // Button
        submitButton.setBounds(25, 100, 80, 25);
        panel.add(submitButton);
        backButton.setBounds(190, 100, 80, 25);
        panel.add(backButton);
    }
    
    public void initListener(){
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // check if user name exist in customer accounts
                String pass = passText.getText();
                String pass2 = passText2.getText();
                // if 2 pass match, change pass
                if (pass.equals(pass2)){
                    customer.setPass(pass);
                    frame.dispose();
                    prevWin.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Password not match.");
                    return;
                }
            }
        });
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    prevWin.setVisible(true);
            }
        });
    }
}
