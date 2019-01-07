import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class JuegaTopos implements Runnable {
	private Topos t;
	Thread JuegaToposThread;
	int sleepTime;
	int puntuacion=0;

public JuegaTopos(int sleep){
		sleepTime=sleep;
		JuegaToposThread=new Thread(this);
		JuegaToposThread.start();
	}

	public void run(){
		while(true){
			t=new Topos(puntuacion);
			t.init();
			t.start();
			Frame f=new Frame("Topos");
			f.add("Center", t);
            f.setSize(1000, 1000);
			f.setVisible(true);
			

			try{
				Thread.sleep(sleepTime);
			} 	
			catch (Exception e) {
				return;
			}
			puntuacion=puntuacion+(t.puntuacion-puntuacion);
			f.setVisible(false);
		}
	}

	public static void main(String args[]){
		new JuegaTopos(5000);
	}
}