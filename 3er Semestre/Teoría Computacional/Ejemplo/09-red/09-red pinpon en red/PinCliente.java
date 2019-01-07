import java.net.*;
import java.io.*;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class PinCliente extends JApplet implements Runnable
{
Thread hilo ;
boolean jugando ,esperar ;

int jugador ,direccion ,soy ; // INDICADORES
boolean hayBola ;
int xA = 5 ,yA = 200 ,puntosA = 0 // JUGADOR AZUL
   ,xB = 485 ,yB = 200 ,puntosB = 0 // JUGADOR ROJO
   ,xBola ,xBolaAnt = 0 ,yBolaAnt = 0 ,xIncr ,yBolaFin; // BOLA
double yBola ,yIncr ;
double duracion = 3;
boolean iniciando ;
Socket conexion ;
DataInputStream is ;
PrintStream os ;

  public void init()
  {
       try
       {
        conexion = new Socket("localhost" ,7777); 
        is = new DataInputStream(conexion.getInputStream());
        os = new PrintStream(conexion.getOutputStream());
        System.out.println("conectado");
 
        String cual = is.readLine();
        soy = Integer.parseInt(cual);
//System.out.println(soy == 0 ? "soy azul" : "soy rojo");
        jugador = 0;
    }
    catch(Exception e)
    {}     
    // ADICIONA LOS MANEJADORES DE EVENTOS EN LA PANTALLA     
    addMouseListener(accionRaton);
    addMouseMotionListener(movimientoRaton);
    
    // ASIGNA EL COLOR DEL FONDO DE LA CANCHA 
    setBackground(Color.white);
      
    // INICIALIZA LAS VARIABLES DEL JUEGO
    direccion = 0;
    hayBola = false;
    esperar = false;
    jugando = true;
    
    // CREA EL Thread PARA EL CONTROL DEL JUEGO 
    if( hilo == null )
    {
       hilo = new Thread(this);
       hilo.start();
    }
  }
 

  public void run()
  {
      while( jugando )
      {
      int d ;
          if( esperar )
              continue;
          repaint();
          try 
          {
              d = (int)duracion;
              if( direccion == 0 )
                  hilo.sleep(1);
              else
                  hilo.sleep(2);
              hilo.sleep(d);
              duracion -= 0.0004;
              if( duracion < 0 ) duracion = 0;
          }
          catch(Exception ex)
          {}
      }
  }
  
  MouseListener accionRaton = new MouseListener() 
  {
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mousePressed(MouseEvent e) 
    {
        if( jugando == false )
            return;
        switch( soy )
        {
        case 0:
            if( hayBola == false && direccion == 0 )
            { 
                esperar = true;      
                yBola = yA;
                xBola = 16;
                os.println(""+((int)yA));
                yBolaFin = (int)Math.floor(Math.random() * 350) + 25;
                os.println(""+ ((int)yBolaFin));
                os.flush();
                yIncr = (yBolaFin - yBola) / (485 - 15);
                xIncr = 1;
                hayBola = true;
                direccion = 0;
                duracion = 3;
                esperar = false;
            }
            break;
        case 1:
            if( hayBola == false && direccion == 1 )
            {
                esperar = true;
                yBola = yB;
                xBola = 464;
                os.println(""+yB);
                yBolaFin = (int)Math.floor(Math.random() * 350) + 25;
                os.println(""+yBolaFin);
                os.flush();
                yIncr = (yBolaFin - yBola) / (485 - 15);
                xIncr = -1;
                hayBola = true;
                direccion = 1;
                duracion = 3;
                esperar = false;
             }
             break;
       }
    }
  };
  MouseMotionListener movimientoRaton = new MouseMotionListener() 
  {
      public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) 
    {
    Graphics g = getGraphics();
    int yRaton = e.getY();

        if( (yRaton >= 25) && (yRaton <= 345) )
        {
            switch( soy )
            {
            case 1:
                g.setColor(Color.white);
                g.fillRect(xB ,yB ,10 ,30);
                yB = yRaton;       
                g.setColor(Color.red);
                g.fillRect(xB ,yB ,10 ,30);
                break;
            case 0:
                g.setColor(Color.white);
                g.fillRect(xA ,yA ,10 ,30);
                yA = yRaton;
                g.setColor(Color.blue);
                g.fillRect(xA ,yA ,10 ,30);
                break;
            }            
        }
    }
  };

  void daMarcador(Graphics g ,int puntosA ,int puntosB)
  {           
     g.setColor(Color.white);
     g.fillRect(240 ,375 ,200 ,15);
     g.setColor(puntosA > puntosB ? Color.blue : Color.red);
     if( puntosA == puntosB )
         g.setColor(Color.black);
     g.drawString("Azul: "+ puntosA + "   " + "Rojo: " + puntosB,240,385);
     if( puntosA == 3 )
     {
         g.drawString("GANO AZUL" ,240 ,200);
         jugando = false;
     }
     if( puntosB == 3 )
     {
         g.drawString("GANO ROJO" ,240 ,200);
         jugando = false;
     }
  }
  public void paint(Graphics g)
  {
  int _yBola ;
  boolean marcador = false;

    _yBola = (int)yBola;
    // SI HAY BOLA ENTONCES AVANZA
    if( hayBola )
    {
        g.setColor(Color.white);
        g.fillOval(xBolaAnt ,yBolaAnt ,20,20);
        g.setColor(direccion == 1 ? Color.red : Color.blue);
        g.fillOval(xBola ,_yBola ,20,20);
        xBolaAnt = xBola;
        yBolaAnt = _yBola;
        xBola += xIncr;
        yBola += yIncr;
    }
    switch( soy )
    {
    case 0:
        // VERIFICA SI REBOTA O PIERDE
        if( hayBola && (direccion == 1) )
        {
            if( xBola <= 16 )
            {
                if( (_yBola >= (yA - 19)) && (_yBola <= (yA + 29)) )
                {
                    // REBOTE
                    os.println("1");    
                    xBola = 16;
                    yBolaFin = (int)Math.floor(Math.random() * 350) + 25;
                    yIncr = (yBolaFin - yBola) / (485 - 15);
                    xIncr = 1;
                    direccion = 0;
                    os.println(""+yA);
                    os.println(""+yBolaFin);
                }
                else // FALLO AZUL
                {
                    puntosB++;
                    daMarcador(g ,puntosA ,puntosB);
                    direccion = 1;
                    jugador = 1;
                    hayBola = false;
                    os.println("0");
                }
            }
        }
        if( hayBola && (direccion == 0) )
        {
            // CUANDO LLEGUE AL FINAL ESPERA EL RESULTADO
            if( xBola >= 464 )
            {
            int resultado = 0;
            
                try {
                String sResultado = is.readLine();
                resultado = Integer.parseInt(sResultado);
                }
                catch(IOException e){}
                if( resultado == 0 )
                {
                   // FALLO ROJO
                   hayBola = false;
                   direccion = 0;
                   puntosA++;
                   daMarcador(g ,puntosA ,puntosB);
                }
                else
                {
                  // ESPERA LAS TIRADA DE ROJO
                  esperar = true;
                  try 
                  {
                  String syBola = is.readLine();
                      yBola = Integer.parseInt(syBola);
                      xBola = 464;
                  String syBolaFin = is.readLine();
                      yBolaFin = Integer.parseInt(syBolaFin);
                      yIncr = (yBolaFin - yBola) / (485 - 15);
                      xIncr = -1;
                      hayBola = true;
                      direccion = 1;
                      jugador = 1;
                      duracion = 3;
                      // PINTA LA FICHA DE ROJO
                      g.setColor(Color.white);
                      g.drawRect(xB ,yB ,10 ,30);
                      yB = (int)yBola;       
                      g.setColor(Color.red);
                      g.drawRect(xB ,yB ,10 ,30);
                      esperar = false;
                  }catch(IOException e){}
               }
            }
        }
        if( hayBola == false && direccion == 1 )
        {
            // ESPERA LA BOLA DEL JUGADOR 1
            if( puntosA >= 3 || puntosB >= 3 )
                return;
            try 
            {
            xBola = 464;
            String syBola = is.readLine();
            yBola = Integer.parseInt(syBola);
            String syBolaFin = is.readLine();
            yBolaFin = Integer.parseInt(syBolaFin);
            yIncr = (yBolaFin - yBola) / (485 - 15);
            xIncr = -1;
            hayBola = true;
            direccion = 1;
            duracion = 3;
            // PINTA LA FICHA DE ROJO
            g.setColor(Color.white);
            g.drawRect(xB ,yB ,10 ,30);
            yB = (int)yBola;       
            g.setColor(Color.red);
            g.drawRect(xB ,yB ,10 ,30);
            }catch(IOException e){}
        }
        break;
    case 1:
        if( hayBola && direccion == 0 )
        {
            // VERIFICA SI REBOTA O PIERDE
            if( xBola >= 464 )
            {
                  if( (yBola >= (yB - 19)) && (yBola <= (yB + 29)) )
                {
                    // REBOTE
                    os.println("1");    
                    xBola = 464;
                    yBolaFin = (int)Math.floor(Math.random() * 350) + 25;
                    yIncr = (yBolaFin - yBola) / (485 - 15);
                    xIncr = -1;
                    direccion = 1;
                    os.println(""+yB);
                    os.println(""+yBolaFin);
                 }
                 else // FALLO ROJO 
                {
                    puntosA++;
                    daMarcador(g ,puntosA ,puntosB);
                    direccion = 0;
                    jugador = 0;
                    hayBola = false;
                    os.println("0");
                }
            }
        }
        if( hayBola && (direccion == 1) )
        {
            // CUANDO LLEGUE AL FINAL ESPERA EL RESULTADO
            if( xBola <=16 )
            {
            int resultado = 0;
            
                try {
                String sResultado = is.readLine();
                resultado = Integer.parseInt(sResultado);
                }
                catch(IOException e){}
                if( resultado == 0 )
                {
                   // FALLO AZUL
                   hayBola = false;
                   direccion = 1;
                   puntosB++;
                   daMarcador(g ,puntosA ,puntosB);
                }
                else
                {
                  // ESPERA LAS TIRADA DE ROJO
                  esperar = true;
                  try 
                  {
                  String syBola = is.readLine();
                      yBola = Integer.parseInt(syBola);
                      xBola = 16;
                  String syBolaFin = is.readLine();
                      yBolaFin = Integer.parseInt(syBolaFin);
                      yIncr = (yBolaFin - yBola) / (485 - 15);
                      xIncr = 1;
                      hayBola = true;
                      direccion = 0;
                      jugador = 0;
                      duracion = 3;
                      // PINTA LA FICHA DE ROJO
                      g.setColor(Color.white);
                      g.drawRect(xA ,yA ,10 ,30);
                      yA = (int)yBola;       
                      g.setColor(Color.blue);
                      g.drawRect(xA ,yA ,10 ,30);
                      esperar = false;
                  }catch(IOException e){}
               }
            }
        }
        if( hayBola == false && direccion == 0 )
        {
            // ESPERA LA BOLA DEL JUGADOR 0
            if( puntosA >= 3 || puntosB >= 3 )
                return;
            esperar = true;      
            try 
            {
            String syBola = is.readLine();
              yBola = Integer.parseInt(syBola);
              xBola = 16;
            String syBolaFin = is.readLine();
              yBolaFin = Integer.parseInt(syBolaFin);
              yIncr = (yBolaFin - yBola) / (485 - 15);
              xIncr = 1;
              hayBola = true;
              direccion = 0;
              duracion = 3;
              // PINTA LA FICHA AZUL REMOTA
              g.setColor(Color.white);
              g.drawRect(xA ,yA ,10 ,30);
              yA = (int)yBola;       
              g.setColor(Color.blue);
              g.drawRect(xA ,yA ,10 ,30);
              esperar = false;
            }
            catch(IOException e)
            {}
        }
        break;
     }
  }
}