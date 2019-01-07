import javax.swing.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class cadenaImpl extends UnicastRemoteObject implements cadena{
	float result = 0;

	public cadenaImpl() throws RemoteException {
		super();
		result = 0;
	}

	public float getNumCadena(String cadena) throws RemoteException, Exception{
		int tamaño;
		try{
			tamaño = cadena.length();
			return tamaño;
		}
		catch (Exception e){
			System.out.println("Fehler in GetImageIcon:\n"+e.toString()+"\n");
			throw e;
		}
	}

	public String cadMayus(String cadena) throws RemoteException, Exception{
		String newCadena;
		try{
			newCadena = cadena.toUpperCase();
			return newCadena;
		}
		catch (Exception e){
			System.out.println("Fehler in GetImageIcon:\n"+e.toString()+"\n");
			throw e;
		}
	}

	public static void main(String[] args) {
		try{
			cadenaImpl i = new cadenaImpl();
			Naming.rebind("area",i);
			System.out.println("Servidor de cadena listo. ");
		}
		catch(RemoteException re){
			System.out.println("Exception in cadenaImpl.main"+re);
		}
		catch(MalformerURLException e){
			System.out.println("MalformerURLException en cadenaImpl.main "+e);
		}
	}
}