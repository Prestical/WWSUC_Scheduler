import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class EditPane extends JPanel {
    
    private HashMap<String,ArrayList<String>> data;
    private MainPanel mainPanel;
    private JTextField name;
    private JPanel checkBoxPanelL, checkBoxPanelR, checkBoxPane, submitPanel;
    private String[] busyHours, busyDays;
    private JCheckBox[] checkBoxDays, checkBoxHours;
    private JButton submit, addBusy;


    EditPane(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());
        //setPreferredSize(new Dimension(400,300));
        initialization();

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Information sends from data map to UserPane and updates the informations
                if (name.getText().equals("Write name") || name.getText().equals(""))
                    JOptionPane.showMessageDialog(mainPanel.editFrame, "Please enter a name");
                else
                    submitFunc();
            }
        });
        addBusy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Adds new busy days & hours to data Map
                if (name.getText().equals("Write name") || name.getText().equals(""))
                    JOptionPane.showMessageDialog(mainPanel.editFrame, "Please enter a name");
                else
                    addBusyFunc();
            }
        });

        add(name, BorderLayout.NORTH);
        add(checkBoxPane, BorderLayout.CENTER);
        add(submitPanel, BorderLayout.SOUTH);
    }

    private void submitFunc(){
        ArrayList<User> users = mainPanel.userPane.getUsers();
        // Find current user
        String nameStr = name.getText().toLowerCase();
        for (User user : users) {
            if (nameStr.equals(user.name)){
                user.setData(data);
                break;
            }
        }
        JOptionPane.showMessageDialog(mainPanel.editFrame,"Succesfully Added");
        // Setting default values
        data.clear();
        for (JCheckBox chkbox : checkBoxDays)
            chkbox.setSelected(false);
        for (JCheckBox chkbox : checkBoxHours)
            chkbox.setSelected(false);
        
        name.setText("Write name");
        update();
    }
    private void addBusyFunc(){ 
        int countDays = 0;
        String dayName = "";
        for (JCheckBox boxDays : checkBoxDays) {
            if (boxDays.isSelected()) {
                dayName = boxDays.getText();
                countDays++;
            }
            else if (countDays > 1) {
                JOptionPane.showMessageDialog(mainPanel.editFrame, "Please select only one day !");
                return;
            }
        }
        for (JCheckBox boxHours : checkBoxHours) {
            if (boxHours.isSelected()) {
                if (!(data.containsKey(dayName)))
                    data.put(dayName, new ArrayList<>());
                data.get(dayName).add(boxHours.getText());
            }
        }
        String message = name.getText() +  " | " + data.get(dayName);
        JOptionPane.showMessageDialog(mainPanel.editFrame,message);
        update();
    }

    private void initialization(){
        name = new JTextField("Write name");
        busyDays = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        busyHours = new String[]{"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00"};
        data = new HashMap<>();
        checkBoxPane = new JPanel();
        checkBoxPanelL = new JPanel();
        checkBoxPanelR = new JPanel();
        checkBoxPanelL.setLayout(new BoxLayout(checkBoxPanelL, BoxLayout.Y_AXIS));
        checkBoxPanelR.setLayout(new BoxLayout(checkBoxPanelR, BoxLayout.Y_AXIS));
        checkBoxPane.setLayout(new GridLayout(1, 2));
        checkBoxDays = new JCheckBox[busyDays.length];
        checkBoxHours = new JCheckBox[busyHours.length];

        for (int i = 0; i < checkBoxDays.length; i++){
            checkBoxDays[i] = new JCheckBox(busyDays[i]);
            checkBoxPanelL.add(checkBoxDays[i]);
        }
        for (int i = 0; i < checkBoxHours.length; i++) {
            checkBoxHours[i] = new JCheckBox(busyHours[i]);
            checkBoxPanelR.add(checkBoxHours[i]);
        }

        checkBoxPane.add(checkBoxPanelL);
        checkBoxPane.add(checkBoxPanelR);
        
        //Buttons
        submitPanel = new JPanel();
        submit = new JButton("Submit");
        addBusy = new JButton("Add Days");
        submitPanel.add(submit);
        submitPanel.add(addBusy);

    }

    private void update(){
        revalidate();
        repaint();
    }
}
