import javax.swing.*;
import java.util.*;
import java.rmi.*;

public interface cadena extends Remote {
	int getNumCadena (String cadena) throws RemoteException, Exception;
	String cadMayus (String cadena) throws RemoteException, Exception;
}