package bd;

/* Standard java imports. */
import java.lang.*;
import java.sql.*;

/* Servlet imports.*/
import javax.servlet.*;
import javax.servlet.http.*;


public class PersonaBean
{
 String vRfc = "";
 String vNombre = "";
 String vDireccion = "";
 String vTelefono = "";
 HttpServletRequest request = null;

 public void alta(HttpServletRequest request) throws Exception
 {
  String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
  String url = "jdbc:odbc:Datos";
  String usuario = null;
  String contrasena = null;
   
    try
    {
      Class.forName(driver);
    } 
    catch(ClassNotFoundException e)
    {
       try
       {
         Class.forName(driver1);
       } 
       catch(ClassNotFoundException e1)
       {
         System.out.println("ClassNotFoundException: " + e.getMessage());
       }
    }

// TOMA LOS PARAMETROS DE LA FORMA HTML
   String vRfc = request.getParameter("RFC");
   String vNombre = request.getParameter("NOMBRE");
   String vDireccion = request.getParameter("DIRECCION");
   String vTelefono = request.getParameter("TELEFONO");
   

    try
    {
      // EFECTUA LA CONEXION CON LA BASE DE DATOS
      Connection con = DriverManager.getConnection(url ,usuario ,contrasena);
      DatabaseMetaData dbmd = con.getMetaData();

      // CREA UNA CONSULTA TEMPORAL
 //     Statement stmnt = con.createStatement();

   String sql = "INSERT INTO Persona " +
                "(Rfc, Nombre, Direccion, Telefono) " +
                "VALUES " +
                "(?,?,?,?)";

   /* Prepare the SELECT statement.*/
   PreparedStatement stmnt = con.prepareStatement(sql);

   /* Set the parameters for the INSERT run it.*/
   stmnt.setObject (1, vRfc);
   stmnt.setObject (2, vNombre);
   stmnt.setObject (3, vDireccion);
   stmnt.setObject (4, vTelefono);

   boolean returnValue = stmnt.execute();


      // CIERRA LA CONEXION CON LA BASE DE DATOS
      stmnt.close();
      con.close();
    }
    catch(SQLException se)
    {
      while( se != null) 
      {
        System.out.println("SQLState: " + se.getSQLState());
        System.out.println("Mensaje: " + se.getMessage());
        System.out.println("Código: " + se.getErrorCode());
        se = se.getNextException();
        System.out.println(""); 
      }
    } 
  }

}