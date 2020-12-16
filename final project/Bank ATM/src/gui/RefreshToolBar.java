package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class RefreshToolBar extends JToolBar {

    private JButton refreshButton;
    private RefreshListener refreshListener;

    public RefreshToolBar(){
        refreshButton = new JButton();
        refreshButton.setIcon(createIcon("/images/Refresh16.gif"));
        refreshButton.setToolTipText("Refresh");

        setBorder(BorderFactory.createEtchedBorder());

        add(refreshButton);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshListener.refreshEventOccurred();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private ImageIcon createIcon(String path){
        URL url = getClass().getResource(path);

        if(url == null){
            System.err.println("Unable to load image: " + path);
        }

        assert url != null;
        return new ImageIcon(url);
    }

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }
}
