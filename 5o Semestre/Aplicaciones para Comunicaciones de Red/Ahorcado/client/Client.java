import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Client{
	// private static final String dir = "10.100.78.241";
	private static final String dir = "127.0.0.1";
	private static final int pto = 12345;
	private static Socket sClient;
	private static Scanner scConsole;
	private static BufferedReader brSocket;
	private static PrintWriter pwSocket;
	private static int nResult = 0, nGame = 1;
	private static final int INTENTOS_TOTALES = 7;

	public Client(){
		try{
			sClient = new Socket(dir, pto);
			System.out.println("Conexión establecida.");
			brSocket = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
			pwSocket = new PrintWriter(new OutputStreamWriter(sClient.getOutputStream()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static int jugar() throws Exception{
		int nTiradas = 0, nErrors = INTENTOS_TOTALES;

		scConsole = new Scanner(System.in);

		while((nGame != 0) && (nErrors > 0)){
			pwSocket = new PrintWriter(new OutputStreamWriter(sClient.getOutputStream()));

			String cLetter;
			System.out.println("Ingrese la letra...");
			cLetter = scConsole.nextLine();

			pwSocket.flush();
			pwSocket.println(cLetter);
			pwSocket.flush();

			nGame = Integer.parseInt(brSocket.readLine());
			// System.out.println(sValue);
			// Acertó
			if (nGame == 1) {
				nResult -= Integer.parseInt(brSocket.readLine());
			}else if (nGame == 2) { // Erró
				nErrors--;
			}
			System.out.println("Llevas "+(++nTiradas)+" tiradas y "+(INTENTOS_TOTALES-nErrors)+" errores, te quedan "+nErrors+" oportunidades.");
			System.out.println("\n\n"+brSocket.readLine());
		}
		return nTiradas;
	}

	
	public static void main(String[] args) {

		Client cl = new Client();

		try{
			nResult = Integer.parseInt(brSocket.readLine());
			System.out.println(nResult);
			String word = brSocket.readLine();
			System.out.println("\n"+word);

			int aux = jugar();
			if (nGame == 0) {
				System.out.println("¡Felicidades, ganaste!");
				// System.out.println("Te tomó "+aux+" tiradas.");
				System.out.println("Te tomó "+aux+" tiradas con "+brSocket.readLine()+"s de tiempo.");
			}else{
				System.out.println("¡Lo siento, perdiste!");
				// System.out.println("Hiciste "+aux+" tiradas.");
				System.out.println("Hiciste "+aux+" tiradas con "+brSocket.readLine()+"s de tiempo.");
			}

			// fTablero.finalizar(aux);

			sClient.close();

			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}