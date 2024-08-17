import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.*;

public class MainPanel extends JPanel{

    private int width = 800;
    private int height = 600; 
    JPanel currPanel;
    UserPane userPane;
    TablePane tablePane;
    EditPane editPane;
    CardLayout cardLayout;

    // Program Panel. Create everything in here.
    MainPanel(){
        cardLayout = new CardLayout();
        setPreferredSize(new Dimension(width, height));
        setLayout(cardLayout);

        this.userPane = new UserPane(this);
        this.tablePane = new TablePane(this);
        this.editPane = new EditPane(this);

        add(tablePane, "TablePane");
        add(editPane,"EditPane");
        add(userPane, "UserPane");
    }

    public void openUserPane(){
        cardLayout.show(this, "UserPane");
    }

    public void openEditPane(){
        cardLayout.show(this, "EditPane");
    }

    public void openTablePane(){
        cardLayout.show(this, "TablePane");
    }
}
