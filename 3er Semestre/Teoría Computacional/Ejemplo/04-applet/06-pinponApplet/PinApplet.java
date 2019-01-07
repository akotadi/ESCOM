/* PinApplet.java

    JUEGO DE PINPON ENTRE EL TECLADO (AZUL) Y EL RATON (ROJO) APPLET
    El area de juego es (25,15) a (485,375) con direccion 0=azul 1=rojo

2003 08 Jesus Olivares
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class PinApplet extends JApplet implements Runnable
{
Thread hilo ;
boolean jugando = true ;
int jugador ,direccion ; // INDICADORES
boolean hayBola ;
int xA = 5 ,yA = 200 ,puntosA = 0 // JUGADOR AZUL
   ,xB = 485 ,yB = 200 ,puntosB = 0 // JUGADOR ROJO
   ,xBola ,xBolaAnt = 0 ,yBolaAnt = 0 ,xIncr ,yBolaFin; // BOLA
double yBola ,yIncr ;
double duracion = 3;

  public void run()
  {
      while( jugando )
      {
      int d ;

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
  KeyListener eventoTeclado = new KeyListener() 
  {
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
    public void keyPressed(KeyEvent e) 
    {
        if( e.getKeyCode() == KeyEvent.VK_SPACE )
        {
            if( hayBola == false && jugador == 0)
            {
                yBola = yA;
                xBola = 16;
                yBolaFin = (int)Math.floor(Math.random() * 350) + 25;
                yIncr = (yBolaFin - yBola) / (485 - 15);
                xIncr = 1;
                hayBola = true;
                direccion = 0;
                duracion = 3;
              }
        }
        if( e.getKeyCode() == KeyEvent.VK_UP ) // MUEVE HACIA ARRIBA JUGADOR AZUL
        {
        Graphics g = getGraphics();
        
            if( yA >= 30 )
            {
                g.setColor(Color.white);
                g.fillRect(xA ,yA ,10 ,30);
                yA-= 10;
                g.setColor(Color.blue);
                g.fillRect(xA ,yA ,10 ,30);
            }
        }
        if( e.getKeyCode() == KeyEvent.VK_DOWN ) // MUEVE HACIA ABAJO JUGADOR AZUL
        {
        Graphics g = getGraphics();

            if( yA <= 340 )
            {
                g.setColor(Color.white);
                g.fillRect(xA ,yA ,10 ,30);
                yA += 10;
                g.setColor(Color.blue);
                g.fillRect(xA ,yA ,10 ,30);
            }
        }
    }
};

  MouseListener eventoRaton = new MouseListener() 
  {
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mousePressed(MouseEvent e) 
    {
        if( hayBola == false && jugador == 1)
        {
            yBola = yB;
            xBola = 464;
            yBolaFin = (int)Math.floor(Math.random() * 350) + 25;
            yIncr = (yBolaFin - yBola) / (485 - 15);
            xIncr = -1;
            hayBola = true;
            direccion = 1;
            duracion = 3;
          }
    }
  };
  MouseMotionListener eventoRatonMueve = new MouseMotionListener() 
  {
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) 
    {
    Graphics g = getGraphics();
    int yRaton = e.getY();

        if( (yRaton >= 25) && (yRaton <= 345) )
        {
            g.setColor(Color.white);
            g.fillRect(xB ,yB ,10 ,30);
            yB = yRaton;       
            g.setColor(Color.red);
            g.fillRect(xB ,yB ,10 ,30);
        }
    }
  };

  public void init()
  {
      // ADICIONA LOS MANEJADORES DE EVENTOS EN LA PANTALLA     
      addKeyListener(eventoTeclado);
      addMouseListener(eventoRaton);
      addMouseMotionListener(eventoRatonMueve);

      // CREA EL Thread PARA EL CONTROL DEL JUEGO 
      if( hilo == null )
      {
          hilo = new Thread(this);
          hilo.start();
      }

      // ASIGNA EL COLOR DEL FONDO DE LA CANCHA 
      setBackground(Color.white);

      // INICIALIZA LAS VARIABLES DEL JUEGO
      jugador = 0; // INICIA AZUL
      direccion = -1;
      hayBola = false;
  }

  public void paint(Graphics g)
  {
  int _yBola ;
  boolean marcador = false;

      _yBola = (int)yBola;

      // INICIALIZA EL JUEGO
      if( direccion == -1 ) // ESTA INICIANDO EL JUEGO 
      {
          direccion =  0; // INICIA JUGADOR AZUL
          g.setColor(Color.blue);
          g.fillRect(xA ,yA ,10 ,30);
          g.setColor(Color.red);
          g.fillRect(xB ,yB ,10 ,30);
      }
      // SI hayBola AQUI SE MUEVE LA BOLA
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
      // VERIFICA SI HAY PUNTO PARA AZUL O REBOTA
      if( hayBola && (direccion == 0) )
      {
          if( xBola >= 464 )
          {
              _yBola = (int)yBola;
              if( (yBola >= (yB - 19)) && (yBola <= (yB + 29)) )
              {
                  // REBOTE    
                  xBola = 464;
                  yBolaFin = (int)Math.floor(Math.random() * 350) + 25;
                  yIncr = (yBolaFin - yBola) / (485 - 15);
                  xIncr = -1;
                  direccion = 1;
                  try
                  {
                      play(getCodeBase(), "tiro.wav");
                  }
                  catch(Exception e)
                  {  }
              }
              else
              {
                  puntosA++;
                  marcador = true;
                  direccion = 0;
                  jugador = 0;
                  hayBola = false;
              }
          }
      }
      // VERIFICA SI HAY PUNTO PARA ROJO
      if( hayBola && (direccion == 1) )
      {
          if( xBola <= 16 )
          {
              _yBola = (int)yBola;
              if( (_yBola >= (yA - 19)) && (_yBola <= (yA + 29)) )
              {
                  // REBOTE    
                  xBola = 16;
                  yBolaFin = (int)Math.floor(Math.random() * 350) + 25;
                  yIncr = (yBolaFin - yBola) / (485 - 15);
                  xIncr = 1;
                  direccion = 0;
                  try
                  {
                      play(getCodeBase(), "tiro.wav");
                  }
                  catch(Exception e)
                  { }
              }
              else
              {
                  puntosB++;
                  marcador = true;
                  direccion = 1;
                  jugador = 1;
                  hayBola = false;
              }
          }
      }
      if( marcador )
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
  }
}