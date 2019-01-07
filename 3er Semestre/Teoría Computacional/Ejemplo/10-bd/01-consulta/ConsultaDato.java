/* ConsultaDato.java

    CONSULTA DE UN DATO ACEPTADO DEL TECLADO

2003 07 Jesus Olivares
*/

import java.sql.*;
import java.io.*;

class ConsultaDato
{
  public static void main(String args[]) throws IOException
  {
  // MANEJADOR DE LA CONEXION JDBC:ODBC
  String driverSun = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driverMs = "com.ms.jdbc.odbc.JdbcOdbcDriver";

  // UBICACION DE LA BASE DE DATOS, HACE REFERENCIA AL ALIAS DEL ODBC
  String ubicacion = "jdbc:odbc:datos";
  
  // CLAVE DE ACCESO A LA BASE DE DATOS
  String usuario = null;
  String contrasena = null;

  // ASOCIA consola CON EL TECLADO
  BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));

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

          // LEE EL Rfc A CONSULTAR
          System.out.print("Rfc> ");
          String cadena = consola.readLine();

          // SE CREA LA INSTRUCCION EN LENGUAJE SQL PARA ACCESAR LA BASE DE DATOS
          String instruccion = "SELECT * FROM Persona WHERE Rfc = '" + cadena + "'"; // LOS APOSTROFOS '' INDICAN DATO TIPO TEXTO
    
          // EL OBJETO resultado CONTIENE LOS DATOS SELECCIONADOS EN LA consulta
          ResultSet resultado = consulta.executeQuery(instruccion);

          // SE TOMA EL encabezado DE LA TABLA RESULTADO
          ResultSetMetaData encabezado = resultado.getMetaData();

          // DESPLEGA EL ENCABEZADO
          System.out.println(encabezado.getColumnName(1) + " "
              + encabezado.getColumnName(2) + " "
              + encabezado.getColumnName(3) + " "
              + encabezado.getColumnName(4));

          // RECORRE LOS RENGLONES DE LA TABLA RESULTADO
          while( resultado.next() )
          {
              // TOMA LOS DATOS POR COLUMNA DE LA TABLA resultado
              String rfc = (String)resultado.getString(1);
              String nombre = (String)resultado.getString(2);
              String direccion = (String)resultado.getString(3);
              String telefono = (String)resultado.getString(4);

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
