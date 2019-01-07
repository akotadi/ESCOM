import java.net.*;
import java.io.*;

class SumaCliente
{
 public static void main(String args[])
 {
 Socket conexion ;
 DataInputStream is ;
 PrintStream os ;
 
   try
   {
       conexion = new Socket("localhost" ,7777);
       is = new DataInputStream(conexion.getInputStream());
       os = new PrintStream(conexion.getOutputStream());
       System.out.println("servidor contactado");
       os.println("5");
       os.flush();
       os.println("8");
       os.flush();
       String entra = is.readLine();
       System.out.println("La suma de 5 y 8 es: " + entra);
       conexion.close();
       System.exit(0);
   }
   catch(IOException e)
   { }
 }
}