import java.awt.*;
import java.util.*;
import java.text.DateFormat;

public class Reloj extends javax.swing.JApplet implements Runnable
{
Thread hilo ;

    public void start()
    {
        // SI ESTA CORRIENDO ASI CONTINUA
        if( hilo == null )
        {
            hilo = new Thread(this, "Reloj");
            hilo.start();
        }
    }
    public void run()
    {
        while( true )
        {
            repaint();
            try
            {
                hilo.sleep(1000);
            }
            catch(InterruptedException e)
            { }
        }
    }
    public void paint(Graphics g)
    {
    Date hora = new Date();
    Font tipo = new Font("Arial" ,Font.BOLD ,20);

        g.setColor(Color.white);
        g.fillRect(0,0,150,30);
        DateFormat horaFormateada = DateFormat.getTimeInstance();
        g.setColor(Color.blue);
        g.setFont(tipo);
        g.drawString(horaFormateada.format(hora), 10, 20);
    }
    public void stop()
    {
        hilo = null;
    }
}
