// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

// The TierraVir class sets up the window and canvas and
// reads in the options for the virtual world program.

import javax.media.j3d.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.*;
import javax.vecmath.*;
import java.applet.Applet;
import javax.swing.JApplet;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Primitive;
import java.util.Vector;
import java.util.HashMap;

public class TierraVir extends Applet implements ActionListener, KeyListener {
protected Button go = new Button("   Start   ");
private Timer timer;
private SimpleUniverse universe = null;
private Vector3f viewVector;
private ViewingPlatform vp = null;
private TransformGroup viewTG = null;
private Transform3D viewTransform = new Transform3D();
private Mover mover;
//private RootPaneContainer frame;
private Ride activeRide = null;
private boolean onRide = false;
private Ride spinner=null;
private Ride spinner2=null;
protected static boolean redblue = false;
protected static int screenSize = 420;
protected static boolean mainRun=false;
protected static int mode;
protected Canvas3D canvas3D;
protected static boolean sound=false;
public static boolean on=false;
protected Canvas3D canvas3D2;
protected int delay = 70;
protected boolean firstTime=true;
private static int NUMRIDES=4;
protected Point3f[] rideLocation = new Point3f[NUMRIDES];
protected Ride[] ride  = new Ride[NUMRIDES];
private JComboBox jcb;
HashMap map;


public static final int THREE_DIMENSIONAL=1, TWO_DIMENSIONAL=2, STEREO=3;

public static void main(String[] args) {
        mainRun = true;
        mode=TWO_DIMENSIONAL;
        TierraVir land = new TierraVir();     
        new MainFrame(land, screenSize, screenSize);
}
public TierraVir() {
    go.addActionListener(this);
    go.addKeyListener(this);
    this.setUp();
}
public void setUp() {   
       setLayout(new BorderLayout());
       add(go,BorderLayout.NORTH);
       Vector nomPlanet=new Vector();
    	nomPlanet.addElement("Carrusel");
    	nomPlanet.addElement("Rueda");
    	nomPlanet.addElement("Sillas");
       map = new HashMap();
       map.put( "Carrusel", new Vector3f(7f,1f,7f));
       map.put( "Rueda", new Vector3f(0f,1f,-15f));
       map.put(  "Sillas", new Vector3f(-5f,1f,0f));
       jcb=new JComboBox(nomPlanet);
       jcb.addActionListener(this);
       add(jcb,BorderLayout.SOUTH);
  // create and size canvas, add it to frame
    GraphicsConfiguration config =
            SimpleUniverse.getPreferredConfiguration();
           canvas3D = new Canvas3D(config);
        timer = new Timer(delay,this);
    universe = new SimpleUniverse(canvas3D);
    BranchGroup group = new BranchGroup();
    group.setCapability(group.ALLOW_CHILDREN_WRITE);
    group.setCapability(group.ALLOW_CHILDREN_EXTEND);
    // directional and ambient white light
    new MyLights(group);

    // background
    new SkyBackground(group,this); // use this for icons: , C.blue);
     // Create a textured floor

    int primFlags=Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS;
    //Sphere sphere = new Sphere(0.5f, primflags, ap);
    Appearance ap = new Appearance();
    Palette.addTexture("tile2.gif",ap,this);
    //Palette.addTexture("tiles.jpg",ap,this);

    Box floor = new Box(4000f,4000f,0.05f,primFlags,new StandardAppearance(Color.green),2);
    floor.getShape(Box.FRONT).setAppearance(ap);
    //use this for icons - floor.getShape(Box.FRONT).setAppearance(Palette.GREEN);
    //Box floor = new Box(4000f,.1f,4000f,new A(C.green));
    //floor.setPickable(false);
    Transform3D turnFloor = new Transform3D();
    turnFloor.rotX(-Math.PI/2);
    Transform3D turnFloor2 = new Transform3D();
    turnFloor2.rotZ(Math.PI/4);
    turnFloor.mul(turnFloor2);
    TransformGroup floorTG = new TransformGroup(turnFloor);
    floorTG.addChild(floor);
    group.addChild(floorTG);
    addRides(group, universe);
    // set position of viewer
    viewVector = new Vector3f(.0f,1.8f, 10.0f);
/*
    viewVector = new Vector3f(.0f,.0f, 0.0f);
    rideLocation[0] = new Point3f(-5f,0f,0f);
    rideLocation[1] = new Point3f(11f,0f,0f);
    rideLocation[2] = new Point3f(0f,0f,-15f);
    (1f,3.1f,-2f);*/
    //viewVector = new Vector3f(7f,0f,7f);
    viewTransform.setTranslation(viewVector);
    vp = universe.getViewingPlatform();
    viewTG =vp.getViewPlatformTransform();
    viewTG.setTransform(viewTransform);
    mover = new Mover(viewTransform, viewVector,
                            viewTG, 0);
    universe.addBranchGraph(group);
  //  timer = new Timer(80,this);
   // timer.start();

}
public void addRides(Group group, SimpleUniverse universe) {
  //  rideLocation = new )Point3f[2];
  //  ride  = new Ride[2];
    TransformGroup firstSpinnerPos = Pos.at(-5f,0f,0f);
    rideLocation[0] = new Point3f(-5f,0f,0f);
    group.addChild(firstSpinnerPos);
    TransformGroup secondSpinnerPos = Pos.at(11f,0f,0f);
    rideLocation[1] = new Point3f(11f,0f,0f);
    group.addChild(secondSpinnerPos);
    TransformGroup ferrisPos = Pos.at(0f,0f,-15f);
    group.addChild(ferrisPos);
    rideLocation[2] = new Point3f(0f,0f,-15f);
    TransformGroup objectPos = Pos.at(1f,3.1f,-2f);
    Transform3D scaleT = new Transform3D();
    scaleT.set(3);
    TransformGroup scale = new TransformGroup(scaleT);
    objectPos.addChild(scale);
    GetModel.add("tour.obj", scale, this);
    //GetModel.add("super3.obj", scale, this);
    group.addChild(objectPos);
    TransformGroup merryPos = Pos.at(7f,0f,7f);
    rideLocation[3] = new Point3f(7f,0f,7f);
    group.addChild(merryPos);
    ride[3]=new MerryGoRound(merryPos, universe);
    ride[0]=new Spinner(firstSpinnerPos, universe,4);
    ride[1] = new Spinner(secondSpinnerPos, universe,
                           3 , 1.2f, (float)(-2*Math.PI)/18,
                           3, .8f, (float)(-2*Math.PI)/6,
                           Palette.ORANGE, Palette.GREEN, Palette.CYAN,
                           Palette.BLUE, Palette.RED, Palette.CYAN);
    Transform3D ferrisAngleT = new Transform3D();
    ferrisAngleT.rotY(Math.PI/9);
    TransformGroup ferrisAngle = new TransformGroup(ferrisAngleT);
    ferrisPos.addChild(ferrisAngle);
    ride[2] = new Ferris(ferrisAngle, universe);

    activeRide = ride[0];

}
public void destroy() {
	universe.removeAllLocales();
}
public void actionPerformed(ActionEvent e ) {
	if (e.getSource()==go){
	    if (!timer.isRunning()) {
	        timer.start();
	        go.setLabel("Pause");
               if (firstTime) {
                   firstTime = false; 
                   add("Center", canvas3D);  
                   canvas3D.addKeyListener(this);
                   validate();
                }
	    }
            else {System.out.println("trying to save image");
                timer.stop();
                canvas3D.repaint();    
                go.setLabel("Continue");
            }
	} else if(e.getSource() == jcb){
		String nombre=(String)jcb.getSelectedItem();
                System.out.println(nombre+" b e "+(Vector3f)map.get(nombre));
                viewVector = (Vector3f)map.get(nombre);
                viewTransform.setTranslation(viewVector);
		viewTG.setTransform(viewTransform);
                
                mover = new Mover(viewTransform, viewVector,
                            viewTG, 0);
                
        }
        else {
		mover.actionPerformed(e);
    if (activeRide != null)  {
    	e.setSource(mover);
      activeRide.actionPerformed(e);
    } 
    float closest = Float.MAX_VALUE;
    for (int i=0; i < NUMRIDES; i++) {
        float distSquared = mover.ahead(0f).distanceSquared(rideLocation[i]);
        if (distSquared < closest) {
            closest = distSquared;
            activeRide = ride[i];
        }
        if (!ride[i].riding()) {
        //System.out.println("Distance: "+ distSquared);
           if ((distSquared) < 30) {
              //System.out.println("Get on the ride!");
          	 on = true;
              ride[i].getOn();        
           }
        } else { 
          if ((distSquared) >= 30) {
             ride[i].getOff();
             on = false;
          }
        }
        }
   }
}
public void keyReleased(KeyEvent e){
  mover.keyReleased(e);
// Invoked when a key has been released.
//  System.out.println("Key Released");
  }
public void keyTyped(KeyEvent e){
//System.out.println(e.getKeyChar() +" Key Typed "+e.getKeyCode());
}
public void keyPressed(KeyEvent e) {
  //System.out.println(e.getKeyChar() +" Key Pressed "+e.getKeyCode());
  mover.keyPressed(e);
}
public void init() {
        if (!mainRun) {
            mode=TWO_DIMENSIONAL;
            try {
              setUp();
            }
            catch(Throwable thrown) {
              System.out.println(thrown);
              thrown.printStackTrace();
            }
           // playGame();
	   }
	}
} // end of class
