import javax.swing.*;
import java.util.*;
import java.rmi.*;


public interface Imagenes extends Remote {

  ImageIcon getImage(String imageID) throws RemoteException, Exception;
  Vector getNombres() throws java.rmi.RemoteException;

}
