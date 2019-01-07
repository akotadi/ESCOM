import java.net.*;
import java.io.*;

class Escucha extends Thread
{
Socket conexion ;
static Socket conectados[] = new Socket[20];
static int ultimo = 0;

// FLUJO DE ENTRADA PARA ESCUCHAR
DataInputStream is ;


    public Escucha(Socket _conexion)
    {
        conexion = _conexion;
        try
        {
            is = new DataInputStream(conexion.getInputStream());
        }
        catch(Exception e)
        {
        }

        // ADICIONA EL NUEVO SOCKET EN LA TABLA DE CONECTADOS
        conectados[ultimo++] = _conexion;
    }
    public void run()
    {
        // USA EL SOCKET PARA ESCUCHAR Y RETRANSMITIRLO A CLIENTES
        while( true )
        {
            try{
            String cadena = is.readLine();
                for( int i = 0; i < ultimo; i++ )
                {
                PrintStream os = new PrintStream(conectados[i].getOutputStream());
                os.println(cadena);
                os.flush();
                }
            }
            catch(Exception excep)
            {
            }
        }
    }
}

class MultiServidor
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
              new Escucha(conexion).start();
          }
      }
      catch(IOException  excepcion)
      {  }
  }
}