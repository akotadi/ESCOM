import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.text.*;

public class Cliente{

	public final int port = 4000;
	public final String address = "127.0.0.1";
	public int n;
	public Socket cl;

	public Cliente(){
		try{
			cl = new Socket(address, port);
			System.out.println("Se establecio la Conexion");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Object[][] getArticulos(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			n = Integer.parseInt(br.readLine());

			Object[][] data = new Object[n][8];

			for(int i = 0; i < n; i ++){

				ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());

				Articulo a  = new Articulo();
				a = (Articulo) ois.readObject();
				data[i][0] = false;
				data[i][1] = a.id;
				data[i][2] = a.nombre;
				data[i][3] = a.descripcion;
				data[i][4] = a.precio;
				data[i][5] = a.existencia;
				Formatter fmt = new Formatter();
				fmt.format("%.2f", (100.0-(a.promocion*100.0)));
				data[i][6] = fmt + "%";

				DataInputStream dis = new DataInputStream(cl.getInputStream());
				DataOutputStream dos = new DataOutputStream(new 	FileOutputStream(a.imagen));
				File imagen = new File(a.imagen);
				imagen.deleteOnExit();

				long tam = dis.readLong();

				int read = 0, p;
				long received = 0;

				while(received < tam){
					byte[] buf = new byte[1500];
					read = dis.read(buf);
					dos.write(buf,0,read);
					received += read;
				}

				data[i][7] = convertToImage(a.imagen);

				// dis.close();
				dos.close();
				// ois.close();
				// oos.close();
			}
			return data;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public boolean sendBuy(String comprados){
		try{
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
			pw.println(comprados);
			pw.flush();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public String getPrice(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			return br.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public boolean closeConnection(){
		try{
			cl.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public ImageIcon convertToImage(String name){
		ImageIcon imageIcon = new ImageIcon(name);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
	}
}