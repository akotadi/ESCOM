import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;

public class Igualdad extends JApplet implements ActionListener{
	
	JButton calcular;
	JLabel l;
	JTextField t1,t2;
	public Igualdad(){}
	public void init(){
	calcular = new JButton("Igualdad ");
	l = new JLabel("                              ");
	t1 = new JTextField(10);
	t2 = new JTextField(10);
	setLayout(new GridLayout(5,1,15,5)); 
	add(t1);
	add(t2);
	add(calcular);
	add(l);
	calcular.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
 	int x = Integer.parseInt(t1.getText());
 	int y = Integer.parseInt(t2.getText());
 	if (y==x) {
 		l.setText("Los números son iguales");
 	}
 	else
 		l.setText("Los números son distintos");
    }
}
