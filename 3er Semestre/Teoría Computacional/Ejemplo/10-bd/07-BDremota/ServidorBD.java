import java.net.*;
import java.io.*;
import java.sql.*;

class ServidorBD
{
public static void main(String args[])
{
ServerSocket sServ = null;
boolean escuchando = true;
// DETERMINA LOS COMPONENTES QUE SERVIRAN PARA REFERENCIAR LA BD
  String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
  String url = "jdbc:odbc:datos";
  String usuario = null;
  String contrasena = null;

    try
    {
        sServ = new ServerSocket(7777);
        while( escuchando )
        {
        Socket sCliente = null;

            System.out.print("Escucho...");
            try
            {
                sCliente = sServ.accept();
            }
            catch(IOException e)
            {
                continue;
            }
            if( sCliente != null )
            {
            DataInputStream is ;
            PrintStream os ;
            String entra ,sale ;

            try
            {
               is = new DataInputStream(sCliente.getInputStream());
               os = new PrintStream(sCliente.getOutputStream());
               
               System.out.println("conectado");
               String rfc = is.readLine();
               System.out.println("el rfc es :" + rfc);
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
                       System.out.println("No se encuentra el driver: " + e.getMessage());
                   }
               }

               try
               {
                // EFECTUA LA CONEXION CON LA BASE DE DATOS
                Connection con = DriverManager.getConnection(url ,usuario ,contrasena);
                DatabaseMetaData dbmd = con.getMetaData();

                // CREA UNA CONSULTA TEMPORAL
                Statement stmnt = con.createStatement();

                // REALIZA LA CONSULTA
                String strSQL= "SELECT * FROM Persona WHERE Rfc='" + rfc +  "' ";
                ResultSet rspersona = stmnt.executeQuery(strSQL);
                ResultSetMetaData rsmetapersona = rspersona.getMetaData();

                // DESPLEGA UN RENGLON DE DATOS
                rspersona.next();
                String nombre = rspersona.getString("Nombre");
                String direccion = rspersona.getString("Direccion");
                String telefono = rspersona.getString("Telefono");
                os.println(nombre);
                os.println(direccion);
                os.println(telefono);

                //CIERRA LA CONEXION CON LA BASE DE DATOS
                stmnt.close();
                con.close();

               } // try efectua conexion
               catch(SQLException se) // try de conexion
               { }
           } 
           catch(IOException  e)
           { }
        }
        try
        {
            sCliente.close();
        }
        catch(IOException  e)
        {  }
        }
    }
    catch(IOException  e)
    {  }
  }
}