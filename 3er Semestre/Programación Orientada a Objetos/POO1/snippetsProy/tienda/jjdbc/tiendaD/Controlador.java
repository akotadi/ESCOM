import javax.swing.table.*;
import java.util.*;
import java.text.*;
import java.sql.*;

class Controlador {
	Connection conexion;
	//private static DateFormat formato = DateFormat.getDateInstance();
	
	private  String url = "jdbc:mysql://localhost/test";	
	private  String	usuario ="root";
	private  String pass = "ramses";


//public Controlador(String usuario, String pass) throws DAOException{
public Controlador() throws DAOException{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conexion = DriverManager.getConnection(url,usuario,pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Driver no encontrado.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("No se pude realizar la conexión.");
		}
	}

public void insertar(DiscoVO disco) throws DAOException{
		Statement stmt = null;
		try{
       		stmt = this.conexion.createStatement();
		String titulo = disco.getTitulo();
		float precio = disco.getPrecio();
		String genero = disco.getGenero();
		short existencia = disco.getExistencia();
		
		
		String query = "insert into tDisco (cTitulo,cPrecio,cGenero,cExistencia) " +
				"values ('" + titulo + "'," + precio + ",'" + genero + "'," + existencia + ")";
		
		System.out.println(query);
		
		stmt.executeUpdate(query);
		
		} catch(SQLException sqle){
			sqle.printStackTrace();
			throw new DAOException("");
		}
	}


public void eliminar(String titulo) throws SQLException{
		Connection conexion = DriverManager.getConnection(url,usuario,pass);
		conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		PreparedStatement ps = conexion.prepareStatement("DELETE FROM tDisco WHERE cTitulo= ?");
		
		ps.setString(1,titulo);
		
		ps.executeUpdate();
		ps.close();
		conexion.close();
}
	
public void actualizar(DiscoVO disco)throws Exception
{
	
		String titulo = disco.getTitulo();
		float precio = disco.getPrecio();
		String genero = disco.getGenero();
		short existencia = disco.getExistencia();
		Connection conexion = DriverManager.getConnection(url,usuario,pass);
		conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		PreparedStatement ps = conexion.prepareStatement("UPDATE tDisco SET cPrecio=?,cGenero=?,cExistencia=? WHERE cTitulo=?");
				

		ps.setFloat(1,precio);
		ps.setString(2,genero);
		ps.setInt(3,existencia);
		ps.setString(4,titulo);
		
		//System.out.println(" dentro de Actualizar "+us);	
		ps.executeUpdate();
		
		ps.close();
		conexion.close();
			
}


public void consulta(String titulo){
		Statement statement;
		boolean hasResults = false;
		String sql="select * from tDisco where cTitulo like '"+titulo+"'";
		try {
			statement = conexion.createStatement();
			hasResults = statement.execute(sql);
			if(hasResults){
			    ResultSet result;
				result = statement.getResultSet();
				if(result!=null)	
					displayResults(result);
			}else System.out.println("no hay resultados");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}


void displayResults(ResultSet r) throws SQLException {
	  String dato=null;
	  ResultSetMetaData rmeta = r.getMetaData();
	  int numColumns=rmeta.getColumnCount();
	  //System.out.println("en display cols"+numColumns);
	  String text="";
	  for(int i=1;i<=numColumns;++i) {
	  	if(i<numColumns)
	    		text+=rmeta.getColumnName(i)+" | ";
	   	else
	    		text+=rmeta.getColumnName(i);
	  }
	  text+="\n";
	  //System.out.println("campos"+text);
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
