// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

// The Fair class sets up the window and canvas and
// reads in the options for the virtual world program.

import javax.media.j3d.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.*;
import javax.vecmath.*;
import java.applet.Applet;
import com.sun.j3d.utils.geometry.Primitive;
import java.util.Vector;
import java.util.StringTokenizer;

public class Fair implements ActionListener, KeyListener {
protected JButton go;
private Timer timer;
private SimpleUniverse universe = null;
private Vector3f viewVector;
private ViewingPlatform vp = null;
private TransformGroup viewTG = null;
private Transform3D viewTransform = new Transform3D();
private Mover mover;
private Ride activeRide = null;
protected static boolean redblue = false;
protected static int screenSize = 240;
protected static boolean mainRun=false;
protected static int mode;
protected PrintCaptureCanvas3D canvas3D;
protected static boolean sound=false;
protected Container container;
protected FairBuilder fairBuilder;
protected BranchGroup group = null;
protected String ridesString="";
protected Canvas3D canvas3D2;
protected int delay = 70;
protected boolean firstTime=true;

protected Vector rides = new Vector(6,3);

public static final int THREE_DIMENSIONAL=1, TWO_DIMENSIONAL=2, STEREO=3;

public void setOptions(int screenSize, int mode, boolean redblue, boolean sound){
	Fair.mode = mode;
  Fair.screenSize = screenSize;
  Fair.redblue = false;
  Fair.sound = false;
}
/*       if (mode == STEREO) {
           new MainFrame(land, screenSize, screenSize/2);
        } else {e
            //Frame frame =
           new MainFrame(land, screenSize, screenSize);
       }
} */
public Fair(JPanel panel, FairBuilder fairBuilder) {
    javax.swing.Box box = new javax.swing.Box(BoxLayout.Y_AXIS);
    panel.add(box);
    this.container = box;
    this.fairBuilder = fairBuilder;
}
public void initialize() {
    go = new JButton("Back to the Drawing Board");
    go.addActionListener(this);
    go.addKeyListener(this);

          //canvas3D.writeJPEG_ = false;
          //canvas3D.saveJPEG_  = false;
//    go.setLabel("");
          //add("Center", canvas3D);
    this.setUp();
       timer = new Timer(80,this);
    timer.start();
    canvas3D.setFocusable(false);
    firstTime = false;

               if (mode == STEREO) {
         //     int canvasSize = screenSize/2-70;
         //     canvas3D.setSize(canvasSize, canvasSize);
         //     canvas3D.setMonoscopicViewPolicy(View.LEFT_EYE_VIEW);

             // !!!   container.add(canvas3D,BorderLayout.WEST);

           //    canvas3D2 = new Canvas3D(config);
           //    canvas3D2.setSize(canvasSize, canvasSize);
            //   canvas3D2.setMonoscopicViewPolicy(View.RIGHT_EYE_VIEW);

            //   System.out.println("adding second canvas");
            canvas3D2.setFocusable(false);
            // !!!!   container.add(canvas3D2,BorderLayout.EAST);
            }
            else {
              //System.out.println("Adding the canvas to the container");
              canvas3D.setSize(screenSize,screenSize);
              container.removeAll();
              container.add(canvas3D);//, BorderLayout.SOUTH);
           }
  System.out.println("Adding the go JButton.");
  container.add(go);
  container.validate();

  // canvas cannot handle key events so set focus to JButton
  KeyboardFocusManager.
    getCurrentKeyboardFocusManager().setGlobalCurrentFocusCycleRoot(container);
  KeyboardFocusManager.
    getCurrentKeyboardFocusManager().focusNextComponent(canvas3D);
  container.validate();
  //container.addActionListener(this);
}

public void setUp() {
 
  // create and size canvas, add it to frame
    GraphicsConfiguration config =
            SimpleUniverse.getPreferredConfiguration();
          //canvas3D = new Canvas3D(config);
          canvas3D =  new PrintCaptureCanvas3D(config);
          if (mode == STEREO) {
            int canvasSize = screenSize/2-7;
            canvas3D.setSize(canvasSize, canvasSize);
            canvas3D.setMonoscopicViewPolicy(View.LEFT_EYE_VIEW);
            //add(canvas3D);

            canvas3D2 = new Canvas3D(config);
            canvas3D2.setSize(canvasSize, canvasSize);
            canvas3D2.setMonoscopicViewPolicy(View.RIGHT_EYE_VIEW);
            //System.out.println("adding second canvas");
          //  add(canvas3D2);
        }
        else {
            //add("Center", canvas3D);
        }


    /*
    canvas.setSize(400, 400);
    canvas.addKeyListener(this);
    getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(go);
    frame.getContentPane().add(canvas);     //  for JFrame

    //frame.add3(canvas);
    */
    universe = new SimpleUniverse(canvas3D);
    /*BranchGroup*/
    group = new BranchGroup();
    group.setCapability(group.ALLOW_CHILDREN_WRITE);
    group.setCapability(group.ALLOW_CHILDREN_EXTEND);
    // directional and ambient white light
    new MyLights(group);

    // background
    Applet applet = new Applet();
    new SkyBackground(group,applet); // use this for icons: , C.blue);
     // Create a textured floor

    int primFlags=Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS;
    //Sphere sphere = new Sphere(0.5f, primflags, ap);
    Appearance ap = new Appearance();
    Palette.addTexture("tile2.gif",ap,applet);
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
    //addRides(group, universe);
    //addRidesFromDesign("-5 0 0 ferris\n");
    addRidesFromDesign(ridesString);

    // set position of viewer
    viewVector = new Vector3f(.0f,1.8f, 10.0f);
    viewTransform.setTranslation(viewVector);


    vp = universe.getViewingPlatform();

    viewTG =vp.getViewPlatformTransform();
    viewTG.setTransform(viewTransform);
    mover = new Mover(viewTransform, viewVector,
                            viewTG, 0);
   // group.compile();
   //if (mainRun) {
   // ((JFrame)frame).pack();
   // ((JFrame)frame).show();
   // }
    /*Button go = new Button("Go");
    go.addActionListener(this);
    go.addKeyListener(this);
    */
    universe.addBranchGraph(group);
     if (mode == STEREO) {
            View view0 = universe.getViewer().getView();
            View view = new View();
            PhysicalBody myBod = view0.getPhysicalBody();
            myBod.setLeftEyePosition(new Point3d(-.001,0.0, 0.0)); // default is(-0.033, 0.0, 0.0)
            myBod.setRightEyePosition(new Point3d(+.001,0.0, 0.0));
            view.setPhysicalBody(myBod);
            view.setPhysicalEnvironment(view0.getPhysicalEnvironment());
            view.attachViewPlatform(universe.getViewingPlatform().getViewPlatform());
            view.addCanvas3D(canvas3D2);
        }

   // timer.start();

}
public RideData placeRide(float x, float y, float z, String name){
  //System.out.println("Adding " + name + " to " + x + ", "+ y +", " +z);
  RideData data = new RideData();
  data.pos = Pos.at(x, y, z);
  data.location = new Point3f(x, y, z);
  data.ride = null;
  group.addChild(data.pos);
  rides.add(data);
  return data;
}
public void addRidesFromDesign(String ridesString){
     System.out.println("Trying to add these rides:\n"+ridesString);
     StringTokenizer st = new StringTokenizer(ridesString,"\n");
     while (st.hasMoreTokens()) {
        String line;
        line = st.nextToken();
        System.out.println(line);
        StringTokenizer t = new StringTokenizer(line," ");
        float x = 0;
        float y = 0;
        float z = 0;
        try{
         x =  Float.valueOf(t.nextToken()).floatValue() ;
         y =  Float.valueOf(t.nextToken()).floatValue() ;
         z =  Float.valueOf(t.nextToken()).floatValue() ;
        } catch(Throwable thrown) {System.out.println(thrown);}
        String string = t.nextToken();
        addRide(string,x,y,z);
     }
}
public void addRideToDesign(String string, float x, float y, float z) {
  ridesString = ridesString + x +" " + y + " " + z + " " + string + "\n";
}
public void removeRideFromDesign(String string, float x, float y, float z) {
  String toRemove = "" + x +" " + y + " " + z + " " + string;
//  System.out.println("Removing:"+toRemove);
//  System.out.println("from:"+ridesString);

  StringTokenizer st = new StringTokenizer(ridesString,"\n");
  String result = "";
  String line = "";
  while (st.hasMoreTokens()) {
    line = st.nextToken();
    if (line.equals(toRemove)) {
      toRemove="nothing";
    } else {
      result += line + "\n";
    }
  }
  ridesString = result;
//  System.out.println("result:"+ridesString);
}

public void addRide(String string, float x, float y, float z) {
  //addRides(group, universe);
  RideData data;
  data = placeRide(x,y,z,string);
  if ("twister".equals(string)) {
    data.ride = new Spinner(data.pos, universe,4);
  } else if ("bermuda".equals(string)) {
    data.ride = new Spinner(data.pos, universe,
                           3 , 1.2f, (float)(-2*Math.PI)/18,
                           3, .8f, (float)(-2*Math.PI)/6,
                           Palette.ORANGE, Palette.GREEN, Palette.CYAN,
                           Palette.BLUE, Palette.RED, Palette.CYAN);
  } else if ("carousel".equals(string)) {
    data.ride =  new MerryGoRound(data.pos, universe);
  } else if ("ferris".equals(string)) {
    Transform3D ferrisAngleT = new Transform3D();
    ferrisAngleT.rotY(Math.PI/9);
    TransformGroup ferrisAngle = new TransformGroup(ferrisAngleT);
    data.pos.addChild(ferrisAngle);
    data.ride = new Ferris(ferrisAngle, universe);
  } else {
    System.out.println(string +" is an unknown type of ride.");
  }
}
public void addRides(Group group, SimpleUniverse universe) {

    RideData data;
    data = placeRide(-5f,0f,0f,"twister1");
    data.ride = new Spinner(data.pos, universe,4);

    // add the eiffel tower
    TransformGroup objectPos = Pos.at(1f,3.1f,-2f);
    Transform3D scaleT = new Transform3D();
    scaleT.set(3);
    TransformGroup scale = new TransformGroup(scaleT);
    objectPos.addChild(scale);
    GetModel.add("tour.obj", scale, new Applet());

    //GetModel.add("super3.obj", scale, this);
    group.addChild(objectPos);
    if (rides.size() > 1) {
      activeRide = ((RideData)rides.get(0)).ride;
    }
}
public void destroy() {
	universe.removeAllLocales();
}
public void actionPerformed(ActionEvent e ) {
	if (e.getSource()==go){
    System.out.println("Go back to design stage");
    timer.stop();
    rides.removeAllElements();
    fairBuilder.backToTheDrawingBoard();
            /* timer.stop();

            canvas3D.writeJPEG_ = true;
            canvas3D.saveJPEG_ = true;
            canvas3D.repaint();
          //  canvas3D.writeJPEG_ = false;
           // canvas3D.saveJPEG_ = false;
            go.setLabel("Continue");
            */
	}else {
 // System.out.println("Action"+e);
    mover.actionPerformed(e);

    if (null != activeRide)  {
      activeRide.actionPerformed(e);
    }
    float closest = Float.MAX_VALUE;
    for (int i=0; i < rides.size(); i++) {
        boolean isClosest = false;
        RideData rideData = (RideData)rides.get(i);
        float distSquared = mover.ahead(5f).distanceSquared(rideData.location);
//        System.out.println("" + i + ". "+distSquared);
        if (distSquared < closest) {
            closest = distSquared;
            activeRide = rideData.ride;
            isClosest = true;
        }
        distSquared = mover.ahead(0f).distanceSquared(rideData.location);
        if (isClosest) {
          if (!rideData.ride.riding()) {
        //System.out.println("Distance: "+ distSquared);
            if ((distSquared) < 30) {
              //System.out.println("Get on the ride!");
              rideData.ride.getOn();
           }
          }
        }
        if (rideData.ride.riding())  {
          if ((distSquared) >= 30)
             rideData.ride.getOff();
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
//Invoked when a key has been typed.
}
public void keyPressed(KeyEvent e) {
  mover.keyPressed(e);
}


// equivalent of main for applet
public void init() {
/*  if (!mainRun) {
    mode=TWO_DIMENSIONAL;
    String v = getParameter("mode");
     String s = getParameter("sound");
    if ("ON".equals(s)) sound = true;
    if ("3D".equals(v)) mode = THREE_DIMENSIONAL;
    if ("STEREO".equals(v)) mode = STEREO;
    */
    int sz =0;
    /*
    String siz=getParameter("screenSize");
    if (siz != null) {
      try {
        sz = Integer.parseInt(siz);
      } catch (Throwable t){}
    }
    */
    if (sz > 0) screenSize = sz;
      try {
        setUp();
      }
      catch(Throwable thrown) {
        System.out.println(thrown);
        thrown.printStackTrace();
      }
           // playGame();
    }


} // end of class
