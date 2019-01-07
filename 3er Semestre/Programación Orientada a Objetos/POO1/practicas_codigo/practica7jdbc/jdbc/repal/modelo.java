
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.lang.ClassNotFoundException;
import javax.swing.JOptionPane;

/*************************************************************************************
  Modelo de datos. Responsable del acceso a datos (conexion y consulta)
  Encapsula el ResultSet, es decir, el uso del cursor no es visible desde fuera de la clase.

  Se puede hacer con Access. Pero no se puede ir muy lejos en el uso de cursor.
  Access no admite cursor con desplazamiento (scroll).
**************************************************************************************/
class modelo extends AbstractTableModel {
    private Statement stat;
    private ResultSet rs;               // Conjunto de datos de sentencia SELECT
    private ResultSetMetaData rsmd;     // Necesario para nombres de columnas
    private Connection con;             // Conexión con base de datos
    private String mensaje_error = null;// Registro de mensajes de error
    private String sentencia;           // Ultima sentencia ejecutada

    /*********************************************************************
      Conecta a la base de datos y carga los datos. Devuelve true si ha ido bien.
    **********************************************************************/
    public boolean conexion(  String driver, String host_basedatos, String tabla, String login,
			      String pwd ) {

	//// Si no puedo cargar driver de base de datos, muestro error y salgo
	if ( !cargarDriver( driver ) ){
	    System.out.println( obt_mensaje_error() );
	   return false;
	}

	//// Si no puedo conectar, muestro error y salgo
	if ( !conectar( host_basedatos, login, pwd )  ) {
	    System.out.println( obt_mensaje_error() );
	   return false;
	}

	//// Si puedo conectar, ...
	else {
	    //// Si no puedo cargar datos, muestro error y salgo
	    if ( !cargarDatos( tabla ) ) {
		System.out.println( obt_mensaje_error() );
		return false;
	    }
	}
	return true;
    }

    /*************************************************************************
     * Carga el driver. Devuelve:
     *  - true: todo ha ido bien
     *  - false: hay un error. El mensaje de error se registra en atributo mensaje_error
     *************************************************************************/
    private boolean cargarDriver( String driver) {
	try {
	    Class.forName( driver );

	    //// Versión para MySQL local ***/
//	    Class.forName("com.mysql.jdbc.Driver");         // Driver de mySQL
	    //// Versión para Access local
//jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};
    	    //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  // Driver de Access
	    //// Versión para MySQL remoto
//    	    Class.forName("org.gjt.mm.mysql.Driver");  // Driver de mySQL

	    return true;
	}
	catch( ClassNotFoundException e ) {
	    mensaje_error = new String( "No encuentra el driver. " + e.getMessage() );
	    return false;
	}
    }

    /**************************************************************
     Conecta a la base de datos señalada en el parámetro.
     Devuelve:
       - true: todo ha ido bien
       - false: hay un error. El mensaje de error se registra en atributo mensaje_error
     Para Access en local:
       1-Crear vista.policy en el directorio donde está la página html con:
          grant {
            permission java.util.PropertyPermission "file.encoding", "read";
            permission java.lang.RuntimePermission "accessClassInPackage.sun.jdbc.odbc", "resolve";
          };
       2-Crear en jre/lib/security/java.security:
          policy.url.n=file:c:/doc/java/jdbc02/class/vista.policy
     Para un applet local y MySQL en remoto:
       1-Poner en grant{} de vista.policy:
          permission java.net.SocketPermission "proactiva-calidad.com", "connect,resolve";
       2-También tiene que haber referencia a vista.policy en jre/lib/security/java.security
	  policy.url.n=file:c:/doc/java/jdbc02/class/vista.policy
     ****************************************************************/
    private boolean conectar( String base, String login, String pwd ) {

	try {
	    con = DriverManager.getConnection( base, login, pwd );

	    //// Versión para MySQL local
//	    con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/" + base, "proactiv_alumnos", password);
	    //// Versión para Access local
	    //con = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=f:\\jdbc\\verduleros.MDB", "", "");
	    //// Versión para MySQL remoto
//	    con = DriverManager.getConnection( "jdbc:mysql://proactiva-calidad.com:3306/" + base, "proactiv_alumnos", password);

	    return true;
	}
	catch (SQLException e) {
	    mensaje_error = new String( "******Error de conexión. " + e.getMessage() );
	    return false;
	}
    }

    /**********************************************
     Ejecuta la sentencia SELECT, con lo que carga el ResultSet y el ResultSetMetaData.
     Devuelve:
       - true: todo ha ido bien
       - false: hay un error. El mensaje de error se registra en atributo mensaje_error
    ************************************************/
    private boolean cargarDatos( String tabla ) {
	try {
	    stat = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
	    boolean resultado = actualizar( tabla);
	    if ( resultado )
		rsmd = rs.getMetaData();  // Obtenemos un ResultSetMetaData

	    return resultado;
	}
	catch (SQLException e) {
	    mensaje_error = new String( e.getMessage() );
	    return false;
	}
    }

    /**************** obt_mensaje_error() ******************/
    public String obt_mensaje_error() { return mensaje_error; }

    /**************** actualizar() ************************/
    public boolean actualizar( String tabla ) {
	try {
	    sentencia = "SELECT * FROM " + tabla;
         System.out.println("cmd=("+sentencia+")");
	    if (rs != null)
		rs.close();
	    if ( stat != null ) {
		rs = stat.executeQuery(sentencia); // Ejecutar la consulta
		fireTableStructureChanged();       // Ordena a la JTable que se actualice
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

    /*************** getColumnName() *********************/
    public String getColumnName( int c ) {
       try {
	   if ( rsmd != null )
	       return rsmd.getColumnName(c + 1);
	   return "";
      }
       catch(SQLException e) {  e.printStackTrace();  return ""; }
    }

    /*************** getColumnCount() ******************/
    public int getColumnCount() {
       try {
	   if ( rsmd != null )
	       return rsmd.getColumnCount();
	   return 0;
       }
       catch(SQLException e) {  e.printStackTrace();  return 0;  }
    }

    /****************** getRowCount() *******************/
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

    /******************** getResultSet() *******************/
    public ResultSet getResultSet() {
       return rs;
    }

    /*******************  getValueAt() *********************/
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

    /****************** cierra recursos ********/
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
