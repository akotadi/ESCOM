//mover el ave
import java.awt.*; 
import java.awt.event.*;
import java.awt.image.*; 
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
public class ProgDibuja extends JFrame {
BufferedImage birds[], pipes[], fondos[];
BufferedImage spriteSheet=null;
int cta=0, x=0, longi=0;
final int width = 34, height = 42;
final int rows = 3, cols = 1;
private BufferedImage getSprite(BufferedImage spriteSheet, int i, int j, 
         int width, int height){      
    return  spriteSheet.getSubimage(j , i , width, height);
}
public ProgDibuja(){
   super("Flappy Bird");
   try {   
       spriteSheet = ImageIO.read(new File("images/Flappy-Graphics.png")); 
   }catch(IOException e){  System.out.println("Image not found"); }
   fondos = new BufferedImage[2]; 
   birds = new BufferedImage[3];  
   pipes = new BufferedImage[6]; 
   fondos[0] =getSprite(spriteSheet,0, 0, 290, 512);  //dia
   birds [0] =getSprite(spriteSheet,750, 220, 50, 50);//alas arriba
   birds [1] =getSprite(spriteSheet,800, 220, 50, 50);//alas enmedio
   birds [2] =getSprite(spriteSheet,850, 220, 50, 50);//alas abajo
   pipes[0] =getSprite(spriteSheet,640, 0, 55, 210);
   pipes[1] =getSprite(spriteSheet, 760, 110, 55, 210);
   longi=190+(int)(Math.random()*130);
   pipes[2] =getSprite(spriteSheet, 960-longi, 110, 55, longi);
   pipes[3] =getSprite(spriteSheet, 450+longi, 0, 55, 510-longi);
   pipes[4] =getSprite(spriteSheet,640, 0, 55, 320);
   pipes[5] =getSprite(spriteSheet, 760, 110, 55, 190);
   setSize(290, 510); setVisible(true);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void paint(Graphics g){
    g.drawImage(fondos[0], 0, 0, this);
    g.drawImage(pipes[1], 0, 0, this);
    g.drawImage(pipes[0], 0, 300, this);
    g.drawImage(pipes[2], 100, 0, this);
    g.drawImage(pipes[3], 100, longi, this);
    g.drawImage(pipes[4], 200, 0, this);
    g.drawImage(pipes[5], 250, 320, this);
    g.drawImage(birds[0], x, 250, this); 
}
public static void main(String[] args){ ProgDibuja pd=new  ProgDibuja(); }
} 

