import java.awt.*;
import java.util.Date;
import java.lang.Math;
import java.applet.Applet;

public class arbolcrece extends Applet implements Runnable
{
// HEBRA DE EJECUCIàN
Thread hilo ;
int Tamano ;
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

    public void run()
    {
        while(true)
        {
            repaint();
            Tamano = (Tamano + 10) % 100 + 10;
            try
            {
                Thread.sleep(999);
            }
            catch(InterruptedException e)
            {  }
        }
    }
    public void init(Graphics g)
    {
        Tamano = 10;
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
    public void paint(Graphics g)
    {
    Font f = new Font("Arial", Font.BOLD,18);
    Color color_usuario = new Color(50,180,10); // RGB (Red,Green,Blue)
    long e,ef,eg ;
    int c ;
        
            // CIELO
           for( c = 0; c < 256; c++ )
           {
            g.setColor(new Color(c,c,255));
            g.fillRect(0,c,640,20);
           }
            // TIERRA
            g.setColor(color_usuario);
            g.fillRect(0,255,640,480);

            // AUTOR
            g.setColor(Color.white);
        g.setFont(f);
            g.drawString("Jesús Olivares" ,10 ,270);
        
            izq = Math.floor(Math.random() * 40) + 10; 
            der = Math.floor(Math.random() * 40) + 10;
            tronco = 0.6;  
            arbol(g, 320, 300, Tamano, 90, 5);
            arbol(g, 550, 310, 80, 90, 5);

    }
}
