import java.util.*;
import java.text.*;
import java.sql.*;

class Controlador {

String driverClassName = "com.mysql.jdbc.Driver";
String conexionUrl = "jdbc:mysql://localhost:3306/classicmodels";
String dbUser = "root";
String dbPwd = "gatito";
Connection conexion = null;
PreparedStatement stmt = null;
public Connection getConnection(){
   Connection conn;
   try {
     conn = ConnectionFactory.getInstance(driverClassName, conexionUrl, dbUser, dbPwd )
.getConexion();
   } catch (SQLException e) {
		e.printStackTrace();
		conn = null;
   }
   return conn;
}
public void insertar(PerroBean perro) throws DAOException{
	try{
String queryString = "INSERT INTO perro(Nombre, Raza, Edad, Genero) VALUES(?,?,?,?)";
       conexion = getConnection();
       stmt = conexion.prepareStatement(queryString);
		stmt.setString(1,perro.getNombre());
		stmt.setString(2,perro.getRaza());
		stmt.setInt(3,perro.getEdad());
		stmt.setString(4,perro.getGenero());
		stmt.executeUpdate();
	} catch(SQLException sqle){
		sqle.printStackTrace();
		throw new DAOException("");
	}
}

public void eliminar(String nombre) throws SQLException{
String queryString = "DELETE FROM perro WHERE Nombre= ?";
	conexion = getConnection();
       stmt = conexion.prepareStatement(queryString);
	stmt.setString(1, nombre);	     
	stmt.executeUpdate();
	stmt.close();
	conexion.close();
}
public void actualizar(PerroBean perro) throws Exception
{
String queryString = "UPDATE perro SET Raza=?, Edad=?, Genero=? WHERE Nombre=?";
	conexion = getConnection();
stmt = conexion.prepareStatement(queryString);
	stmt.setString(1, perro.getRaza());
	stmt.setInt(2, perro.getEdad());
	stmt.setString(3, perro.getGenero());
        stmt.setString(4, perro.getNombre());
	stmt.executeUpdate();	
	stmt.close();
	conexion.close();
}

public void consulta(String nombre){
   Statement statement;
   boolean hasResults = false;
   String queryString ="select * from perro where Nombre like '"+nombre+"'";
   try {
	conexion = getConnection();
	statement = conexion.createStatement();
	hasResults = statement.execute(queryString);
	if(hasResults){
		ResultSet result;
		result = statement.getResultSet();
		if(result!=null)	
			displayResults(result);
		} else System.out.println("no hay resultados");
   } catch (SQLException e) {
		e.printStackTrace();
   }
}
void displayResults(ResultSet r) throws SQLException {
   String dato=null;
   ResultSetMetaData rmeta = r.getMetaData();
   int numColumns=rmeta.getColumnCount();
   String text="";
   for(int i=1;i<=numColumns;++i) {
	  if(i<numColumns)
	    	text+=rmeta.getColumnName(i)+" | ";
	   else
	    	text+=rmeta.getColumnName(i);
   }
   text+="\n";
   while(r.next()){
	  for(int i=1;i<=numColumns;++i) {
		dato=r.getString(i);
		if(dato!=null){
	    		if(i<numColumns) //{
	     			text+=r.getString(i)+" | ";
	      				//System.out.println("["+r.getString(i)+"]");
	    			//}
	    		else
	     			text+=r.getString(i).trim();
		}
	  }
	   text+="\n";
   }
   System.out.println(text);
}
}
		
		
