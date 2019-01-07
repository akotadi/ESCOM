import java.awt.*;
import javax.swing.*;

public class SinDobleBuffer extends JApplet implements Runnable
{
int y ;
Thread hilo ;
Image imagen;

    public void init()
    {
        imagen = getImage(getCodeBase(),"tulum.jpg");
        hilo = new Thread(this);
        hilo.start();
    }
    public void run()
    {
        for( y = 10; y < 400; y += 1 )
        {
            repaint();
            try
            {
                hilo.sleep(10); // 3 decima de segundo
            }
            catch(Exception e){}
        }
    }

    public void paint( Graphics g )
    {
        g.setColor(Color.white);
        g.fillRect(0,y -2,300,200);
        g.drawImage(imagen, 0, y ,300,200, this);
    }
}

