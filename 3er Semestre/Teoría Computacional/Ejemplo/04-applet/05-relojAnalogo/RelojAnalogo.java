/* RelojAnalogo.java

    DESPLIEGUE DE UN RELOJ ANALOGICO
    
2003 08 Jesus Olivares
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.text.DateFormat;

public class RelojAnalogo extends JApplet implements Runnable
{
Thread hilo ;

// AREA DE GRAFICACION DE LA PANTALLA
int xPantMax ,yPantMax ,xPantMin ,yPantMin ;

// AREA DE GRAFICACION DEL MUNDO REAL
double xRealMax ,yRealMax ,xRealMin ,yRealMin ;

Date fechaHora ;
double horario = 40 ,minutero = 60 ,segundero = 80 ;// AGUJAS
double aHora ,aMinuto ,aSegundo ; // ANGULOS DE LAS AGUJAS
boolean inicio ;

  public void init()
  {
      // INICIALIZA LAS VARIABLES DE LAS COORDENADAS
      xPantMin = 0;
      yPantMin = 0;
      xPantMax = Integer.parseInt(getParameter("XPantMax"));
      yPantMax = Integer.parseInt(getParameter("YPantMax"));
      xRealMin = - segundero * 1.5;
      yRealMin = - segundero * 1.5;
      xRealMax = segundero * 1.5;
      yRealMax = segundero * 1.5;
      inicio = true;
      
      // ASIGNA LA LONGITUD DE LAS AGUJAS
      horario = Integer.parseInt(getParameter("Horas"));
      minutero = Integer.parseInt(getParameter("Minutero"));
      segundero = Integer.parseInt(getParameter("Segundero"));
    
      // CREA EL Thread PARA EL CONTROL DE LA ITERACION
      if( hilo == null )
      {
          hilo = new Thread(this);
          hilo.start();
      }

      // ASIGNA EL COLOR DEL FONDO DE LA CANCHA 
      setBackground(Color.white);
  }

  // MAPEA LAS COORDENADAS X REALES A PANTALLA PASANDO POR NORMALIZADAS 
  int mapeaX( double x_real)
  {
  double x_normal ;

    x_normal = ( x_real - xRealMin ) / ( xRealMax - xRealMin );
    return xPantMin + (int)(x_normal * ( xPantMax - xPantMin ));
  }

  //  MAPEA LAS COORDENADAS Y REALES A PANTALLA PASANDO POR NORMALIZADAS
  int mapeaY( double y_real )
  {
  double y_normal;
  int y_pantalla ;

    y_normal = ( y_real - yRealMin ) / ( yRealMax - yRealMin );
    y_pantalla = yPantMin + (int)(y_normal * ( yPantMax - yPantMin ));
    return yPantMax - y_pantalla;
  }

  public void run()
  {
      while( true )
      {
          repaint();
      }
  }

  void aguja(Graphics g ,int radio ,int _angulo ,Color color)
  {
  int x ,y ,angulo ;
   
      g.setColor(color);
      angulo = ((int)_angulo + 90) % 360; // SUMA 90 GRADOS AL VECTOR
      x = (int)(radio * Math.cos(angulo / 180.0 * Math.PI));
      y = (int)(radio * Math.sin(angulo / 180.0 * Math.PI));
      g.drawLine(mapeaX(0), mapeaY(0) ,mapeaX(x), mapeaY(y));
  }

  public void paint(Graphics g)
  {
  int hora ,minuto ,segundo ;   

      if( inicio )
      {
          // PINTA EL CIRCULO DE LA CARATULA 
          g.setColor(Color.blue);
          g.drawOval(mapeaX(-segundero), mapeaY(segundero)
            ,mapeaX(segundero) - mapeaX(-segundero) 
            ,mapeaY(-segundero) - mapeaY(segundero) );
      }
      
      // TOMA EL TIEMPO DEL RELOJ
      Calendar tiempo = Calendar.getInstance();
      Date fecha = new Date();
      hora = tiempo.get(Calendar.HOUR_OF_DAY);
      minuto = tiempo.get(Calendar.MINUTE);
      segundo = tiempo.get(Calendar.SECOND);
      DateFormat horaFormateada = DateFormat.getTimeInstance();
      g.drawString(horaFormateada.format(fecha)
        ,mapeaX(-segundero), mapeaY(-segundero * 1.2));
        
      // CONVIERTE LAS HORAS MINUTOS Y SEGUNDOS A SU ANGULO CORRESPONDIENTE
      // SE LE SUMA LA PARTE PROPORCIONAL DE SEGUNDOS Y MINUTOS A MINUTOS Y HORAS
      aSegundo = segundo / 60.0 * 360.0;
      aMinuto = minuto / 60.0 * 360.0 + segundo / 60.0;
      aHora = hora / 12.0 * 360.0 + minuto / 60.0 + segundo / 3600.0;

      // PINTA LAS AGUJAS, USA ANGULOS NEGATIVOS PORQUE ES EL GIRO CONTRARIO 
      // AL CIRCULO TRIGONOMETRICO
      aguja(g ,(int)segundero ,-(int)aSegundo ,Color.gray);
      aguja(g ,(int)minutero ,-(int)aMinuto ,Color.blue);
      aguja(g ,(int)horario ,-(int)aHora ,Color.red);

      // DETIENE LA EJECUCION DURANTE UN SEGUNDO
      try 
      {
          hilo.sleep(1000);
      }
      catch(Exception ex)
      {}

      // BORRA LAS AGUJAS
      aguja(g ,(int)segundero ,-(int)aSegundo ,Color.white);
      aguja(g ,(int)minutero ,-(int)aMinuto ,Color.white);
      aguja(g ,(int)horario ,-(int)aHora ,Color.white);

      // PINTA LA HORA
      g.drawString(horaFormateada.format(fecha)
       ,mapeaX(-segundero), mapeaY(-segundero * 1.2));
    }
}