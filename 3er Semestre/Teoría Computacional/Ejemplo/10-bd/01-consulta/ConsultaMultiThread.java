/* Consulta.java

    DESPLIEGA EL CONTENIDO DE UNA TABLA COMPLETA DE UNA BD
    HACE REFERENCIA DIRECTAMENTE A LA BASE DE DATOS

2003 07 Jesus Olivares
*/

import java.sql.*;

class Recupera extends Thread
{
String nombre ;

  Recupera(String n)
  {
      nombre = n; 
      System.out.println("ARRANCA "+n);
      
        
  }  
    
  public void run()
  {
       consulta();
  }
  
    void consulta()
  {
  // MANEJADOR DE LA CONEXION JDBC:ODBC
  String driverSun = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driverMs = "com.ms.jdbc.odbc.JdbcOdbcDriver";

  // UBICACION DE LA BASE DE DATOS
 String ubicacion = "jdbc:odbc:mibase";

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
          Connection conexion =
           DriverManager.getConnection(ubicacion ,usuario ,contrasena);

          // SE CREA UN OBJETO consulta ASOCIADO A LA BASE DE DATOS
          Statement consulta = conexion.createStatement();

          // SE CREA LA INSTRUCCION EN LENGUAJE SQL PARA ACCESAR LA BASE DE DATOS
          String instruccion = "SELECT * FROM Persona";

          // EL OBJETO resultado CONTIENE LOS DATOS SELECCIONADOS EN LA consulta
          ResultSet resultado = consulta.executeQuery(instruccion);

          // RECORRE LOS RENGLONES DE LA TABLA RESULTADO
          while( resultado.next() )
          {
              // TOMA LOS DATOS POR COLUMNA DE LA TABLA resultado
              String rfc = resultado.getString("Rfc");
              String nombre = resultado.getString("Nombre");
              String direccion = resultado.getString("Direccion");
              String telefono = resultado.getString("Telefono");
              double ingreso = resultado.getDouble("Ingreso");

              // DESPLIEGA LOS DATOS LEIDOS
              System.out.println(this.nombre+": " +rfc +" "+ nombre +" " + direccion + " " 
              + telefono+" " + ingreso);
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

class Consulta4
{
 public static void main(String args[])
 {
 Recupera r1 = new Recupera("A");
 Recupera r2 = new Recupera("B");
 Recupera r3 = new Recupera("C");
 Recupera r4 = new Recupera("D");
 Recupera r5 = new Recupera("E");
 Recupera r6 = new Recupera("F");
 Recupera r7 = new Recupera("G");
 Recupera r8 = new Recupera("h");
 Recupera r9 = new Recupera("i");
 Recupera r10 = new Recupera("j");
 Recupera r11 = new Recupera("k");
 Recupera r12 = new Recupera("l");
 Recupera r13 = new Recupera("ll");
 Recupera r14 = new Recupera("m");
 Recupera r15 = new Recupera("n");
 Recupera r16 = new Recupera("o");
 Recupera r17 = new Recupera("p");
 Recupera r18 = new Recupera("q");
 Recupera r19 = new Recupera("r");
 Recupera r20 = new Recupera("s");
 Recupera r21 = new Recupera("t");
 Recupera r22 = new Recupera("u");
 Recupera r23 = new Recupera("v");
 Recupera r24= new Recupera("w");
 Recupera r25 = new Recupera("x");
 
 
    r1.consulta();
    r2.consulta();
    r3.consulta();
    r4.consulta();
    r5.consulta();
   r1.start();
    
    r11.start();   r17.start();
    r12.start();
    r13.start();
    r14.start();
    r15.start();   r18.start();
    r16.start();
    r19.start();
    r20.start();
    r21.start();   
    r22.start();
    r23.start();
    r24.start();
    r25.start();
 
 }    
}
