import javax.swing.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class SalarioImpl extends UnicastRemoteObject implements salario{
	float result = 0;

	public salarioImpl() throws RemoteException{
		super();
		result = 0;
	}

	public float getSalario(int horas, int sueldo) throws RemoteException, Exception{
		int h = 0, sueltoT = 0;
		try{
			if(horas > 40){
				h = horas - 40;
				sueldoT = (40*sueldo)+(h*sueldo*2);
			}
			else{
				sueldoT = (40*sueldo);
			}

			return sueldoT;
		}
		catch (Exception e){
			System.out.println("Fehler in getImageIcon: \n"+e.toString()+"\n");
			throw e;
		}
	}

	public static void main(String[] args) {
		try{
			salarioImpl i = new SalarioImpl();
			Naming.rebind("salario",i);
			System.out.println("Servidor de salario listo.");
		}
		catch (RemoteException re){
			System.out.println("Exception in salarioImpl.main: "+re):
		}
		catch (MalformedURLException e){
			System.out.println("MalformedURLException en salarioImpl.main: "+e):
		}
	}
}