import javax.swing.*;
import java.util.*;
import java.rmi.*;
import java.awt.*;
import java.awt.event.*;
import java.swing.event.*;


public class salarioClient extends JFrame implements ActionListener{
	JLabel jresult = new JLabel();
	JTextField horas = new JTextField();
	JTextField sueldo = new JTextField();
	JButton calcular = new JButton("Calcular salario");
	Container c;
	static salario i;
	float r = 0;

	public salarioClient(){
		Container c = getContentPane();
		c.setLayout(new GridLayout(2,1));
		c.add(horas, "horas a la semana");
		c.add(sueldo, "sueldo por hora");
		c.add(calcular);
		calcular.addActionListener(this);
		c.add(jresult);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		int sueldoa, horase;
		sueldoa = Integer.parseInt(sueldo.getText());
		horase = Integer.parseInt(horas.getText());

		try{
			r=i.getSalario(horase,sueldoa);
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
			i = (salario) Naming.lookup("salario");
			System.out.println("salarioCliente: ");
		}
		catch (Exception e){
			System.out.println("Exception in main: "+e);
		}
		salarioClient a = new salarioClient();
	}
}
