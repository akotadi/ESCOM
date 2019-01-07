import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;

public class Mayor extends Applet implements ActionListener{
	
	Button calcular;
	Label l;
	TextField t1,t2,t3;
	public Mayor(){}
	public void init(){
	calcular = new Button("Calcular mayor");
	l = new Label("                              ");
	t1 = new TextField(10);
	t2 = new TextField(10);
	t3 = new TextField(10);
	setLayout(new GridLayout(5,1,15,5)); 
	add(t1);
	add(t2);
	add(t3);
	add(calcular);
	add(l);
	calcular.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
 	int x = Integer.parseInt(t1.getText());
 	int y = Integer.parseInt(t2.getText());
 	if (y>x) {
 		x=y;
 	}
 	int z = Integer.parseInt(t3.getText());
 	if (z>x) {
 		x=z;
 	}
 	l.setText("El número mayor es: "+ x);
    }
}
