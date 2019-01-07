import java.net.*;
import java.io.*;

public class S_ECO{
	public static void main(String[] args) {
		try{
			int pto = 1234;
			ServerSocket s = new ServerSocket(pto, 200 /* Backlog */);
			System.out.println("Servicio de eso iniciando... Esperando clientes... ");
			for (; ; ) {
				Socket cl = s.accept();
				System.out.println("Cliente conectado, recibiendo mensaje... ");
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream())); // PrintWriter por bloques, OutputStreamWriter cambia caracteres, getOutputStream recibe bytes
				BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream())); // BufferedReader por bloques, OutputStreamWriter cambia bytes, getOutputStream recibe caracteres
				for (; ; ) {
					String msj = br.readLine();
					System.out.println("Mensaje recibido: " + msj);
					if (msj.compareToIgnoreCase("salir") == 0) {
						break;
					} else{
						msj. msj + " eco";
						pw.println(msj);
						pw.flush();
					}
				}
				br.close();
				pw.close();
				cl.close();
			}
		} catch (Exception e){
			e.printStockTrace();
		}
	}
}