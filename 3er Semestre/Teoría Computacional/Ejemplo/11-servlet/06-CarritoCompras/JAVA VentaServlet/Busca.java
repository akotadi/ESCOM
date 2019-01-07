import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;

public class Busca extends HttpServlet
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
     
      // LECTURA DE PARAMETROS DE LA PAGINA WEB
      destino = entrada.getParameter("destino");
      origen = entrada.getParameter("origen");
      
      // CREA EL FLUJO DE SALIDA HACIA EL CLIENTE
      ServletOutputStream os = salida.getOutputStream();

      // EFECTUA LA CONEXION CON LA BASE DE DATOS
      try
      {
          conexion = DriverManager.getConnection(ubicacion ,usuario ,contrasena);
      Statement consulta = conexion.createStatement();

       os.println("<html><head><title>Compra de Boletos</title></head><BODY bgcolor=\"#FFFFFF\">");
   
       os.println("<h3>SELECCIONE SU LINEA DE PREFERENCIA</h3>");
       os.println("<FORM ACTION=\"http://localhost:8080/examples/servlet/Comprar\" METHOD=POST>");
       os.println("<font face=Arial SIZE=4>Transportes de "+origen+" A "+destino+"</font>");
       os.println("<BR>");
       
       os.println("<INPUT TYPE=HIDDEN NAME=origen VALUE=\""+origen+ "\"> ");
       os.println("<INPUT TYPE=HIDDEN NAME=destino VALUE=\""+destino+ "\"> ");
       
       os.println("<TABLE BORDER=1>");
       os.println("<TR>");
       os.println("   <TD><font face=Arial SIZE=2 COLOR=BLUE>CANTIDAD </font></TD>");
       os.println("   <TD><font face=Arial SIZE=2 COLOR=BLUE>COMPRAR </font></TD>");
       os.println("   <TD><font face=Arial SIZE=2 COLOR=BLUE>LINEA </font></TD>");
       os.println("   <TD><font face=Arial SIZE=2 COLOR=GREEN>COSTO </font></TD>");
       os.println("   <TD><font face=Arial SIZE=2 COLOR=BLUE>DISPONIBILIDAD </font></TD>");
       os.println("   <TD><font face=Arial SIZE=2 COLOR=BLUE>TIPO </font></TD>");
       os.println("</TR>");
       
       String instruccion = "SELECT * FROM Avion WHERE Origen ='" + origen + "' AND Destino = '" + destino + "'";   
                        
       ResultSet resultado = consulta.executeQuery(instruccion);
       
       int cuenta = 1;
       // ENVIA LOS AVIONES RESPUESTA 
       while( resultado.next() )
       {  
       
       os.println("<TR>");
       os.println("   <TD> <INPUT TYPE=TEXT VALUE=1 NAME=ad"+cuenta+" SIZE=3 > </TD>");
       os.println("   <TD> <CENTER> <INPUT TYPE=CHECKBOX NAME=ac"+cuenta+"> </CENTER> </TD>");
      String linea = resultado.getString("Linea");
       os.println("   <TD> " + linea + 
       " <INPUT TYPE=HIDDEN NAME=al"+cuenta+ " VALUE=\""+ linea +"\"></TD>");
       
    
  NumberFormat precision2 = NumberFormat.getInstance();
        precision2.setMaximumFractionDigits(2);
        precision2.setMinimumFractionDigits(2);
        String costoSinFormato = resultado.getString ("Costo");
  String costoFormateado = precision2.format( 
           Double.parseDouble(costoSinFormato) );
       
       os.println("   <TD> $"+ costoFormateado +"</TD>");
       
       String cupo = resultado.getString ("Cupo");
       String vendido = resultado.getString ("Vendido");
       int disponible =  ( Integer.parseInt(cupo) - Integer.parseInt(vendido) ); 
            os.println("   <TD> <CENTER>"+ (disponible > 0 ? (""+disponible) : "AGOTADO") +"</CENTER> </TD>");
            os.println("   <TD> Avión </TD>");  
            os.println("</TR>");
            cuenta++;
       }


       instruccion = "SELECT * FROM Camion WHERE Origen ='" + origen + "' AND Destino = '" + destino + "'";   
                        
       resultado = consulta.executeQuery(instruccion);
       
       cuenta = 1;
       // ENVIA LOS CAMIONES RESPUESTA 
       while( resultado.next() )
       {  
       
       os.println("<TR>");
       os.println("   <TD> <INPUT TYPE=TEXT VALUE=1 NAME=cd"+cuenta+" SIZE=3 > </TD>");
       os.println("   <TD> <CENTER> <INPUT TYPE=CHECKBOX NAME=cc"+cuenta+"> </CENTER> </TD>");
        String linea = resultado.getString("Linea");
       os.println("   <TD> " + linea + 
       " <INPUT TYPE=HIDDEN NAME=cl"+cuenta+ " VALUE=\""+ linea +"\"> </TD>");
       
       NumberFormat precision2 = NumberFormat.getInstance();
        precision2.setMaximumFractionDigits(2);
        precision2.setMinimumFractionDigits(2);
        String costoSinFormato = resultado.getString ("Costo");
  String costoFormateado = precision2.format( 
           Double.parseDouble(costoSinFormato) );
       
       os.println("   <TD> $"+ costoFormateado +"</TD>");
       
      
       String cupo = resultado.getString ("Cupo");
       String vendido = resultado.getString ("Vendido");
       int disponible =  ( Integer.parseInt(cupo) - Integer.parseInt(vendido) ); 
            os.println("   <TD> <CENTER>"+ (disponible > 0 ? (""+disponible) : "AGOTADO") +"</CENTER> </TD>");
            os.println("   <TD> Camión </TD>");  
            os.println("</TR>");
            cuenta++;
       }
      os.println("</TABLE>");
      os.println("<BR>");
      os.println("<INPUT TYPE=Submit VALUE=\"Comprar\">");
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

