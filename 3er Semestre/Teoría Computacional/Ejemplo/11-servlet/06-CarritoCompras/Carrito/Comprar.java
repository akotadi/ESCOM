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
String ubicacion = "jdbc:odbc:carrito";
String usuario = null;
String contrasena = null;
Connection conexion;
String user;
HttpSession sesion ;


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

         
      sesion = entrada.getSession();
      user = (String)sesion.getValue("usuario");
      
      if( user == null )
      {
         os.println("<html><head><title>Carrito de Servicio</title></head>");
         os.println("<BODY bgcolor=\"#FFFFFF\">");
         os.println("<P>Debe darse de alta en el Sistema por favor");
        
        os.println("</BODY></html>");
         
        return;
      }
      // EFECTUA LA CONEXION CON LA BASE DE DATOS
      try
      {

       conexion = DriverManager.getConnection(ubicacion ,usuario ,contrasena);
       Statement consulta = conexion.createStatement();
       String instruccion = "SELECT * FROM Articulo";   
       ResultSet resultado = consulta.executeQuery(instruccion);
       
         os.println("<html><head><title>Carrito de Servicio</title></head>");
         os.println("<BODY bgcolor=\"#FFFFFF\">");
         os.println("<center>Hola " + user+"</center><BR><BR>");
         os.println("<FORM ACTION=\"http://localhost:8080/examples/servlet/Guarda\" METHOD=POST>");
          
          os.println("<center><TABLE BORDER=1>");
             os.println("<TR>");
             os.println("<TD>Comprar</TD>");
             os.println("<TD>Cantidad</TD>");
             os.println("<TD>Clave</TD>");
             os.println("<TD>Descripción</TD>");
             os.println("</TR>");
             
         while( resultado.next() )
         {
          String articulo = resultado.getString("articulo");
          String descripcion = resultado.getString("descripcion");
        
              os.println("<TR>");
              os.println("<TD><INPUT TYPE=Checkbox NAME=c></TD>"); 
              os.println("<TD><INPUT TYPE=TEXT NAME=f SIZE=3 VALUE=1></TD>"); 
              os.println("<TD>" + articulo + "</TD>");
              os.println("<TD>" + descripcion + "</TD>");
              os.println("</TR>");
              
           }
             
             os.println("</TABLE></center>");
             os.println("<INPUT TYPE=submit VALUE=\"Comprar\"></TD>"); 
             os.println("</FORM>"); 
             os.println("</body>");
             os.println("</html>");
        
     }
      catch(SQLException esql){
        System.out.println("ERROR GRAVE EN LA BD");
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

