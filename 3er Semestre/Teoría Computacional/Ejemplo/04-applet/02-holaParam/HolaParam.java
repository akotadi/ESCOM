import java.awt.Graphics;
import javax.swing.JApplet;

public class HolaParam extends JApplet
{
StringBuffer cadena = new StringBuffer(100);

    public void init()
    {
    String nombre ,valor ;
    int numero = 1;

      
        do
        {
            nombre = new String("dato" + numero);
            valor = getParameter(nombre);
            if( valor != null )
                cadena.append((nombre + "=" + valor + "   "));
            numero++;
        } while( valor != null );
    }
    public void paint( Graphics g )
    {
        g.drawString(cadena.toString(), 10, 30);  // X Y
    }
}

