import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

// javac -cp .:MS3DLoader.jar:portfolio.jar:j3dutils.jar:j3dcore.jar:vecmath.jar Tamagochi.java
// java -cp .:MS3DLoader.jar:portfolio.jar:j3dutils.jar:j3dcore.jar:vecmath.jar Tamagochi


public class Tamagochi extends JFrame  implements LeeRed {
  static String preguntas []= { "COMO TE LLAMAS","DONDE VIVES"};
  static String respuestas []= {"MI NOMBRE ES TAMAGOCHI","EN LA COMPU"};
  private Canvas3D canvas3D;
  private Appearance ap;
  private static Texture texture;
  private JPanel jp1, jp2;
  private JTextField leer;
  private JLabel responder;
  private JButton bcambia;
  private EventHandler eh; 

  private String nombres[][] = {
    {"Arizona.jpg", "caraenfermo.jpg"},
    {"carafeliz.jpg", "caraenfermo.jpg"},
    {"carafeliz.jpg", "caraenfermo.jpg"},
    {"bob-esponja.jpg", "bobEnf.jpg"},
    {"cabeza.png", "cabezamal.png"},
    {"carafeliz.png", "caraenfermo.jpg"},
  };
  private int turno;
  private MicroChat microChat;
  //private Body body;
  private Red r;


  Body b;
  BodyZoey bz;
  BodyBob bb;
  Movible movi;
  Esfera e;
  int avatar=4;


  public Tamagochi(){
   super("Tamagochi 3D");
   turno=0;
   //setResizable(false); 
   setSize(400, 500);
   GraphicsConfiguration config =     
   SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   canvas3D.setSize(300, 400);
   eh = new EventHandler();  
   leer = new JTextField("");
   responder = new JLabel("   ");
   bcambia=new JButton("Cambiar");
   bcambia.addActionListener(eh);  
   
   jp1=new JPanel();
   jp1.setLayout(new GridLayout(3,1,5,5));
   jp1.add(leer);
   jp1.add(responder);
   jp1.add(bcambia);  
   add("North", jp1); 
   add("Center",canvas3D); 
   setup3DGraphics();
   setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   setVisible(true);
   r=new Red(this);
   microChat = new MicroChat(r);
   add("South", microChat);
  }


  void setup3DGraphics(){
      BranchGroup group=null;
     DirectionalLight light1;
     SimpleUniverse universe = new SimpleUniverse(canvas3D);
     universe.getViewingPlatform().setNominalViewingTransform();
     if(avatar== 0){
  	   movi=new Esfera(this);
       movi.changeTextureCab(texture, "Arizona.jpg");
       group = movi.myBody();
     }
     if(avatar== 1){
  	   movi=new Esfera(this);
       movi.changeTextureCab(texture, "carafeliz.jpg");
       group = movi.myBody();
     }
     if(avatar== 2){
       movi=new Body(-0.4f,0.0f,0.0f,"",true, this, "Avatar1");
       movi.changeTextureCab(texture, "carafeliz.jpg");
       group = movi.myBody();
     }
     if(avatar== 3){
       movi=new BodyBob(-0.4f,0.0f,0.0f,"",true, this, "bob-esponja.jpg");
       group = movi.myBody();
     }
     if(avatar== 4){
       movi=new BodyZoey(-0.4f,0.0f,0.0f,"",true, this, "cabeza.png");
       group = movi.myBody();
     }
     if(avatar== 5){
       movi=new Stan(-0.4f,0.0f,0.0f,"",true, this, "Avatar1", null);
       group= movi.myBody();
     }
     BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
     Background fondo=new Background();
     fondo.setColor(1.0f,1.0f,1.0f);
     fondo.setApplicationBounds(bounds);
     group.addChild(fondo);
  //SE DA LUZ A TODO EL ESCENARIO
     AmbientLight luz= new AmbientLight();
     luz.setInfluencingBounds(bounds);
     group.addChild(luz);
     universe.addBranchGraph(group);
  }


  public static void main(String[] args) { new Tamagochi(); }


  class EventHandler implements ActionListener {  
    public void actionPerformed(ActionEvent e1) { 
       Object obj=e1.getSource();
       if(obj instanceof JButton){ 
       	JButton btn=(JButton)e1.getSource();
       	if(btn==bcambia){ 
    	   String s = leer.getText(); 
        r.escribeRed(new Mensaje("Tamagochi",s));} 
       }
    }
  }


  public void leeRed(Object obj){
      if(obj instanceof Mensaje){   
         Mensaje i=(Mensaje)obj;
         String mensaje=i.getTexto();  
         System.out.println("Recibi "+i.getTexto());  
         switch (mensaje.toLowerCase()) {
            case "en que ciudad vives?":
                responder.setText("DF");
                break;
            case "cuantos años tienes?":
                responder.setText("22");;
                break;
            case "en que escuela estudias?":
                responder.setText("ESCOM");;
                break;
            case "que clase estas tomando?":
                responder.setText("POO");;
                break;
            case "quien es tu profesor?":
                responder.setText("Tecla Parra");;
                break;
            case "donde estas?":
                responder.setText("En el laboratorio");;
                break;
            case "en que año naciste?":
                responder.setText("1995");;
                break;
            case "donde naciste?":
                responder.setText("DF");;
                break;
            case "como se llama tu perro?":
                responder.setText("Coffee");;
                break;
            case "cual es tu numero de celular?":
                responder.setText("5549482643");
                break;
            case "como te llamas?":
                responder.setText("Manuel");
                break;
            case "cual es tu color favorito?":
                responder.setText("Rojo");
                break;
            default: 
                responder.setText("OK");
                break;
        }    
      }
      /*if(obj instanceof Mensaje){ 
  		  System.out.println("Recibi "+(Mensaje)obj);
        microChat.recibir((Mensaje)obj);
      }*/
    }
    

    /*static String findMatch(String str) {
  		String result = "";
  		for(int i = 0; i < preguntas.length; ++i) {
  			if(preguntas[i].equalsIgnoreCase(str)) {
  				result = respuestas[i];
  				break;
  			}
  		} 
  		return result;
    }*/
}
