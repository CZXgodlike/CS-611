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

public class LoginWindow extends JFrame {
    private JFrame frame = new JFrame("Welcome to Bank ATM");
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("Username:");
    private JTextField userText = new JTextField();
    private JLabel passLabel = new JLabel("Password:");
    private JPasswordField passText = new JPasswordField(20);
    private JButton registerButton = new JButton("register");
    private JButton loginButton = new JButton("login");
    
    public LoginWindow(){
        initListener();
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 200);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        panel.setLayout(null);
        userLabel.setBounds(30, 30, 80, 25);
        panel.add(userLabel);
        userText.setBounds(105, 30, 165, 25);
        panel.add(userText);
        passLabel.setBounds(30, 60, 80, 25);
        panel.add(passLabel);
        passText.setBounds(105, 60, 165, 25);
        panel.add(passText);
        loginButton.setBounds(25, 100, 80, 25);
        panel.add(loginButton);
        registerButton.setBounds(190, 100, 80, 25);
        panel.add(registerButton);
    }
    
    public void initListener(){
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // check if user name exist in customer accounts
                String username = userText.getText();
                String pass = new String(passText.getPassword());
                // if user is banker, check banker pass
                if (username.equals("Admin")){
                    if (ReadFileUtil.getField("data/AccountData/bankerAccount.csv", "Admin", 0,1).equals(pass)){
                        JOptionPane.showMessageDialog(null,"Login as banker");
                        frame.dispose();
                        // jump to banker GUI
                        try {
                            new BankerAccountMainFrame();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Wrong password for Banker");
                    }
                } else if (ReadFileUtil.findMatch("data/customerData.csv",new int[]{0, 1}, new String[]{username,pass})){
                    JOptionPane.showMessageDialog(null,"Login success");
                    frame.dispose();
                    // jump to user window
                    new CustomerMainFrame(username);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                    return;
                }
            }
        });
        
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String username = userText.getText();
                // if account exist, ask for login
                if (ReadFileUtil.isExist("data/customerData.csv",0,username)){
                    JOptionPane.showMessageDialog(null,"Username exist, please login");
                    return;
                }else{
                    String pass = new String(passText.getPassword());
                    String content = username+","+pass+",";
                    WriteFileUtil.writeLine("data/customerData.csv", content);
                    JOptionPane.showMessageDialog(null,"registered");
                    frame.dispose();
                    // go to user GUI
                    new CustomerMainFrame(username);
                }
            }
        });
    }
}
