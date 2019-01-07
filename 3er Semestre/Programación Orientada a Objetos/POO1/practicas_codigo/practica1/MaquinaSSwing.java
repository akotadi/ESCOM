import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class MaquinaSSwing extends JApplet implements ActionListener {
	JButton bman, bnar, blim;
	JLabel mensaje;
        BufferedImage bimas[];
	Container c;
	public MaquinaSSwing(){}
	public void init(){
                c=getContentPane();
                bimas= new BufferedImage [3];
                bimas[0]=leeImagen("naranja.jpg");
                bimas[1]=leeImagen("manzana.jpg");
                bimas[2]=leeImagen("limon.jpg");
		bman = new JButton(new ImageIcon(bimas[0]));
		bnar = new JButton(new ImageIcon(bimas[1]));
		blim = new JButton(new ImageIcon(bimas[2]));
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
		MaquinaSSwing m=new MaquinaSSwing();
		m.init(); m.start();
		Frame f=new Frame("Maquina"); 
		f.add("Center", m);
                f.setSize(900, 300);
		f.setVisible(true);
	}
        public BufferedImage leeImagen(String nombre){
  		BufferedImage imagen;
   		try {
      			imagen = ImageIO.read(new File(nombre));     
   		} catch (IOException e){
        		System.err.println(e+" "+nombre);
        		return null;
   		}
   		return imagen;
   	}
}
