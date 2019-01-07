import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import javax.swing.*;
import java.util.*;

//java -Djava.security.policy=no.policy ImagenesImpl
public class ImagenesImpl extends UnicastRemoteObject implements Imagenes {
  Vector <String> nombres=new Vector<String>();

  public ImagenesImpl() throws RemoteException  {
    super();
    nombres.addElement("leon.gif");
    nombres.addElement("gato.jpg");
    nombres.addElement("conejo.jpg");
    nombres.addElement("panda.jpg");
   
  }

  public Vector getNombres() throws java.rmi.RemoteException {
         return nombres;
  }
  public ImageIcon getImage(String imageID) throws RemoteException , Exception {
	///System.out.println("Request accepted: getImage");
	//ImageIcon img = new ImageIcon(imageID);
	//return img;
        try
{ javax.swing.ImageIcon img = new javax.swing.ImageIcon(imageID);
return img;
}
catch (Exception e)
{ System.out.println("Fehler in GetImageIcon:\n "+ e.toString()+"\n");
throw e;
}
 }

  public static void main(String args[]) {

    try {
      ImagenesImpl i = new ImagenesImpl();
      Naming.rebind("imagenes", i);
      System.out.println("Servidor Imagenes listo.");
    }
     catch (RemoteException re) {
      System.out.println("Exception in ImagenesImpl.main: " + re);
      //re.printStackTrace();
    }
    catch (MalformedURLException e) {
      System.out.println("MalformedURLException en HelloImpl.main: " + e);
    }

  }

}

