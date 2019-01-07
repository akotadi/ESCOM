import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class AnioNacimiento extends Applet implements ActionListener{
	
	Button calcular;
	Label l;
	TextField t;
	
	public void init(){
	calcular = new Button("Calcular");
	l = new Label("****************");
	t = new TextField(30);
	add(calcular);
	add(t);
	add(l);
	calcular.addActionListener(this);
	};
	public void actionPerformed(ActionEvent e)
	{
 	int x = Integer.parseInt(t.getText());
 	int edad = 2017 - x;
 	l.setText("Tu edad es: "+ edad);
    }
}
