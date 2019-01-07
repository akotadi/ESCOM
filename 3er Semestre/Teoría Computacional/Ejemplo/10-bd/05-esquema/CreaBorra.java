/* CreaBorra.java

    CREACION Y BORRADO DE TABLAS

2003 07 Jesus Olivares
*/

import java.sql.*;

class CreaBorra
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
              System.exit(0); // CONCLUYE
          }
      }
      try
      {
          // SE HACE LA CONEXION CON LA BASE DE DATOS
          Connection conexion = DriverManager.getConnection(ubicacion ,usuario ,contrasena);

          // SE CREA UN OBJETO comando ASOCIADO A LA BASE DE DATOS
          Statement comando = conexion.createStatement();

          // CREA LA TABLA Prueba1
          String instruccion =
          "CREATE TABLE Prueba1 (Nombre varchar(30), Pasatiempo int)";
          comando.executeUpdate(instruccion);

          // CREA UN INDICE PARA Prueba1
          instruccion =
          "CREATE UNIQUE INDEX indice1 ON Prueba1(Nombre)";
          comando.executeUpdate(instruccion);

          // CREA LA TABLA Pasatiempo
          instruccion =
          "CREATE TABLE Pasatiempo (Actividad varchar(30), Clave int)";
          comando.executeUpdate(instruccion);

          // CREA UN INDICE PARA Pasatiempo
          instruccion =
          "CREATE UNIQUE INDEX indice2 ON Pasatiempo (Clave)";
          comando.executeUpdate(instruccion);

          instruccion = "DROP TABLE Prueba1";
          comando.executeUpdate(instruccion);

          // CIERRA EL comando Y LA conexion CON LA BASE DE DATOS
          comando.close();
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
