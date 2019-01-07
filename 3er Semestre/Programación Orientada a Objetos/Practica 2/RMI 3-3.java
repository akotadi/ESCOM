import javax.swing.*;
import java.util.*;
import java.rmi.*;
import java.awt.*;
import java.awt.event.*;
import java.swing.event.*;


public class cadenaClient extends JFrame implements ActionListener{
	JLabel jresult = new JLabel();
	JTextField cadena = new JTextField("Ingrese la cadena");
	JButton mayusculas = new JButton("Mayusculas");
	JButton tamaño = new JButton("Tamaño");
	Container c;
	static cadena i;
	String r;

	public cadenaClient(){
		Container c = getContentPane();
		c.setLayout(new GridLayout(2,1));
		c.add(cadena);
		c.add(mayusculas);
		mayusculas.addActionListener(this);
		c.add(tamaño);
		tamaño.addActionListener(this);
		c.add(jresult);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		int a=0;
		if(e.getSource()==mayusculas){
			try{
				r=i.cadMayus(cadena.getText());
			}
			catch (Exception ex){
				System.out.println("Falla servidor");
				System.out.println(ex.toString());
			}
		}
		else{
			try{
				r=i.getNumCadena(cadena.getText());
			}
			catch (Exception ex){
				System.out.println("Falla servidor");
				System.out.println(ex.toString());
			}
		}
		jresult.setText(r+"");
	}

	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try{
			i = (cadena) Naming.lookup("cadena");
			System.out.println("cadenaCliente: ");
		}
		catch (Exception e){
			System.out.println("Exception in main: "+e);
		}
		cadenaClient a = new cadenaClient();
	}
}
