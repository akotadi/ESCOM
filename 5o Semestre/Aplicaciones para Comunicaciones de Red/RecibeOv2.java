import java.net.*;
import java.io.*;

public class RecibeO{
	public static void main(String[] args) {
		try{
			ServerSocket cl = new ServerSocket(8000);
			for (; ; ) {
				Socket cl2 = cl.accept();
				ObjectInputStream ois = new ObjectInputStream(cl2.getInputStream());
				Dato d1 = (Dato)ois.readObject();
				System.out.println("Objeto recibido->v1:"+d1.getv1()+" v2"+d1.getv2()+" v3"+d1.getv3()+" v4"+d1.getv4());
				ObjectOutputStream oos = new ObjectOutputStream(cl2.getOutputStream());
				Dato d2 = new Dato("1",1,1.0f,5);
				System.out.println("Se enviará el siguiente objeto...");
				oos.writeObject(d2);
				oos.flush();
				ois.close();
				oos.close();
				cl2.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}