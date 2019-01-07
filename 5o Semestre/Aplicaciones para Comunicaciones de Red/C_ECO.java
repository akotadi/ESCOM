import java.net.*;
import java.io.*;
import java.util.*;

public class C_ECO{
	public static void main(String [] args) throws Exception{
		DatagramSocket cl = new DatagramSocket();
		Scanner sc = new Scanner(System.in);
		System.out.print("Escribe la direccion del servidor: ");
		String dir = sc.nextLine();
		System.out.print("Escribe el puerto: ");
		int port = sc.nextInt();
		sc.nextLine();
		InetAddress dst = InetAddress.getByName(dir);
		System.out.print("Escribe un texto: ");
		String nombre = sc.nextLine();
		funciones.sendParts(nombre.getBytes(), cl, dst, port);

		Object[] info = funciones.receiveParts(cl);
		System.out.println("Mensaje recibido del servidor: " + (String)info[0]);
	}
}