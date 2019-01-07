// LIBRERÍAS
import java.sql.*;
import java.util.*;

// CLASE PRINCIPAL
public class getConnectionToDataBase extends JPanel{
	
	// VARIABLES
	static Connection conex; // Clase de la librerías sql que permite la conexión con este tipo de BD
	static String url = "jdbc:mysql://localhost:3306/"; // URL de la BD predefinida
	static String dbName = "AplicadorExamen"; // Nombre de la BD en nuestro Servidor

	// CONSTRUCTOR
	public getConnectionToDataBase(){

		//CONEXIÓN CON LA BASE DE DATOS
		try { 
			Class.forName("com.mysql.jdbc.Driver"); // Usaremos el driver de MySQL 
			// Introduciremos las credenciales necesarias para conectarse a la base de datos       		
			conex=DriverManager.getConnection(url+dbName, "root", "77Manuel95:" ); 
			System.out.println("Connection with database established.");
		}
		catch (Exception ex){ 
			System.out.println(ex); 
			throw new EmptyStackException(); 
		}
	}

	public void closeConnectionToDataBase(){
		try{
			conex.close();
			System.out.println("Connection closed.");
		} catch (Exception ex){ System.out.println(ex); System.exit(0); }
	}

	/*
	Descripción: Método encargado de registrar nuevos usuarios en la Base de Datos
	Recibe: String sqlInsert (Sentencia que se va a insertar en la Base de Datos)
	Devuelve: 
	Observaciones: Se coloca dentro de un bloque try porque puede generar excepciones
	*/
	public static void RegisterNewUser(String sqlInsert){
		try{
			int i; // Variable que nos dirá el número de tuplas afectadas
			Statement statement=conex.createStatement(); // Variable que permite ejecutar cambios en la BD
			i=statement.executeUpdate(sqlInsert); // executeUpdate regresa como entero el número de tuplas afectadas
			System.out.println("QUERY OK, "+i+" row affected.");
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
	}

	public void newReactivo(String sqlInsert){
		try{
			int i;
			Statement statement=conex.createStatement();
			i=statement.executeUpdate(sqlInsert);
			System.out.println("QUERY OK, "+i+" row affected.");
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
	}

	public void deleteMateria(String sqlInsert){
		try{
			int i;
			Statement statement=conex.createStatement();
			i=statement.executeUpdate(sqlInsert);
			System.out.println("QUERY OK, "+i+" row affected.");
		} catch (Exception ex){ 
			System.out.println(ex); 
		}
	}

	public void nuevoEU(String sqlInsert){
		try{
			int i;
			Statement statement=conex.createStatement();
			i=statement.executeUpdate(sqlInsert);
			System.out.println("QUERY OK, "+i+" row affected.");
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
	}

	public void updateRespuesta(String sqlInsert){
		try{
			int i;
			Statement statement=conex.createStatement();
			i=statement.executeUpdate(sqlInsert);
			System.out.println("QUERY OK, "+i+" row affected.");
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
	}

	public void newExam(String sqlInsert){
		try{
			int i;
			Statement statement=conex.createStatement();
			i=statement.executeUpdate(sqlInsert);
			System.out.println("QUERY OK, "+i+" row affected.");
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
	}

	public int ConnectAdmin(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			int count = 0, i = 0;
			while(result.next()){
				if(result.getInt("idAdministrador") != 0)
					i = result.getInt("idAdministrador");
				++count;
			}
			if (count == 1) {
				return i;
			}
			else
				return -1;
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
			return -1;
		}
	}

	public int ConnectUser(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			int count = 0, i = 0;
			while(result.next()){
				if(result.getInt("idUsuario") != 0)
					i = result.getInt("idUsuario");
				++count;
			}
			if (count == 1) {
				return i;
			}
			else
				return -1;
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
			return -1;
		}
	}

// 5

	public String getReactivoPregunta(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			result.next();
			return result.getString("pregunta");
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return null;
	}

	public String getMateria(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			result.next();
			return result.getString("materia");
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return null;
	}

	public int checkReactivo(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			result.next();
			return result.getInt("total");
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return 0;
	}

	public Vector getExams(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			Vector v = new Vector();
			while(result.next()){
				v.add(result.getString("materia"));
			}
			return v;
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return null;
	}

	public Vector getSubjects(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			Vector<String> v = new Vector();
			while(result.next()){
				v.add(result.getString("materia"));
			}
			return v;
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return null;
	}

	public Vector getReactivo(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			Vector v = new Vector(6);
			result.next();
			for (int i= 2;i<8 ;++i ) {
				v.add(result.getString(i));
			}
			return v;
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return null;
	}

	public Vector valueReactivo(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			Vector<Integer> vReactivo = new Vector();
			while(result.next()){
				vReactivo.add(result.getInt("idReactivo"));
			}
			return vReactivo;
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return null;
	}

	public Vector getValuesExam(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			Vector v = new Vector(12);
			result.next();
			for (int i= 1;i<13 ;++i ) {
				v.add(result.getString(i));
			}
			return v;
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return null;
	}

	public Vector getExam(String sqlSelect){
		try{
			Statement statement=conex.createStatement();
			ResultSet result=statement.executeQuery(sqlSelect);
			result.next();
			int j = 2+result.getInt("ultimaPregunta");
			Vector v = new Vector(j);
			System.out.println(result.getInt("ultimaPregunta"));
			for (int i= 4;i<result.getInt("ultimaPregunta")+6 ;++i ) {
				v.add(result.getString(i));
			}
			return v;
		} catch (Exception ex){ 
			System.out.println(ex); 
			System.exit(0); 
		}
		return null;
	}

}