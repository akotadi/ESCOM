import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.GradientPaint;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.Point;  
import java.awt.RenderingHints;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
  
/** 
 * 
 * @author jzavaleta 
 */  
public class GradientPanel extends JPanel {  
  
    public GradientPanel() {  
        setOpaque(false);  
    }  
  
    @Override  
    public void paintComponent(Graphics g) {  
        Graphics2D g2d = (Graphics2D) g;  
  
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                RenderingHints.VALUE_ANTIALIAS_ON);  
  
        GradientPaint gradientPaint = new GradientPaint(  
                new Point(0, 0),  
                Color.ORANGE,  
                new Point(0, getHeight() / 2),  
                Color.RED);  
          
        g2d.setPaint(gradientPaint);  
  
        g2d.fillRect(0, 0, getWidth(), getHeight());  
  
        super.paintComponent(g);  
    }  
  
    public static void main(String args[]) {  
        JFrame frame = new JFrame();  
        frame.setLayout(new BorderLayout());  
        frame.add(new GradientPanel(), BorderLayout.CENTER);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(new Dimension(300, 300));  
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);  
    }  
}  

