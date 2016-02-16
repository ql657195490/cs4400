import java.awt.Graphics;

import javax.swing.JPanel;


public class drawPanel extends JPanel {
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawLine(15, 55, 435, 55);
    }
    
    public void drawLine(Graphics g, int x, int y){
        
    }
}
