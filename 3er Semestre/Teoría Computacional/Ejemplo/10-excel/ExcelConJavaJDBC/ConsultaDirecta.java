/* Consulta.java

    DESPLIEGA EL CONTENIDO DE UNA TABLA COMPLETA DE UNA BD
    HACE REFERENCIA DIRECTAMENTE A LA BASE DE DATOS

2003 07 Jesus Olivares
*/

import java.sql.*;

public class ConsultaDirecta
{
  public static void main(String args[])
  {
  // MANEJADOR DE LA CONEXION JDBC:ODBC
  String driverSun = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driverMs = "com.ms.jdbc.odbc.JdbcOdbcDriver";

  // UBICACION DE LA BASE DE DATOS
  //String nombreBD = "C:/Jesus/Taller/Java/Ejemplo/10-bd/persona.mdb";
  String nombreBD = "demo.xls";
  String ubicacion = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+nombreBD;

  // CLAVE DE ACCESO A LA BASE DE DATOS
  String usuario = null;
  String contrasena = null;

      // ACTIVACION DEL MANEJADOR DE LA CONEXION JDBC:ODBC
      try
      {
          Class.forName(driverSun);
      }
      catch(ClassNotFoundException noEstaSun)
      {
          try
          {
              Class.forName(driverMs);
          }
          catch(ClassNotFoundException noEstaMs)
          {
              System.out.println("Falta el driver Jdbc:Odbc: " + noEstaMs.getMessage());
              System.exit(0); // CONCLUYE LA CONSULTA
          }
      }
      try
      {
          // SE HACE LA CONEXION CON LA BASE DE DATOS
          Connection conexion = DriverManager.getConnection(ubicacion ,usuario ,contrasena);

          // SE CREA UN OBJETO consulta ASOCIADO A LA BASE DE DATOS 
          Statement consulta = conexion.createStatement();

          // SE CREA LA INSTRUCCION EN LENGUAJE SQL PARA ACCESAR LA BASE DE DATOS
          String instruccion = "SELECT * FROM [Hoja1$]";
   
          // EL OBJETO resultado CONTIENE LOS DATOS SELECCIONADOS EN LA consulta
          ResultSet resultado = consulta.executeQuery(instruccion);

          // RECORRE LOS RENGLONES DE LA TABLA RESULTADO
          while( resultado.next() )
          {
              // TOMA LOS DATOS POR COLUMNA DE LA TABLA resultado
              double rfc = resultado.getDouble(1);
              String nombre = resultado.getString(2);
              String direccion = resultado.getString(3);
              String telefono = resultado.getString(4);

              // DESPLEGA LOS DATOS LEIDOS
              System.out.println(rfc +" "+ nombre +" " + direccion + " " + telefono);
          }
   
          // CIERRA LA consulta Y LA conexion CON LA BASE DE DATOS
          consulta.close();
          conexion.close();
      }
      catch(SQLException e)
      {
          // DESPLIEGA LA DESCRIPCION DE LA EXCEPCION
          while( e != null)
          {
              System.out.println("Estado del SQL: " + e.getSQLState());
              System.out.println("Mensaje: " + e.getMessage());
              System.out.println("Codigo: " + e.getErrorCode());
              e = e.getNextException();
          }
      }
  }
}
