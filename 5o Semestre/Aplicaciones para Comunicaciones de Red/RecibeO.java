import java.net.*;
importa java.io.*;

public clasi RecibeO{
	public static void recibeO(String [] args){
			try{
			ServerSocket ss = new ServerSocke(8000);

			for(; ; ){
				Sockect si = ss.accept();
				ObjectInputStream ois = si.getInputStream();
				Dato d = (Dato) ios.readObject();
				System.out.println("Se recibió");
				Dato d2 = Dato(1, "xd", 3.5, long(1)<<40);
				ObjectOutputStream oos = si.getOutputStream();
				oos.writeObject(d2);
				oos.flush();
				System.out.printl("Objeto enviado");
				si.close();
				ois.close();
				oos.close();
			}

		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
} 

/*Si no se sabe el tipo de dato que se va a recibir.
Java : IstanceOf
Object d1 = readObject();
if(d1 instanceof Dato ){
	Dato dx = (Dato) d1;
}
*/