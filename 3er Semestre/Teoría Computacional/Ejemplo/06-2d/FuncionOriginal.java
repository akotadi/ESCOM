import java.awt.*;
import java.lang.Math;
import javax.swing.*;

class Funcion2 extends JFrame
{
// AREA DE GRAFICACION DE LA PANTALLA
int xPantMax ,yPantMax ,xPantMin ,yPantMin ;

// AREA DE GRAFICACION DEL MUNDO REAL
double xRealMax ,yRealMax ,xRealMin ,yRealMin ;

int resolucionY  ;

  // MAPEA LAS COORDENADAS X REALES A PANTALLA PASANDO POR NORMALIZADAS
  int mapeaX( double x_real)
  {
  double x_normal ;

    x_normal = ( x_real - xRealMin ) / ( xRealMax - xRealMin );
    return xPantMin + (int)(x_normal * (double)( xPantMax - xPantMin ));
  }


  //  MAPEA LAS COORDENADAS Y REALES A PANTALLA PASANDO POR NORMALIZADAS
  int mapeaY( double y_real )
  {
  double y_normal;
  int y_pantalla ;

    y_normal = ( y_real - yRealMin ) / ( yRealMax - yRealMin );
    y_pantalla = yPantMin + (int)(y_normal * ( yPantMax - yPantMin ));
    return resolucionY - y_pantalla;
  }

  public void paint(Graphics g)
  {
  double x ,y ,incr ;
// RESOLUCION DE LA PANTALLA
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

// RESOLUCION DE LA VENTANA
    Dimension ventana = this.getSize();
    resolucionY = ventana.height;
    System.out.println(""+resolucionY);

    xPantMin = 0;
    yPantMin = 20;
    xPantMax = 800;
    yPantMax = 600;

    xRealMin = -1; // -10;
    yRealMin = -1 ;// -10;
    xRealMax = 5;
    yRealMax = 5;



    g.setColor(Color.white);
    g.fillRect(mapeaX(xRealMin),mapeaY(yRealMax),xPantMax-xPantMin,yPantMax-yPantMin);

    g.setColor(Color.green);
    g.fillRect(mapeaX(xRealMin), mapeaY(yRealMax) ,5,5);


    g.setColor(new Color(0,0,0));

    g.drawLine(mapeaX(xRealMin), mapeaY(0) ,mapeaX(xRealMax), mapeaY(0));
    g.drawLine(mapeaX(0), mapeaY(yRealMin) ,mapeaX(0), mapeaY(yRealMax));

    incr = (xRealMax - xRealMin) / (xPantMax - xPantMin);

    double x1=-1,y1=3,x2=3,y2=-4;

    for( x = xRealMin; x < xRealMax; x += incr )
    {
        // FUNCION GRAFICABLE
        g.setColor(Color.blue);
        // (y - y2) = ((y2 - y1) / (x2 - x1) ) * (x - x2);
        y = ((y2 - y1) / (x2 - x1) ) * (x - x2) + y2;

        g.setColor(Color.red);

   //     g.drawLine(mapeaX(x),mapeaY(y),mapeaX(x),mapeaY(y));
        g.fillRect(mapeaX(x), mapeaY(y) ,2,2);
    }
  }
}


class Funcion
{
    public static void main(String arg[])
    {
    Funcion2 funcion = new Funcion2();

         funcion.setSize(800,600);
         funcion.show();
    }
}