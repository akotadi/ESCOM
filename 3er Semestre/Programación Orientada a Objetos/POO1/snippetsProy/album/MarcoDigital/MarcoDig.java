import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class MarcoDig extends JFrame {
 Vector <String> nombres=new Vector<String>();
 Panel p;
 ImageIcon iconos[];
 JLabel jlimg;
 public MarcoDig() {
	super("Marco");
    	nombres.addElement("conejo.jpg");
   	p=new Panel(); 
   	iconos=new ImageIcon[nombres.size()];
        iconos[0] =new ImageIcon(nombres.elementAt(0));
   	jlimg=new JLabel(iconos[0], JLabel.CENTER);
   	p.add(jlimg);
    	Container content = getContentPane();
    	content.add(p, BorderLayout.CENTER);
    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        setSize(400, 300);
    	setVisible(true);
 }
 public static void main(String[] args) { new MarcoDig(); }
}
