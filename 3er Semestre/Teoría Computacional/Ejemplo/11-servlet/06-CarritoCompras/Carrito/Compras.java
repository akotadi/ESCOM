import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;

public class Compras extends HttpServlet
{
String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
String ubicacion = "jdbc:odbc:carrito";
String usuario = null;
String contrasena = null;
Connection conexion;
String user;
String pwd;

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
     
      // LECTURA DE PARAMETROS DE LA PAGINA WEB
      user = entrada.getParameter("usuario");
      pwd = entrada.getParameter("pwd");
      
      // CREA EL FLUJO DE SALIDA HACIA EL CLIENTE
      ServletOutputStream os = salida.getOutputStream();

      // EFECTUA LA CONEXION CON LA BASE DE DATOS
      try
      {
      
       conexion = DriverManager.getConnection(ubicacion ,usuario ,contrasena);
       Statement consulta = conexion.createStatement();
       String instruccion = "SELECT * FROM Usuario WHERE usuario='" + user + "' AND pwd = '" + pwd + "'";   
                        
       ResultSet resultado = consulta.executeQuery(instruccion);
       
         if( resultado.next() )
         {  
         HttpSession sesion ;
         
           sesion = entrada.getSession();
           sesion.putValue("usuario" ,user);
     
             os.println("<html><head><title>Carrito de Servicio</title></head>");
             os.println("<BODY bgcolor=\"#FFFFFF\">");
            os.println("<center>Hola " + user+"</center><BR><BR>");
             instruccion = "SELECT * FROM Compras WHERE usuario='" + user + "'";             
             resultado = consulta.executeQuery(instruccion);
             os.println("<center><TABLE BORDER=1>");
             os.println("<TR>");
             os.println("<TD>Clave</TD>");
             os.println("<TD>Descripción</TD>");
             os.println("<TD>Cantidad</TD>");
             os.println("</TR>");
             int nArt = 0;       
              Statement consulta2 = conexion.createStatement();  
         while( resultado.next() )
         {
              String articulo = resultado.getString("articulo");
              String cantidad = resultado.getString("cantidad");
              String descripcion = "";
       System.out.println("CREO LA INSTRUCCION="+nArt);        
      
       String instruccion2 = "SELECT descripcion FROM Articulo WHERE articulo ='" + articulo + "'";           
       ResultSet resultado2 = consulta2.executeQuery(instruccion2);
              System.out.println("HICE LA CONSULTA ="+nArt);
                if( resultado2.next() )
                {
                    descripcion = resultado2.getString("descripcion"); 
                }
                else
                    descripcion = "NO TIENE"; 
              
              os.println("<TR>");
              os.println("<TD>" + articulo + "</TD>");
              os.println("<TD>" + descripcion + "</TD>");
              os.println("<TD>" + cantidad + "</TD>");
              System.out.println("N="+nArt);
              os.println("</TR>");
              
                  nArt++;
           }
             
             os.println("</TABLE></center>");
             if( nArt == 0)
             {
                os.println("<center>NO HAY COMPRAS</center>");
             }
             os.println("<BR><BR>");
             
             os.println("<center>");
             os.println("<a href=\"http://localhost:8080/examples/servlet/Comprar\">Seguir comprando</a><BR>");
             os.println("<a href=\"http://localhost:8080/examples/servlet/Pagar\">Pagar</a>");
             os.println("<center>");
             os.println("</body>");
             os.println("</html>");
             
             //sesion.removeAttribute("usuario");
         }
         else
         {
            os.println("<html><head><title>Carrito de Servicio</title></head>");
            os.println("<BODY bgcolor=\"#FFFFFF\">");
            os.println("<center> <font color=#ff0000>Usuario Invalido, por favor reintentelo</font></center>");
            os.println("  <form action =\"http://localhost:8080/examples/servlet/Compras\" method=GET >");
            os.println("    Usuario: <input type=text name=usuario>");
            os.println("    <BR><BR>");
            os.println("    Contraseña: <input type=password name=pwd >");
            os.println("    <BR><BR>");
            os.println("    <input type=submit value=\"Enviar\">");
            os.println("    </form>");   
            os.println("</BODY>");
            os.println("</html>");
         }
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

