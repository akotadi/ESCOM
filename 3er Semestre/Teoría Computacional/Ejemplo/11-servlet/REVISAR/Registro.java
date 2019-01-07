import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.sql.*;

public class Registro extends HttpServlet
{
String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
String url = "jdbc:odbc:gestion";
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
  public void service(HttpServletRequest  peticion, HttpServletResponse respuesta)
    throws ServletException, IOException
  {
      // ESTABLECE EL TIPO DE SALIDA
      respuesta.setContentType("text/html");

      // CREA EL FLUJO DE SALIDA HACIA EL CLIENTE
      ServletOutputStream out = respuesta.getOutputStream();

      // LECTURA DEL PARAMETRO Registro
      String oficina = peticion.getParameter("OFICINA");
      String numero = peticion.getParameter("NUMERO");
      String year = peticion.getParameter("YEAR");
      String nombre = peticion.getParameter("NOMBRE");
      String direccion = peticion.getParameter("DIRECCION");
      String telefono = peticion.getParameter("TELEFONO");
	  String status = peticion.getParameter("STATUS");
	 
	 out.println("oficina="+oficina);
	 out.println("numero="+numero);
	 out.println("año="+year);
	 out.println("nombre="+nombre);
	 out.println("direccion="+direccion);
	 out.println("telefono="+telefono);
	 out.println("status="+status);
	 
      // EFECTUA LA CONEXION CON LA BASE DE DATOS
      try
      {
          conexion = DriverManager.getConnection(url ,usuario ,contrasena);
      DatabaseMetaData dbmd = conexion.getMetaData();
      Statement mandato = conexion.createStatement();

          // ENCABEZADO DE LA SALIDA
          out.println("<html><head><title>Registro de un Documento en la BD</title></head><BODY>");
          out.println("<hr>");
      String SQL;
          SQL = "INSERT INTO Documento (oficina,numero,year,nombre,direccion,telefono,status) VALUES ('"+oficina+"','"+numero+"','"+year+"','"+nombre+"','"+direccion+"','"+telefono+"','"+status+"')";
                                                                                                     
          // EJECUTA LA INSTRUCCION SQL
          mandato.executeUpdate(SQL);
         out.println("YA PASE");
          mandato.close();
          conexion.close();
           out.println("Alta Efectuada");
      }
      catch(SQLException e)
      {
        out.print("**ERROR en la alta de datos");
      }
      
      out.println("<hr></body></html>");
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

