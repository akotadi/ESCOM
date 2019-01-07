import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;

public class Comprar extends HttpServlet
{
String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
String ubicacion = "jdbc:odbc:mibase";
String usuario = null;
String contrasena = null;
Connection conexion ;
String origen ;
String destino ;

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
os.println("<html>");
os.println("<head>");
os.println("<title>Compra de boletos </title>");
os.println("<body>");

       String param;
        origen = entrada.getParameter("origen");
        destino = entrada.getParameter("destino");
     try {
     
     Connection conexion = DriverManager.getConnection(ubicacion,usuario ,contrasena);
     Statement consulta = conexion.createStatement();
      
       // LECTURA DE PARAMETROS DE LA PAGINA WEB
      int i = 1;
      while( (param=entrada.getParameter("ad"+i)) != null )
      {
        System.out.println(" ai="+i);
      int cantidad = Integer.parseInt(param);
      
         String elegidoA = entrada.getParameter("ac"+i);
         if( elegidoA == null ) // MARCO EL CHECKBOX ?
         {
             i++;
             continue;
         }
         String linea = entrada.getParameter("al"+i);
        
         String instruccion = "SELECT * FROM Avion WHERE Linea = '"+linea+"' AND Origen = '"+origen+"' AND Destino = '"+destino+"'";
         ResultSet resultado = consulta.executeQuery(instruccion);
         if( resultado.next() )
         {
             instruccion = "UPDATE Avion SET Vendido = "
                + 
                (Integer.parseInt(resultado.getString("Vendido")) + 1)
                            +" WHERE Linea = '"+linea+"' AND Origen = '"+origen+"' AND Destino = '"+destino+"'";
                            consulta.executeUpdate(instruccion);
         }
         os.println("<P>Reserva "+ cantidad +" boletos de Avion por "+linea);
         i++;
      } 
      i = 1;
      while( (param=entrada.getParameter("cd"+i)) != null )
      {
        System.out.println(" ci="+i);
      int cantidad = Integer.parseInt(param);
      
         String elegidoA = entrada.getParameter("cc"+i);
         if( elegidoA == null ) // MARCO EL CHECKBOX ?
         {
             i++;
             continue;
         }
         String linea = entrada.getParameter("cl"+i);
        
         String instruccion = "SELECT * FROM Camion WHERE Linea = '"+linea+"' AND Origen = '"+origen+"' AND Destino = '"+destino+"'";
         ResultSet resultado = consulta.executeQuery(instruccion);
         if( resultado.next() )
         {
             instruccion = "UPDATE Camion SET Vendido = "
                + 
                (Integer.parseInt(resultado.getString("Vendido")) + 1)
                            +" WHERE Linea = '"+linea+"' AND Origen = '"+origen+"' AND Destino = '"+destino+"'";
                            consulta.executeUpdate(instruccion);
         } 
         os.println("<P>Reserva "+ cantidad +" boletos de Camión por "+linea);
         i++;
      }
      os.println("</body>");
      os.println("</html>");
   }catch(SQLException exs){
    System.out.println("Hay una falla en la BD");
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

