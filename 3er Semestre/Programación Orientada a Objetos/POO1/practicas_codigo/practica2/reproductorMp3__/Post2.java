import java.io.File;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
//javac -cp .:JID3.jar:jl-1.0.1.jar:basicplayer3.0.jar Post2.java
//java -cp .:JID3.jar:jl-1.0.1.jar:basicplayer3.0.jar Post2
public class Post2 extends Panel implements ActionListener {
public Boolean reproduciendo, pausado;
JButton bc1, bc2; 
Reproductor aTunesPlayer;
public Post2(){
        reproduciendo=false;  
        pausado=false; 
	aTunesPlayer=new Reproductor(this);
	bc1=new JButton("feel.mp3");
	bc1.addActionListener(this);
	add(bc1);
        bc2=new JButton("Loosing.mp3");
	bc2.addActionListener(this);
	add(bc2);
}  
public void actionPerformed(ActionEvent e){
	JButton b=(JButton)e.getSource();
	accion(2, b.getText());
}  
public void accion(int n, String mp3){
	if (n == 2){
		System.out.println("Entro a algo");
		if (reproduciendo){
			try {
				aTunesPlayer.Pausa();
				reproduciendo = false;
				pausado = true;
			} catch (Exception e1) { e1.printStackTrace();}
		}
		else{  
			System.out.println("Entro un poco mas");
			if (pausado){
				try{
					aTunesPlayer.Continuar();
					reproduciendo = true;
					pausado =false;
					System.out.println("Entro c");
				}catch(Exception xd){
						xd.printStackTrace();
				}
			} else{
				System.out.println("Entro donde quiero");
				try{					
                                     String songPath = mp3;                      
                			try {
						aTunesPlayer.AbrirFichero(songPath);
					} catch (Exception ex) { ex.printStackTrace();}
					aTunesPlayer.Play();
					reproduciendo = true;
					System.out.println("play");
				} catch(Exception xd){
					System.out.println("Error "+xd);
					xd.printStackTrace();
				}
			}
		}
	}
	if (n == 3){
		try {
			System.out.println("stop");
			aTunesPlayer.Stop();
			reproduciendo=false;  
                	pausado=false;  
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
public static void main(String[] args) {
	Post2 p=new Post2();
	JFrame f=new JFrame("Reproduce");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.add("Center", p);
	f.setSize(400,400);
	f.setVisible(true);
}
}
