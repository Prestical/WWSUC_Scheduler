import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TablePane extends JPanel {

    private MainPanel mainPanel;
    private String[][] emptyRows;
    private JTable table;
    private JPanel tablePanel ,buttonsPanel;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JButton sortButton;
    private JLabel label;
    private String[] col;
    private User[] users;
    private HashMap<Integer,String> mapTimeIndex;
    private HashMap<Integer,String> mapDayIndex;
    
    TablePane(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        this.tablePanel = new JPanel();
        this.buttonsPanel = new JPanel();
        this.sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                sortAlgorithm();
            }
        });

        setBackground(new Color(150,100,100));
        tablePanel.setBackground(new Color(150,100,100));
        setLayout(new BorderLayout());
        
        label = new JLabel("Table");
        buttonsPanel.add(label);
        buttonsPanel.add(sortButton);

        mapTimeIndex = timeToIndex();
        mapDayIndex = dayToIndex();
        users = mainPanel.userPane.getUsers();
        setTable();

        add(buttonsPanel,BorderLayout.NORTH);
        add(tablePanel,BorderLayout.CENTER);
    }

    private void setTable(){
        col = new String[]{
            "Times","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"
        };
        emptyRows = new String[][] {
            {"9:00","","","","","","",""},
            {"10:00","","","","","","",""},
            {"11:00","","","","","","",""},
            {"12:00","","","","","","",""},
            {"13:00","","","","","","",""},
            {"14:00","","","","","","",""},
            {"15:00","","","","","","",""},
            {"16:00","","","","","","",""},
            {"17:00","","","","","","",""},
            {"18:00","","","","","","",""},
            {"19:00","","","","","","",""},
        };

        model = new DefaultTableModel(emptyRows,col);
        table = new JTable(model);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(100);
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        table.setDefaultEditor(Object.class, null);
        table.setPreferredScrollableViewportSize(new Dimension(600,500));
        
        table.setRowHeight(45);
        table.setGridColor(Color.BLACK);

        scroll = new JScrollPane(table);
        tablePanel.add(scroll);
    }

    public void sortAlgorithm(){
        String[][] copyTable = new String[emptyRows.length][emptyRows[0].length];
        for (int i = 0; i < emptyRows.length; i++)
            copyTable[i] = emptyRows[i].clone();

        // Index : [X][0] = Time, [X][1...7] = Days (Monday,Tuesday,Wednesday,...); 
        // Index : [0][X] = 9:00, [1][X] = 10:00 , ...
        // 11 rows , 9 columns
        boolean[][] isEmptyCell = new boolean[11][9];
        int[] screenTime = new int[users.length]; 
        for(int i = 0; i < users.length; i++)
            screenTime[i] = 5;
        int userIndex = 0;
        User currUser = users[userIndex];
        int totalScreenTime = users.length * 5;
        Random random = new Random();
        int errorChecker = 0;
        int i, j;

        while(totalScreenTime != 0){
            if (errorChecker > 10000) { // Value can be change
                JOptionPane.showMessageDialog(this, "Program can't change anything anymore");
                break;
            }

            i = random.nextInt(11);
            j = random.nextInt(1,8);

            if (screenTime[userIndex] == 0 && userIndex < users.length - 1){
                currUser = users[++userIndex];
            }

            if (isEmptyCell[i][j] == false && copyTable[i][j].equals("")) {
                if ((!currUser.busyDays.containsKey(mapDayIndex.get(j)))){
                    copyTable[i][j] = currUser.name;
                    screenTime[userIndex]--;
                    isEmptyCell[i][j] = true;
                    totalScreenTime--;
                }
                else{
                    ArrayList<String> timeStr = currUser.busyDays.get(mapDayIndex.get(j));
                    if (!timeStr.contains(mapTimeIndex.get(i))) {
                        copyTable[i][j] = currUser.name;
                        screenTime[userIndex]--;
                        isEmptyCell[i][j] = true;
                        totalScreenTime--;
                    }
                } 
            }
            errorChecker++;
        }

        model.setDataVector(copyTable, col);
        table.setModel(model);
    }

    // Couldn't do with enum because of that I used HashMap
    private HashMap<Integer,String> timeToIndex(){
        HashMap<Integer, String> mapTimeIndex = new HashMap<>();
        mapTimeIndex.put(0,"9:00");
        mapTimeIndex.put(1,"10:00");
        mapTimeIndex.put(2,"11:00");
        mapTimeIndex.put(3,"12:00");
        mapTimeIndex.put(4,"13:00");
        mapTimeIndex.put(5,"14:00");
        mapTimeIndex.put(6,"15:00");
        mapTimeIndex.put(7,"16:00");
        mapTimeIndex.put(8,"17:00");
        mapTimeIndex.put(9,"18:00");
        mapTimeIndex.put(10,"19:00");
        return mapTimeIndex;
    }
    private HashMap<Integer,String> dayToIndex(){
        HashMap<Integer, String> mapDayIndex = new HashMap<>();
        mapDayIndex.put(1,"Monday");
        mapDayIndex.put(2,"Tuesday");
        mapDayIndex.put(3,"Wednesday");
        mapDayIndex.put(4,"Thursday");
        mapDayIndex.put(5,"Friday");
        mapDayIndex.put(6,"Saturday");
        mapDayIndex.put(7,"Sunday");
        return mapDayIndex;
    }
}