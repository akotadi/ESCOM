import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.sql.*;

public class Consulta extends HttpServlet
{
String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
String url = "jdbc:odbc:datos";
String nombreBD = "e:/Investigador Actual/Beni/basedatos/datos.mdb";
String ubicacion = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+nombreBD;
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
      Statement mandato = conexion.createStatement();

          // ENCABEZADO DE LA SALIDA
          out.println("<html><head><title>Respuesta de la BD</title></head><BODY>");
          out.println("<hr><div align=center><h2>Resultado</h2><div><hr>");
      String SQL;
          SQL = new String("SELECT * FROM Persona WHERE Rfc = '" + cRfc +"' ;");
          ResultSet resultado = mandato.executeQuery(SQL);

          // RECUPERACION DE LA INFORMACION OBTENIDA
          resultado.next();
          out.println("<P>Nombre: ");
          out.println(resultado.getString("Nombre"));
          out.println("<BR>Dirección: ");
          out.println(resultado.getString("Direccion"));
          out.println("<BR>Teléfono: ");
          out.println(resultado.getString("Telefono"));
          out.print("</P>");
      }
      catch(SQLException e)
      {
        out.print("ERROR en la recuperación de datos");
      }
      out.println("<hr></body></html>");
  }
}

