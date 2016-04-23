import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class ImagePanel extends JPanel{
//    public static Graphics g;
    public static ImageIcon icon;
//    
    public ImagePanel(ImageIcon icon){
        this.icon = icon;
    }
    
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        g.drawImage(this.icon.getImage(), 0, 0, null);

    }
}
