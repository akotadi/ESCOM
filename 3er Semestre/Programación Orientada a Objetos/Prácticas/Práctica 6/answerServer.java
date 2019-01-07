import java.io.*;
import java.net.*;
import java.util.*;	

public class answerServer{

	public final static int answerPort = 1025;

	public static void main(String[] args) {
		ServerSocket theServer;
		Socket theClient;
		BufferedReader in;
		PrintStream out;

		HashMap<String,String> map = new HashMap<>();
		map.put("HOLA" , "HOLA, MUCHO GUSTO");
		map.put("ADIOS" , "ADIOS, FUE UN PLACER");
		map.put("COMO TE LLAMAS?" , "ME LLAMO MANUEL");
		map.put("CUANTOS AÑOS TIENES?" , "22");
		map.put("DONDE ESTUDIAS?" , "EN ESCOM");
		map.put("QUE CLASE ES ESTA?" , "PROGRAMACION ORIENTADA A OBJETOS");
		map.put("QUE LENGUAJE UTILIZAS?" , "JAVA");
		map.put("COLOR FAVORITO?" , "ROJO");
		map.put("QUE MUSICA ESCUCHAS?" , "ROCK");
		map.put("A QUE HORA SALES?" , "3 DE LA TARDE");
		map.put("DONDE NACISTE?" , "CIUDAD DE MEXICO");
		map.put("COMIDA FAVORITA?" , "MOLE CON POLLO");

		try{
			theServer = new ServerSocket(answerPort);
			try{
				theClient = theServer.accept();
				System.out.println("Connection Established.\n");
				in = new BufferedReader(new InputStreamReader(theClient.getInputStream()));
				out = new PrintStream(theClient.getOutputStream());
				String theQuestion, theAnswer;
				while(true){
					theQuestion = in.readLine();
					if(theQuestion.equalsIgnoreCase("ADIOS")){
						out.println(map.get(theQuestion.toUpperCase()));
						break;
					}
					else if(map.containsKey(theQuestion.toUpperCase())){
						out.println(map.get(theQuestion.toUpperCase()));
					}
					else{
						out.println("LO SIENTO, NO TE ENTIENDO");
					}
				}
				theClient.close();
			} catch (IOException e){
				theServer.close();
				System.err.println(e);
			}
		} catch (IOException e){
			System.err.println(e);
		}
	}
}