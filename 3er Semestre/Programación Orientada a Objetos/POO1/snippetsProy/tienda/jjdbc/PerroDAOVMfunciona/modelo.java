
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.Vector;
import java.lang.ClassNotFoundException;
import javax.swing.JOptionPane;

class modelo extends AbstractTableModel {
    private Statement stat;
    private ResultSet rs;               
    private ResultSetMetaData rsmd;     // Necesario para nombres de columnas
    private Connection con;             // Conexión con base de datos
    private String mensaje_error = null;// Registro de mensajes de error
    private String sentencia;           // Ultima sentencia ejecutada
    Control control=null;
    public Connection getConexion(){
           return con;
    }
    
public boolean conexion(  String driver, String host_BD, String tabla, String login, String pwd ) {
    control=new Control(driver, host_BD, login, pwd);
	if ((con=control.getConexion()) ==null ) {
	    System.out.println( obt_mensaje_error() );
	   return false;
	}
	else {
	    if ( !cargarDatos( tabla ) ) {
		System.out.println( obt_mensaje_error() );
		return false;
	    }
	}
	return true;
    }
    
    private boolean cargarDatos( String tabla ) {    
	try {
            boolean resultado = actualizar( tabla );
	    if ( resultado )
		rsmd = rs.getMetaData();  
	    return resultado;
	}
	catch (SQLException e) {
	    mensaje_error = new String( e.getMessage() );
	    return false;
	}
    }
    public String obt_mensaje_error() { return mensaje_error; }

    public boolean actualizar( String tabla ) {
	try {
	    sentencia = "SELECT * FROM " + tabla;
            System.out.println("11111cmd=("+sentencia+")2222");
	    if (rs != null)
		rs.close();
            rs=control.consulta(sentencia);
            fireTableStructureChanged();
	    if ( rs != null ) { 
		return true;
	    }
	    mensaje_error = new String( "No se pueden cargar los datos. Probablemente no hay conexión" );
	    return false;
	}
	catch (SQLException e) {
	    mensaje_error = new String( "No se pueden cargar los datos. " + e.getMessage() );
	    return false;
	
    }
 }

public void insertar(Object obj, String tabla) throws DAOException{  
    control.insertar(obj, "perro") ;
}
    public String getColumnName( int c ) {
       try {
	   if ( rsmd != null )
	       return rsmd.getColumnName(c + 1);
	   return "";
      }
       catch(SQLException e) {  e.printStackTrace();  return ""; }
    }

    public int getColumnCount() {
       try {
	   if ( rsmd != null )
	       return rsmd.getColumnCount();
	   return 0;
       }
       catch(SQLException e) {  e.printStackTrace();  return 0;  }
    }

    public int getRowCount() {
	try {
	    if ( rs != null ) {
		rs.last(); // Nos situamos en la última fila
		return rs.getRow(); // Devolvemos el número de la fila
	    }
	    return 0;
	}
	catch(SQLException e) {  e.printStackTrace();  return 0;  }
    }

    public ResultSet getResultSet() {
       return rs;
    }

    public Object getValueAt( int fila, int col ) {
	try {
	    if ( rs != null ) {
		rs.absolute( fila + 1 );
		return rs.getObject( col + 1 );
	    }
	    return "";
	}
	catch(SQLException e) {  e.printStackTrace();  return null;  }
    }
    protected void finalize() {
	try {
	    if (rs != null)
		rs.close();
	    if (rsmd != null)
		rs.close();
	    if (con != null)
		rs.close();
	    if (stat != null)
		stat.close();
	}
	catch(SQLException e) {  e.printStackTrace();  }
    }
}
