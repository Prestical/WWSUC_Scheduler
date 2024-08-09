import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class User {

    private String name;
    private ImageIO profilePhoto;

    
    User(){
        name = "Asim";
    }

    public void showUserInfo(Panel panel){
        JOptionPane.showMessageDialog(panel, this.name,null,JOptionPane.INFORMATION_MESSAGE);
    }
}
