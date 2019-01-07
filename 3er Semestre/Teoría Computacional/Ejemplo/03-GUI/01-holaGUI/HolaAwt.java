/* HolaAwt.java

    HOLA

2003 07 Jesus Olivares
*/
import java.awt.*;

class VentanaAwt extends Frame
{
Label etiqueta = new Label("Hola Amigos");

    // CONSTRUCTOR DE LA CLASE Ventana
    public VentanaAwt()
    {
    Panel p = new Panel(new FlowLayout()); // USA DISTRIBUCION DE FLUJO

        p.add(etiqueta);
        // ADICIONA EL PANEL EN EL Frame
        this.add(p);
    }

    public boolean handleEvent(Event evento)
    {
        if( evento.id == Event.WINDOW_DESTROY )
            System.exit(0);
        return super.handleEvent(evento);
    }

}

class HolaAwt
{
    public static void main(String args[])
    {
    VentanaAwt captura = new VentanaAwt(); // VENTANA captura

        captura.setSize(300, 200); // ASIGNA EL TAMA¥O DE LA VENTANA X, Y
        captura.show(); // DESPLIEGA LA VENTANA
    }
}