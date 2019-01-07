/* Esquema.java

    DESPLIEGA EL ESQUEMA DE LA BASE DE DATOS.
    Basado en el programa de Jesus Urbina, Ana XXXX, Alberto YYYY

2003 07 Jesus Olivares
*/

import java.sql.*;

class Esquema
{
  public static void main(String args[])
  {
  // MANEJADOR DE LA CONEXION JDBC:ODBC
  String driverSun = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driverMs = "com.ms.jdbc.odbc.JdbcOdbcDriver";

  // UBICACION DE LA BASE DE DATOS, HACE REFERENCIA AL ALIAS DEL ODBC
  String ubicacion = "jdbc:odbc:datos";
  
  // CLAVE DE ACCESO A LA BASE DE DATOS
  String usuario = null;
  String contrasena = null;

  String nombres = "%"; // % INDICA TODAS
  String tipo[] = new String[1]; // TIPO DE ELEMENTO A RECUPERAR


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
          DatabaseMetaData esquema = conexion.getMetaData();

          tipo[0] = "TABLE";
          ResultSet entidad = esquema.getTables(null, null, nombres, tipo);

          // DESPLIEGA NOMBRE Y ATRIBUTOS DE CADA ENTIDAD
          while( entidad.next() )
          {
              String nombreEntidad = entidad.getString("TABLE_NAME");
              System.out.println("Entidad: " + nombreEntidad); 

              // LEE LOS ATRIBUTOS DE LA ENTIDAD
              ResultSet atributo = esquema.getColumns(null, null, nombreEntidad, null); 

              // DESPLIEGA LOS ATRIBUTOS
              while( atributo.next() )
              {
                  String nombreAtributo = atributo.getString("COLUMN_NAME");
                  System.out.println("    " + nombreAtributo); 
              }
          }
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

