/* Fractal.java

    REPRESENTACIÓN DE UN OBJETO FRACTAL
    
2006 08 Jesus Olivares
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

class Pantalla extends JFrame
{
float pila[] = new float[100] ;
int tope ;

  // CONSTRUCTOR
  Pantalla()
  {
      // ASIGNA EL COLOR DEL FONDO DE LA CANCHA
      setBackground(Color.white);

      // EVENTO PARA CERRAR LA VENTANA
      addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent e)
        {
            dispose();
            System.exit(0);
        }
      });
  }

void push(float dato)
{
     pila[tope] = dato;
     tope++;
}

float pop()
{
     tope--;
     return pila[tope];
}


  public void paint(Graphics g)
  {


       tope = 0;
       push(90); // angulo
       push(50); // t
       push(155); // y
       push(80); // x
       push(6); // nivel recursion

       while(tope > 0 )
       {
     float n,x,y,t,a,x1,y1;

          n = pop();
          x = pop();
          y = pop();
          t = pop();
          a = pop();
          // DRAW LINE
          x1 = x- t * (float)Math.cos(a/180.0f*Math.PI);
          y1 = y- t * (float)Math.sin(a/180.0f*Math.PI);
          g.drawLine((int)x,(int)y,(int)x1,(int)y1);
          if( n > 0 )
          {
          // arbol a la izquierda
          push(a-35); // angulo
          push(t/1.5f); // t
          push(y1); // y
          push(x1); // x
          push(n-1); // nivel recursion

          // arbol a la derecha
          push(a+35); // angulo
          push(t/1.5f); // t
          push(y1); // y
          push(x1); // x
          push(n-1); // nivel recursion
          }
       }
  }
}

class Fractal
{
  public static void main(String args[])
  {
  Pantalla pantalla = new Pantalla();

      pantalla.setSize(400, 300);
      pantalla.show();
  }
}