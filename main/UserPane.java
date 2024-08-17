import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;


public class UserPane extends JPanel {

    private User[] users;
    private MainPanel mainPanel;
    private JList<User> usersList;
    private JScrollPane scrollPane;
    private JPanel leftPanel, rightPanel;
    private JLabel topLabel;
    private JButton showInfo;

    UserPane(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        initialization();
        showInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInfoFunc(usersList.getSelectedValue());
            }
        });
        leftPanel.add(topLabel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.setBackground(new Color(70,200,250));
        rightPanel.add(showInfo);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    private void initialization(){
        this.users = new User[] {new User("Asim"),new User("Huseyin"),new User("Levent")}; // Test Initializion
        usersList = new JList<>(users); // It will read /src/data.txt in the future
        scrollPane = new JScrollPane(usersList);
        scrollPane.setPreferredSize(new Dimension(200,200));
        showInfo = new JButton("Show Info");
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200,200));
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        topLabel = new JLabel("User List");
        topLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    private void showInfoFunc(User selectedUser){
        String message = selectedUser.name + "\'s Busy Days & Hours: \n";
        for (Map.Entry<String,ArrayList<String>> e: selectedUser.busyDays.entrySet()) {
            message += e.getKey() + "->";
            for (String str : e.getValue()) {
                message += str + ", ";
            } 
            message += '\n';
        }
        JOptionPane.showMessageDialog(mainPanel,message);
    }    

    public User[] getUsers() { return this.users; }
}