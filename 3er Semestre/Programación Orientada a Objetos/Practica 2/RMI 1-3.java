import javax.swing.*;
import java.util.*;
import java.rmi.*;
import java.awt.*;
import java.awt.event.*;
import java.swing.event.*;


public class areaClient extends JFrame implements ActionListener{
	JLabel jresult = new JLabel();
	JTextField base = new JTextField();
	JTextField altura = new JTextField();
	JButton calcular = new JButton("Calcular area");
	Container c;
	static area i;
	float r = 0;

	public areaClient(){
		Container c = getContentPane();
		c.setLayout(new GridLayout(2,1));
		c.add(base, "base");
		c.add(altura, "altura");
		c.add(calcular);
		calcular.addActionListener(this);
		c.add(jresult);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		int alturaa, basee;
		alturaa = Integer.parseInt(altura.getText());
		basee = Integer.parseInt(base.getText());

		try{
			r=i.getArea(basee,alturaa);
		}
		catch (Exception ex){
			System.out.println("Falla servidor");
			System.out.println(ex.toString());
		}
		jresult.setText(r+"");
	}

	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try{
			i = (area) Naming.lookup("area");
			System.out.println("areaCliente: ");
		}
		catch (Exception e){
			System.out.println("Exception in main: "+e);
		}
		areaClient a = new areaClient();
	}
}
