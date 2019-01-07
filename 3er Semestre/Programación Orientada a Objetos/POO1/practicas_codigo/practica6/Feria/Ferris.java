// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

// A Ferris wheel, big wheel, London Eye or similar ride

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import com.sun.j3d.utils.universe.ViewingPlatform;

//package park;

public class Ferris implements Ride, ActionListener, KeyListener {

private Transform3D viewTransform = new Transform3D();
private TransformGroup rotTG;
private TransformGroup[] seatRotTG;
private TransformGroup[] rotTG2;
private Transform3D rot = new Transform3D();
private Transform3D sRot = new Transform3D();
private TransformGroup riderPos = new TransformGroup();
private static Timer timer;
private int tics=0;
private int numArms;
private SimpleUniverse universe = null;
private Transform3D turn = new Transform3D();
private Vector3f viewVector;
private ViewingPlatform vp = null;
private TransformGroup viewTG = null;
private Mover mover;
 
private boolean riding=false;
private static boolean onRide=false;
private double upAngle = 0;
private double turnSpeed = .045;
public boolean pageDown = false;
public boolean pageUp = false;
public boolean end = false;


private float rimLength;
private Transform3D stillTransform = new Transform3D();
private double rotY;
private boolean justGotOn;

protected static boolean mainRun=false;

public void getOn() {
  riding = true;
  justGotOn = true;
  vp.detach();
  Virtuland.on = true;
  riderPos.addChild(vp);
}

public void getOff() {
  riding = false;
  Virtuland.on = false;
  vp.detach();
  ((BranchGroup)(((Locale)(universe.getAllLocales().nextElement())).getAllBranchGraphs().nextElement())).addChild(vp);
}
public boolean riding() {
  return riding;
}

public Ferris(Group group, SimpleUniverse universe) {    
       setUp(group, universe, 12, 3.1f, .7f, Palette.BLUE);
}
protected void setUp(Group group,
                     SimpleUniverse universe,
                     int numArms,
                     float radius,
                     float width ,
                     Appearance axleColor) {
    this.universe = universe;
    this.numArms = numArms;
    double angle = Math.PI/numArms;
    rimLength = radius * (float) Math.sin(angle);
    turn.rotY((Math.PI));
    // rotate  ride
    TransformGroup tg  = new TransformGroup();
    rotTG = new TransformGroup();
    rotTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    Transform3D rot7 = new Transform3D();
    rot7.rotX(Math.PI*3.0/4.0);
    rotTG.setTransform(rot7);
    Vector3f vector = new Vector3f(0, radius+rimLength , 0);
    Transform3D transform = new Transform3D();
    transform.setTranslation(vector);
    tg.setTransform(transform);
    tg.addChild(rotTG);
    Box aBox = new Box(width/2+.2f,0.05f,0.05f, axleColor);
    aBox.setPickable(false);
    rotTG.addChild ( aBox );
    


 // columns at the side of the of the wheel
    double angleOut = Math.PI/15;
    double angleApart = Math.PI/10;

    float height = .5f+(radius+rimLength)/2;
    //float offset = height * (float)Math.tan(angleOut);
    //float offset2 = height * (float)Math.tan(angleApart);
    Appearance columnColor = Palette.MAGENTA;

    TransformGroup pos = Pos.at(width/2+.1f,-height,0f);
    TransformGroup pos2 = Pos.at(-width/2-.1f,-height,0f);
    TransformGroup posA = Pos.at(width/2+.1f,-height,0f);
    TransformGroup pos2A = Pos.at(-width/2-.1f,-height,0f);
    Box bBox = new Box(.06f,height,.06f,columnColor);
    Box cBox = new Box(.06f,height,.06f,columnColor);
    Box bBoxA = new Box(.06f,height,.06f,columnColor);
    Box cBoxA = new Box(.06f,height,.06f,columnColor);
    TransformGroup angle1 = new TransformGroup();
    TransformGroup angle2 = new TransformGroup();
    TransformGroup angle1A = new TransformGroup();
    TransformGroup angle2A = new TransformGroup();
    TransformGroup columnDown = Pos.at(0,-height, 0);
    TransformGroup columnUp = Pos.at(0,height, 0);
    TransformGroup columnDown2 = Pos.at(0,-height, 0);
    TransformGroup columnUp2 = Pos.at(0,height, 0);
    TransformGroup columnDownA = Pos.at(0,-height, 0);
    TransformGroup columnUpA = Pos.at(0,height, 0);
    TransformGroup columnDown2A = Pos.at(0,-height, 0);
    TransformGroup columnUp2A = Pos.at(0,height, 0);
    Transform3D columnOut = new Transform3D();
    Transform3D columnApart = new Transform3D();

    columnOut.rotZ(angleOut);
    columnApart.rotX(angleApart);
    columnOut.mul(columnApart);
    angle1.setTransform(columnOut);

    columnOut.rotZ(angleOut);
    columnApart.rotX(-angleApart);
    columnOut.mul(columnApart);
    angle1A.setTransform(columnOut);

    columnOut.rotZ(-angleOut);
    columnApart.rotX(angleApart);
    columnOut.mul(columnApart);
    angle2.setTransform(columnOut);

    columnOut.rotZ(-angleOut);
    columnApart.rotX(-angleApart);
    columnOut.mul(columnApart);
    angle2A.setTransform(columnOut);

    bBox.setPickable(false);

    pos.addChild(columnUp);
    columnUp.addChild(angle1);
    angle1.addChild(columnDown);
    columnDown.addChild(bBox);
    tg.addChild(pos);

    pos.addChild(columnUpA);
    columnUp.addChild(angle1A);
    angle1A.addChild(columnDownA);
    columnDownA.addChild(bBoxA);
    tg.addChild(posA);

    pos2.addChild(columnUp2);
    columnUp2.addChild(angle2);
    angle2.addChild(columnDown2);
    columnDown2.addChild(cBox);
    tg.addChild(pos2);

    pos2A.addChild(columnUp2A);
    columnUp2A.addChild(angle2A);
    angle2A.addChild(columnDown2A);
    columnDown2A.addChild(cBoxA);
    tg.addChild(pos2A);


    // First level


    Box[] arm = new Box[numArms];
    Transform3D[] armT = new Transform3D[numArms];
    Transform3D[] armRY = new Transform3D[numArms];
    TransformGroup[] seatTG = new TransformGroup[numArms];
    seatRotTG = new TransformGroup[numArms];
    Transform3D[] seatRotate = new Transform3D[numArms];
    TransformGroup[] armTG = new TransformGroup[numArms];
    rotTG2 = new TransformGroup[numArms];
    Transform3D armRZ = new Transform3D();

    armRZ.rotZ((float)Math.PI/2);
    TransformGroup[] endArm = new TransformGroup[numArms];
    Transform3D endArmT = new Transform3D();
    vector = new Vector3f(radius/2,0f,0f);
    endArmT.setTranslation(vector);

   // Transform3D endArmLevel = new Transform3D();
   // endArmLevel.rotZ(-armAngle);
    vector = new Vector3f(radius/2, width/2, 0f);
    Vector3f vector2 = new Vector3f(radius/2, -width/2, 0f);
    for (int i = 0; i < numArms; i++) {
      arm[i]= new Box(radius/2,.04f,.04f, Palette.YELLOW);
      arm[i].setPickable(false);
      armT[i] = new Transform3D();
      //vector = new Vector3f(radius, width/2, 0f);
      armT[i].setTranslation(vector);

      Box arm2 = new Box(radius/2,.04f,.04f, Palette.YELLOW);
      arm2.setPickable(false);
      Transform3D armT2 = new Transform3D();
      armT2.setTranslation(vector2);

      armTG[i] = new TransformGroup();
      seatRotTG[i] = new TransformGroup();
      seatRotTG[i].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      TransformGroup armTG2 = new TransformGroup();
      endArm[i] = new TransformGroup();
      endArm[i].setTransform(endArmT);
      armRY[i] = new Transform3D();
      armRY[i].rotX((i * 2*Math.PI)/(numArms));

      armRY[i].mul(armRZ);
      Transform3D armRY2 = new Transform3D(armRY[i]);
      armRY[i].mul(armT[i]);
      armRY2.mul(armT2);
      armTG[i].setTransform(armRY[i]);
      armTG2.setTransform(armRY2);
      armTG[i].addChild(arm[i]);
      armTG2.addChild(arm2);
      armTG[i].addChild(endArm[i]);

      rotTG.addChild(armTG[i]);
      rotTG.addChild(armTG2);


      //Sphere sphere = new Sphere(.2f);
      //Sphere sphere = new Sphere(.2f, Sphere.GENERATE_NORMALS |
      //                            Sphere.GENERATE_TEXTURE_COORDS,
			//	  5, Palette.ORANGE);
      Box across = new Box(.02f,width/2,.02f,Palette.BLUE);
      Box acrossStrut = new Box(.01f,(width)/2,.01f,Palette.GREEN);
      Box rim = new Box(rimLength, .02f, .02f, Palette.CYAN);
      Box rim2 = new Box(rimLength, .02f, .02f, Palette.CYAN);

      // add the seats
      Box seat = new Box(width/6, width/2, width/6,Palette.BLUE);
      Box seatSupport = new Box(.666f*rimLength/2,.02f, .02f,Palette.RED);
      Box seatSupport2 = new Box(.666f*rimLength/2,.02f, .02f,Palette.RED);
      TransformGroup seatSupportTG = Pos.at(.666f*rimLength/2,-width/2,0f);
      TransformGroup seatSupport2TG = Pos.at(.666f*rimLength/2,+width/2,0f);
      seatSupportTG.addChild(seatSupport);
      seatSupport2TG.addChild(seatSupport2);
      //seatTG[i].setTransform(seatT);
      seatTG[i] = new TransformGroup();

      Transform3D seatT = new Transform3D();
      seatT.setTranslation(new Vector3f(0f,-width/2,0f));
      TransformGroup seatAdjust = new TransformGroup(seatT);
      seatRotate[i] = new Transform3D();
      seatRotate[i].rotY((Math.PI*2*i)/(numArms)-Math.PI/4);
      seatTG[i].setTransform(seatRotate[i]);
      TransformGroup seatOffset = Pos.at(.666f*rimLength+width/12, 0f, 0f);
      if (i==0) {
          Transform3D lookForward = new Transform3D();
          lookForward.rotZ(Math.PI/2);
          riderPos = new TransformGroup();
          TransformGroup lookForwardTG = new TransformGroup(lookForward);
          lookForwardTG.addChild(riderPos);
          riderPos.setCapability(Group.ALLOW_LOCAL_TO_VWORLD_READ);
          riderPos.setCapability(Group.ALLOW_CHILDREN_EXTEND);
          riderPos.setCapability(Group.ALLOW_CHILDREN_WRITE);
          riderPos.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
          seatTG[i].addChild(lookForwardTG);
      }
      //TransformGroup seatSupportOffset = Pos.at(rimLength+width/12, width/8, width/12);
      //TransformGroup seatSupport2Offset = Pos.at(rimLength+width/12, width/8, width/12);
      seatOffset.addChild(seat);
      seatRotTG[i].addChild(seatTG[i]);

      seatTG[i].addChild(seatOffset);
      seatTG[i].addChild(seatSupportTG);
      seatTG[i].addChild(seatSupport2TG);
      TransformGroup rimTG = new TransformGroup();
      TransformGroup rimTG2 = new TransformGroup();
      Transform3D rimT = new Transform3D();
      double rotAmount = Math.PI/2 - angle;
      TransformGroup acrossPos = Pos.at(0f,-width/2,0f);
      TransformGroup acrossStrutPos = Pos.at(0f,-width/2,0f);
      acrossStrutPos.addChild(acrossStrut);

      // diagonal supporting struts
      Box firstStrut = new Box(
            (float)Math.sqrt(radius*radius/16+(width-.1)*(width-.1)/4),
            .01f,.01f,Palette.GREEN);
      Box secondStrut = new Box(
            (float)Math.sqrt(radius*radius/16+(width-.1)*(width-.1)/4),
            .01f,.01f,Palette.GREEN);
      double strutAngle = Math.PI/2 - Math.acos((double)((width-.1)*2/radius));
      Transform3D strutTurn = new Transform3D();
      strutTurn.rotZ(strutAngle);
      TransformGroup firstStrutTG = new TransformGroup(strutTurn);
      Transform3D halfArmT = new Transform3D();
      halfArmT.setTranslation(new Vector3f(-radius/4,0f,0f));
      TransformGroup halfArmTG = new TransformGroup(halfArmT);
      acrossStrutPos.addChild(halfArmTG);
      halfArmTG.addChild(firstStrutTG);
      firstStrutTG.addChild(firstStrut);

      TransformGroup secondStrutTG = new TransformGroup(strutTurn);
      halfArmT.setTranslation(new Vector3f(radius/4,0f,0f));
      halfArmTG = new TransformGroup(halfArmT);
      acrossStrutPos.addChild(halfArmTG);
      halfArmTG.addChild(secondStrutTG);
      secondStrutTG.addChild(secondStrut);


      TransformGroup rimPos = Pos.at(-rimLength,0f,0f);
      TransformGroup rimPos2 = Pos.at(-rimLength,-width,0f);
      rimT.rotY(rotAmount);
      rimTG.setTransform(rimT);
      rimTG2.setTransform(rimT);
      rimTG.addChild(rimPos);
      rimTG2.addChild(rimPos2);
      rimPos.addChild(rim);
      rimPos2.addChild(rim2);

      acrossPos.addChild(across);
      across.setPickable(false);
      endArm[i].addChild(acrossPos);


      armTG[i].addChild(acrossStrutPos);

      endArm[i].addChild(rimTG);
      endArm[i].addChild(rimTG2);
      endArm[i].addChild(seatAdjust);
      seatAdjust.addChild(seatRotTG[i]);
      //
      rotTG2[i] = new TransformGroup();
      rotTG2[i].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      endArm[i].addChild(rotTG2[i]);
    }

    group.addChild(tg);
    viewVector = new Vector3f(.0f,1.0f, 10.0f);
    viewTransform.setTranslation(viewVector);
    vp = universe.getViewingPlatform();
    viewTG =vp.getViewPlatformTransform();
    viewTG.setTransform(viewTransform);
    mover = new Mover(viewTransform, viewVector,
                            viewTG, 0);
  }
  public static void main( String[] args ) {

        mainRun = true;
        if (args.length > 0) {
            if (args[0].equals("ON")) onRide = true;
        }
        // general Set-up
        JFrame frame = new JFrame("Ferris Wheel");
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
        Ferris wheel = new Ferris(group,uni);
        wheel.setUp(group);
        canvas.addKeyListener(wheel);
        Button go = new Button("Go");
        go.addActionListener(wheel);
        go.addKeyListener(wheel);
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
     
  	Mover outsideMover = null;
  	try {
  		 outsideMover = (Mover) e.getSource();
  	} catch (Throwable t) {
  	}
   
    if (outsideMover != null) {
	    if (justGotOn) {
	    	outsideMover.setFacingAngle(0);
	    	justGotOn = false;
	    }
   	 rotY = outsideMover.getFacingAngle();
   	 //System.out.println(outsideMover.getFacingAngle());
    }
    
    /*
     *   if (leftArrow && !alt) {
    	 System.out.println("leftArrow");
      facingAngle = facingAngle + turnSpeed;
    }
 
    if (rightArrow && !alt) {
      facingAngle = facingAngle - turnSpeed;
    }
    if (downArrow) {
      viewVector.z = viewVector.z + (float)(moveSpeed * Math.cos(facingAngle));
      viewVector.x = viewVector.x + (float)(moveSpeed * Math.sin(facingAngle));
    }
    // strafe left
    if (leftArrow && alt) {
      viewVector.z = viewVector.z - (float)(moveSpeed * Math.cos(facingAngle+Math.PI/2));
      viewVector.x = viewVector.x - (float)(moveSpeed * Math.sin(facingAngle+Math.PI/2));
    }
    // strafe right
    if (rightArrow && alt) {
      viewVector.z = viewVector.z - (float)(moveSpeed * Math.cos(facingAngle-Math.PI/2));
      viewVector.x = viewVector.x - (float)(moveSpeed * Math.sin(facingAngle-Math.PI/2));
    }
    */
    if (pageUp && upAngle < (Math.PI/2)) {
      upAngle = upAngle + turnSpeed;
    }
    if (pageDown && upAngle > (-Math.PI/2)) {
      upAngle = upAngle - turnSpeed;
    }
    if (end) {
      upAngle = 0;
    }
    tics++;
    rot.rotX(Math.PI*3.0/4.0 + (Math.PI*tics)/120.0);
    rotTG.setTransform(rot);
    for (int i = 0; i < numArms; i++) {
        //System.out.println("i: "+i+"  tic: "+tics);
        sRot.rotY((Math.PI*tics)/120.0);
        //System.out.println("sRot set");
        //System.out.println(seatRotTG[i]);
        seatRotTG[i].setTransform(sRot);
        //System.out.println("seatRotTG set");
    }
    if (riding) {
//       try {
//      	// endArmLevel2.add()
//          // riderPos.getLocalToVworld(viewTransform);
//          // viewTransform.mul(endArmLevel2);
//       }
//       catch (Throwable blah){
//           System.out.println(blah);
//       }
       stillTransform= new Transform3D();
       stillTransform.setTranslation(new Vector3d(0,0,0));
       TransformGroup viewPlatformTransform = vp.getMultiTransformGroup().getTransformGroup(0);

       viewPlatformTransform.setTransform(stillTransform );
       stillTransform.rotY(rotY);
       
      
     
       
            ///ViewPlatform viewPlatform = vp.getViewPlatform();
      // 
       riderPos.setTransform(stillTransform);
   //    TransformGroup viewPlatformTransform = vp.getViewPlatformTransform();
     
       //viewTG.setTransform(stillTransform );
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