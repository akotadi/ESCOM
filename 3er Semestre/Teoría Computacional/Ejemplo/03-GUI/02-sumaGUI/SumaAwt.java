/* SumaAwt.java

    SUMA DE DOS NUMEROS UTILIZANDO UNA INTERFASE AWT

2003 07 Jesus Olivares
*/
import java.awt.*;

class VentanaAwt extends Frame
{
// COMPONENTES DE LA INTERFASE DE USUARIO, SON OBJETOS DE CLASE
Label etiqueta = new Label("Numeros:");
TextField datoA = new TextField(5);
TextField datoB = new TextField(5);
Label resultado = new Label(" = ____");
Button sumar = new Button("SUMAR");

    // CONSTRUCTOR DE LA CLASE Ventana
    public VentanaAwt()
    {
    Panel p = new Panel(new FlowLayout()); // USA DISTRIBUCION DE FLUJO

        // ADICIONA LOS COMPONENTES DE LA INTERFASE EN EL PANEL
        p.add(etiqueta);
        p.add(datoA);
        p.add(datoB);
        p.add(resultado);
        p.add(sumar);

        // ADICIONA EL PANEL EN EL Frame
        this.add(p);
    }

    public boolean handleEvent(Event evento)
    {
        if( evento.id == Event.WINDOW_DESTROY )
            System.exit(0);
        return super.handleEvent(evento);
    }

    // PROCESA LAS ACCIONES EN LOS COMPONENTES DE LA INTERFASE
    public boolean action(Event evento, Object objeto)
    {
        if( evento.target instanceof Button)
        {
            if( objeto.equals("SUMAR") )
            {
                // CONVIERTE LOS TEXTOS DE datoA Y datoB A NUMERO Y LOS SUMA
                resultado.setText("= " + (
                    Integer.parseInt(datoA.getText()) +
                    Integer.parseInt(datoB.getText())) );
            }
        }
        return true;
    }
}

class SumaAwt
{
    public static void main(String args[])
    {
    VentanaAwt captura = new VentanaAwt(); // VENTANA captura

        captura.setSize(300, 200); // ASIGNA EL TAMA¥O DE LA VENTANA X, Y
        captura.show(); // DESPLIEGA LA VENTANA
    }
}