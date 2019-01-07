// LIBRERÍAS
import java.io.*;
import java.net.*;
import java.util.*;

// CLASE PRINCIPAL
public class getConnectionClient{

	// VARIABLES
	private Socket theConnection; // Socket para establecer conexión con el servidor
	private int port; // Puerto a utilizar
	private BufferedReader in; // Flujo de entrada
	private PrintStream out; // Flujo de salida
	private String hostname, query; // URL del servidor, órdenes en formato MySQL que serán enviadas

	// CONSTRUCTOR
	public getConnectionClient(String ip, int port) { // Recibimos la IP y el Puerto por medio de la interfaz
		
		// Inicialización de variables de conexión
		hostname = ip;
		this.port = port;

		// INICIAMOS LA CONEXIÓN CON EL SERVIDOR
		try{
			theConnection = new Socket(hostname, port);

			// ESTABLECEMOS EL FLUJO DE ENTRADA/SALIDA CON EL SERVIDOR
			out = new PrintStream(theConnection.getOutputStream());
			in = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));

		} catch (UnknownHostException e) { 
			System.err.println(e);
			throw new EmptyStackException();
		} catch (IOException e) {
			System.err.println(e);
			throw new EmptyStackException();
		}
	}

	/*
	Descripción: Método encargado de registrar un nuevo usuario en la base de datos
	Recibe: String type (tipo de usuario), String usuario (nombre que se va a registrar), 
		String email (email del usuario), String contraseña (contraseña proporcionada por el usuario)
	Devuelve: 
	Observaciones: 
	*/
	public void SignInNewUser(String type, String usuario, String email, String contraseña){
		// Comando a ejecutar por el servidor
		out.println("SignInNewUser"); 
		// Sentencia que será enviada al servidor para su ejecución
		out.println("INSERT INTO "+type+" (nombre, email, contraseña) VALUES ('"
			+usuario.toUpperCase()+"',	'"+email.toUpperCase()+"', '"+contraseña.toUpperCase()+"')");
	}

	public int LoginAdmin(String usuario, String contraseña){
		out.println("LoginAdmin");
		out.println("SELECT idAdministrador from Administrador where (nombre = '"+usuario.toUpperCase()
			+"' or email = '"+usuario.toUpperCase()+"') and contraseña = '"+contraseña.toUpperCase()+"'");
		try{
			String s = in.readLine();
			System.out.println(s);
			if (!s.equalsIgnoreCase("Failed")) {
				return Integer.parseInt(s);
			}
			else
				return -1;
		}
		catch(IOException e){
			System.err.println(e);
			return -1;
		}
	}

	public int LoginUser(String usuario, String contraseña){
		out.println("LoginUser");
		out.println("SELECT idUsuario from Usuario where (nombre = '"+usuario.toUpperCase()+"' or email = '"
			+usuario.toUpperCase()+"') and contraseña = '"+contraseña.toUpperCase()+"'");
		try{
			String s = in.readLine();
			System.out.println(s);
			if (!s.equalsIgnoreCase("Failed")) {
				return Integer.parseInt(s);
			}
			else
				return -1;
		}
		catch(IOException e){
			System.err.println(e);
			return -1;
		}
	}

	public void nuevoReactivo(Reactivo r, String materia){
		out.println("NewReactivo");
		out.println("INSERT INTO Reactivo (pregunta, opcionA, opcionB, opcionC, opcionD, respuesta, materia) VALUES ('"
			+r.getPregunta()+"','"+r.getOpcionA()+"','"+r.getOpcionB()+"','"
			+r.getOpcionC()+"','"+r.getOpcionD()+"','"+r.getRespuesta()+"','"+materia+"')");
	}

	public void deleteUser(int idUsuario){
		out.println("DeleteUser");
		out.println("DELETE FROM Usuario WHERE idUsuario ="+idUsuario);
		out.println("DELETE FROM EU WHERE idUsuario ="+idUsuario);
		out.println("DELETE FROM Examen WHERE idUsuario ="+idUsuario);
	}

	public void deleteReactivo(int idReactivo){
		out.println("DeleteUser");
		out.println("DELETE FROM Reactivo WHERE idReactivo ="+idReactivo);
	}

	public void deleteMateria(String materia){
		out.println("DeleteMateria");
		out.println("DELETE FROM Materia WHERE materia = '"+materia+"'");
		out.println("DELETE FROM EU WHERE materia idExamen = (SELECT idExamen FROM Examen WHERE materia = '"+materia+"')");
		out.println("DELETE FROM Reactivo WHERE materia = '"+materia+"'");
		out.println("DELETE FROM Examen WHERE materia = '"+materia+"'");
	}

	public void nuevaMateria(String materia){
		out.println("NuevaMateria");
		out.println("INSERT INTO Materia VALUES ('"+materia+"')");
	}

	public void nuevoEU(int idUsuario, Examen exam){
		out.println("NewEU");
		out.println("INSERT INTO EU (idUsuario,idExamen,fechaPresentacion,ultimaPregunta,calificacion) VALUES ("
			+idUsuario+",(SELECT idExamen FROM Examen where tituloExamen = '"+exam.titulo+"' and idUsuario = "+idUsuario+"),'"
			+exam.fecha+"',"+exam.ultimaPregunta+","+exam.calificacion+")");
	}

	public void updateRespuesta(int idUsuario, Examen exam){
		for (int i =0;i<exam.vAnswers.size();++i ) {
			out.println("UpdateRespuesta");
			System.out.println(exam.vAnswers.elementAt(i));
			out.println("UPDATE EU SET Respuesta"+(i+1)+" = '"+exam.vAnswers.elementAt(i)+"' WHERE idUsuario = "
				+idUsuario+" and idExamen = (SELECT idExamen FROM Examen where tituloExamen = '"+exam.titulo+"' and idUsuario = "+idUsuario+")");
		}
		
	}	

	public void updateEU(int idUsuario, Examen exam){
		out.println("UpdateEU");
		out.println("UPDATE EU SET fechaPresentacion = '"+exam.fecha+"', ultimaPregunta = "+exam.ultimaPregunta+", calificacion = "
			+exam.calificacion+" WHERE idUsuario = "+idUsuario+" and idExamen = (SELECT idExamen FROM Examen where tituloExamen = '"+exam.titulo
			+"' and idUsuario = "+idUsuario+")");
	}

	public String getMateriaReactivo(int idReactivo){
		out.println("GetMateria");
		out.println("SELECT materia FROM Reactivo WHERE idReactivo = "+idReactivo);
		try{
			return in.readLine();
		}
		catch (IOException e){
			System.err.println(e);
			return null;
		}
	}

	public Vector nuevoExamen(String titulo, String Materia, int idUsuario){
		double x;
		int y;
		Vector v = new Vector(10);
		if (Materia.equalsIgnoreCase("Historia")) {
			for (int i = 0;i<10 ;++i ) {
				x = 0;
				x = Math.random();
				x *= 50;
				y = (int)x;
				if (!v.contains(y)) {
					v.add(y);
				}
				else
					i--;
			}
			
		}
		else{
			int i = 50;
			for (; i<100; ++i) {
				if (getMateriaReactivo(i).equalsIgnoreCase(Materia)) {
					break;
				}
			}
			for (int j = 0; j<10; ++j) {	
				v.add(i++);
			}
		}
		out.println("NewExam");
		out.println("INSERT INTO Examen (tituloExamen, idReactivo1, idReactivo2, idReactivo3, idReactivo4, "
			+"idReactivo5, idReactivo6, idReactivo7, idReactivo8, idReactivo9, idReactivo10,materia,idUsuario) VALUES ('"
			+titulo+"',"+v.elementAt(0)+","+v.elementAt(1)+","+v.elementAt(2)+","+v.elementAt(3)+","+v.elementAt(4)+","
			+v.elementAt(5)+","+v.elementAt(6)+","+v.elementAt(7)+","+v.elementAt(8)+","+v.elementAt(9)+",'"+Materia+"',"+idUsuario+")");
		Vector vReactivo = new Vector(10);
		for (int i = 0 ; i<10 ; ++i ) {
			out.println("GetReactivoPregunta");
			out.println("SELECT pregunta FROM Reactivo where idReactivo = "+v.elementAt(i)+"");
			try{
				vReactivo.add(in.readLine());
			}
			catch(IOException e){
				System.err.println(e);
			}
		}
		return vReactivo;
	}

	public Vector getExams(){
		out.println("GetExams");
		out.println("SELECT materia FROM Materia");
		try{
				int i = Integer.parseInt(in.readLine());
				System.out.println(i);
				Vector v = new Vector(i);
				String s;
				for (int j = 0; j < i; ++j) {
					s = in.readLine();
					v.add(s);
				}
				return v;
		}
		catch(IOException e){
			System.err.println(e);
			return null;
		}
	}

	public Vector getExamsUser(String idUsuario){
		out.println("GetExamsUser");
		out.println("SELECT e.materia FROM Examen e, EU eu where e.idExamen = eu.idExamen and eu.idUsuario = "+idUsuario);
		try{
			int i = Integer.parseInt(in.readLine());
			Vector v = new Vector(i);
			String s;
			for (int j = 0; j < i; ++j) {
				s = in.readLine();
				v.add(s);
			}
			return v;
		}
		catch(IOException e){
			System.err.println(e);
			return null;
		}
	}

	public Vector<Reactivo> getValuesExam(String titulo, int idUsuario){
		out.println("GetValuesExam");
		out.println("SELECT * FROM Examen where materia = '"+titulo+"' and idUsuario = "+idUsuario);
		try{
			System.out.println(1000);
			String s;
			s = in.readLine();
			System.out.println(s);
			s = in.readLine();
			System.out.println(s);
			Vector v = new Vector(10);
			for (int j = 0; j < 10; ++j) {
				s = in.readLine();
				System.out.println(s);
				v.add(s.toUpperCase());
			}

			Vector<Reactivo> Questions = new Vector(10);
			for (int i = 0; i<10; ++i) {
				Questions.add(getReactivo(Integer.valueOf(v.elementAt(i).toString())));
			}
			return Questions;
		}
		catch(IOException e){
			System.err.println(e);
			return null;
		}
	}

	public String getMateria(String titulo, int idUsuario){
		out.println("GetMateria");
		out.println("SELECT materia FROM Examen WHERE idExamen = (SELECT idExamen FROM Examen where materia = '"+titulo+"' and idUsuario = "+idUsuario+")");
		try{
			return in.readLine();
		}
		catch (IOException e){
			System.err.println(e);
			return null;
		}
	}

	public Vector<String> getSubjects(){
		out.println("GetSubjects");
		out.println("SELECT * FROM Materia");
		try{
				int i = Integer.parseInt(in.readLine());
				System.out.println(i);
				Vector<String> v = new Vector(i);
				String s;
				for (int j = 0; j < i; ++j) {
					s = in.readLine();
					v.add(s);
				}
				return v;
		}
		catch(IOException e){
			System.err.println(e);
			return null;
		}
	}

	public Examen getExam(String titulo, int idUsuario){
		out.println("GetExam");
		out.println("SELECT * FROM EU WHERE idUsuario = "+idUsuario+" and idExamen = (SELECT idExamen FROM Examen where materia = '"
			+titulo+"' and idUsuario = "+idUsuario+")");
		try{
			String s, p, q;
			s = in.readLine();
			System.out.println(s);
			p = in.readLine();
			System.out.println(p);
			Vector Answers = new Vector(Integer.valueOf(s));
			System.out.println(1+" - "+Integer.valueOf(s));
			for (int i = 0; i<Integer.valueOf(s); ++i) {
				q = in.readLine();
				System.out.println(q);
				Answers.add(q.charAt(0));
			}
			Vector<Reactivo> Questions = getValuesExam(titulo, idUsuario);
			Examen e = new Examen(titulo, idUsuario, Integer.parseInt(s), Integer.valueOf(p), Questions, Answers, getMateria(titulo,idUsuario));
			return e;
		}
		catch(IOException e){
			System.err.println(e);
			return null;
		}

	}

	public Vector checkReactivo(String materia){
		out.println("CheckReactivo");
		out.println("SELECT count(*) as total FROM Reactivo where materia = '"+materia+"'");
		try{
			int nReactivo = Integer.valueOf(in.readLine());
			out.println("SELECT idReactivo FROM Reactivo WHERE materia = '"+materia+"'");
			Vector<Integer> vID = new Vector(nReactivo);
			for (int i = 0; i<nReactivo; ++i) {
				vID.add(Integer.valueOf(in.readLine()));
			}

			Vector<Reactivo> vReactivo = new Vector(nReactivo);
			for (int i = 0;i<nReactivo; i++) {
				vReactivo.add(getReactivo(vID.elementAt(i)));
			}
			return vReactivo;
		}
		catch(IOException e){
			System.err.println(e);
		}
		return null;
	}

	public Reactivo getReactivo(int idReactivo){
		out.println("GetReactivo");
		out.println("SELECT * FROM Reactivo where idReactivo = "+idReactivo);
		try{
			Vector v = new Vector(6);
			String s;
			for (int j = 0; j < 6; ++j) {
				s = in.readLine();
				v.add(s.toUpperCase());
			}
			Reactivo r = new Reactivo(idReactivo,v.elementAt(0).toString(),v.elementAt(1).toString(),v.elementAt(2).toString(),v.elementAt(3).toString(),v.elementAt(4).toString(),v.elementAt(5).toString());
			System.out.println(r.getPregunta());
			return r;
		}
		catch(IOException e){
			System.err.println(e);
			return null;
		}
	}

	public void closeConnectionClient(){
		out.println("Close");
		return;
	}
}