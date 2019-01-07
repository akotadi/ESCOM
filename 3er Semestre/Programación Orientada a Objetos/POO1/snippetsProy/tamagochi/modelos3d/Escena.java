import com.sun.j3d.utils.picking.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
//import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class Escena {

private PickCanvas pickCanvas;
BranchGroup group;
//Frame frame;
public Escena(){
    JFrame jf = new JFrame("Planetario");
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas = new Canvas3D(config);
    canvas.setSize(600, 500);
    SimpleUniverse universe = new SimpleUniverse(canvas);

    Background fondo=new Background();
   fondo.setColor(1.0f,1.0f,1.0f);
   fondo.setApplicationBounds(getBoundingSphere()/*bounds*/);
   
    BranchGroup group = new BranchGroup();
    group.addChild(fondo);
    lightScene(group);
    universe.addBranchGraph(group);
    universe.getViewingPlatform().setNominalViewingTransform();

    Stan body=new Stan(-0.4f,0.0f,0.0f,"",true, jf, "Avatar1", null);
    group= body.mybody();
    universe.addBranchGraph(group);
    Body b=new Body(-0.8f,0.0f,0.0f,"",true, jf, "Avatar1");
    group= b.mybody();
    universe.addBranchGraph(group);
    BodyBob bb=new BodyBob(0.0f,0.0f,0.0f,"",true, jf, "bob-esponja.jpg");
    //BodyBob bb = new BodyBob(jf);
    group = bb.mybody();
    universe.addBranchGraph(group);
    BodyZoey bz=new BodyZoey(-0.4f,0.0f,0.0f,"",true, jf, "cabeza.png");
    group = bz.myBody();
    universe.addBranchGraph(group);
    Raton raton=new Raton();
    group=raton.getGroup();
    universe.addBranchGraph(group);
    Gato gato=new Gato();
    group=gato.getGroup();
    universe.addBranchGraph(group);
    group=obtenCoche();
    universe.addBranchGraph(group);
    
    pickCanvas = new PickCanvas(canvas, group);
    pickCanvas.setMode(PickCanvas.BOUNDS);
    
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    jf.add(canvas); jf.pack(); jf.setVisible(true); 
}
private void lightScene(Group bg)
  // one directional light
  { Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

    Vector3f lightDir = new Vector3f(1.0f, -1.0f, -0.8f); // upper left
    DirectionalLight light1 = 
            new DirectionalLight(white, lightDir);
    light1.setInfluencingBounds(getBoundingSphere()/*bounds*/);
    /*AmbientLight luz= new AmbientLight();
    luz.setInfluencingBounds(bounds);
    bg.addChild(luz);*/
    bg.addChild(light1);
  }  //
BoundingSphere getBoundingSphere( ){
		return new BoundingSphere( new Point3d( 0.0,0.0,0.0 ), 250.0 );
}
public static void main( String[] args ) { new Escena(); }

BranchGroup obtenCoche(){
     
    BranchGroup group = new BranchGroup();
    Vector3f vector = new Vector3f(0.5f, 0.0f, 0.0f);
    Transform3D transform = new Transform3D();
    transform.setTranslation(vector);
    Vector3d escala = new Vector3d(0.2f, 0.2f, 0.2f);
    transform.setScale(escala);
    TransformGroup transformGroup = new TransformGroup(transform);
  
    Node carBody = CarBuilder.getBody();
    transformGroup.addChild(carBody);

    group.addChild(transformGroup);
    return group;
}
}
