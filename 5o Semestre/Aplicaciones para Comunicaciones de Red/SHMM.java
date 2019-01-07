import java.net.*;
import java.io.*;
public class SHMM{
	public static void main(String args[]){
		try{
			MulticastSocket s = new MulticastSocket(7777);
			InetAddress gpo = InetAddress.getByName("229.1.2.3");
			s.joinGroup(gpo);
			System.out.println("Servicio multicast iniciado, unido al grupo " + gpo);
			String anuncio = "Un mensaje multicast";
			byte[] b = anuncio.getBytes();
			for(;;){
				DatagramPacket p = new DatagramPacket(b, b.length, gpo, 7778);
				s.send(p);
				System.out.println("Mensaje enviado al grupo");
				try{
					Thread.sleep(3000);

				} catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}