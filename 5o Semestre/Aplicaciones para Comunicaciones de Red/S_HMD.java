import java.net.*;
import java.io.*;

public class S_HMD{

	int pto = 9999;

	public static void main(String[] args) {
		try{
			DatagramSocket s = new DatagramSocket(pto);
			System.out.println("Servidor iniciado, espreando datagramas...");
			String msj = "Hola";

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while(true){
				DatagramPacket p = new DatagramPacket(new byte[1500], 1500);
				s.receive(p);

				ByteArrayInputStream bais = new ByteArrayInputStream(p.getData());
				ObjectInputSream ois = new ObjectInputStream(bais);

				Datos d1 = (Datos)ois.readObject();
				if (d1.last()) {
					baos.write(d1.getB());
				}else{
					String nombre = baos.toString();
					baos = new ByteArrayOutputStream();
				}

				String nombre = new String(p.getData(), 0, p.getLength());
				System.out.println("Datagrama recibido desde " + p.getAddress() 
					+ ":" + p.getPort() + "Con el nombre: " + nombre + "\nDevolviendo saludo... ");
			}
		}
	}
}

// DatagramPacket p = new DatagramPacket("un mensaje".getByte(), 10, InetAddress.getByName("255.255.255.255"),pto)