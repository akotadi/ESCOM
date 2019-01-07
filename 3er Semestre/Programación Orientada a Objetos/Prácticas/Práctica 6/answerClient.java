import java.io.*;
import java.net.*;
import java.util.*;

public class answerClient{
	public final static int answerPort = 1025;

	public static void main(String[] args) {
		Socket theConnection;
		String hostname = "localhost";
		BufferedReader in;
		PrintStream out;

		try{
			theConnection = new Socket(hostname, answerPort);
			Scanner scan = new Scanner(System.in);
			out = new PrintStream(theConnection.getOutputStream());
			in = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));
			String theQuestion, theAnswer;
			while(scan.hasNextLine()){
				theQuestion = scan.nextLine();
				out.println(theQuestion);
				theAnswer = in.readLine();
				System.out.println("The server says: "+theAnswer+"\n");
				if (theAnswer.length() == 20) {
					return;
				}
			}
		} catch (UnknownHostException e) { 
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}