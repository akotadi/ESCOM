import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;

public class TransferableImage implements Transferable {

public TransferableImage(Image image) {
    _image = image;
}

// Returns an object which represents the data to be transferred.
public Object getTransferData(DataFlavor flavor) {
  Object ret = null;
  if (flavor == DataFlavor.imageFlavor) {
    ret = _image;
  }
  if (flavor == DataFlavor.stringFlavor) {
    ret = _string;
  }

  return ret;
}

// Returns an array of DataFlavor objects indicating the flavors the data can be provided in
public DataFlavor[] getTransferDataFlavors() {
  return _flavors;
}

//Returns whether or not the specified data flavor is supported for this object.
public boolean isDataFlavorSupported(DataFlavor flavor) {
  return
    ((flavor == DataFlavor.imageFlavor)||(flavor == DataFlavor.stringFlavor));
}
public void setString(String string) {
  _string = string;
}
private Image _image;
private String _string;
//private DataFlavor _flavor = DataFlavor.imageFlavor ;
private DataFlavor[] _flavors = {DataFlavor.imageFlavor, DataFlavor.stringFlavor};


}  // end of class
