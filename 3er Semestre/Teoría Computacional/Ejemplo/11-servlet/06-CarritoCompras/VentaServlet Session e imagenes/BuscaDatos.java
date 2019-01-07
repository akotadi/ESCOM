import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class BuscaDatos extends HttpServlet
{
String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
String ubicacion = "jdbc:odbc:mibase";
//"jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Agencia.mdb";

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
  public void service(
    HttpServletRequest  entrada
  , HttpServletResponse salida)
    throws ServletException, IOException
  {
      // ESTABLECE EL TIPO DE SALIDA
      salida.setContentType("text/html");

      // CREA EL FLUJO DE SALIDA HACIA EL CLIENTE
      ServletOutputStream os = salida.getOutputStream();

      // EFECTUA LA CONEXION CON LA BASE DE DATOS
      try
      {
          conexion = DriverManager.getConnection(ubicacion ,usuario ,contrasena);
      Statement consulta = conexion.createStatement();

       os.println("<html><head><title>Compra de Boletos</title></head><BODY bgcolor=\"#FFFFFF\">");
       os.println("<h3>SELECCIONE SU ORIGEN Y DESTINO</h3>");
       os.println("<FORM ACTION=\"http://localhost:8080/examples/servlet/Busca\">");
       os.println("<font face=Arial SIZE=4>ORIGEN</font>");
       os.println("<BR>");
       os.println("<SELECT NAME=origen>");
       
       os.println("<OPTION VALUE=\"noHay\">[Selecciona Origen]</OPTION>");
 
      // SE CREA LA INSTRUCCION EN LENGUAJE SQL PARA ACCESAR LA BASE DE DATOS
       String instruccion = "SELECT DISTINCT Origen FROM Avion";

      // EL OBJETO resultado CONTIENE LOS DATOS SELECCIONADOS EN LA consulta
      ResultSet resultado = consulta.executeQuery(instruccion);

      // RECORRE LOS RENGLONES DE LA TABLA RESULTADO
      Vector ciudad = new Vector();
                 
      while( resultado.next() )
      {
          // TOMA LOS DATOS POR COLUMNA DE LA TABLA resultado
          String unOrigenAvion = resultado.getString(1);

          ciudad.add(unOrigenAvion);
      }
      // SE CREA LA INSTRUCCION EN LENGUAJE SQL PARA ACCESAR LA BASE DE DATOS
      instruccion = "SELECT DISTINCT Origen FROM Camion";

                 // EL OBJETO resultado CONTIENE LOS DATOS SELECCIONADOS EN LA consulta
                 resultado = consulta.executeQuery(instruccion);

                 while( resultado.next() )
                 {
                 String unOrigenCamion = resultado.getString(1);

                    if( (ciudad.contains(unOrigenCamion) == false) 
                       && (unOrigenCamion != null) )
                        ciudad.add(unOrigenCamion);
                 }
    
    // ENVIA EL COMBOBOX             
                 for(int i=0; i< ciudad.size();i++)
                 {
                 String unOrigen = (String)ciudad.get(i);
                                  
      os.println("<OPTION VALUE=\""+unOrigen + "\">"+unOrigen+"</OPTION>");
                 }
                                     
      os.println("</SELECT>");
      os.println("<BR>");
      os.println("<BR>");
   
      os.println("<font face=Arial SIZE=4>DESTINO</font>");
      os.println("<BR>"); 
      os.println("<SELECT NAME=destino>");
      os.println("<OPTION VALUE=\"noHay\">[Selecciona Origen]</OPTION>");                                    
                 // *******************************
                 // SE CREA LA INSTRUCCION EN LENGUAJE SQL PARA ACCESAR LA BASE DE DATOS
                 instruccion = "SELECT DISTINCT Destino FROM Avion";

                 // EL OBJETO resultado CONTIENE LOS DATOS SELECCIONADOS EN LA consulta
                 resultado = consulta.executeQuery(instruccion);

                 // RECORRE LOS RENGLONES DE LA TABLA RESULTADO
                 ciudad = new Vector();
                 
                 while( resultado.next() )
                 {
                 // TOMA LOS DATOS POR COLUMNA DE LA TABLA resultado
                 String unDestinoAvion = resultado.getString(1);

                        ciudad.add(unDestinoAvion);
                 }

                 // SE CREA LA INSTRUCCION EN LENGUAJE SQL PARA ACCESAR LA BASE DE DATOS
                 instruccion = "SELECT DISTINCT Destino FROM Camion";

                 // EL OBJETO resultado CONTIENE LOS DATOS SELECCIONADOS EN LA consulta
                 resultado = consulta.executeQuery(instruccion);

                 while( resultado.next() )
                 {
                 String unDestinoCamion = resultado.getString(1);

                    if( (ciudad.contains(unDestinoCamion) == false) 
                       && (unDestinoCamion != null) )
                        ciudad.add(unDestinoCamion);
                 }
                 
                 for(int i=0; i< ciudad.size();i++)
                 {
                 String unDestino = (String)ciudad.get(i);
    
os.println("<OPTION VALUE=\""+unDestino + "\">"+unDestino+"</OPTION>");
                 }
                                     
       
      os.println("</SELECT>");
      os.println("<BR>");
      os.println("<BR>");
      os.println("<INPUT TYPE=Submit VALUE=\"Buscar\">");
      os.println("</FORM>");
      os.println("</body>");
      os.println("</html>");
  }
  catch(SQLException esql){
  }
  }

  // EL DESTRUCTOR SE ACTIVA CUANDO SE DESCARGA EL SERVLET
  public void destroy()
  {
      try
      {
          conexion.close();
      }
      catch(SQLException e)
      {
          System.out.print("Error al cerrar la Base de Datos");
          return;
      }
  }
}

