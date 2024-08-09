import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {

    Frame(){
        setDefaultValues();
    }

    public void setDefaultValues(){
        setTitle("WWSUC Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel();
        add(panel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
