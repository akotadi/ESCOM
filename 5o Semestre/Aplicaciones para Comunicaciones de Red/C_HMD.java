import java.net.*;
import java.io.*;

public class C_HMD{
	public static void main(String[] args) {
		try{
			DatagramSocket cl = new DatagramSocket();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String dir = "";
			InnetAddress dst = null;
			int pto = 9999;
			int limite = 1500;

			while(true){
				System.out.print("\nEscribe la direccion del servidor: ");
				dir = br.readLine();
				try{
					dst = InnetAddress.getByName(dir);
				}catch(UnknownHostException in){
					System.err.println("Direccion no valida\n");
					main(args);
				}
				System.out.print("\nEscribe un nombre: ");
				String nombre = br.readLine();
				byte[] b = nombre.getBytes();

				if (b.length > limite) {
					byte[] tmp = new byte[100];
					ByteArrayInputStream bais = new ByteArrayInputStream(b);
					int c = 0;
					int np = (int)(b.length / tmp.length);
					if (b.length % tmp.length > 0) {
						np++;
					}
					while( (int n = bais.read(tmp)) != -1){
						Dato d = new Dato(++c, np, tmp, tmp.length);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(baos);
						oos.writeObject();
						oos.flush();
						byte[] b1 = baos.toByteArray();
						DatagramPacket p = new DatagramPacket(b1, b1.length, dst, pto);
						cl.send(p);
					}
				}

				System.out.println("Datagrama enviado... Recibiendo respuesta... ");
				DatagramPacket p1 = new DatagramPacket(new byte[limite], limite);
				cl.receive(p1);
				System.out.println("Respuesta recibida con el mensaje: "+ new String(p1.getData(), 0, p1.getLength()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}