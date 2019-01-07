import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;


public class Camion extends JApplet implements ActionListener{

	JButton[] b = new JButton[44]; // Asientos del camión
	JButton reservado; // Botón que muestra los reservados
	JLabel mensaje; // Despliegue de los reservados
	String cadena = ""; // Cadena que contiene los asientos que han sido reservados
	public Camion(){}
	public void init(){
		
		reservado = new JButton("Reservados: ");
		mensaje = new JLabel("*");
		setLayout(new GridLayout(12,4,15,5)); // Layout del programa, 11 filas + botón final
		for(int i = 0;i<44;i++){ // Acciones sobre botones
			b[i] = new JButton(""+(1+i));
			add(b[i]);
			b[i].addActionListener(this);
		}
		add(reservado); // Acción sobre el botón para reservados
		reservado.addActionListener(this);
		add(mensaje); // Mensaje de reservados
		setSize(650,700);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		JButton seleccionado=(JButton)e.getSource();  // Con getsource se lee 
		if(seleccionado == reservado){ // Si se seleccionó reservado, mostrar asientos reservados
			mensaje.setText(""+(cadena));
		}
		else{ // en otro caso, colorear el botón y agregar a la cadena
		 	seleccionado.setBackground(Color.lightGray);
         	seleccionado.setForeground(Color.red);
         	cadena = cadena +"  "+ seleccionado.getText();
     	}
	}
}
