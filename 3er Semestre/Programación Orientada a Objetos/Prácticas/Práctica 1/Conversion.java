import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;

public class Conversion extends JApplet implements ActionListener{
	
	JButton calpes, caldol;
	JLabel pes,dol;
	JTextField t1,t2;
	public Conversion(){}
	public void init(){
	calpes = new JButton(" Pesos a dólares ");
	caldol = new JButton(" Dólares a pesos ");
	pes = new JLabel("Pesos: ");
	dol = new JLabel("Dólares: ");
	t1 = new JTextField(10);
	t2 = new JTextField(10);
	setLayout(new GridLayout(1,7,5,15)); 
	add(pes);
	add(t1);
	add(dol);
	add(t2);
	add(calpes);
	add(caldol);
	calpes.addActionListener(this);
	caldol.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton seleccionado=(JButton)e.getSource();  // Con getsource se lee 
		if(seleccionado == calpes){ // Si se seleccionó reservado, mostrar asientos reservados
			float x = Float.parseFloat(t1.getText());
			float y = (x/18);
			t2.setText(x+" pesos son "+y+" dólares");
		}
		else{ // en otro caso, colorear el botón y agregar a la cadena
		 	float y = Float.parseFloat(t2.getText());
		 	t1.setText(y+" dólares son "+y*18+" pesos");
		}
	}
}