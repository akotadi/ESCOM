import java.net.*;
import java.io.*;

class ClienteMulti
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

       for( int i = 0; i < 30; i ++)
       {
           os.println(""+ i);
           os.flush();
       String entra = is.readLine();
           System.out.print("" + entra + " ");
       }
       conexion.close();
       System.exit(0);
   }
   catch(IOException e)
   { }
 }
}