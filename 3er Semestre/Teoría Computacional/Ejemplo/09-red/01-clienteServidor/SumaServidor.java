import java.net.*;
import java.io.*;

class SumaServidor
{
  public static void main(String args[])
  {
  ServerSocket servicio = null;
  boolean escuchando = true;

      try
      {
          servicio = new ServerSocket(7777);
          while( escuchando )
          {
          Socket conexion = null;

              System.out.println("Escucho...");
              try
              {
                  conexion = servicio.accept();
              }
              catch(IOException e)
              {
                  continue;
              }
              if( conexion != null )
              { 
              DataInputStream is ;
              PrintStream os ;
              String numeroA ,numeroB ;

                  try
                  {
                      is = new DataInputStream(conexion.getInputStream());
                      os = new PrintStream(conexion.getOutputStream());
                      System.out.println("Atendiendo a " + conexion.toString());
                      numeroA = is.readLine();
                      numeroB = is.readLine();
                      os.println("" + (Integer.parseInt(numeroA) + Integer.parseInt(numeroB)));
                      os.flush();
                      conexion.close();
                  }
                  catch(IOException  e)
                  {  }
              }
          }
      }
      catch(IOException  excepcion)
      {  }       
  }
}