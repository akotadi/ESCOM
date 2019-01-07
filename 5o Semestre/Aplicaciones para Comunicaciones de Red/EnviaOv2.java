import java.net.*;
import java.io.*;

public class EnviaO{
	public static void main(String[] args) {
		try{
			Socket cl = new Socket("localhost",8000);
			Dato d = new Dato("Juan",10,1.0f,30000);
			ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
			System.out.println("Se enviará el siguiente objeto:\n v1:"+d.getv1()+" v2"+d.getv2()+" v3"+d.getv3()+" v4"+d.getv4());
			oos.writeObject(d);
			oos.flush();
			System.out.println("Objeto enviado, ahora se recibirá un objeto...");
			ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
			Dato d1 = (Dato)ois.readObject();
			System.out.println("Objeto recibido->v1:"+d1.getv1()+" v2"+d1.getv2()+" v3"+d1.getv3()+" v4"+d1.getv4());
			ois.close();
			oos.close();
			cl.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}