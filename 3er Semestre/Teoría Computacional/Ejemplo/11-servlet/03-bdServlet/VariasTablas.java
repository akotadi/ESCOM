import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.sql.*;

public class VariasTablas extends HttpServlet
{
String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
String url = "jdbc:odbc:datos";
String usuario = null;
String contrasena = null;
Connection conexion ;

  // INICIALIZA EL SERVLET
  public void init(ServletConfig config) throws ServletException
  {
      super.init(config); // ASEGURA LA CARGA DEL SERVLET
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
              System.out.println("No hay driver: " + e.getMessage());
          }
      }
  }

  // RECIBE Y DESPACHA LOS SERVICIOS PEDIDOS AL SERVLET
  public void service(HttpServletRequest  peticion,
   HttpServletResponse respuesta)
    throws ServletException, IOException
  {
      // ESTABLECE EL TIPO DE SALIDA
      respuesta.setContentType("text/html");

      // CREA EL FLUJO DE SALIDA HACIA EL CLIENTE
      ServletOutputStream out = respuesta.getOutputStream();

      // LECTURA DEL PARAMETRO Rfc
      String cRfc = peticion.getParameter("Rfc");

      // EFECTUA LA CONEXION CON LA BASE DE DATOS
      try
      {
          conexion = DriverManager.getConnection(url ,usuario ,contrasena);
      DatabaseMetaData dbmd = conexion.getMetaData();
      Statement mandato1 = conexion.createStatement();
      Statement mandato2 = conexion.createStatement();
      Statement mandato3 = conexion.createStatement();

          // ENCABEZADO DE LA SALIDA
          out.println("<html><head><title>Respuesta de la BD</title></head><BODY>");
          out.println("<hr><div align=center><h2>Resultado</h2><div><hr>");
          
  ResultSet r1 = mandato1.executeQuery(
  "SELECT Nombre FROM Persona WHERE Rfc = '"+ cRfc+"'");
  r1.next();        
          out.println("<P>Los pasatiempos de "+
          r1.getString(1)
          +" son: <BR><BR>");
          
  ResultSet r2 = mandato2.executeQuery(
  "SELECT Clave FROM Actividad WHERE Rfc = '"+ cRfc+"'");
  while( r2.next() )
  {
  //String res = ;
  
       ResultSet r3 = mandato3.executeQuery(
  "SELECT Descripcion FROM Pasatiempo WHERE Clave = '"+ r2.getString(1) +"'");
  while( r3.next() )
  {
  String solucion = r3.getString(1);
          out.print(solucion+"<BR>");
  
    
  }
  
    
  }        
  
   
  
          // RECUPERACION DE LA INFORMACION OBTENIDA
      }
      catch(SQLException e)
      {
        out.print("ERROR en la recuperación de datos");
      }
      out.println("<hr></body></html>");
  }
}

