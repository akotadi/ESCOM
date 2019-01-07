import java.net.*;
importa java.io.*;

public class EnviaO{
	public static void main(String [] args){
		try{
			Socket cl = new Socke("localhost", 8000);
			Dato d = new Dato("Juan", 20, 1.0f, long(1) << 60);
			ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
			System.out.println("Se enviará el sig. objeto :\n ");
			oos.writeObject(d);
			oos.flush();
			System.out.printl("Objeto enviado, Ahora se recibirá un objeto");
			ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
			Dato d1 = (Dato) ois.readObject();
			System.out.printl("Se recibió el objeto");
			ois.close();
			oos.close();
			cl.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
} 

//Enviar un objeto de golpe. 
//Alternativas -> SSLSocket para mayor seguridad.

//Modificador transient : Al copiarse, no da el valor, si no, el valor del constructor vacio. Primitivo = 0, objecto = null;