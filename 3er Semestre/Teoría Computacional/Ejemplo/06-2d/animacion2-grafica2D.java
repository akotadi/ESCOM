import java.awt.*;
import java.lang.Math;
import java.applet.Applet;
import java.lang.*;

public class animacion2 extends Applet implements Runnable
{

// VARIABLES PARA EL DOBLE BUFFER
Image buffer2 ;
Graphics g2 ;
int xAnt ,yAnt ,tallaAnt ;

// AREA DE GRAFICACION DE LA PANTALLA
int xPantMax ,yPantMax ,xPantMin ,yPantMin ;

// AREA DE GRAFICACION DEL MUNDO REAL
double xRealMax ,yRealMax ,xRealMin ,yRealMin ;
double x ,y ,incr ;
Thread hilo ;
Image imagen ;
double talla ;

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

    public void init()
    {

      xPantMin = 0;
      yPantMin = 0;
      xPantMax = 800;
      yPantMax = 600;

      xRealMin = -6.28;
      yRealMin = -2;
      xRealMax = 6.28;
      yRealMax = 2;

      incr = (xRealMax - xRealMin) / (xPantMax - xPantMin);
      x = xRealMin;
      talla = 5;

        imagen = getImage(getCodeBase(), "tulum.jpg");
        setBackground(Color.white);
    Dimension dimActual = size();
        buffer2 = createImage(dimActual.width ,dimActual.height);
        g2 = buffer2.getGraphics();
        tallaAnt = -1;
    }
    public void start()
    {
        if( hilo == null )
        {
            hilo = new Thread(this);
            hilo.start();
        }
    }
    public void stop()
    {
        if( hilo != null )
        {
            hilo.stop();
            hilo = null;
        }
    }


    public void run()
    {
        while( x < (xRealMax - incr * 250) )
        {
            repaint();
            x += incr;
            talla += 0.2;
            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e)
            {  }
        }
    }

    public void update(Graphics g)
    {

        // FUNCION GRAFICABLE

        g2.setColor(Color.red);
        y = 2*Math.sin(x)*Math.cos(x);
        g2.drawLine(mapeaX(x),mapeaY(y),mapeaX(x),mapeaY(y));

        g2.setColor(Color.blue);
        y = Math.cos(x);

        if( tallaAnt != -1)
        {
            g2.setColor(Color.white);
            g2.fillRect(xAnt - 2 ,yAnt ,tallaAnt + 2 ,tallaAnt + 2);
        }
        g2.drawImage(imagen, mapeaX(x), mapeaY(y), (int)talla, (int)talla, this);
        xAnt = mapeaX(x);
        yAnt = mapeaY(y);
        tallaAnt = (int)talla;



        paint(g);
    }

  public void paint(Graphics g)
  {
    if( buffer2 != null )
    {
        g.drawImage(buffer2 ,0 ,0 ,null);
    }
  }
}
