// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

// The  Spinner Class is for a ride that can be called Spider
// or Bermuda Triangle it has a central spinning section
// which splits into more sections that also spin

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import com.sun.j3d.utils.universe.ViewingPlatform;

//package park;

public class Spinner implements Ride, ActionListener, KeyListener {

private Transform3D viewTransform = new Transform3D();
private TransformGroup rotTG;
private TransformGroup[] rotTG2;
private Transform3D rot = new Transform3D();
private Transform3D rot2 = new Transform3D();
private TransformGroup riderPos = new TransformGroup();
//private TransformGroup rotTG2;
private static Timer timer;
private int tics=0;
private int numArms = 4;
private SimpleUniverse universe = null;
private Transform3D endArmLevel2;
private Transform3D turn = new Transform3D();
private Vector3f viewVector;
private ViewingPlatform vp = null;
private TransformGroup viewTG = null;
private Mover mover;
private boolean riding=false;
private static boolean onRide=false;

private float armLength = 1.2f;
private float armAngle = (float)(-2*Math.PI)/14;

protected static boolean mainRun=false;

public void getOn() {
  riding = true;
  Virtuland.on = true;
}
public void getOff() {
  riding = false;
  Virtuland.on = false;
}
public boolean riding() {
  return riding;
}

public Spinner(Group group, SimpleUniverse universe, int numArms) {

       setUp(group, universe,
             numArms, armLength, armAngle, 3, .8f, (float)(-2*Math.PI)/6,
             Palette.CYAN, Palette.RED, Palette.BLUE, Palette.WHITE,
             Palette.RED, Palette.YELLOW);
}
public Spinner(Group group, SimpleUniverse universe,
                    int numArms,
                    float armLength,
                    float armAngle,
                    int numArms2,
                    float armLength2,
                    float armAngle2,
                    Appearance columnColor,
                    Appearance capColor,
                    Appearance armColor,
                    Appearance endArmColor,
                    Appearance armColor2,
                    Appearance seatColor) {

       setUp(group, universe, numArms, armLength, armAngle, numArms2,armLength2,
             armAngle2, columnColor, capColor, armColor, endArmColor, armColor2,
             seatColor);
}


protected void setUp(Group group,
                     SimpleUniverse universe,
                     int numArms,
                     float armLength,
                     float armAngle,
                     int numArms2,
                     float armLength2,
                     float armAngle2,
                     Appearance columnColor,
                     Appearance capColor,
                     Appearance armColor,
                     Appearance endArmColor,
                     Appearance armColor2,
                     Appearance seatColor) {


    this.universe = universe;
    this.numArms = numArms;
    this.armAngle = armAngle;
    this.armLength = armLength;


    turn.rotY((Math.PI));

    // rotate top of ride
    TransformGroup tg  = new TransformGroup();
    rotTG = new TransformGroup();
    rotTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    Transform3D rot = new Transform3D();
    rot.rotY(Math.PI*3.0/4.0);
    rotTG.setTransform(rot);
    Vector3f vector = new Vector3f(-.2f, 3f , .3f);
    Transform3D transform = new Transform3D();
    transform.setTranslation(vector);
    tg.setTransform(transform);
    tg.addChild(rotTG);
    Box aBox = new Box(0.15f,0.20f,0.15f, capColor);
    aBox.setPickable(false);
    rotTG.addChild ( aBox );

    TransformGroup pos = Pos.at(0f,-1.4f,0f);
    Box bBox = new Box(.1f,1.6f,.1f,columnColor);
    bBox.setPickable(false);
    pos.addChild(bBox);
    tg.addChild(pos);
    // First level


    Box[] arm = new Box[numArms];
    Transform3D[] armT = new Transform3D[numArms];
    Transform3D[] armRY = new Transform3D[numArms];
    TransformGroup[] armTG = new TransformGroup[numArms];
    rotTG2 = new TransformGroup[numArms];
    Transform3D armRZ = new Transform3D();

    armRZ.rotZ(armAngle);
    TransformGroup[] endArm = new TransformGroup[numArms];
    Transform3D endArmT = new Transform3D();
    vector = new Vector3f(armLength,0f,0f);
    endArmT.setTranslation(vector);

    Transform3D endArmLevel = new Transform3D();
    endArmLevel.rotZ(-armAngle);
    endArmT.mul(endArmLevel);

    // Second level, use suffix 2
    //int numArms2 = 3;

    Box[] arm2 = new Box[numArms2];
    Transform3D[] armT2 = new Transform3D[numArms2];
    Transform3D[] armRY2 = new Transform3D[numArms2];
    TransformGroup[] armTG2 = new TransformGroup[numArms2];
    Transform3D armRZ2 = new Transform3D();
    armRZ.rotZ(armAngle);
    armRZ2.rotZ(armAngle2);
    TransformGroup[] endArm2 = new TransformGroup[numArms2];
    Transform3D endArmT2 = new Transform3D();
    Vector3f vector2 = new Vector3f(armLength2,0f,0f);
    Vector3f vector3 = new Vector3f(0f,-.5f,0f);
    Transform3D downAbit = new Transform3D();
    downAbit.setTranslation(vector3);
    endArmT2.setTranslation(vector2);
    endArmLevel2 = new Transform3D();
    endArmLevel2.rotZ(-armAngle2);
    //endArmLevel2.mul(endArmT2);
    endArmT2.mul(endArmLevel2);
    endArmLevel2.mul(turn);
    endArmLevel2.mul(downAbit);

    for (int i = 0; i < numArms; i++) {
      arm[i]=new Box(armLength,.07f,.07f, armColor);
      arm[i].setPickable(false);
      armT[i] = new Transform3D();
      //vector = new Vector3f(armLength, 0f, 0f);
      armT[i].setTranslation(vector);
      armTG[i] = new TransformGroup();
      endArm[i] = new TransformGroup();
      endArm[i].setTransform(endArmT);
      armRY[i] = new Transform3D();
      armRY[i].rotY((i * 2*Math.PI)/(numArms));
      armRY[i].mul(armRZ);
      armRY[i].mul(armT[i]);
      armTG[i].setTransform(armRY[i]);
      armTG[i].addChild(arm[i]);
      armTG[i].addChild(endArm[i]);
      rotTG.addChild(armTG[i]);
      //Sphere sphere = new Sphere(.2f);
      Sphere sphere = new Sphere(.2f, Sphere.GENERATE_NORMALS |
                                  Sphere.GENERATE_TEXTURE_COORDS,
				  5, endArmColor);
      sphere.setPickable(false);
      endArm[i].addChild(sphere);
      rotTG2[i] = new TransformGroup();
      rotTG2[i].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      endArm[i].addChild(rotTG2[i]);
      for (int j = 0; j < numArms2; j++) {
        arm2[j]=new Box(armLength2,.05f,.05f,armColor2);
        arm2[j].setPickable(false);
        armT2[j] = new Transform3D();
        vector2 = new Vector3f(armLength2, 0f, 0f);
        armT2[j].setTranslation(vector2);
        armTG2[j] = new TransformGroup();
        endArm2[j] = new TransformGroup();
        endArm2[j].setTransform(endArmT2);
        armRY2[j] = new Transform3D();
        armRY2[j].rotY((j * 2*Math.PI)/(numArms2));
        armRY2[j].mul(armRZ2);
        armRY2[j].mul(armT2[j]);

        armTG2[j].setTransform(armRY2[j]);
        armTG2[j].addChild(arm2[j]);
        armTG2[j].addChild(endArm2[j]);

        //rotTG2.addChild(armTG2[i]);
        rotTG2[i].addChild(armTG2[j]);
        if (j==0) {
          riderPos = endArm2[j];
          riderPos.setCapability(Group.ALLOW_LOCAL_TO_VWORLD_READ);

        }
        Box cBox = new Box(.2f,.06f,.3f,seatColor);
        TransformGroup pos1 = Pos.at(.1f,-.1f,0f);
        TransformGroup pos2 = Pos.at(.0f,0f,0f);
        Transform3D rSeatT = new Transform3D();
        rSeatT.rotY(-Math.PI/2);
        TransformGroup rSeat = new TransformGroup(rSeatT);
        TransformGroup rSeat2 = new TransformGroup(rSeatT);
        Box dBox = new Box(.07f,.2f,.3f,seatColor);
        cBox.setPickable(false);
        endArm2[j].addChild(rSeat);
        rSeat.addChild(pos1);
        pos1.addChild(cBox);
        endArm2[j].addChild(rSeat2);
        rSeat.addChild(pos2);
        pos2.addChild(dBox);
        //Sphere(.2f));
      }
    }

    group.addChild(tg);

    //group.addChild(new Box(0.3f,0.3f,0.3f, new A(C.green)));


   // universe.getViewingPlatform().setNominalViewingTransform();

    viewVector = new Vector3f(.0f,1.0f, 10.0f);
    viewTransform.setTranslation(viewVector);


    vp = universe.getViewingPlatform();

    viewTG =vp.getViewPlatformTransform();
    viewTG.setTransform(viewTransform);
    mover = new Mover(viewTransform, viewVector,
                            viewTG, 0);
    //viewTG.setCapability(vp.ALLOW_DETACH);
    //vp.detach();

  }
  public static void main( String[] args ) {

        mainRun = true;
        if (args.length > 0) {
            if (args[0].equals("ON")) onRide = true;
        }
        // general Set-up
        JFrame frame = new JFrame("Spinner");
        frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent winEvent) {
				  System.exit(0);
			  }	});
        GraphicsConfiguration config =
            SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        canvas.setSize(400, 400);
        frame.getContentPane().add(canvas);

        SimpleUniverse uni = new SimpleUniverse(canvas);
        BranchGroup group = new BranchGroup();
        Spinner spinner = new Spinner(group,uni,4);
        spinner.setUp(group);
        canvas.addKeyListener(spinner);
        Button go = new Button("Go");
        go.addActionListener(spinner);
        go.addKeyListener(spinner);
        frame.pack();
        frame.setVisible(true);
  }

  public void setUp(BranchGroup group) {
    // directional and ambient white light
    if (onRide) riding = true;
    new MyLights(group);
    new SkyBackground(group, null);
    Box floor = new Box(40000f,.1f,40000f,new StandardAppearance(Color.green));
    floor.setPickable(false);

    group.addChild(floor);
    group.compile();

   // frame.getContentPane(). addComponentListener(this);

    universe.addBranchGraph(group);
    timer = new Timer(80,this);
    timer.start();
  }

  public void actionPerformed(ActionEvent e ) {
    //mover.actionPerformed(e);
    //System.out.println("tic"+tics);
    tics++;
    rot.rotY((Math.PI*tics)/36.0);
    rotTG.setTransform(rot);
    for (int i = 0; i < numArms; i++) {
    //  int s = i%2;
    //  s = s*2-1;
      rot2.rotY((-Math.PI*tics)/12.0);
      rotTG2[i].setTransform(rot2);
    }

    if (riding) {
       try {
           riderPos.getLocalToVworld(viewTransform);
           viewTransform.mul(endArmLevel2);
       }
       catch (Throwable blah){
           System.out.println(blah);
       }
       viewTG.setTransform(viewTransform);
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
  mover.keyPressed(e);

}
}