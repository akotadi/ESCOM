// Septiembre 2002
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.lang.Math;
import java.applet.Applet;

public class esfera extends java.applet.Applet
{
// AREA DE GRAFICACION DE LA PANTALLA
int xPantMax ,yPantMax ,xPantMin ,yPantMin ;

// AREA DE GRAFICACION DEL MUNDO REAL
double xRealMax ,yRealMax ,xRealMin ,yRealMin ;

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

  void esfera_espiral( float RADIO, float centroX, float centroY,
    float centroZ, int num_p, int num_c, Color color, double Xotro, float Yotro, float Rotro ,
   Graphics g)
  {
  float alfa, teta, cX, cY, cZ, n;
  float inc_alfa, inc_teta, radio;
  float distancia;

  xRealMin = -200;
  yRealMin = -200;
  xRealMax = 200;
  yRealMax = 200;

g.setColor(color);
    inc_alfa = (float)(2.0*Math.PI * num_c / num_p);
    inc_teta = (float)(Math.PI / num_p);
    for( alfa = 0, teta = 0, n = 0; n < num_p; n++, alfa += inc_alfa ,teta += inc_teta )
    {
      radio = (float)(RADIO * Math.sin( teta ));
      cY = (float)(RADIO * Math.cos( teta )+centroY);
      cX = (float)(radio * Math.cos( alfa )+centroX);
      cZ = (float)(radio * Math.sin( alfa )+centroZ);
      if( cZ < centroZ )
        continue;

      // REVISA LA SITUACION CON RESPECTO AL OTRO CIRCULO
      distancia = (float)(Math.sqrt( Math.pow( (double)(Xotro - cX), 2.0d ) + Math.pow( (double)(Yotro - cY), 2.0f ) ));
      if( distancia < Rotro )
        continue;
      g.drawLine( mapeaX( cX ), mapeaY( cY ), mapeaX( cX ), mapeaY( cY ) );

    }

    // DIBUJO DEL CIRCULO QUE RODEA A LA ESFERA

    inc_alfa = (float)(2.0 * Math.PI / 1000.0);
    for( alfa = 0; alfa < (2.0 * Math.PI); alfa += inc_alfa )
    {
        cX = (float)(RADIO * Math.sin( alfa )+centroX);
        cY = (float)(RADIO * Math.cos( alfa )+centroY);
        distancia = (float)(Math.sqrt( Math.pow( (double)(Xotro - cX), 2.0d ) + Math.pow( (double)(Yotro - cY), 2.0d ) ));
        if( distancia < Rotro )
            continue;
        g.drawLine( mapeaX( cX ), mapeaY( cY ), mapeaX( cX ), mapeaY( cY ) );
    }

  }



  public void paint(Graphics g)
  {
   double x ,y ,incr ;

/*
		xPantMin=Integer.parseInt(getParameter("xMinPant"));
		xPantMax=Integer.parseInt(getParameter("xMaxPant"));
		yPantMin=Integer.parseInt(getParameter("yMinPant"));
		yPantMax=Integer.parseInt(getParameter("yMaxPant"));
  */


                    xPantMin = 0;
                    yPantMin = 0;
                    xPantMax = 800;
                    yPantMax = 600;

                    xRealMin = -2.30;
                    yRealMin = -2;
                    xRealMax = 2.30;
                    yRealMax = 2;

                    g.setColor(Color.white);
                    g.fillRect(0,0,800,600);

                    g.setColor(new Color(0,0,0));
               g.drawLine( mapeaX( 0 ), mapeaY( yRealMin ), mapeaX( 0 ), mapeaY(yRealMax ) );
                    incr = (xRealMax - xRealMin) / (xPantMax - xPantMin);
                    for( x = xRealMin; x < xRealMax; x += incr )
                    {
                        // FUNCION GRAFICABLE
                        g.setColor(Color.blue);
                        y = Math.cos(x);
                         g.drawLine( mapeaX( x ), mapeaY( y ), mapeaX( x ), mapeaY( y ) );

                        g.setColor(Color.blue);
                        y = Math.sin(x);
                   g.drawLine( mapeaX( x ), mapeaY( y ), mapeaX( x ), mapeaY( y ) );
                    }

                      esfera_espiral( 30, -20, 0, 0, 1024, 8, Color.red, 300, 300, 0 ,g);
                      esfera_espiral( 60, 50, 20, 10, 1024, 10, Color.green, -20, 0, 30 ,g );
//        esfera_espiral( 100, 0, 0, 0, 4096, 35, LIGHTGREEN, 300, 300, 0 );

                    }
                  }


