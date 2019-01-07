import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.*;
import javax.vecmath.*;
import javax.media.j3d.BranchGroup;
import com.sun.j3d.utils.image.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import javax.media.j3d.*;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*; 

public class RotaEsfe extends UnicastRemoteObject {
    static JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, 60);
    static Canvas3D canvas3D;
    Appearance ap;
    
    private TextureLoader loader;
    static Sphere sphere;
    TransformGroup tg1 = new TransformGroup();
    private Transform3D rotation = new Transform3D();

    private ChatServer mycs;

     int primflags = Primitive.GENERATE_NORMALS +

   Primitive.GENERATE_TEXTURE_COORDS/*+Primitive.ENABLE_APPEARANCE_MODIFY*/ ; 
   private double angle = 0.0;


//metodo remoto
public synchronized void gira (String s) 
{
   System.out.println("Message: "+s);
   angle=Math.PI*Integer.parseInt(s)/50.0f;
   rotation.rotY(angle);
   tg1.setTransform(rotation);
   //slider.setValue(Integer.parseInt(s));

}


public RotaEsfe(ChatServer cs) throws RemoteException
{
   
    mycs=cs;
    mycs.register(this);

    
    slider.setMajorTickSpacing(10);
    slider.setMinorTickSpacing(5); 
    slider.setPaintTicks(true);
    slider.addChangeListener(new SliderHandler());

    GraphicsConfiguration config =
           SimpleUniverse.getPreferredConfiguration();
    canvas3D = new Canvas3D(config);
    

    SimpleUniverse universe = new SimpleUniverse(canvas3D);
    
    tg1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    Transform3D transform1 = new Transform3D();
    Vector3f vector1 = new Vector3f(+0.3f, 0.0f, 0.0f);
    transform1.setTranslation(vector1);
    tg1.setTransform(transform1);
   
    ap = new Appearance();
    loader = new TextureLoader("carafeliz.jpg", null);
    ap.setTexture(loader.getTexture());
    sphere  = new Sphere(0.5f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, ap);

    tg1.addChild(sphere);
    BranchGroup group = new BranchGroup();
    group.addChild(tg1);

    universe.getViewingPlatform().setNominalViewingTransform();
    universe.addBranchGraph(group);   
    

    
    

}
public class SliderHandler implements ChangeListener {
       public void stateChanged(ChangeEvent e) {       
          angle=Math.PI*slider.getValue()/50.0f;
	  rotation.rotY(angle);
          tg1.setTransform(rotation);
          try
          {
              mycs.broadcast(""+slider.getValue());
           }
		catch(Exception ee)
		{
			System.err.println("Problema slider"+ee);
		}
       }
}
public static void main( String[] args ) {  
   ChatServer cs;
   Frame f=new Frame("Rota");
   f.setSize(500, 400); 
   f.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent winEvent) {
          System.exit(0);
       }
   	 }); 
   String url = "rmi://localhost/ChatServer";
   try
   {
         cs= (ChatServer) Naming.lookup(url);
         new RotaEsfe(cs);
   }
   catch (Exception e)
   {
          System.err.println("Problema c"+e) ;
   }
   f.add("West", slider);
   f.add("Center",canvas3D);
   f.setVisible(true);
   
}
} 
