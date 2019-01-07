import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import javax.swing.*;
import java.util.*;

//java -Djava.security.policy=no.policy ImagenesImpl
public class ImagenesImpl extends UnicastRemoteObject implements Imagenes {
  Vector <String> nombres;
  Map mapa = new HashMap();

  public ImagenesImpl() throws RemoteException  {
    super();
  }
  public Vector getNombres() throws java.rmi.RemoteException {
	Iterator it = mapa.entrySet().iterator();
        nombres=new Vector<String>();
	while (it.hasNext()) {
		Map.Entry e = (Map.Entry)it.next();
                nombres.add((String)e.getKey());
        }
         return nombres;
  }
  public void setImage(String imageID, ImageIcon image) throws RemoteException {
       mapa.put(imageID, image);
  }
  public ImageIcon getImage(String imageID) throws RemoteException , Exception {
        try
{   
return (ImageIcon)mapa.get(imageID);
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

