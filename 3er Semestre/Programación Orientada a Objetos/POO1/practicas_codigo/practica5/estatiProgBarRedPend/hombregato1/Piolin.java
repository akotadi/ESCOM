import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.*;
import java.awt.image.*;

public class Piolin extends Frame implements LeeRed {
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
SimpleUniverse universe;
BranchGroup group;

Body bodyyo, bodyotro;
Paleta pal;
private Red r;
String opciones[]={"Cambia", "Envia", "Atender", "J gato", "Imagen", "Pinta"};

public void leeRed(Object c){
	if(c instanceof Login){ 
		Login l=(Login)c;  
                if(l.getPrograma().equals("Piolin")){
                      if(!l.getNick().equals(jtnick.getText())){
                               System.out.println("login.");
                               bodyotro=new Body(0.4f, 0.0f, 0.0f, l.getNick(), 
                                                  true, this, "Avatar1");
   				bodyotro.changeTextureCab(texture, "carafeliz.jpg");  
   				group= bodyotro.mybody();
   				objRotateotro=bodyotro.getCab(); 
				universe.addBranchGraph(group);
                                if(!l.getRep()){
                                Object clog=null; 
   				clog=new Login("Piolin", nick, true);
				r.escribeRed(clog);
                                }//if	
			}
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
                         if(!m.getNick().equals(jtnick.getText()))				 jl.setText(m.getNick()+":"+m.getTexto());

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
			if(!game.getNick().equals(jtnick.getText())){
				Frame f=new Frame("Gato");
				f.add("Center", game.getMinino());
				f.addWindowListener(eh);
                                f.setVisible(true);
			}
                 }
	}
	if(c instanceof Pinta){
		Pinta pinta=(Pinta)c;
		if(pinta.getPrograma().equals("Piolin")){
			if(!pinta.getNick().equals(jtnick.getText())){
				Frame f=new Frame("Paint");
				f.add("Center", pinta.getDibuja());
				f.addWindowListener(eh);
                                f.setVisible(true);
			}
		}
	}

}
public Piolin(){
   super("Piolin 3D");   
   nick = JOptionPane.showInputDialog("Escriba su nick", ""); 
   if(nick.equals(""))
	System.exit(0);
   turno=0; turno1=0; 
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
   r=new Red(this);
   Object clog=null; 
   clog=new Login("Piolin", nick, false);
   r.escribeRed(clog);
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
   universe = new SimpleUniverse(canvas3D); 
   universe.getViewingPlatform().setNominalViewingTransform();
   bodyyo=new Body(-0.4f, 0.0f, 0.0f, nick , true, this, "Avatar1");
   bodyyo.changeTextureCab(texture, "carafeliz.jpg"); 
   group= bodyyo.mybody();
   objRotateyo =bodyyo.getCab();
   //universe.addBranchGraph(group);
   
   Color3f light1Color = new Color3f(1.0f, 1.0f, 0.0f);
   BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   light1 = new DirectionalLight(light1Color, light1Direction);
   light1.setInfluencingBounds(bounds);
   group.addChild(light1);
   universe.addBranchGraph(group);
}
public static void main(String[] args) { new Piolin(); }
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
         c=new Juega("Piolin", nick,new Gato("localhost"));  
	Frame f=new Frame("Gato Local");
	f.add("Center", ((Juega)c).getMinino());
	f.addWindowListener(eh);
        f.setVisible(true);
     }
     if(n==5){  
         c=new Imagen("Piolin", nick, jtima.getText());  
     }
     if(n==6){  
         c=new Pinta("Piolin", nick, new Dibuja("localhost"));  
	Frame f=new Frame("Pinta Local");
	f.add("Center", ((Pinta)c).getDibuja());
	f.addWindowListener(eh);
        f.setVisible(true);
     }
     r.escribeRed(c);	
  }
  public void windowClosing(WindowEvent e){ System.exit(0); }
}
}
