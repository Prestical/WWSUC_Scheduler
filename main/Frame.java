import javax.swing.*;

public class Frame extends JFrame {

    JMenu saveMenu, uploadMenu;
    JMenuBar menuBar;

    Frame(){
        setDefaultValues();
    }

    public void setDefaultValues(){
        setTitle("WWSUC Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeMenuBar();
        
        MainPanel panel = new MainPanel();
        add(panel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // These functions will avainable in future
    private void initializeMenuBar(){
        saveMenu = new JMenu("Save");
        saveMenu.add(new JMenuItem("Save As PDF"));

        uploadMenu = new JMenu("Upload");
        uploadMenu.add(new JMenuItem("Upload File"));

        menuBar = new JMenuBar();
        menuBar.add(saveMenu);
        menuBar.add(uploadMenu);
        setJMenuBar(menuBar);
    }
}
