import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileUploader {
    
    private JFileChooser fileChooser;
    private File selectedFile;
    private Scanner sc;
    private MainPanel mainPanel;

    FileUploader(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }

    public void startUpload(){
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files","txt","text"));
        fileChooser.showOpenDialog(null);
        implementData();
    }

    private void implementData(){
        try {
            selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            sc = new Scanner(selectedFile);
            User newUser = null; // Implementing in here to make it visible for every scope
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split(" ");
                String day = "";
                if (line.charAt(0) == '#') {
                    newUser = new User(words[0].substring(1));
                    mainPanel.userPane.addNewUser(newUser);
                    continue;
                }
                else if (line.charAt(0) == '-') {
                    day = words[0].substring(1);
                }

                for (int i = 1; i < words.length; i++) {
                    if (!newUser.busyDays.containsKey(day)) {
                        newUser.busyDays.put(day, new ArrayList<>());
                    }
                    newUser.busyDays.get(day).add(words[i]);
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JFileChooser getFileChooser() { return this.fileChooser; }
}
