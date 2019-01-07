import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
//javac -cp .:JID3.jar:jl-1.0.1.jar:basicplayer3.0.jar Post1.java
//java -cp .:JID3.jar:jl-1.0.1.jar:basicplayer3.0.jar Post1
public class Post1 extends Panel implements ActionListener {
public Boolean reproduciendo, pausado;
Button bc; 
Reproductor aTunesPlayer;
public Post1(){
        reproduciendo=false;  
        pausado=false; 
	aTunesPlayer=new Reproductor();
	bc=new Button("feel.mp3");
	bc.addActionListener(this);
	add(bc);
}  
public void actionPerformed(ActionEvent e){
	accion(2);
}  

public void accion(int n){
		if (n == 2){
			System.out.println("Entro a algo");
			if (reproduciendo){
				try {
					aTunesPlayer.Pausa();
					reproduciendo = false;
					pausado = true;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
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
				}
				else{
					System.out.println("Entro donde quiero");
					try{					
                                     String songPath =bc.getLabel();              			           
                try {
			aTunesPlayer.AbrirFichero(songPath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
						aTunesPlayer.Play();
						reproduciendo = true;
						System.out.println("play");
					}catch(Exception xd){
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


					                                          Post1 p=new Post1();
Frame f=new Frame("Reproduce");
f.add("Center", p);
f.setSize(400,400);
f.setVisible(true);
}
}


/*
    public static void main(String[] args) {
        try {
            BasicPlayer player= new BasicPlayer(); // Llamo la clase de la libreria Basic Player, que reproduce
            player.open(new File("feel.mp3"));// Dentro las "" va la                     ruta de tu archivo mp3.
            player.play();// Llama al método Reproducir también existen los métodos  stop,resume.           
        } catch (BasicPlayerException ex) {
            System.out.print("-------Error-----"+ex.getMessage());
        }// Fin try
    }// Fin Main
}*/
