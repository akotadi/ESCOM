// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

// The Virtuland class sets up the window and canvas and
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

public class Virtuland extends Applet implements ActionListener, KeyListener {
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
protected PrintCaptureCanvas3D canvas3D;
protected static boolean sound=false;
public static boolean on=false;
protected Canvas3D canvas3D2;
protected int delay = 70;
protected boolean firstTime=true;
private static int NUMRIDES=4;
protected Point3f[] rideLocation = new Point3f[NUMRIDES];
protected Ride[] ride  = new Ride[NUMRIDES];

public static final int THREE_DIMENSIONAL=1, TWO_DIMENSIONAL=2, STEREO=3;

public static void main(String[] args) {
        mainRun = true;
        mode=TWO_DIMENSIONAL;
        if (args.length > 0) {
            if (args[0].equals("3D")) mode = THREE_DIMENSIONAL;
            if (args[0].equals("REDBLUE")) {
                mode = THREE_DIMENSIONAL;
                redblue = true;
            }
            if (args[0].equals("STEREO")) {
                mode = STEREO;
                screenSize = 500;
            }
        }
        if (args.length > 1) {
            if (args[1].equals("SOUND")) sound = true;
        }
        if (args.length > 2) {
            int s =0;
            try {
                s = Integer.parseInt(args[2]);
            } catch (Throwable t){}
            if (s >  0) screenSize = s;

            System.out.println("Size = "+screenSize);
        }
        Virtuland land = new Virtuland();
        if (mode == STEREO) {
           new MainFrame(land, screenSize, screenSize/2);
        } else {
            //Frame frame =
           new MainFrame(land, screenSize, screenSize);
       }
       // p.playGame();

}
public Virtuland() {
    //go = new Button("Go");
    go.addActionListener(this);
    go.addKeyListener(this);

    // general Set-up
    if  (mainRun)  {
        System.out.println("run from main");
        /*
        JFrame jFrame = new JFrame("Welcome to Virtuland");
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent winEvent) {
				           System.exit(0);
            }
        });
        frame = jFrame;
        */
    }
    else {
        System.out.println("run from Applet");
        //frame = this;
         /*
        if (mode == STEREO) {
          mainFrame = new MainFrame(this, screenSize, screenSize/2);
        } else {
           Frame mainFrame = new MainFrame(this, screenSize, screenSize);
        }
        */
    }
    this.setUp();
}
public void setUp() {
     // if (mode == STEREO) {
     //       setLayout(new FlowLayout());
      //  } else {
            setLayout(new BorderLayout());
      //  }
      // Panel p =new Panel();
      // p.add(go);
       add(go,BorderLayout.NORTH);

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

        timer = new Timer(delay,this);


    /*
    canvas.setSize(400, 400);
    canvas.addKeyListener(this);
    getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(go);
    frame.getContentPane().add(canvas);     //  for JFrame

    //frame.add3(canvas);
    */
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
          canvas3D.writeJPEG_ = false;
          canvas3D.saveJPEG_  = false;
	        go.setLabel("Pause");
          //add("Center", canvas3D);


          if (firstTime) {
            firstTime = false;
            if (mode == STEREO) {    
              	add(canvas3D,BorderLayout.WEST);      
               System.out.println("adding second canvas");
               add(canvas3D2,BorderLayout.EAST);
            }
            else {
              add("Center", canvas3D);
            }
            canvas3D.addKeyListener(this);
            validate();
          }
	    }
        else {System.out.println("trying to save image");
            timer.stop();
            canvas3D.writeJPEG_ = true;
            canvas3D.saveJPEG_ = true;
            canvas3D.repaint();    
            go.setLabel("Continue");

    }
	}else {
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
    /*float distSquared =
                        viewVector.x*mover.viewVector.x
                        + mover.viewVector.y*mover.viewVector.y
                        + mover.viewVector.z*mover.viewVector.z;
                        */
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
//Invoked when a key has been typed.
}
public void keyPressed(KeyEvent e) {
  //Invoked when a key has been pressed.
  //System.out.println(e.getKeyChar() +" Key Pressed "+e.getKeyCode());
  mover.keyPressed(e);
}


// equivalent of main for applet
public void init() {
        if (!mainRun) {
            mode=TWO_DIMENSIONAL;
            String v = getParameter("mode");
            String s = getParameter("sound");
            if ("ON".equals(s)) sound = true;
            if ("3D".equals(v)) mode = THREE_DIMENSIONAL;
            if ("STEREO".equals(v)) mode = STEREO;
            int sz =0;
            String siz=getParameter("screenSize");
            if (siz != null) {
                try {
                    sz = Integer.parseInt(siz);
                } catch (Throwable t){}
            }
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
	}
} // end of class
