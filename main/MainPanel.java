import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class MainPanel extends JPanel{

    private int width = 800;
    private int height = 600; 
    UserPane userPane;
    TablePane tablePane;
    EditPane editPane;

    // Program Panel. Create everything in here.
    MainPanel(){
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());

        this.userPane = new UserPane(this);
        this.tablePane = new TablePane(this);
        this.editPane = new EditPane(this);

        add(tablePane,BorderLayout.NORTH);
        add(userPane,BorderLayout.WEST);
        add(editPane, BorderLayout.EAST);
    }
}
