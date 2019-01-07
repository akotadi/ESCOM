
// TouristControls.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* Arrow keys to move and rotate a 3D sprite.

   We have restricted the functionality to XZ plane movement
   and Y rotation in the sprite, so not all movements
   and rotations need to have keys here.

   The viewpoint is set here, based on the TourSprite's position.
   As the sprite moves, the viewpoint is also moved. 

   The user can press 'i' or 'o' to 'zoom' in or out on the sprite.

   Unchanged from the version in Tour3D.
*/

import java.awt.event.*;
import java.awt.AWTEvent;
import java.util.Enumeration;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.text.DecimalFormat;


public class TouristControls extends Behavior
{
  private WakeupCondition keyPress;

  private final static int forwardKey = KeyEvent.VK_DOWN;
  private final static int backKey = KeyEvent.VK_UP;
  private final static int leftKey = KeyEvent.VK_LEFT;
  private final static int rightKey = KeyEvent.VK_RIGHT;

  private final static int inKey = KeyEvent.VK_I;   // for moving viewer
  private final static int outKey = KeyEvent.VK_O;

  private final static double HEIGHT = 2.0;    // height of viewer
  private final static double ZOFFSET = 8.0;   // distance from sprite
  private final static double ZSTEP = 1.0;   // viewer's zoom step

 
  private TourSprite bob;   // the 3D sprite being controlled from here

  private TransformGroup viewerTG;  // the viewpoint TG
  private Transform3D t3d, toMove;  // used to affect viewerTG;
  private Point3d bobPosn;

  private DecimalFormat df;   // for simpler output during debugging


  public TouristControls(TourSprite b, TransformGroup vTG)
  {
    df = new DecimalFormat("0.###");  // 3 dp
    bob = b;
    viewerTG = vTG;
    t3d = new Transform3D();
    toMove = new Transform3D();
    setViewer();
    keyPress = new WakeupOnAWTEvent( KeyEvent.KEY_PRESSED );
  } // end of TouristControls()


  private void setViewer()
  /* Position the viewpoint so it is offset by ZOFFSET from the
     sprite along the z-axis, and is at height HEIGHT. 
  */
  {
    bobPosn = bob.getCurrLoc();   // start location for bob
    viewerTG.getTransform( t3d );
    // args are: viewer posn, where looking, up direction
    t3d.lookAt( new Point3d(bobPosn.x, HEIGHT, bobPosn.z + ZOFFSET), 
				new Point3d(bobPosn.x, HEIGHT, bobPosn.z), 
				new Vector3d(0,1,0));
    t3d.invert();
    viewerTG.setTransform(t3d);
  }  // end of setViewer()


  public void initialize( )
  {
    wakeupOn( keyPress );
  }

  public void processStimulus(Enumeration criteria)
  {
    WakeupCriterion wakeup;
    AWTEvent[] event;

    while( criteria.hasMoreElements() ) {
      wakeup = (WakeupCriterion) criteria.nextElement();
      if( wakeup instanceof WakeupOnAWTEvent ) {
        event = ((WakeupOnAWTEvent)wakeup).getAWTEvent();
        for( int i = 0; i < event.length; i++ ) {
          if( event[i].getID() == KeyEvent.KEY_PRESSED )
            processKeyEvent((KeyEvent)event[i]);
        }
      }
    }
    wakeupOn( keyPress );
  } // end of processStimulus()


  private void processKeyEvent(KeyEvent eventKey)
  {
    int keyCode = eventKey.getKeyCode();
    // System.out.println(keyCode);    
 
    if( eventKey.isAltDown() )
      altMove(keyCode);
    else
      standardMove(keyCode);

    viewerMove();
  } // end of processKeyEvent()


  private void standardMove(int keycode)
  // make sprite moves forward or backward; rotate left or right
  // the in and out keys affect the viewer's z-axis position
  {
    if(keycode == forwardKey )
      bob.moveForward();
    else if(keycode == backKey)
      bob.moveBackward();
    else if(keycode == leftKey)
      bob.rotClock();
    else if(keycode == rightKey)
      bob.rotCounterClock();
    else if(keycode == inKey)
      shiftViewer(-ZSTEP);    // move viewer negatively on z-axis
    else if(keycode == outKey)
      shiftViewer(ZSTEP);
  } // end of standardMove()


  private void altMove(int keycode)
  // moves sprite left or right
  {
    if(keycode == leftKey)
      bob.moveLeft();
    else if(keycode == rightKey)
      bob.moveRight();
  }  // end of altMove()


  private void shiftViewer(double zDist)
  // move the viewer inwards or outwards
  {
    Vector3d trans = new Vector3d(0,0,zDist);
    viewerTG.getTransform( t3d );
    toMove.setTranslation(trans);  // overwrites previous translation
    t3d.mul(toMove);
    viewerTG.setTransform(t3d);
  }  // end of viewerMove()


  private void viewerMove()
  // Updates the view point by the translation change of the sprite
  {
    Point3d newLoc = bob.getCurrLoc();
    // printTuple(newLoc, "newLoc");
    Vector3d trans = new Vector3d( newLoc.x - bobPosn.x,
								0, newLoc.z - bobPosn.z);
    viewerTG.getTransform( t3d );
    toMove.setTranslation(trans);  // overwrites previous translation
    t3d.mul(toMove);
    viewerTG.setTransform(t3d);

    bobPosn = newLoc;   // save for next time
  }  // end of viewerMove()


  // ----------------- debugging ------------------------------

  private void printTuple(Tuple3d t, String id)
  // used for debugging
  {
    System.out.println(id + " x: " + df.format(t.x) + 
				", " + id + " y: " + df.format(t.y) +
				", " + id + " z: " + df.format(t.z));
  }  // end of printTuple()

} // end of TouristControls class
