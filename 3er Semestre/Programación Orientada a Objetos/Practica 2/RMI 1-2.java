import javax.swing.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class areaImpl extends UnicastRemoteObject implements area{
	float result = 0;

	public areaImpl() throws RemoteException {
		super();
		result = 0;
	}

	public float getArea(int base, int altura) throws RemoteException, Exception{
		try{
			return (base*altura)/2;
		}
		catch (Exception e){
			System.out.println("Fehler in GetImageIcon:\n"+e.toString()+"\n");
			throw e;
		}
	}

	public static void main(String[] args) {
		try{
			areaImpl i = new areaImpl();
			Naming.rebind("area",i);
			System.out.println("Servidor de area triangulo listo. ");
		}
		catch(RemoteException re){
			System.out.println("Exception in areaImpl.main"+re);
		}
		catch(MalformerURLException e){
			System.out.println("MalformerURLException en areaImpl.main "+e);
		}
	}
}