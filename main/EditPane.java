import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class EditPane extends JPanel {
    
    private HashMap<String,ArrayList<String>> data;
    private MainPanel mainPanel;
    private JTextField name;
    private JComboBox<String> busyHours, busyDays;
    private JButton submit, addBusy;


    EditPane(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(400,100));
        // setLayout();
        initialization();

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Information sends from data map to UserPane and updates the informations
                if (name.getText().equals("Write name") || name.getText().equals(""))
                    JOptionPane.showMessageDialog(mainPanel, "Please enter a name");
                else
                    submitFunc();
            }
        });
        addBusy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Adds new busy days & hours to data Map
                if (name.getText().equals("Write name") || name.getText().equals(""))
                    JOptionPane.showMessageDialog(mainPanel, "Please enter a name");
                else
                    addBusyFunc();
            }
        });

        add(name); 
        add(busyDays);
        add(busyHours); 
        add(addBusy);
        add(submit);
    }

    private void submitFunc(){
        User[] users = mainPanel.userPane.getUsers();
        // Find current user
        String nameStr = name.getText().toLowerCase();
        for (User user : users) {
            if (nameStr.equals(user.name)){
                user.getData(data);
                break;
            }
        }
        JOptionPane.showMessageDialog(mainPanel,"Succesfully Added");
        data.clear();
        name.setText("Write name");
        update();
    }
    private void addBusyFunc(){
        // index [0] = days , [1] = hour 
        String[] compoInfo = {(String)busyDays.getSelectedItem(), (String)busyHours.getSelectedItem()}; 
        if (!(data.containsKey(compoInfo[0])))
            data.put(compoInfo[0], new ArrayList<String>());

        data.get(compoInfo[0]).add(compoInfo[1]);

        String message = "Added: " + name.getText() + " | " + compoInfo[0] + ", " + compoInfo[1];
        JOptionPane.showMessageDialog(mainPanel,message);
        update();
    }

    private void initialization(){
        name = new JTextField("Write name");
        busyDays = new JComboBox<String>(new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"});
        busyHours = new JComboBox<String>(new String[]{"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00"});
        data = new HashMap<>();
        //Buttons
        submit = new JButton("Submit");
        addBusy = new JButton("Add Days");

    }

    private void update(){
        revalidate();
        repaint();
    }
}
