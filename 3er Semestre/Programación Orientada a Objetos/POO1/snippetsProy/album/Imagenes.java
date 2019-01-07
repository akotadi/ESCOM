import javax.swing.*;
import java.util.*;
import java.rmi.*;


public interface Imagenes extends Remote {

  ImageIcon getImage(String imageID) throws RemoteException, Exception;
  void setImage(String imageID, ImageIcon image) throws RemoteException;
  Vector getNombres() throws java.rmi.RemoteException;

}
