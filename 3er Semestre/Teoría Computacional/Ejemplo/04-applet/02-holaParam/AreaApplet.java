import java.awt.*;

public class AreaApplet extends javax.swing.JApplet
{
    public void paint( Graphics g )
    {
    String figura;
    float radio;

        // LEE LOS PARAMETROS DE HTML figura Y radio
        figura = getParameter("figura");
        radio = ((Float)Float.valueOf(getParameter("radio"))).floatValue();

        // CALCULA EL AREA
        g.drawString("El area de un "+ figura + " de radio "
          + radio + " = " + (3.1416*radio*radio), 5, 75 );

       // DIBUJA UN CIRCULO
       g.setColor(new Color(0 ,0x090 ,0xff));
       g.fillOval(100, 20, 40, 40);
    }
}

