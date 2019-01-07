import java.awt.*;
import java.lang.Math;
import javax.swing.*;

class Punto2D
{
double x ,y ;

  public Punto2D(double _x ,double _y)
  {
      x = _x;
      y = _y;
  }
}
class Punto3D
{
double x ,y ,z ;

  public Punto3D(double _x ,double _y ,double _z)
  {
      x = _x;
      y = _y;
      z = _z;    
  }
  
  Punto2D perspectiva(double planoProy ,Punto3D proyeccion)
  {
  double tx ,ty , u ;
      u = (planoProy - proyeccion.z) / (z - proyeccion.z);
      tx = proyeccion.x + (x - proyeccion.x) * u;
      ty = proyeccion.y + (y - proyeccion.y) * u;
  Punto2D temporal = new Punto2D(tx ,ty);
      return temporal;
  }

  public String toString()
  {
      return "" + x + " " + y + " " + z;
  }
}

class Linea3D
{
int de ,hacia ;

  public Linea3D(int _de ,int _hacia)
  {
      de = _de;
      hacia = _hacia;
  }
}

class Matriz3D
{
double elem[][] = new double[4][4];

  public Matriz3D()
  {
  int ren ,col ;

      for( ren = 0; ren < 4; ren++ )
          for( col = 0; col < 4; col++ )
              elem[ren][col] = 0;
  }

  void identidad()
  {
      elem[0][0] = 1;
      elem[1][1] = 1;
      elem[2][2] = 1;
      elem[3][3] = 1;
  }

  void rotacionX(double _angulo)
  {
  double angulo = _angulo / 180.0 * Math.PI; // CONVERSION DE GRADOS A RADIANES

      elem[1][1] = Math.cos(angulo);
      elem[1][2] = Math.sin(angulo);
      elem[2][1] = - Math.sin(angulo);
      elem[2][2] = Math.cos(angulo);
  }

  void rotacionY(double _angulo)
  {
  double angulo = _angulo / 180.0 * Math.PI; // CONVERSION DE GRADOS A RADIANES        

      elem[0][0] = Math.cos(angulo);
      elem[0][2] = - Math.sin(angulo);
      elem[2][0] = Math.sin(angulo);
      elem[2][2] = Math.cos(angulo);
  }

  void rotacionZ(double _angulo)
  {
  double angulo = _angulo / 180.0 * Math.PI; // CONVERSION DE GRADOS A RADIANES        

      elem[0][0] = Math.cos(angulo);
      elem[0][1] = Math.sin(angulo);
      elem[1][0] = - Math.sin(angulo);
      elem[1][1] = Math.cos(angulo);
  }

  Punto3D multiplica(Punto3D _punto)
  {
  double _x, _y, _z ,tx ,ty ,tz ;

      tx = _punto.x * elem[0][0] +
           _punto.y * elem[1][0] +
           _punto.z * elem[2][0] + elem[3][0];
      ty = _punto.x * elem[0][1] +
           _punto.y * elem[1][1] +
           _punto.z * elem[2][1] + elem[3][1];
      tz = _punto.x * elem[0][2] +
           _punto.y * elem[1][2] +
           _punto.z * elem[2][2] + elem[3][2];
      Punto3D temporal = new Punto3D(tx ,ty ,tz);
      return temporal;
  }
}

public class Grafica3D extends JApplet
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

  public void paint(Graphics g)
  {
  double x ,y ,incr ;

      // COORDENADAS DE LA PANTALLA
      xPantMin = 0;
      yPantMin = 0;
      xPantMax = 500;
      yPantMax = 400;

      // COORDENADAS DEL MUNDO REAL
      xRealMin = -30;
      yRealMin = -30;
      xRealMax = 30;
      yRealMax = 30;

      // FIGURA A GRAFICAR CON PUNTOS EN 3D Y LINEAS 
  Punto3D cubo[] = new Punto3D[8];
      cubo[0] = new Punto3D(-10,10,10);
      cubo[1] = new Punto3D(10,10,10);
      cubo[2] = new Punto3D(10,-10,10);
      cubo[3] = new Punto3D(-10,-10,10);
      cubo[4] = new Punto3D(-10,10,-10);
      cubo[5] = new Punto3D(10,10,-10);
      cubo[6] = new Punto3D(10,-10,-10);
      cubo[7] = new Punto3D(-10,-10,-10);

      // LAS LINEAS APUNTAN A LOS PUNTOS
  Linea3D arista[] = new Linea3D[12] ;
      arista[0] = new Linea3D(0,1);
      arista[1] = new Linea3D(1,2);
      arista[2] = new Linea3D(2,3);
      arista[3] = new Linea3D(3,0);
      arista[4] = new Linea3D(4,5);
      arista[5] = new Linea3D(5,6);
      arista[6] = new Linea3D(6,7);
      arista[7] = new Linea3D(7,4);
      arista[8] = new Linea3D(4,0);
      arista[9] = new Linea3D(5,1);
      arista[10] = new Linea3D(6,2);
      arista[11] = new Linea3D(7,3);

  // SELECCIONA LOS ELEMENTOS DE PROYECCION
  Punto3D proyeccion = new Punto3D(0,0,50);
  double planoProyeccion = 20;

    // ASIGNA EL COLOR DEL FONDO DEL APPLET    
    setBackground(Color.white);

    // GRAFICA LOS EJES CARTESIANOS X Y
    g.setColor(new Color(0,0,0));
    g.drawLine(mapeaX(xRealMin), mapeaY(0) ,mapeaX(xRealMax), mapeaY(0));
    g.drawLine(mapeaX(0), mapeaY(yRealMin) ,mapeaX(0), mapeaY(yRealMax));

    // ASIGNA LOS ELEMENTOS DE ROTACION
    Matriz3D matriz = new Matriz3D();
    matriz.identidad();
    matriz.rotacionX(25.0); // ROTA 30 GRADOS EN EL EJE X

    // APLICA LA MATRIZ DE ROTACION A LOS PUNTOS
    for( int i = 0; i < 8; i++ )
    {
        cubo[i] = matriz.multiplica(cubo[i]);
    }

    // GRAFICA EL CUBO RESULTANTE DE COLOR AZUL
    g.setColor(Color.blue);
    for( int i = 0; i < 12; i++ )
    {
    Punto2D puntoDe ,puntoA ;

        puntoDe = cubo[arista[i].de].
          perspectiva(planoProyeccion ,proyeccion);
        puntoA = cubo[arista[i].hacia].
          perspectiva(planoProyeccion ,proyeccion);
        g.drawLine(mapeaX(puntoDe.x),mapeaY(puntoDe.y)
         ,mapeaX(puntoA.x),mapeaY(puntoA.y));
    }
  }
}
