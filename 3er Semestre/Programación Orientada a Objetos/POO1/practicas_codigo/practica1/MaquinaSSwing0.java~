import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
public class MaquinaSSwing0 extends JApplet implements ActionListener {
	JButton bman, bnar, blim;
	JLabel mensaje;
	Container c;
	public MaquinaSSwing0(){}
	public void init(){
                c=getContentPane();
		bman = new JButton(new ImageIcon("naranja.jpg"));
		bnar = new JButton(new ImageIcon("manzana.jpg"));
		blim = new JButton(new ImageIcon("limon.jpg"));
                mensaje = new JLabel("           ");
		bman.addActionListener( this );
		bnar.addActionListener( this );
		blim.addActionListener( this );
		c.add( "West", bman );c.add( "Center", bnar );
		c.add( "East", blim );c.add("South", mensaje );
		setSize(900,250);
	}
	public void actionPerformed ( ActionEvent e ){
		JButton b=(JButton)e.getSource();
		mensaje.setIcon( b.getIcon() );
	}
	public static void main(String s[]){
		MaquinaSSwing0 m=new MaquinaSSwing0();
		m.init(); m.start();
		Frame f=new Frame("Maquina"); 
		f.add("Center", m);
                f.setSize(900, 300);
		f.setVisible(true);
	}
}
