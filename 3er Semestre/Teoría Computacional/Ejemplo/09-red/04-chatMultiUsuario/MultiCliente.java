import java.net.*;
import java.io.*;

class EscuchaCte extends Thread
{
Socket conexion ;

// FLUJO DE ENTRADA PARA ESCUCHAR
DataInputStream is ;


    public EscuchaCte(Socket _conexion)
    {
        conexion = _conexion;
        try
        {
            is = new DataInputStream(conexion.getInputStream());
        }
        catch(Exception e)
        {
        }
    }
    public void run()
    {
        // USA EL SOCKET PARA ESCUCHAR Y DESPLEGAR
        while( true )
        {
            try
            {
            String cadena = is.readLine();
                System.out.println(cadena);
            }
            catch(Exception excep)
            {
            }
        }
    }
}

class MultiCliente
{
    public static void main(String args[])
    {
    Socket conexion ;
    PrintStream os ;

        try
        {
            conexion = new Socket("localhost" ,7777);
            os = new PrintStream(conexion.getOutputStream());

            System.out.println("servidor contactado");
            new EscuchaCte(conexion).start();

            while(true)
            {
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));

                // DESPLEGA UN INDICADOR PARA LEER UN DATO
                System.out.print("> ");

                // LEE UNA cadena DE LA CONSOLA
                String cadena = consola.readLine();
                os.println(cadena);
                os.flush();
            }
            //conexion.close();
            //System.exit(0);
        }
        catch(IOException e)
        { }
    }
}