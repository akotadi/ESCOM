import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculadora extends JApplet implements ActionListener{
	
	JButton calsum, calres, calmult, caldiv;
	JLabel l;
	JTextField t1,t2;
	public Calculadora(){}
	public void init(){
	calsum = new JButton(" + ");
	calres = new JButton(" - ");
	calmult = new JButton(" * ");
	caldiv = new JButton(" / ");
	l = new JLabel("          ");
	t1 = new JTextField(10);
	t2 = new JTextField(10);
	setLayout(new GridLayout(1,7,5,15)); 
	add(t1);
	add(t2);
	add(calsum);
	add(calres);
	add(calmult);
	add(caldiv);
	add(l);
	calsum.addActionListener(this);
	calres.addActionListener(this);
	calmult.addActionListener(this);
	caldiv.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton seleccionado=(JButton)e.getSource();  // Con getsource se lee 
		float x = Float.parseFloat(t1.getText());
		float y= Float.parseFloat(t2.getText());
		if (seleccionado == calsum) {
			l.setText(x+" + "+y+" = "+(x+y));
		}
		else if (seleccionado == calres) {
			l.setText(x+" - "+y+" = "+(x-y));
		}
		else if (seleccionado == calmult) {
			l.setText(x+" * "+y+" = "+(x*y));
		}
		else if (seleccionado == caldiv) {
			l.setText(x+" / "+y+" = "+(x/y));
		}
	}
}