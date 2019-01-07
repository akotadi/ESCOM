import javax.swing.*;
import java.util.*;
import java.rmi.*;

public interface salario extends Remote {
	float getSalario (int horas, int sueldo) throws RemoteException, Exception;
}