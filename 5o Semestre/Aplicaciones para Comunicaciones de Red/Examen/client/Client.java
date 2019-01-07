import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Client{
	private static final String dir = "10.100.78.241";
	// private static final String dir = "127.0.0.1";
	private static final int pto = 8000;
	private static Socket sClient;
	private static Scanner scConsole;
	private static BufferedReader brSocket;
	private static PrintWriter pwSocket;
	private static String aPalabras[], aMostrar[];
	private static char aGame[][];
	private static Tablero fTablero;
	private static int nResult = 0;
	private static String sOpcion;
	private static boolean bFind[];

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

	public static void actualizarLista(){
		// System.out.println("Enter actualizarLista");
		int nWords = (int)(Math.random() * 15);
		// System.out.println("nWords = "+nWords);
		for (int i = 0; i < nWords; i++) {
			int idxWord = (int)(Math.random() * 15);
			// System.out.println("idxWord = "+idxWord);
			// System.out.println("Word = "+aMostrar[idxWord]);
			String sNewWord = "";
			int rangeWord = aPalabras[idxWord].length(); 
			int idxChar = (int)(Math.random() * rangeWord);
			// System.out.println("idxChar = "+idxChar);
			// System.out.println("Char = "+aPalabras[idxWord].charAt(idxChar));
			for (int j = 0; j < aPalabras[idxWord].length(); j++) {
				if (j == idxChar) {
					sNewWord += aPalabras[idxWord].charAt(idxChar);
					sNewWord += " ";
				}else{
					sNewWord += aMostrar[idxWord].charAt(j*2);
					sNewWord += aMostrar[idxWord].charAt((j*2)+1);
				}
			}
			// System.out.println(sNewWord);
			aMostrar[idxWord] = sNewWord;
		}
	}

	public static int jugar() throws Exception{
		int nTiradas = 0;

		bFind = new boolean[15];
		for (boolean value : bFind) {
			value = false;
		}

		while(nResult < 15){
			pwSocket = new PrintWriter(new OutputStreamWriter(sClient.getOutputStream()));

			int x1, y1, x2, y2;
			System.out.println("Ingrese las coordenadas de la palabra...");
			System.out.print("\tx1: ");
			x1 = Integer.parseInt(scConsole.nextLine());
			System.out.print("\ty1: ");
			y1 = Integer.parseInt(scConsole.nextLine());
			System.out.print("\tx2: ");
			x2 = Integer.parseInt(scConsole.nextLine());
			System.out.print("\ty2: ");
			y2 = Integer.parseInt(scConsole.nextLine());
			System.out.println();
			// System.out.println(tPalabra.toString());

			if (!(new Tiro(x1,y1,x2,y2).checkPoints())) {
				continue;
			}

			String send = Integer.toString(y1);
			send += ";"+Integer.toString(x1);
			pwSocket.flush();
			pwSocket.println(send);
			pwSocket.flush();

			send = Integer.toString(y2);
			send += ";"+Integer.toString(x2);
			pwSocket.flush();
			pwSocket.println(send);
			pwSocket.flush();

			String sValue = brSocket.readLine();
			// System.out.println(sValue);
			if (Integer.parseInt(sValue) != -1) {
				nResult++;
				fTablero.actualizarTiro(y1,x1,y2,x2);
				bFind[Integer.parseInt(sValue)] = true;
				fTablero.actualizarPalabra(bFind, aMostrar);
			}else if(Integer.parseInt(sOpcion) == 2){
				actualizarLista();
				fTablero.actualizarPalabra(bFind, aMostrar);
			}

			nTiradas++;
		}
		return nTiradas;
	}

	public static void ajustarDificultad(){
		aMostrar = new String[15];
		switch(Integer.parseInt(sOpcion)){
			case 2:
				for (int i = 0; i < aPalabras.length; i++) {
					String sWord = "";
					for (int j = 0; j < aPalabras[i].length(); j++) {
						sWord += "_ ";
					}
					aMostrar[i] = sWord;
				}
				break;
			case 3:
				for (int i = 0; i < aPalabras.length; i++) {
					aMostrar[i] = Integer.toString(aPalabras[i].length());
				}
				break;
			default:
				aMostrar = aPalabras;
				break;
		}
	}

	public static void iniciarJuego() throws Exception{
		String res = brSocket.readLine();
		// System.out.println("Respuesta recibida con el mensaje: \n\t"+ res);
		aPalabras = res.split(";");
		// System.out.println(Arrays.toString(aPalabras));

		res = brSocket.readLine();
		// System.out.println("Respuesta recibida con el mensaje: \n\t"+ res);
		String sLines[] = res.split(";");
		aGame = new char[15][15];
		for (int i = 0; i < sLines.length; i++) {
			aGame[i] = sLines[i].toCharArray();
		}
		// for (char[] sLine : aGame) {
		// 	System.out.println(Arrays.toString(sLine));
		// }

		ajustarDificultad();

		// System.out.println(Arrays.toString(aMostrar));

		fTablero = new Tablero(aGame, aMostrar);
        fTablero.pack();
        fTablero.setLocation(150, 150);
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		fTablero.setBounds((screenSize.width-900)/2, (screenSize.height-750)/2, 900, 750);
        fTablero.setVisible(true);

		// String str = null; 
		// while((str = brSocket.readLine()) != null){ 
		// 	System.out.println(str); 
		// }
	}

	public static String opcion() {
		System.out.print("\nOpciones de juego:");
		System.out.print("\n\t1.- Anagrama facil");
		System.out.print("\n\t2.- Anagrama medio");
		System.out.print("\n\t3.- Anagrama dificil");
		System.out.print("\n\t4.- Conceptos\n\n");

		scConsole = new Scanner(System.in);
		String aux = "0";
		while(Integer.parseInt(aux) < 1 || Integer.parseInt(aux) > 4){
			System.out.print("\nElige una modalidad: ");
			aux = scConsole.nextLine();
		}
		return sOpcion = aux;
	}

	
	public static void main(String[] args) {

		Client cl = new Client();

		try{
			// brConsole = new BufferedReader(new InputStreamReader(System.in));

			pwSocket.println(opcion());
			pwSocket.flush();
			// System.out.println("Dato enviado... Recibiendo respuesta... ");

			iniciarJuego();

			int aux = jugar();
			System.out.println("¡Felicidades, ganaste!");
			System.out.println("Te tomó "+aux+" tiradas con "+brSocket.readLine()+"s de tiempo.");

			fTablero.finalizar(aux);

			sClient.close();

			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}