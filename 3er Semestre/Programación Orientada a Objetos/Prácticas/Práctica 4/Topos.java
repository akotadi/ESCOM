import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class Topos extends JApplet implements ActionListener, Runnable {
	JButton[] botones=new JButton[20];
	JLabel l,l1,l2;
	int puntuacion=0,sleepTime;
	int[] activos=new int[20];
	//Container c;
	Thread JuegaToposThread;

	public Topos(int sleep){
		sleepTime=sleep;
		JuegaToposThread=new Thread(this);
		JuegaToposThread.start();
	}

	public void init(){

		//c=getContentPane();
		l = new JLabel("Tu puntuacion es: "+(puntuacion));	
		l1 = new JLabel("");
		l2 = new JLabel("");
		setLayout(new GridLayout(5,5,20,7));
		for(int i=0; i<20; i++){
			float a;
 			a=(float)(Math.random());
 			a=a*2;
			if((int)a==0){
				botones[i] = new JButton(new ImageIcon("Topo.jpg"));
				add(botones[i]);
				activos[i] = 1;
			}
			else{
				botones[i] = new JButton("X");
				add(botones[i]);
				activos[i] = 0;
			}

			botones[i].addActionListener(this);
		}
		add(l1);
		add(l2);
		add("Center",l);
		setSize(1000, 1000); 
//		setVisible(true);  

	}

	public void run(){
		while(true){
			for(int i=0; i<20; i++){
				remove(botones[i]);
			float a;
 			a=(float)(Math.random());
 			a=a*2;
			if((int)a==0){
				botones[i] = new JButton(new ImageIcon("Topo.jpg"));
				add(botones[i]);
				activos[i] = 1;
			}
			else{
				botones[i] = new JButton("X");
				add(botones[i]);
				activos[i] = 0;
			}

			botones[i].addActionListener(this);
		}
		//add(l1);
		//add(l2);
		//add("Center",l);
			try{
				Thread.sleep(sleepTime);
			} 	
			catch (Exception e) {
				return;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {

		JButton seleccionado=(JButton)e.getSource();
		if (seleccionado.getText()!="X") {
			puntuacion++;
		}
		l.setText("Tu puntuacion es: "+(puntuacion));
	}

	public static void main(String args[]){
		Topos m=new Topos(200);
		m.init(); m.start();
		Frame f=new Frame("Topos");
		f.add("Center", m);
                f.setSize(900, 400);
		f.setVisible(true);
	}
}
