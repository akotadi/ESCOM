import java.rmi.*;
import java.util.*;
//java -Djava.security.policy=no.policy DateClient
public class DateClient {
  protected static RemoteDate netConn = null;

  public static void main(String args[]) {
        
    System.setSecurityManager(new RMISecurityManager());

    try {

      netConn = (RemoteDate)Naming.lookup(RemoteDate.LOOKUPNAME);
	Date today = netConn.getRemoteDate( );
	 System.out.println("RemoteClient: " +today.toString( ));
    } 
    catch (Exception e) {
      System.out.println("Exception in main: " + e);
      e.printStackTrace( );
    }

  }

}
