package gui;

import assets.DailyTransaction;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class DailyReportTablePanel extends JPanel {

    private JTable table;
    private DailyReportModel model;

    public DailyReportTablePanel(){
        model = new DailyReportModel();
        table = new JTable(model);

        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border outerBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<DailyTransaction> data){
        model.setData(data);
    }
}
