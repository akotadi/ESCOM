import javax.swing.*;
import java.util.*;
import java.rmi.*;

public interface area extends Remote {
	float getArea (int base, int altura) throws RemoteException, Exception;
}