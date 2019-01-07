import java.net.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.Vector;


class ServidorAgencia
{

public void cargaLista()
{
ServerSocket sServ = null;
boolean escuchando = true;
// DETERMINA LOS COMPONENTES QUE SERVIRAN PARA REFERENCIAR LA BD
  String driver1 = "sun.jdbc.odbc.JdbcOdbcDriver";
  String driver = "com.ms.jdbc.odbc.JdbcOdbcDriver";
  String nombreBD = "Agencia.mdb";
  String ubicacion = 
  "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+nombreBD;
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
                 System.out.println("Si se conecto con la base\n");
                 try
                 {
                 // EFECTUA LA CONEXION CON LA BASE DE DATOS
                 // SE HACE LA CONEXION CON LA BASE DE DATOS
                 Connection conexion = DriverManager.getConnection(ubicacion,usuario ,contrasena);
                 System.out.println("Enviando los Origenes");
                 // SE CREA UN OBJETO consulta ASOCIADO A LA BASE DE DATOS

                 Statement consulta = conexion.createStatement();
                                                              
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
//                        System.out.print("*"+unOrigenAvion+" ");
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
                 
                 for(int i=0; i< ciudad.size();i++)
                 {
                 String unOrigen = (String)ciudad.get(i);
                 
                    System.out.println("#"+unOrigen);
                    os.println(unOrigen);
                    os.flush();
                 }
                                     
                 os.println("#"); // Codigo de operacion para indicar al cliente que realice cambio de arreglo
                 os.flush();
                                    
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
                 
                    System.out.println("*"+unDestino);
                    os.println(unDestino);
                    os.flush();
                 }
                                     
                 os.println("*"); // Codigo de operacion para indicar al cliente que realice cambio de arreglo
                 os.flush();
                 // *******************************
                 // CODIGO DE CRISTINA Busqueda de Aviones y Camiones                 
                                        System.out.println("conectado");
                 String origen = is.readLine(); 
                 String destino = is.readLine();
                   
                   System.out.println("RECIBI "+origen+" " +destino);
                   
                    instruccion = "SELECT * FROM Avion WHERE Origen ='" + origen + "' AND Destino = '" + destino + "'";   
                        
                    resultado = consulta.executeQuery(instruccion);
                    // ENVIA LOS AVIONES RESPUESTA 
                    while( resultado.next() )
                    {  
                        os.println( "a" );
                        os.flush();
                    String linea = resultado.getString("Linea");
                        os.println( linea );
                        os.flush();
                    String costo = resultado.getString ("Costo");
                        os.println(costo);
                        os.flush();
                    String cupo = resultado.getString ("Cupo");
                        os.println(cupo);
                        os.flush();    
                    String vendido = resultado.getString ("Vendido");
                        os.println(vendido);
                        os.flush();
                        System.out.println("ENVIO "+linea+" a "+costo );
                    }//fin de while
                        
                        // REALIZA LA CONSULTA EN LA TABLA CAMION
                    instruccion = "SELECT * FROM Camion WHERE Origen ='" + origen + "' AND Destino = '" + destino + "'";   
                    resultado = consulta.executeQuery(instruccion);
                    
                    while( resultado.next() )
                    { 
                        os.println( "c" );
                        os.flush();
                    String linea = resultado.getString("Linea");
                        os.println( linea );
                        os.flush();
                    String costo = resultado.getString ("Costo");
                        os.println(costo);
                        os.flush();
                    String cupo = resultado.getString ("Cupo");
                        os.println(cupo);
                        os.flush();    
                    String vendido = resultado.getString ("Vendido");
                        os.println(vendido);
                        os.flush();
                        System.out.println("ENVIO "+linea+" a "+costo );
                    }//fin de while
                    os.println("$");
                    os.flush();
                    // FIN DE CODIGO DE CRISTINA
                    
                    // EL USUARIO DECIDIO COMPRAR: CODIGO DE ARTURO-EDUARDO 
                    String tipo = is.readLine();
                    String linea  = is.readLine();
                      
                      if( tipo.equals("a") )
                      {
                        instruccion = 
                        "SELECT * FROM Avion WHERE Linea = '"+linea+"' AND Origen = '"+origen+"' AND Destino = '"+destino+"'";
                        resultado = consulta.executeQuery(instruccion);
                        if( resultado.next() )
                        {
                            instruccion = "UPDATE Avion SET Vendido = "
                            + 
                            (Integer.parseInt(resultado.getString("Vendido")) + 1)
                            +" WHERE Linea = '"+linea+"' AND Origen = '"+origen+"' AND Destino = '"+destino+"'";
                            consulta.executeUpdate(instruccion);
                        }
                      }
                      else
                      {
                        instruccion = 
                        "SELECT * FROM Camion WHERE Linea = '"+linea+"' AND Origen = '"+origen+"' AND Destino = '"+destino+"'";
                        resultado = consulta.executeQuery(instruccion);
                        if( resultado.next() )
                        {
                            instruccion = "UPDATE Camion SET Vendido = "
                            + 
                            (Integer.parseInt(resultado.getString("Vendido")) + 1)
                            +" WHERE Linea = '"+linea+"' AND Origen = '"+origen+"' AND Destino = '"+destino+"'";
                            consulta.executeUpdate(instruccion);
                        }
  
 
                      }
                 
                 
                            //CIERRA LA CONEXION CON LA BASE DE DATOS
                            consulta.close();
                            conexion.close();
              } // try efectua conexion
              catch(SQLException se) // try de conexion
              {
                 System.out.println("El error es:  "+se);}
              }
              catch(IOException  e)
             { }
        }
        try
        {
            sCliente.close();
        }
        catch(IOException  e)
        { 
         }
        }
    }
    catch(IOException  e)
    { 
    System.out.println("NO se puede crear el Socket del Servidor");
     }

}

   public static void main(String args[])
   {
      ServidorAgencia sa=new ServidorAgencia();
  
        sa.cargaLista();
   }
}