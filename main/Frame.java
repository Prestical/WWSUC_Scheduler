import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame extends JFrame {

    JMenu saveMenu, uploadMenu, changePage;
    JMenuItem save, upload, editPane, userPane, tablePane; 
    JMenuBar menuBar;
    MainPanel panel;

    Frame(){
        setDefaultValues();
        setLayout(new FlowLayout());
    }

    public void setDefaultValues(){
        setTitle("WWSUC Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeMenuBar();
        
        panel = new MainPanel();
        add(panel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // These functions will avainable in future
    private void initializeMenuBar(){
        saveMenu = new JMenu("Save");
        uploadMenu = new JMenu("Upload");
        changePage = new JMenu("Go to");
        
        save = new JMenuItem("Save as PDF");
        upload = new JMenuItem("Upload File");
        editPane = new JMenuItem("Edit");
        tablePane = new JMenuItem("Table");
        userPane = new JMenuItem("Users");

        saveMenu.add(save);
        uploadMenu.add(upload);
        changePage.add(tablePane);
        changePage.add(editPane);
        changePage.add(userPane);

        userPane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panel.openUserPane();
            }
        });
        editPane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panel.openEditPane();
            }
        });
        tablePane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panel.openTablePane();
            }
        });
        


        menuBar = new JMenuBar();
        menuBar.add(saveMenu);
        menuBar.add(uploadMenu);
        menuBar.add(changePage);

        setJMenuBar(menuBar);
    }
}
