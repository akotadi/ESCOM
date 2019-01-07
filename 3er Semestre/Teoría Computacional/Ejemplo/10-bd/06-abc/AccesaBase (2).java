import java.sql.*;
import javax.swing.table.*;

class AccesaBase
{

  // MANEJADOR DE LA CONEXION JDBC:ODBC
  String driverSun = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driverMs = "com.ms.jdbc.odbc.JdbcOdbcDriver";

  // UBICACION DE LA BASE DE DATOS, HACE REFERENCIA AL ALIAS DEL ODBC
  String ubicacion = "jdbc:odbc:datos2";

  // CLAVE DE ACCESO A LA BASE DE DATOS
  String usuario = null;
  String contrasena = null;

boolean activa()
{
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
 return false;
           }
      }
 return true;
}

void consulta(DefaultTableModel _modelo ,String _consulta)
{
      if( activa() == false )
          return;
      try
      {
          // SE HACE LA CONEXION CON LA BASE DE DATOS
          Connection conexion = DriverManager.getConnection(ubicacion ,usuario ,contrasena);

          // SE CREA UN OBJETO consulta ASOCIADO A LA BASE DE DATOS
          Statement consulta = conexion.createStatement();

          // EL OBJETO resultado CONTIENE LOS DATOS SELECCIONADOS EN LA consulta
          ResultSet resultado = consulta.executeQuery(_consulta);
          // CIERRA LA consulta Y LA conexion CON LA BASE DE DATOS
           while( resultado.next() )
      {
      String info [] = new String[4];

          info[0] = resultado.getString("Rfc");
          info[1] = resultado.getString("Nombre");
          info[2] = resultado.getString("Direccion");
          info[3] = resultado.getString("Telefono");
          _modelo.addRow(info);
        }
          consulta.close();
          conexion.close();
      }
      catch(SQLException e)
      {
         System.out.println("ERROR EN CONSULTA");
      }
  }
 void actualiza(String _accion)
{
      if( activa() == false )
          return;
      try
      {
          // SE HACE LA CONEXION CON LA BASE DE DATOS
          Connection conexion = DriverManager.getConnection(ubicacion ,usuario ,contrasena);

          // SE CREA UN OBJETO consulta ASOCIADO A LA BASE DE DATOS
          Statement accion = conexion.createStatement();

          accion.executeUpdate(_accion);
          // CIERRA LA consulta Y LA conexion CON LA BASE DE DATOS
          accion.close();
          conexion.close();

      }
      catch(SQLException e)
      {

      }
  }
}
