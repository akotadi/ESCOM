import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;
/**
A JLabel capable of initiating drag operations
*/

public class DragSourceJLabel extends JLabel
  implements DragSourceListener, DragGestureListener {

private static final long serialVersionUID = 1L;
private String name;
//private static DropTargetJPanel dropTargetJPanel  = new DropTargetJPanel();
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public DragSourceJLabel(String text) {
  this(text, DnDConstants.ACTION_COPY_OR_MOVE | DnDConstants.ACTION_LINK);
}

public DragSourceJLabel(String text, int actions) {
  super(text, CENTER);
  _dragSource = new DragSource();
  _dragGesture = _dragSource.createDefaultDragGestureRecognizer(
    this, actions, this);
  _type = STRING_TYPE;
}
public DragSourceJLabel(Icon icon) {
  this(icon, DnDConstants.ACTION_COPY_OR_MOVE | DnDConstants.ACTION_LINK);
}

public DragSourceJLabel(Icon icon, int actions) {
  super(icon, CENTER);
  _dragSource = new DragSource();
  _dragGesture = _dragSource.createDefaultDragGestureRecognizer(
    this, actions, this);
  _type = IMAGE_TYPE;
}

public void dragGestureRecognized(DragGestureEvent evt) {
  Cursor cursor = DragSource.DefaultCopyDrop;
  if (_type == IMAGE_TYPE) {
    ImageIcon icon = (ImageIcon) getIcon();
    Image image= icon.getImage();
    TransferableImage transferable = new TransferableImage(image);
    transferable.setString(name);
    //System.out.println(_dragSource.isDragImageSupported());
    Component parent = getParent();
    //if (parent.getClass().isInstance(dropTargetJPanel))
    if (parent.getClass().getName().equals("DrawingBoard"))
    {
      ((Container)parent).remove(this);
      parent.repaint();
      cursor = DragSource.DefaultMoveDrop;
      //System.out.println("Moving ride: " + name);

      ((DropTargetJPanel)parent).doPickUp(name, this.getLocation());
    }
         _dragSource.startDrag(evt, cursor,
          image, new Point(), transferable, this,
          SystemFlavorMap.getDefaultFlavorMap());
            //startDrag(DragGestureEvent trigger, Cursor dragCursor, Image dragImage,
      //Point imageOffset, Transferable transferable, DragSourceListener dsl,
      // FlavorMap flavorMap)
  }
  if (_type == STRING_TYPE) {
    StringSelection item = new StringSelection(getText());
    Component parent = getParent();
    if (parent.getClass().getName().equals("DropTargetJPanel"))
    {
      ((Container)parent).remove(this);
      parent.repaint();
      cursor = DragSource.DefaultMoveDrop;
    }
    _dragSource.startDrag(evt, cursor, item, this);
  }
}

// implements DragSourceListener
public void dragDropEnd(DragSourceDropEvent evt){}
public void dragEnter(DragSourceDragEvent evt){}
public void dragExit(DragSourceEvent evt){}
public void dragOver(DragSourceDragEvent evt){}
public void dropActionChanged(DragSourceDragEvent evt){}

public final static int IMAGE_TYPE = 1;
public final static int STRING_TYPE = 2;
// personal body ====================================
private DragSource _dragSource;
private DragGestureRecognizer _dragGesture;
private int _type;

} // end of class

