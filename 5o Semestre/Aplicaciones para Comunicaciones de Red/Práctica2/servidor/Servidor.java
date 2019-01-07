import java.net.*;
import java.io.*;
import java.util.*;

public class Servidor{

	public static int n;
	public static ArrayList<Articulo> lista;

	public static void LeerProductos() throws Exception{

		File F = new File("inventario");

		lista = new ArrayList<Articulo>();

		Scanner s = new Scanner(F);

		n = Integer.parseInt(s.nextLine());

		for(int i  = 0; i < n; i ++){
			Articulo a = new Articulo();
			a.id = Integer.parseInt(s.nextLine());
			a.nombre = s.nextLine();
			a.descripcion = s.nextLine();
			a.precio = Double.parseDouble(s.nextLine());
			a.existencia = Integer.parseInt(s.nextLine());
			a.promocion = Double.parseDouble(s.nextLine());;
			a.imagen = s.nextLine();
			lista.add(a);
		}		

		s.close();

	}

	public static void EscribeProductos() throws Exception{
		FileWriter fileWriter = new FileWriter("inventario");
	    PrintWriter pw = new PrintWriter(fileWriter);
	    
	    n = 0;
	    for(Articulo a : lista )
	    	if(a.existencia != 0) n++;

	    pw.println(n);

	    for(Articulo a : lista){
	    	if(a.existencia == 0) continue;
	    	pw.println(a.id);
	    	pw.println(a.nombre);
	    	pw.println(a.descripcion);
	    	pw.println(a.precio);
	    	pw.println(a.existencia);
	    	pw.println(a.promocion);
	    	pw.println(a.imagen);
	    }

	    pw.close();
	}

	public static void main(String[] args) throws Exception{

		ServerSocket ss = new ServerSocket(4000);

		for(;;){

			LeerProductos();

			Socket so = ss.accept();

			System.out.println("Conexion establecida desde " + so.getInetAddress());

			

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(so.getOutputStream()));

			pw.println(n);
			pw.flush();			

			for(int i = 0; i < n; i ++){

				ObjectInputStream ois = new ObjectInputStream(so.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(so.getOutputStream());

				Articulo a = lista.get(i);

				//a.Imprime();

				oos.flush();
				oos.writeObject(a);
				oos.flush();

				File imagen = new File(a.imagen);

				DataOutputStream dos = new DataOutputStream(so.getOutputStream());
				DataInputStream dis = new DataInputStream(new FileInputStream(imagen));

				long sent = 0;
				int read;
				long tam = imagen.length();

				dos.writeLong(tam);
				dos.flush();
				while(sent < tam){
					byte[] buf = new byte[1500];
					read = dis.read(buf);
					dos.write(buf, 0, read);
					dos.flush();
					sent += read;					
				}

				dis.close();
				/*dos.close();
				oos.close();
				ois.close();*/

			}

			BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
			String comprados[] = br.readLine().split(";");

			ArrayList<Integer> ticket = new ArrayList<Integer>();

			for(String s : comprados)
				ticket.add(Integer.parseInt(s));

			double suma = 0;
			for(Articulo a : lista)
				if(ticket.contains(a.id)){
					suma += a.precio * a.promocion;
					a.existencia--;
				}

			Formatter fmt = new Formatter();
			fmt.format("%.2f", (suma));

			System.out.println("Compra realizada por un valor de: $" + fmt);

			pw = new PrintWriter(new OutputStreamWriter(so.getOutputStream()));
			pw.println("El valor de su compra es de : $" + fmt);
			pw.flush();

			System.out.println("Transaccion finalizada");
			
			so.close();			

			EscribeProductos();

		}
		
		
	}
}