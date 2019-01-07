import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.util.*;


public class RemoteDateImpl extends UnicastRemoteObject implements RemoteDate {

  public RemoteDateImpl() throws RemoteException {
    super();
  }
    
public Date getRemoteDate( ) throws RemoteException {
    return new Date( );
}

  public static void main(String args[]) {

    try {
      RemoteDateImpl im = new RemoteDateImpl( );
      Naming.rebind(RemoteDate.LOOKUPNAME, im);
	 System.out.println("DateServer ready.");
    }
     catch (RemoteException re) {
      System.out.println("Exception in RemoteImpl.main: " + re);
      System.exit(1);
    }
    catch (MalformedURLException e) {
      System.out.println("MalformedURLException in RemoteImpl.main: " + e);
     System.exit(1);
    }

  }

}
