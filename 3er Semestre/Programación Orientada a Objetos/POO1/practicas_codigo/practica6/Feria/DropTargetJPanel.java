import java.util.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;
/**
A JPanel capable of accepting drop operations
 */
public class DropTargetJPanel extends JPanel implements DropTargetListener {


private static final long serialVersionUID = 1L;
public DropTargetJPanel(){
  this(DnDConstants.ACTION_COPY_OR_MOVE | DnDConstants.ACTION_LINK);
}

public DropTargetJPanel(int actions){

         setLayout(null);



  _dropTarget = new DropTarget(this, actions, this);

}

public void addItem(Component item)
{
  add(item);
  revalidate();
}
public void removeItem(Component item)
{
  remove(item);
  revalidate();
}
//over-ride in subclasses
protected void doDrop(String string, Point point) {
}
protected void doPickUp(String string, Point point) {
}
// DropTargetListener implementation
public void dragEnter(DropTargetDragEvent evt){}
public void dragExit(DropTargetEvent evt){}
public void dragOver(DropTargetDragEvent evt){}
public void dropActionChanged(DropTargetDragEvent evt){}
public void drop(DropTargetDropEvent evt){
  if (evt.getDropAction()==DnDConstants.ACTION_NONE) {
    evt.rejectDrop();
    return;
  }
  Transferable tr = evt.getTransferable();
  boolean dropped = false;

  if (tr.isDataFlavorSupported(DataFlavor.imageFlavor)) {
  // try the drop
  DragSourceJLabel component = null;
  try {
    evt.acceptDrop(evt.getDropAction());
    Image item = (Image) tr.getTransferData(DataFlavor.imageFlavor);
    component = new DragSourceJLabel(new ImageIcon(item));
    thePoint = evt.getLocation();
    component.setLocation(thePoint);
    component.setSize(70,70);
   // component.setVisible(true);

    addItem(component);
    theImage = item;
    repaint();
    evt.getDropTargetContext().dropComplete(true);
    dropped = true;
  } catch (Exception e) {
    e.printStackTrace();
    evt.rejectDrop();
  }

   if (tr.isDataFlavorSupported(DataFlavor.stringFlavor)) {
   String rideType = null;

   try {
    rideType =  (String) tr.getTransferData(DataFlavor.stringFlavor);
    doDrop(rideType, thePoint);
    component.setName((String) tr.getTransferData(DataFlavor.stringFlavor));
   }
   catch (Throwable t) {}
   }

  }
  else   if (tr.isDataFlavorSupported(DataFlavor.stringFlavor)) {
  // try the drop
  try {
    evt.acceptDrop(evt.getDropAction());
    String item = (String) tr.getTransferData(DataFlavor.stringFlavor);
    thePoint = evt.getLocation();
    DragSourceJLabel component = new DragSourceJLabel(item);
    component.setName(item);
    component.setLocation(thePoint);
    component.setSize(70,70);
    addItem(component);
    repaint();
    evt.getDropTargetContext().dropComplete(true);
    dropped=true;
  } catch (Exception e) {
    e.printStackTrace();
    evt.rejectDrop();
  }
  }
  if (!dropped) {
    evt.rejectDrop();
    return;
  }
}
/*public void paint(Graphics g) {
 //super.paint(g);
  Rectangle bounds = g.getClipBounds();
  g.clearRect(bounds.x, bounds.y, bounds.width, bounds.height);
  if (theImage != null)  {
    g.drawImage(theImage, thePoint.x, thePoint.y, null);
  }
}
*/
//  personal body =============================
protected DropTarget _dropTarget;
protected Image theImage;
protected Point thePoint;
} // end of class
