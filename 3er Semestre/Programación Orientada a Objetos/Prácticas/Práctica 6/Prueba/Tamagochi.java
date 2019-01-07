import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// javac -cp .:MS3DLoader.jar:portfolio.jar:j3dutils.jar:j3dcore.jar:vecmath.jar Tamagochi.java
// java -cp .:MS3DLoader.jar:portfolio.jar:j3dutils.jar:j3dcore.jar:vecmath.jar Tamagochi


public class Tamagochi extends JFrame  implements LeeRed {
  static String preguntas []= { "COMO TE LLAMAS","DONDE VIVES","CUANTOS AÑOS TIENES","DONDE ESTUDIAS","DONDE ESTAS","CUAL ES TU COLOR FAVORITO",
  "DONDE NACISTE","PELICULA FAVORITA","COMO SE LLAMA TU MASCOTA","MUSICA FAVORITA"};
  static String respuestas []= {"MI NOMBRE ES TAMAGOCHI","EN LA COMPU","1045","INTERNET","LABORATORIO","ROJO","JAVA","YO, ROBOT","MOUSE","ROCK"};
  //private JPanel jp1, jp2;
  private MicroChat microChat;
  //private Body body;
  private Red r;
  private JPanel jp;

  public Tamagochi(){
   super("Tamagochi 3D");
   //setResizable(false); 
   r=new Red(this);
   microChat = new MicroChat(r);
   jp = new JPanel();
   jp.setLayout(new GridLayout(1,1,5,5));
   jp.add(microChat);
   setSize(400, 500);
   add("Center",jp);
   
   setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   setVisible(true);
   
   //add("South", microChat);
  }

  public static void main(String[] args) { new Tamagochi(); }

  public void leeRed(Object obj){
      if(obj instanceof Mensaje){   
         Mensaje i=(Mensaje)obj;
         String mensaje=i.getTexto();  
         System.out.println("Recibi "+i.getTexto());  
         Mensaje j = new Mensaje("Tamagochi",findMatch(mensaje));
         microChat.recibir(j);
      }
    }
    

    static String findMatch(String str) {
  		String result = "";
  		for(int i = 0; i < 10; ++i) {
  			if(preguntas[i].equalsIgnoreCase(str)) {
  				result = respuestas[i];
  				break;
  			}
  		} 
  		return result;
    }
}
