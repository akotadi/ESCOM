import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;

public class AnioMuerte extends JApplet implements ActionListener{
	
	JButton calcular;
	JLabel l;
	JTextField t;
	public void init(){
	calcular = new JButton("Calcular Año Muerte");
	l = new JLabel("                              ");
	t = new JTextField(10);
	setLayout(new GridLayout(3,1,7,2));
	add(t);
	add(calcular);
	add(l);
	calcular.addActionListener(this);
	};
	public void actionPerformed(ActionEvent e)
	{
 	int x = Integer.parseInt(t.getText());
 	if (x>77) {
 		l.setText("Estás muerto");
 	}
 	else
 		l.setText("Vas a morir en el año "+(2017+77-x));
    }
}
