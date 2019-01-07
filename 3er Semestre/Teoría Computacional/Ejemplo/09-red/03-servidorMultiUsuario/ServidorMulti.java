import java.net.*;
import java.io.*;

class Atencion extends Thread
{
DataInputStream is ;
PrintStream os ;
int turno ;

    Atencion(Socket conexion ,int _turno)
    {
        turno = _turno;
        try
        {
            is = new DataInputStream(conexion.getInputStream());
            os = new PrintStream(conexion.getOutputStream());
            System.out.println("\nInicia " + turno + " @ " + conexion.toString());
        }
        catch(IOException e)
        {}
    }
    public void run()
    {
    String dato ;

        try {
        while( (dato = is.readLine()) != null )
        {
            sleep(300);
            os.println("siguiente");
            System.out.print("" + turno + " ");
        }
        }
        catch(Exception e)
        {}
        System.out.println("\nTermina " + turno);
    }
}

class ServidorMulti
{
  public static void main(String args[])
  {
  ServerSocket servicio = null;
  boolean escuchando = true;
  int turno = 1;

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
                  new Atencion(conexion ,turno).start();
                  turno++;
              }
              catch(IOException e)
              {
                  continue;
              }
          }
      }
      catch(IOException  excepcion)
      {  }
  }
}