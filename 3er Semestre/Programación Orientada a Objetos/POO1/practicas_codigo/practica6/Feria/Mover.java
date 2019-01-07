import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Mover implements ActionListener, KeyListener {
private Transform3D viewTransform;
private Transform3D rotationY;
private Transform3D rotationX;
public Vector3f viewVector;
private TransformGroup viewTG;
private double facingAngle = 0;
private double upAngle = 0;
private double moveSpeed = .15;
private double turnSpeed = .045;
private boolean downArrow = false;
private boolean upArrow = false;
private boolean leftArrow = false;
private boolean rightArrow = false;
private boolean alt = false;
public boolean pageDown = false;
public boolean pageUp = false;
public boolean end = false;


public Mover(Transform3D viewTransform,
             Vector3f viewVector,
             TransformGroup viewTG,
             double facingAngle) {
  this.viewTransform = viewTransform;
  this.viewVector = viewVector;
  this.viewTG = viewTG;
  this.facingAngle = facingAngle;
  rotationY = new Transform3D();
  rotationX = new Transform3D();
  
};
public Point3f ahead(double distance) {
    float z1 = viewVector.z - (float)(distance * Math.cos(facingAngle));
    float x1 = viewVector.x - (float)(distance * Math.sin(facingAngle));
    float y1 = viewVector.y;
    return new Point3f(x1,y1,z1);
}
public void actionPerformed(ActionEvent e ) {
  //  called by timer
  //  int k =  e.getKeyCode();
  //  switch(k) {
  // case 27: // escape
  // case 18: // alt
  // case 16: // shift

  if (leftArrow && !alt) {
    facingAngle = facingAngle + turnSpeed;
  }
  if (upArrow) {
    viewVector.z = viewVector.z - (float)(moveSpeed * Math.cos(facingAngle));
    viewVector.x = viewVector.x - (float)(moveSpeed * Math.sin(facingAngle));
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
  if (pageUp && upAngle < (Math.PI/2)) {
    upAngle = upAngle + turnSpeed;
  }
  if (pageDown && upAngle > (-Math.PI/2)) {
    upAngle = upAngle - turnSpeed;
  }
  if (end) {
    upAngle = 0;
  }
  viewTransform = new Transform3D();
  viewTransform.setTranslation(viewVector);
  rotationY.rotY(facingAngle);
  rotationX.rotX(upAngle);
  viewTransform.mul(rotationY);
  viewTransform.mul(rotationX);
  if (!Virtuland.on) { 
  	viewTG.setTransform(viewTransform);
  	//System.out.println("actionPerformed: " + facingAngle);
  }
}
public void keyReleased(KeyEvent e){
// Invoked when a key has been released.
  int k =  e.getKeyCode();
  //System.out.println("Key released: "+k);
  switch(k) {
  // case 27: // escape
  case 18: // alt
    alt = false;
    break;
  case 33:
    pageUp = false;
    break;
  case 34:
    pageDown = false;
    break;
  case 35:
    end = false;
    break;
  // case 16: // shift
  case 37:   //left
    leftArrow = false;
    break;
  case 38:   //up arrow
    upArrow = false;
    break;
  case 39:   //right arrow
    rightArrow = false;
    break;
  case 40:   //down arrow
    downArrow = false;
    break;
  }
}

public void keyTyped(KeyEvent e){
//Invoked when a key has been typed.
}
public void keyPressed(KeyEvent e) {

  //Invoked when a key has been pressed.
  //System.out.println(e.getKeyChar() +" Key Pressed "+e.getKeyCode());
  int k =  e.getKeyCode();
  switch(k) {
  // case 27: // escape
  case 18: // alt
    alt = true;
    break;
  case 33:
    pageUp = true;
    break;
  case 34:
    pageDown = true;
    break;
  case 35:
    end = true;
    break;
  // case 16: // shift
  case 37:   //left
    leftArrow = true;
    break;
  case 38:   //up arrow
    upArrow = true;
    break;
  case 39:   //right arrow
    rightArrow = true;
    break;
  case 40:   //down arrow
    downArrow = true;
    break;
  }
  
}
public  double getFacingAngle() {
	return facingAngle;
}
public void setFacingAngle(double facingAngle) {
	this.facingAngle = facingAngle;
}


} // end of class
