import java.awl.event.*;
import java.awl.*;
import Java.applet.*;
import javax.string.*;

public class Maquina extends JApplet implements ActionListener{
	JButton bman, bnar, blim;
	Jlabel mensaje;
	Container c;
	
	public Maquina(){}

	public void init(){
		c=getContentPane();
		bman = new JButton(new ImageIcon('manzana.jpg')); // Un Jbutton puede tener una etiqueta gráfica (imagen) o html u hojas de estilo / en cascada
		bnar = new JButton(new ImageIcon('naranja.jpg')); // path o misma carpeta en la mención
		blim = new JButton(new ImageIcon('limon.jpg'));  // soporta gif, png y jpg
		mensaje = new JLabel('******');
		bman.addActionListener(this);
		bnar.addActionListener(this);
		blim.addActionListener(this);
		c.add("West",bman);
		c.add("Center",bnar);
		c.add("East",blim);
		c.add("South",mensaje); // el layout acomoda la interfaz gráfica
	}

	public void actionPerformed(Action){
		JButton b = (JButton)e.getSource();
		mensaje.setIcon(b.getIcon());
	}
}