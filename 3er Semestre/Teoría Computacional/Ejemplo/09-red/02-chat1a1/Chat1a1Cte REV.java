import java.net.*;
import java.io.*;

class Chat1a1Cte
{
  public static void main(String args[])
  {
  Socket conexion = null;
  DataInputStream is ;
  PrintStream os ;
  String entra ;
  StringBuffer cadena;
  char ch;

    try
    {
       // conexion = new Socket("148.204.45.6" ,7777);
        conexion = new Socket("localhost" ,7777);
        is = new DataInputStream(conexion.getInputStream());
        os = new PrintStream(conexion.getOutputStream());
        for( int i = 0; i < 5; i++ )
        {
            System.out.print(">");
            cadena = new StringBuffer(50);
            while( (ch = (char)System.in.read()) != '\n' )
                cadena.append(ch);
            String salida = cadena.toString().substring(0,cadena.length() - 1);
            os.println(salida);
            os.flush();
            entra = is.readLine();
            System.out.println(" " + entra);
        }
        conexion.close();
    }
    catch(IOException  e)
    { }
  }
}