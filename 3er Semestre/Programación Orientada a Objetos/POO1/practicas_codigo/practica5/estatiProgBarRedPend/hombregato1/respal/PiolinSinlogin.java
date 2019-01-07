import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.image.*;

public class Piolin extends Frame implements Runnable {
private Canvas3D canvas3D;
private static Texture texture;
private Appearance appearance;
private TextureLoader loader;
private JPanel jp, /*jp1,*/ jp2, jp3;
private JLabel jl, jlima;
private JTextField jt, jtima;
private JTextField jtnick;
private EventHandler eh; 
private JFrame fimareci;
private MuestraIcon mi;

private Socket cliente;  

private ObjectInputStream oisNet;
private ObjectOutputStream oosNet;
private int puerto;
private Thread hilo;
private int turno, turno1;
private BufferedImage bufimas[];
private ImageIcon iconos[];
private String nombres[]={"carafeliz.jpg","caraenfermo.jpg"};
private String textos[]={"Ignorar","Atender"};
private String nick;
private double angulo = 0.0;
private TransformGroup objRotateyo=new TransformGroup(); 
private TransformGroup objRotateotro=new TransformGroup();
private Transform3D rotationyo = new Transform3D();
private Transform3D rotationotro = new Transform3D();
Body bodyyo, bodyotro;
Paleta pal;
String opciones[]={"Cambia", "Envia", "Atender", "J gato", "Imagen"};
public void run()
{
	Object c=null;
	int j,k=0;

   	for(;;)
   	{
    		j=0;
    		try {
                        c=oisNet.readObject();	
    		} catch ( IOException e) {
			System.out.println("IO ex"+e);
         		j=1;
                } catch (ClassNotFoundException ex) {
                     	System.out.println("Class no found"+ex);
			j=1;
		} 
    		if (j==0) {	
			if(c instanceof Login){ 
				 Login l=(Login)c;  
                           if(l.getPrograma().equals("Piolin")){
                             if(!l.getNick().equals(jtnick.getText()))
                                 
			    }	
			}
			if(c instanceof Icono){      
                             Icono i=(Icono)c;  
                           if(i.getPrograma().equals("Piolin")){
                             ImageIcon ic=(ImageIcon)i.getIcon(); 
                             if(!i.getNick().equals(jtnick.getText()))
                                 bodyotro.changeTextureCab(texture, nombres[i.getTurno()]);                   
			    }	
			}
			if(c instanceof Imagen){      
                             Imagen i=(Imagen)c;   
                           if(i.getPrograma().equals("Piolin")){ 
                             if(!i.getNick().equals(jtnick.getText())){
			       ImageIcon ic=(ImageIcon)i.getIcon(); 
			       mi.setComent(i.getNick());
			       mi.setImage(ic);  
                               
			     } 
 			   }           
			}
                        if(c instanceof Mensaje){
				Mensaje m=(Mensaje)c;
                           if(m.getPrograma().equals("Piolin")){
                                if(!m.getNick().equals(jtnick.getText()))				   jl.setText(m.getNick()+":"+m.getTexto());
                           }
			}
			if(c instanceof Rotacion){
				Rotacion r=(Rotacion)c;
			   if(r.getPrograma().equals("Piolin")){
				if(!r.getNick().equals(jtnick.getText()))
				   rotationotro.rotY(r.getAngulo());
                                   objRotateotro.setTransform(rotationotro);
			   }
			}
			if(c instanceof Juega){
				Juega game=(Juega)c;
			   if(game.getPrograma().equals("Piolin")){
				if(!game.getNick().equals(jtnick.getText()))
				{
				Frame f=new Frame("Gato");
				f.add("Center", game.getMinino());
				f.addWindowListener(eh);
                                f.setVisible(true);
				}
                           }
			}	
		}
	}
}
public Piolin(){
   super("Piolin 3D");   
   nick = JOptionPane.showInputDialog("Escriba su nick", ""); 
   if(nick.equals(""))
	System.exit(0);
   turno=0; turno1=0; puerto=5000;
   setSize(400, 400);
   GraphicsConfiguration config =     
SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   eh = new EventHandler();  
   pal=new Paleta(opciones, new FlowLayout(), eh);
   fimareci=new JFrame("Imagen"); 
   jl=new JLabel("Red:               ");
   jt=new JTextField(15);
   jlima=new JLabel("Imagen");
   jtima=new JTextField(15);
   jtnick=new JTextField(5);
   jtnick.setText(nick);
   jp2=new JPanel(); jp3=new JPanel();
   jp=new JPanel();
   jp.setLayout(new GridLayout(3,1));
   jp3.add(jlima);
   jp3.add(jtima);
   jp2.add(jl);jp2.add(jt);jp2.add(jtnick);
   jp.add(pal); jp.add(jp2);jp.add(jp3);
   add("North", jp);
   add("Center",canvas3D);
   addWindowListener(eh);
   setup3DGraphics(); 
   mi=new MuestraIcon("imagen recibida", iconos[0]);
   fimareci.add("Center", mi);
   setVisible(true);
   fimareci.setVisible(true);
   int i=0;
   while(i==0)
   {
    	i=1;
    	System.out.println("Esperando por el servidor . . .");
    	try {
		cliente=new Socket( "localhost", puerto);
    	} catch ( IOException e) {
            	System.out.println("Fallo creacion Socket");
            	i=0;
   	}
   }
   System.out.println("Connectado al servidor.");
   try {
            oisNet = getOISNet(cliente.getInputStream());
            oosNet = getOOSNet(cliente.getOutputStream()); 
   } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al crear los fujos de objeto"+e);
   }
   hilo= new Thread(this);
   hilo.start();
}
void setup3DGraphics(){
   DirectionalLight light1;
   iconos=new ImageIcon[2];
   bufimas=new BufferedImage[2];
   loader = new TextureLoader(nombres[0], "RGB",this);
   ImageComponent2D image = loader.getImage();
   bufimas[0] = image.getImage();
   iconos[0] = new ImageIcon(bufimas[0]); 
   loader = new TextureLoader(nombres[1], "RGB",this);
   image = loader.getImage();
   bufimas[1] = image.getImage();
   iconos[1] = new ImageIcon(bufimas[1]); 
   SimpleUniverse universe = new SimpleUniverse(canvas3D); 
   universe.getViewingPlatform().setNominalViewingTransform();
   bodyyo=new Body(-0.4f, 0.0f, 0.0f, nick , true, this, "Avatar1");
   bodyyo.changeTextureCab(texture, "carafeliz.jpg"); 
   BranchGroup group= bodyyo.mybody();
   objRotateyo =bodyyo.getCab();
   universe.addBranchGraph(group);
   bodyotro=new Body(0.4f, 0.0f, 0.0f, "otro", true, this, "Avatar1");
   bodyotro.changeTextureCab(texture, "carafeliz.jpg");  
   group= bodyotro.mybody();
   objRotateotro=bodyotro.getCab();
   Color3f light1Color = new Color3f(1.0f, 1.0f, 0.0f);
   BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   light1 = new DirectionalLight(light1Color, light1Direction);
   light1.setInfluencingBounds(bounds);
   group.addChild(light1);
   universe.addBranchGraph(group);
}
public static void main(String[] args) { new Piolin(); }
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
class EventHandler extends WindowAdapter implements AccionInt {  
  public void accion(int n){
     Object c=null; 
     nick=jtnick.getText(); 
     System.out.println("accion n =( "+n+" )");
     if(n==1){ 
	 turno=1-turno;
         bodyyo.changeTextureCab(texture, nombres[turno]);
         c=new Icono("Piolin", nick, iconos[turno], turno);
     } 
     if(n==2){  
         c=new Mensaje("Piolin", nick, jt.getText());  
     }
     if(n==3){   
         turno1=1-turno1;
         //brota.setText(textos[turno1]);
         angulo += Math.PI;
         
         rotationyo.rotY(angulo);
         objRotateyo.setTransform(rotationyo);
         c=new Rotacion("Piolin", nick, angulo);
     }
     if(n==4){  
         c=new Juega("Piolin", nick,new Gato());  
	Frame f=new Frame("Gato Local");
	f.add("Center", ((Juega)c).getMinino());
	f.addWindowListener(eh);
        f.setVisible(true);
     }
     if(n==5){  
         c=new Imagen("Piolin", nick, jtima.getText());  
     }
     try {
          oosNet.writeObject(c);
     } catch (IOException ex) {
          ex.printStackTrace();
     }		
  }
  public void windowClosing(WindowEvent e){ System.exit(0); }
}
}
