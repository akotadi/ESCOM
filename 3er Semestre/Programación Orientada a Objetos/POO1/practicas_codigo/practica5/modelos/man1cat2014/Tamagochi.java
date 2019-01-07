import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Tamagochi extends JFrame  implements AccionInt , CambioInt, LeeRed {
static String preguntas []= { "COMO TE LLAMAS","DONDE VIVES"};
static String respuestas []= {"MI NOMBRE ES TAMAGOCHI","EN LA COMPU"};
private Canvas3D canvas3D;
private Appearance ap;
private static Texture texture;
private JPanel jp1;
private JButton bcambia;
private String nombres[]={"carafeliz.jpg","caraenfermo.jpg"};
private String etiqs[]={ "Feliz","Enfermo"};
private int turno;
private Stan body;
private MicroChat microChat;
private Red r;
private Paleta pal;
private PalSliders pals;

public Tamagochi(){
   super("Tamagochi 3D");
   turno=0;
   //setResizable(false); 
   setSize(400, 500);
   GraphicsConfiguration config =     
   SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   canvas3D.setSize(300, 400);
   pal=new Paleta(etiqs,new GridLayout(1, etiqs.length),this); 
   pals=new PalSliders(2, new GridLayout(2, 1), this);
   JPanel jp=new JPanel();
   jp.setLayout(new GridLayout(2, 1));
   jp.add(pal); jp.add(pals);
   add("North", jp); 
   add("Center",canvas3D);   
   setup3DGraphics();
   setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   setVisible(true);
   r=new Red(this);
   microChat = new MicroChat();
   microChat.setRed(r);
   add("South", microChat);
}
void setup3DGraphics(){
   DirectionalLight light1;
   SimpleUniverse universe = new SimpleUniverse(canvas3D);
   universe.getViewingPlatform().setNominalViewingTransform();
   //body=new Body(2,0.0f,6, outgoing.getText(),true, frame, "Avatar1", viewerTG);
   body=new Stan(-0.4f,0.0f,0.0f,"",true, this, "Avatar1", null);
   //body.changeTextureCab(texture, "carafeliz.jpg");
   BranchGroup group= body.mybody();
   Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
   BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   light1 = new DirectionalLight(light1Color, light1Direction);
   light1.setInfluencingBounds(bounds);
   group.addChild(light1);
   universe.addBranchGraph(group);
}
public static void main(String[] args) { new Tamagochi(); }
public void accion(int n){ 
        body.changeTextureCab(texture, nombres[n]);
     	r.escribeRed(new Icono("Tamagochi", n)); 
}
public void cambio(int n, float sval){
	if(n==0)
	    body.giraHI(sval);
        if(n==1)
	    body.giraHD(sval);
}
public void leeRed(Object obj){
	if(obj instanceof Icono){      
        	Icono i=(Icono)obj;  
                System.out.println("Recibi"+nombres[i.getTurno()]);
                body.changeTextureCab(texture, nombres[i.getTurno()]);                 	
	}
        if(obj instanceof Mensaje){ 
		System.out.println("Recibi"+(Mensaje)obj);
                microChat.recibir((Mensaje)obj);
	}
}
static String findMatch(String str) {
		String result = "";
		for(int i = 0; i < preguntas.length; ++i) {
			if(preguntas[i].equalsIgnoreCase(str)) {
				result = respuestas[i];
				break;
			}
		} 
		return result;
}
}
