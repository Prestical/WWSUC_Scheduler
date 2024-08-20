import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainPanel extends JPanel{

    private int width = 800;
    private int height = 600; 
    JFrame userFrame, editFrame;
    FileUploader fileUploader;
    UserPane userPane;
    TablePane tablePane;
    EditPane editPane;
    CardLayout cardLayout;

    // Program Panel. Create everything in here.
    MainPanel(){
        setPreferredSize(new Dimension(width, height));
        setLayout(new CardLayout());

        this.fileUploader = new FileUploader(this);
        this.userPane = new UserPane(this);
        this.tablePane = new TablePane(this);
        this.editPane = new EditPane(this);

        userFrame = new JFrame("User List");
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.setResizable(false);
        userFrame.setLocationRelativeTo(null);

        editFrame = new JFrame("Edit Users Busy Days");
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setResizable(false);
        editFrame.setLocationRelativeTo(null);

        editFrame.add(editPane);
        editFrame.pack();
        userFrame.add(userPane);
        userFrame.pack();
        
        add(tablePane, "TablePane");

    }

    public void openFileUploader(){
        fileUploader.startUpload();
    }

    public void openSavePNG(){
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB); 
        Graphics g = bi.createGraphics();
        this.paint(g);
        g.dispose();
        try{
            ImageIO.write(bi,"png",new File("./src/output.png"));}
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openUserPane(){
        userFrame.setVisible(true);
    }

    public void openEditPane(){
        editFrame.setVisible(true);
    }
}
