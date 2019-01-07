import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Chat3D extends Frame {
Canvas3D canvas3D;
Appearance ap;
Texture feliz;
Texture enfermo;
private static Texture texture;
private Appearance appearance;
private TextureLoader loader;
Body b;

Panel p;
Button bfeliz;
Button benfer;
EventHandler eh; 

public Chat3D() {
   super("Texturas");  
   setResizable(false); setSize(500, 400);
   GraphicsConfiguration config =
           SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   eh = new EventHandler();  
   bfeliz=new Button("Feliz");
   bfeliz.addActionListener(eh);
   benfer=new Button("Enfermo");
   benfer.addActionListener(eh);
   p=new Panel();
   p.add(bfeliz); p.add(benfer);
   add("North", p); add("Center",canvas3D);
   addWindowListener(eh);
   setup3DGraphics();
   setVisible(true);
}
void setup3DGraphics(){
   DirectionalLight light1;
   SimpleUniverse universe = new SimpleUniverse(canvas3D);;
   universe.getViewingPlatform().setNominalViewingTransform();
   b=new Body(-0.4f, 0.0f, 0.0f, "", true, this, "Avatar1");
   b.changeTextureCab(texture, "carafeliz.jpg");
   BranchGroup group= b.mybody();
   Color3f light1Color = new Color3f(1.0f, 1.0f, 0.0f);
   BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   light1 = new DirectionalLight(light1Color, light1Direction);
   light1.setInfluencingBounds(bounds);
   group.addChild(light1);
   universe.addBranchGraph(group);
}
class EventHandler extends WindowAdapter implements ActionListener {  
  public void actionPerformed(ActionEvent e) {  
     Button btn=(Button)e.getSource();
     if(btn==bfeliz){
       b.changeTextureCab(texture, "carafeliz.jpg");
     }
     if(btn==benfer){
       b.changeTextureCab(texture, "caraenfermo.jpg");
     }
  }
  public void windowClosing(WindowEvent e){ System.exit(0);}
 }
public static void main(String[] args) { new Chat3D();}
}

