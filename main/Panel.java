import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class Panel extends JPanel{

    private int width = 600;
    private int height = 400; 
    private User[] users; // I can turn it to ArrayList in the future

    // Program Panel. Create everything in here.
    Panel(){
        setPreferredSize(new Dimension(width, height));
        JButton bAddUser = new JButton("Add New User");
        bAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { addUser(); }
        }); 
        add(bAddUser);

        // For adding new sections, you must create panels for each of them in BorderLayout.
        // BorderLayout
        // GridBagLayout
        // ScrollPane

        users = new User[10]; // Limit of total user number
    }


    // Adds new user 
    public void addUser(){
        
    }
}
