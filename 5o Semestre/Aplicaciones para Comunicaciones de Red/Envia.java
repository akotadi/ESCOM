import java.net.*;
import java.io.*;
import javax.swing.*;

public class Envia{
	public static void main(String[] args) {
		try{
			int port = 5678;
			String host = "127.0.0.1";
			JFileChooser jf = new JFileChooser();
			if (jf.showOpenDialog(null) == JFileChooser.APPROVE.OPTION) {
				File f = jf.getSelectedFile();
				String nombre = f.getName();
				long tam = f.length();
				String ruta = f.getAbsolutePath();
				Socket cl = new Socket(host, port);
				System.out.println("Conexion establecida, se enviara el archivo "+ ruta + "\n\n");
				long sent = 0;
				int read = 0, p = 0;
				DataInputStream dis = new DataInputStream(new FileInputStream(ruta));
				DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
				dos.writeUTF(nombre);
				dos.flush();
				dos.writeLong(tam);
				dos.flush();
				while(sent < tam){
					byte[] buf = new byte[8192];
					read = dis.read(buf);
					dos.write(buf, 0, read);
					dos.flush();
					sent t = read;
					p = (int)(sent*100.0)/tam;
					System.out.print("\rEnviado el " + p + "% del archivo");
				}
				System.out.println("\nEnvío completado");
				dos.close();
				dis.close();
				cl.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}