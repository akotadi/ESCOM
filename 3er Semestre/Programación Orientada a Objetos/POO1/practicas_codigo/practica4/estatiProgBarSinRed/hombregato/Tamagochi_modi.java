import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Tamagochi extends Frame  {
private Canvas3D canvas3D;
private Appearance ap;
private Texture feliz, enfermo;
private static Texture texture;

private JPanel jp;
private JButton bcomer;
private JLabel jl;
private JProgressBar progressBar = new JProgressBar();
private EventHandler eh; 

BranchGroup grupos[];
Body b;
BodyBob bb;
Esfera e;
int val;
int avatar=0;

public Tamagochi(){
   super("Tamagochi 3D");
   val=0;
   setResizable(false); setSize(600, 700);
   GraphicsConfiguration config =     
   SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   eh = new EventHandler();  
   bcomer=new JButton("Comer");
   bcomer.addActionListener(eh);
   jl=new JLabel("Hambre");
   progressBar.setValue(0);
   jp=new JPanel();
   jp.add(jl); jp.add(progressBar);
   jp.add(bcomer);
   add("North", jp); add("Center",canvas3D);
   addWindowListener(eh);
   setup3DGraphics();
   setVisible(true);
}
void setup3DGraphics(){
   grupos = new BranchGroup[3];
   //BranchGroup group=null;
   DirectionalLight light1;
   
   SimpleUniverse universe = new SimpleUniverse(canvas3D);
   universe.getViewingPlatform().setNominalViewingTransform();
   if(avatar== 0){
	e=new Esfera(this);
        e.changeTextureCab(texture, "carafeliz.jpg");
        groupos[0]= e.getBranchGroup();
   }
   if(avatar== 1){
      b=new Body(-0.4f,0.0f,0.0f,"",true, this, "Avatar1");
      b.changeTextureCab(texture, "carafeliz.jpg");
      groupos[1]= b.mybody();
   }
   if(avatar== 2){
      bb = new BodyBob(this);
      groupos[2]= bb.getBranchGroup();
   }
   Color3f light1Color = new Color3f(1.0f, 1.0f, 0.0f);
   BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   light1 = new DirectionalLight(light1Color, light1Direction);
   light1.setInfluencingBounds(bounds);
   group.addChild(light1);
   universe.addBranchGraph(group);
}
public static void main(String[] args) { new Tamagochi(); }
class EventHandler extends WindowAdapter implements ActionListener {  
  public void actionPerformed(ActionEvent e1) {  
     JButton btn=(JButton)e1.getSource();
     if(btn==bcomer){ 
	val=100-val;
        System.out.println("Tamagochi come 2#### "+val); 
        if(val == 100){
           if(avatar== 0) e.changeTextureCab(texture, "caraenfermo.jpg"); 
	   if(avatar== 1) b.changeTextureCab(texture, "caraenfermo.jpg"); 
           if(avatar== 2) bb.changeTextureCab(texture, "bobEnf.jpg"); 
        }    
	else { 
           if(avatar== 0) e.changeTextureCab(texture, "carafeliz.jpg");  
           if(avatar== 1) b.changeTextureCab(texture, "carafeliz.jpg");
           if(avatar== 2) bb.changeTextureCab(texture, "bob-esponja.jpg");
        }
        progressBar.setValue(val);
     }
  }
  public void windowClosing(WindowEvent e){ System.exit(0); }
}
}

