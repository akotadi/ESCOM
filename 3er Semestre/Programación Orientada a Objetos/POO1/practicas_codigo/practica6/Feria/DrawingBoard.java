import java.awt.Point;

public class DrawingBoard extends DropTargetJPanel {
private static final long serialVersionUID = 1L;
protected Fair fair = null;
private static final float ratio = 6;
protected void setFair(Fair fair) {
  this.fair = fair;
}
protected void doDrop(String string, Point point) {
  // convert screen coordinates to Fair coordinates

  float x = ((float)(point.getX()-300)) / ratio;
  float y = 0f;
  float z = ((float)(point.getY()-150)) / ratio;
  fair.addRideToDesign(string,x,y,z);
  System.out.println("Dropped " + string + " at:"+ point);
}
protected void doPickUp(String string, Point point) {
  System.out.println("Picked up " + string + " at:"+ point);
  float x = ((float)(point.getX()-300)) / ratio;
  float y = 0f;
  float z = ((float)(point.getY()-150)) / ratio;
  fair.removeRideFromDesign(string,x,y,z);
}
}  // end of class