import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class TablePane extends JPanel {

    private MainPanel mainPanel;
    private JLabel label;
    
    TablePane(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        setBackground(new Color(150,100,100));
        setPreferredSize(new Dimension(800,400));

        label = new JLabel("Table");
        label.setHorizontalAlignment(JLabel.CENTER);
        
        add(label);
    }
}
