import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Pesos extends JApplet implements ActionListener{
	
	Button pesos,dolares;
	TextField cantidad,factor;
	public void init(){
		pesos = new Button("Pesos a dolares");
		dolares = new Button("Dolares a pesos");
		cantidad = new TextField(30);
		factor = new TextField(30);
		add(pesos); 
		add(dolares);
		add(cantidad); 
		add(factor);
		pesos.addActionListener(this);
		dolares.addActionListener(this);
	};
	public void actionPerformed(ActionEvent e)
	{
	 	Button sel = (Button)e.getSource();
	 	int cant = Integer.parseInt(cantidad.getText());
	 	int fac = Integer.parseInt(factor.getText());
		if(sel == pesos){
			cantidad.setText(""+cant*fac);
		}else{
			cantidad.setText(""+cant/fac);
		}
    };
}
