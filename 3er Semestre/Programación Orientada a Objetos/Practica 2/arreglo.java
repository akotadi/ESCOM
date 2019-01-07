import java.awt.event.*;
import javax.swing.*; //X significa extensión
import java.applet.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;


public class arreglo extends JApplet implements ActionListener{

	JButton[] b = new JButton[44];
	JButton dispo; 
	JPanel panelBotones, panelAcciones;
	int contador_disp=44;
	JLabel mensaje;
	String cadena = "";
	public arreglo(){}
	public void init(){
		
		dispo = new JButton("Disponibles ");
		mensaje = new JLabel("*");
		setLayout(new GridLayout(11,4,20,10));
		for(int i = 0;i<44;i++){
			b[i] = new JButton(""+(1+i));
			add(b[i]);
			b[i].addActionListener(this);
		}
		add("South",dispo);
		dispo.addActionListener(this);
		add("South",mensaje);
		setSize(980,830);
		
	}
	
	public void actionPerformed(ActionEvent e){//Con get source no se necesita if, ya que nos indica que botón fue presionado
		
		JButton sel=(JButton)e.getSource(); 
		if(sel == dispo){
			mensaje.setText(""+(cadena));
		}else{
		 sel.setBackground(Color.black);
         sel.setForeground(Color.yellow);
         cadena = cadena +"  "+ sel.getText();
         //sel.setEnabled(false);
         contador_disp = contador_disp + 1;
     }
	}
}
