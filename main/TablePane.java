import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TablePane extends JPanel {

    private MainPanel mainPanel;
    private String[][] emptyRows;
    private JTable table;
    private JPanel tablePanel;
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
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(100,30));
        mapTimeIndex = timeToIndex();
        mapDayIndex = dayToIndex();
        setTable();
        //add(label,BorderLayout.NORTH);
        add(sortButton,BorderLayout.NORTH);
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
        String[][] copyTable = emptyRows.clone();
        users = mainPanel.userPane.getUsers();

        // Index : [X][0] = Time, [X][1...7] = Days (Monday,Tuesday,Wednesday,...); 
        // Index : [0][X] = 9:00, [1][X] = 10:00 , ...
        // 11 rows , 9 columns
        for (int k = 0; k < users.length; k++) {
            User currUser = users[k];
            int addedHours = 0;
            int maxPlayHours = 4;
            for(int i = 0; i < copyTable.length; ++i){
                for (int j = 1; j < copyTable[0].length; ++j) {
                    if (addedHours == maxPlayHours ) {
                        break;
                    }
                    if (copyTable[i][j] == "") {
                        if ((!currUser.busyDays.containsKey(mapDayIndex.get(j)))){
                            copyTable[i][j] = currUser.name;
                            addedHours++;
                        }
                        else{
                            ArrayList<String> timeStr = currUser.busyDays.get(mapDayIndex.get(j));
                            if (!timeStr.contains(mapTimeIndex.get(i))) {
                                copyTable[i][j] = currUser.name;
                                addedHours++;
                            }
                        }
                    }
                }
            }
        }
        model.setDataVector(copyTable, col);
        table.setModel(model);
    }

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
        mapDayIndex.put(4,"Thurday");
        mapDayIndex.put(5,"Friday");
        mapDayIndex.put(6,"Saturday");
        mapDayIndex.put(7,"Sunday");
        return mapDayIndex;
    }
}