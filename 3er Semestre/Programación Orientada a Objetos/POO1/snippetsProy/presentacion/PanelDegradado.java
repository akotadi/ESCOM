import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

class PanelDegradado extends JPanel {
 
 public PanelDegradado(){
  setLayout(new BorderLayout());
  JLabel l=new JLabel("JAVA ZONE");
  l.setFont(new Font("Verdana", Font.BOLD, 16));
  l.setHorizontalAlignment(SwingConstants.CENTER);
  JLabel l2=new JLabel("JAVA ZONE");
  l2.setFont(new Font("Verdana", Font.BOLD, 16));
  l2.setForeground(Color.ORANGE);
  l2.setHorizontalAlignment(SwingConstants.CENTER);
  add(l,BorderLayout.NORTH);
  add(l2,BorderLayout.SOUTH);
 }
 
 public void paintComponent(Graphics g){
  super.paintComponents(g);
  Graphics2D g2d=(Graphics2D)g;
  g2d.setPaint(new GradientPaint(200, 0, Color.WHITE, 200, 400, Color.BLACK));
  g2d.fillRect(0, 0, 400, 400);
 }
 public static void main(String[] args) {
        JFrame frame = new JFrame("Reflection");
        frame.add(new PanelDegradado());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

