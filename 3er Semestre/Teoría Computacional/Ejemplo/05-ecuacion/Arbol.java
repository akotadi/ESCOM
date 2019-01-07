import java.awt.*;
import java.lang.Math;
import javax.swing.JApplet;

public class Arbol extends JApplet 
{
double izq, der, tronco;

  // EN x,y CRECE EL ARBOL, t TRONCO, a ANGULO, n NIVEL DE RECURSION
  public void arbol(Graphics g,int x, int y, int t,double a,int n)
  {
  int xp, yp;

      if( n > 0 )
      {
          xp = (int)(x - t * Math.cos(a / 57.29564553));
          yp = (int)(y - t * Math.sin(a / 57.29564553));

          // ASIGNA COLOR 
          if( n > 4 )
              g.setColor(Color.black);  // TRONCO 
          else
              g.setColor(Color.green);  // RAMAS
          g.drawLine(x, y, xp, yp);
          arbol(g, xp, yp, (int)(t * tronco), a + izq, n - 1);
          arbol(g, xp, yp, (int)(t * tronco), a - der, n - 1);
          arbol(g, xp, yp, (int)(t * tronco), a      , n - 1);
      }
  }

  public void paint(Graphics g)
  {
  Font f = new Font("TimesRoman", Font.BOLD,18);
  Color color_usuario = new Color(50,180,10); // RGB (Red,Green,Blue)
  long e,ef,eg ;

      // CIELO
      g.setColor(Color.blue);
      g.fillRect(0,0,640,280);

      // TIERRA
      g.setColor(color_usuario);
      g.fillRect(0,280,640,480);

      // AUTOR
      g.setColor(Color.white);
      g.setFont(f);
      g.drawString("Jesús Olivares" ,10 ,270);

      izq = Math.floor(Math.random() * 40) + 10; 
      der = Math.floor(Math.random() * 40) + 10;
      tronco = 0.6;  
      arbol(g, 190, 470, 80, 90, 5);
      arbol(g, 550, 310, 80, 90, 5);

      tronco = 0.5;
      arbol(g, 500, 400, 40, 90, 8);

      izq = Math.floor(Math.random() * 30) + 10; 
      der = Math.floor(Math.random() * 30) + 10;
      tronco = 0.8;  
      arbol(g, 350, 380, 90, 90, 8);
      arbol(g, 100, 380, 70, 90, 5);
  }
}