import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.lang.Math;
import java.applet.Applet;
import java.awt.Image;
import java.lang.Thread;

public class animacion extends Applet implements Runnable
{

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
        while( x < (xRealMax - incr * 200) )
//        while(true)
        {
            repaint();
            x += incr;
            talla += 0.2;
            try
            {
                Thread.sleep(30);
            }
            catch(InterruptedException e)
            {  }
        }
    }

void esfera_espiral( float RADIO, float centroX, float centroY,
float centroZ, int num_p, int num_c, int color, float Xotro, float Yotro
, float Rotro )
{
float alfa, teta, cX, cY, cZ, n;
float inc_alfa, inc_teta, radio;
float distancia;

    inc_alfa = (float)(2.0*Math.PI * num_c / num_p);
    inc_teta = Math.PI / num_p;
    for( alfa = 0, teta = 0, n = 0; n < num_p; n++, alfa += inc_alfa
	, teta += inc_teta )
    {
	radio = RADIO * sin( teta );
	cY = RADIO * cos( teta )+centroY;
	cX = radio * cos( alfa )+centroX;
	cZ = radio * sin( alfa )+centroZ;
	if( cZ < centroZ )
	    continue;
   // REVISA LA SITUACION CON RESPECTO AL OTRO CIRCULO
	distancia = sqrt( pow( Xotro - cX, 2.0 ) + pow( Yotro - cY, 2.0 ) );
	if( distancia < Rotro )
	    continue;
	putpixel( ajustax( cX ), ajustay( cY ), color );
    }
    // DIBUJO DEL CIRCULO QUE RODEA A LA ESFERA
    inc_alfa = 2.0 * PI / 1000.0;
    for( alfa = 0; alfa < (2.0 * PI); alfa += inc_alfa )
    {
	cX = RADIO * sin( alfa )+centroX;
	cY = RADIO * cos( alfa )+centroY;
	distancia = sqrt( pow( Xotro - cX, 2.0 ) + pow( Yotro - cY, 2.0 ) );
	if( distancia < Rotro )
	    continue;
	putpixel( ajustax( cX ), ajustay( cY ), color );
    }
}

  public void paint(Graphics g)
  {
/*
      g.setColor(Color.white);
      g.fillRect(xPantMin ,yPantMin ,xPantMax ,yPantMax);
      g.setColor(new Color(0,0,0));
      g.drawLine(mapeaX(xRealMin), mapeaY(0) ,mapeaX(xRealMax), mapeaY(0));
      g.drawLine(mapeaX(0), mapeaY(yRealMin) ,mapeaX(0), mapeaY(yRealMax));
*/


        // FUNCION GRAFICABLE
        g.setColor(Color.blue);
        y = Math.cos(x);
       imagen = getImage(getCodeBase(), "tulum.jpg");
        g.drawImage(imagen, mapeaX(x), mapeaY(y), (int)talla, (int)talla, this);

//        g.drawLine(mapeaX(x),mapeaY(y),mapeaX(x),mapeaY(y));

        g.setColor(Color.red);
        y = Math.sin(x);
        g.drawLine(mapeaX(x),mapeaY(y),mapeaX(x),mapeaY(y));

  }
}
/*
void esfera_espiral( float RADIO, float centroX, float centroY,
float centroZ, int num_p, int num_c, int color, float Xotro, float Yotro
, float Rotro )
{
float alfa, teta, cX, cY, cZ, n;
float inc_alfa, inc_teta, radio;
float distancia;

    inc_alfa = 2.0*PI * num_c / num_p;
    inc_teta = PI / num_p;
    for( alfa = 0, teta = 0, n = 0; n < num_p; n++, alfa += inc_alfa
	, teta += inc_teta )
    {
	radio = RADIO * sin( teta );
	cY = RADIO * cos( teta )+centroY;
	cX = radio * cos( alfa )+centroX;
	cZ = radio * sin( alfa )+centroZ;
	if( cZ < centroZ )
	    continue;
   // REVISA LA SITUACION CON RESPECTO AL OTRO CIRCULO
	distancia = sqrt( pow( Xotro - cX, 2.0 ) + pow( Yotro - cY, 2.0 ) );
	if( distancia < Rotro )
	    continue;
	putpixel( ajustax( cX ), ajustay( cY ), color );
    }
    // DIBUJO DEL CIRCULO QUE RODEA A LA ESFERA
    inc_alfa = 2.0 * PI / 1000.0;
    for( alfa = 0; alfa < (2.0 * PI); alfa += inc_alfa )
    {
	cX = RADIO * sin( alfa )+centroX;
	cY = RADIO * cos( alfa )+centroY;
	distancia = sqrt( pow( Xotro - cX, 2.0 ) + pow( Yotro - cY, 2.0 ) );
	if( distancia < Rotro )
	    continue;
	putpixel( ajustax( cX ), ajustay( cY ), color );
    }
}
  pmaxx = 638; pminx = 1;
  pmaxy = 1;   pminy = 478;

  rmaxx = 200;
  rmaxy = -200;
  rminx = -200;
  rminy = 200;

  {
    outtextxy( 0, 55 * 8,
"c=carga x=rotaX y=rotaY z=rotaZ t=traslada e=escala p=pinta m=min f=fin" );
    outtextxy( 0, 56 * 8,
    "s=con circulos l=con espiral o=con puntos  u=dibuja alcanos"  );
        scanf( "%f%f%f", &ftx, &fty, &ftz );
    case 'l': case 'L':
	esfera_espiral( 30, -20, 0, 0, 1024, 8, WHITE, 300, 300, 0 );
	esfera_espiral( 60, 50, 20, 10, 1024, 10, CYAN, -20, 0, 30 );
	esfera_espiral( 100, 0, 0, 0, 4096, 35, LIGHTGREEN, 300, 300, 0 );


*/
