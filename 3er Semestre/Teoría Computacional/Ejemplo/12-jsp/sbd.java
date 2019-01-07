package bd;

import java.sql.*;

public class sbd
{
boolean hayDatos ;
String Nombre ,Direccion ,Telefono ;


  public sbd()
  {
      hayDatos = false;
  }
  public void inicializa()
  {
     hayDatos = false;
  }

  public boolean hayRespuesta()
  {
    return hayDatos;
  }

  public String getNombre()
  {
      return "" + Nombre;
  }

  public String getDireccion()
  {
      return "" + Direccion;
  }


  public void setRfc(String rfc)
  {
  String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
  String url = "jdbc:odbc:datos";
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

    try
    {
      // EFECTUA LA CONEXION CON LA BASE DE DATOS
      Connection con = DriverManager.getConnection(url ,usuario ,contrasena);

      DatabaseMetaData dbmd = con.getMetaData();

      // CREA UNA CONSULTA TEMPORAL
      Statement stmnt = con.createStatement();

      // REALIZA LA CONSULTA
      String strSQL= "SELECT * FROM Persona WHERE Rfc = '" + rfc +"' ";
      ResultSet rsPersona = stmnt.executeQuery(strSQL);
      ResultSetMetaData rsPersonamd = rsPersona.getMetaData();

      if( rsPersona.next() )
      {
        Nombre = rsPersona.getString("Nombre");
        Direccion= rsPersona.getString("Direccion");
        hayDatos = true;
      }
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
