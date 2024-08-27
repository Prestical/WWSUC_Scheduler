import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.*;

public class EditPane extends JPanel {
    
    private HashMap<String,HashSet<String>> data;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    private int countDays = 0;
    private MainPanel mainPanel;
    private JTextField name;
    private JPanel checkBoxPanelL, checkBoxPanelR, checkBoxPane, submitPanel;
    private String[] busyHours, busyDays;
    private JCheckBox[] checkBoxDays, checkBoxHours;
    private JButton submit, addBusy;


    EditPane(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        setBackground(new Color(140, 48, 97));
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
        User[] users = mainPanel.userPane.getUsers().toArray(new User[0]);
        // Find current user
        String nameStr = name.getText().toLowerCase();
        for (User user : users) {
            if (user.name.equals(nameStr)){
                user.setData(data);
                break;
            }
        }
        User newUser = new User(nameStr);
        if (!mainPanel.userPane.getUsers().contains(newUser)) {
            mainPanel.userPane.addNewUser(newUser);
            newUser.setData(data);
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
    private void addBusyFunc() {
        String dayName = "";
        for (JCheckBox boxDays : checkBoxDays) {
            if (boxDays.isSelected()) {
                dayName = boxDays.getText();
            } else if (countDays > 1) {
                JOptionPane.showMessageDialog(mainPanel.editFrame, "Please select only one day !");
                return;
            }
        }
        for (JCheckBox boxHours : checkBoxHours) {
            if (boxHours.isSelected()) {
                data.computeIfAbsent(dayName, k -> new HashSet<>()).add(boxHours.getText());
            }
        }

        ArrayList<String> timeList = new ArrayList<>(data.get(dayName));
        Collections.sort(timeList, Comparator.comparing(time -> LocalTime.parse(time,formatter)));
        String message = name.getText() + " | " + dayName + ": " + timeList;
        JOptionPane.showMessageDialog(mainPanel.editFrame, message);
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
        checkBoxPanelL.setBackground(new Color(217, 95, 89));
        checkBoxPanelR.setBackground(new Color(217, 95, 89));
        checkBoxDays = new JCheckBox[busyDays.length];
        checkBoxHours = new JCheckBox[busyHours.length];

        for (int i = 0; i < checkBoxDays.length; i++){
            checkBoxDays[i] = new JCheckBox(busyDays[i]);
            checkBoxDays[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e){
                    countDays = e.getStateChange() == 1 ? countDays + 1 : countDays - 1;
                }
            });
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
        submitPanel.setBackground(new Color(217, 95, 89));
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
