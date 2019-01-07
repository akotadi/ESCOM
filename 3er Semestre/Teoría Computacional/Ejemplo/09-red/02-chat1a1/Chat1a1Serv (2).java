import java.net.*;
import java.io.*;

class Chat1a1Serv
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

            System.out.println("Espero cliente...");
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
            String entra ,sale ;
            char ch ;
            StringBuffer cadena ;

                try
                {
                    is = new DataInputStream(conexion.getInputStream());
                    os = new PrintStream(conexion.getOutputStream());

                    System.out.println("atendiendo a: " + conexion.toString());
                    for( int i = 0; i < 5; i++ )
                    {
                         entra = is.readLine();
                         System.out.println(" " + entra);
                         System.out.print(">");
                         cadena = new StringBuffer(50);
                         while( (ch = (char)System.in.read()) != '\n' )
                             cadena.append(ch);
                         String salida = cadena.toString().substring(0,cadena.length() - 1);
                         os.println(salida);
                         os.flush();
                    }
                    conexion.close();
                }
                catch(IOException  e)
                {  }
            }
        }
    }
    catch(IOException  e)
    {  }       
  }
}
