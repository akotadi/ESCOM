import java.awt.*;
import javax.swing.*;

public class ConDobleBuffer extends JApplet implements Runnable
{
int y ;
Thread hilo ;
Image imagen;

// VARIABLES PARA DOBLE BUFFER QUE ELIMINAN EL PARPADEO
Image buffer2 ;
Graphics g2 ;

    public void init()
    {
        hilo = new Thread(this);
        hilo.start();

        imagen = getImage(getCodeBase(), "tulum.jpg");
        setBackground(Color.white);
    Dimension dimActual = size();
        buffer2 = createImage(dimActual.width ,dimActual.height);
        g2 = buffer2.getGraphics();

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
/*
    // EN UPDATE SE DIBUJA EN UN DOBLE BUFFER g2
    public void update( Graphics g )
    {
        g2.setColor(Color.white);
        g2.fillRect(0,y - 2,300,200);
        g2.drawImage(imagen, 0, y ,300,200, this);
        paint(g); // AQUI SE LLAMA A paint() EXPLICITAMENTE
    }
*/
    public void paint(Graphics g)
    {
        // COLOCO LA IMAGEN DE DOBLE BUFFER EN EL APPLET CON UNA INSTRUCCION
        // g2 APUNTA A buffer2 DESDE init()
        g2.setColor(Color.white);
        g2.fillRect(0,y - 2,300,200);
        g2.drawImage(imagen, 0, y ,300,200, this);

        if( buffer2 != null )
            g.drawImage(buffer2, 0, 0 ,null);

    }
}

