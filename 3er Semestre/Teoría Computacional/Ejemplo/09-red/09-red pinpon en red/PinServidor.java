import java.net.*;
import java.io.*;

class PinServidor
{
  public static void main(String args[])
  {
  ServerSocket servicio = null;
  boolean escuchando = true;
  DataInputStream isAzul ,isRojo ;
  PrintStream osAzul ,osRojo ;
  String numeroA ,numeroB ;
  int puntosAzul ,puntosRojo ;
  boolean jugando = true;
  int tira ;

      try
      {
          servicio = new ServerSocket(7777);
          Socket jugadorAzul ,jugadorRojo ;
          System.out.println("Espero jugadores...");
          try
          {
              jugadorAzul = servicio.accept();
              System.out.println("...azul");
              jugadorRojo = servicio.accept();
              System.out.println("...rojo");
              isAzul = new DataInputStream(jugadorAzul.getInputStream());
              osAzul = new PrintStream(jugadorAzul.getOutputStream());
              isRojo = new DataInputStream(jugadorRojo.getInputStream());
              osRojo = new PrintStream(jugadorRojo.getOutputStream());
              osAzul.println("0");
              osAzul.flush();
              osRojo.println("1");
              osRojo.flush();
              puntosAzul = 0;
              puntosRojo = 0;
              tira = 0;
              while( jugando )
              {    
                  if( tira == 0 )
                  {
                  String yA = isAzul.readLine();
                  String yBolaFin = isAzul.readLine();
                  osRojo.println(yA);
                  osRojo.println(yBolaFin);	
                  osRojo.flush();
                  String sResultado = isRojo.readLine();
                  int resultado = Integer.parseInt(sResultado);
                  osAzul.println(""+resultado);
                  if( resultado == 1 )
                    tira = 1;
                  else
                  {
                  	  puntosAzul++;
                  	  if( puntosAzul >= 3 )
                  	      jugando = false;
                  }
                  }
                  if( tira == 1 )
                  {
                  String yB = isRojo.readLine();
                  String yBolaFin = isRojo.readLine();
                  osAzul.println(yB);
                  osAzul.println(yBolaFin);	
                  String sResultado = isAzul.readLine();
                   int resultado = Integer.parseInt(sResultado);
                     osRojo.println(""+resultado);
                  if( resultado == 1 )
                    tira = 0;
                  else
                  {
                  	puntosRojo++;
                  	if( puntosRojo >= 3 )
                  	    jugando = false;
                  }
                }
              }
              jugadorAzul.close();
              jugadorRojo.close();     
          }
          catch(IOException  excepcion)
          {  }       
      }
      catch(IOException  excepcion)
      {  }       
  }
}