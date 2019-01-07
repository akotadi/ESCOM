// LIBRERÍAS
import java.io.*;
import java.net.*;
import java.util.*;	


// CLASE PRINCIPAL
public class TestServer{

	// PUERTO DEL SERVIDOR
	public final static int answerPort = 1025;

	// VARIABLES
	private ServerSocket theServer;
	private Socket theClient;
	private BufferedReader in;
	private PrintStream out;
	private getConnectionToDataBase connection;

	// MAIN
	public static void main(String[] args) {

		// INICIAMOS EL SERVIDOR EN EL PUERTO
		try{
			theServer = new ServerSocket(answerPort);
			
			// RECIBIMOS LA CONEXIÓN DEL CLIENTE
			try{
				theClient = theServer.accept();
				System.out.println("Connection with client established.\n");

				// ESTABLECEMOS EL FLUJO DE ENTRADA/SALIDA CON EL CLIENTE
				in = new BufferedReader(new InputStreamReader(theClient.getInputStream()));
				out = new PrintStream(theClient.getOutputStream());

				// VARIABLES PARA RECIBIR Y ENVIAR COMANDOS AL SERVIDOR
				String theInputLine, query;
				try{

					// INICIAMOS LA CONEXIÓN CON LA BASE DE DATOS
					connection = new getConnectionToDataBase();

					// ITERACIÓN INFINITA PARA INTERCAMBIO DE MENSAJES CON EL CLIENTE
					while(true){
						// Se lee la sentencia de entrada
						theInputLine = in.readLine();

						// Comparamos el comando para decidir cómo ejecutar la acción
						if (theInputLine.equalsIgnoreCase("SignInNewUser")) {
							// Leemos la sentencia a ejecutar
							query = in.readLine();
							// Ejecutamos la sentencia en la base de datos
							connection.RegisterNewUser(query);
						}

						else if (theInputLine.equalsIgnoreCase("Close")) {
							System.out.println("Connection Closed.\n");
							break;
						}
						else if (theInputLine.equalsIgnoreCase("LoginAdmin") || theInputLine.equalsIgnoreCase("LoginUser")) {
							int i;
							query = in.readLine();
							System.out.println(query);
							if (theInputLine.equalsIgnoreCase("LoginAdmin")) 
								i=connection.ConnectAdmin(query);
							else
								i=connection.ConnectUser(query);
							if (i != -1) {
								out.println(i);
							}
							else
								out.println("Failed");
						}
						else if (theInputLine.equalsIgnoreCase("NewReactivo") || theInputLine.equalsIgnoreCase("NuevaMateria")) {
							query = in.readLine();
							connection.newReactivo(query);
						}
						else if (theInputLine.equalsIgnoreCase("DeleteUser")) {
							for (int i = 0; i<3; i++) {
								query = in.readLine();
								connection.deleteMateria(query);
							}
						}
						else if (theInputLine.equalsIgnoreCase("DeleteMateria")) {
							for (int i = 0; i<4; i++) {
								query = in.readLine();
							connection.deleteMateria(query);
							}
						}
						else if (theInputLine.equalsIgnoreCase("NewExam")) {
							query = in.readLine();
							connection.newExam(query);
						}
						else if (theInputLine.equalsIgnoreCase("GetReactivoPregunta")) {
							query = in.readLine();
							out.println(connection.getReactivoPregunta(query));
						}
						else if (theInputLine.equalsIgnoreCase("CheckReactivo")) {
							query = in.readLine();
							int nReactivo = connection.checkReactivo(query);
							out.println(nReactivo);
							query = in.readLine();
							Vector<Integer> vReactivo = connection.valueReactivo(query);
							for (int i = 0; i<vReactivo.size();++i ) {
								out.println(vReactivo.elementAt(i));
							}
						}
						else if (theInputLine.equalsIgnoreCase("GetExams") || theInputLine.equalsIgnoreCase("GetExamsUser")) {
							query = in.readLine();
							Vector v = connection.getExams(query);
							out.println(v.size());
							for (int i = 0; i<v.size();++i ) {
								out.println(v.elementAt(i));
							}
						}
						else if (theInputLine.equalsIgnoreCase("GetMateria")){
							query = in.readLine();
							query = connection.getMateria(query);
							out.println(query);
						}
						else if (theInputLine.equalsIgnoreCase("GetReactivo")) {
							query = in.readLine();
							System.out.println(query);
							Vector v = connection.getReactivo(query);
							for (int i = 0; i<v.size();++i ) {
								out.println(v.elementAt(i));
							}
						}
						else if (theInputLine.equalsIgnoreCase("GetValuesExam")) {
							query = in.readLine();
							System.out.println(query);
							Vector v = connection.getValuesExam(query);
							for (int i = 0; i<v.size();++i ) {
								out.println(v.elementAt(i));
								System.out.println(v.elementAt(i));
							}
						}
						else if (theInputLine.equalsIgnoreCase("GetExam")) {
							query = in.readLine();
							System.out.println(query);
							Vector v = connection.getExam(query);
							System.out.println(v.size());
							for (int i = 0; i<v.size();++i ) {
								System.out.println(v.elementAt(i));
								out.println(v.elementAt(i));
							}
						}
						else if (theInputLine.equalsIgnoreCase("GetSubjects")) {
							query = in.readLine();
							Vector v = connection.getSubjects(query);
							out.println(v.size());
							for (int i = 0; i<v.size();++i ) {
								out.println(v.elementAt(i));
							}
						}
						else if (theInputLine.equalsIgnoreCase("NewEU") || theInputLine.equalsIgnoreCase("UpdateEU") || theInputLine.equalsIgnoreCase("UpdateRespuesta")) {
							query = in.readLine();
							connection.nuevoEU(query);
						}
					}	
				}
				catch(EmptyStackException ems){
					System.out.println("Cannot connect to the database.\n");
					System.exit(0);
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