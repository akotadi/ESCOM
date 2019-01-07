/* HolaSwing.java

    DESPLIEGA UN LETRERO EN SWING

2003 07 Jesus Olivares
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Ventana extends JFrame
{
JLabel etiqueta = new JLabel("Hola Amigos");

  // CONSTRUCTOR
  public Ventana()
  {
  Container cont = getContentPane();

      cont.setLayout(new FlowLayout());
      cont.add(etiqueta);

      // ACCION PARA CUANDO SE PIDE EL CIERRE DE LA VENTANA
      addWindowListener(new WindowAdapter()
      {
          public void windowClosing(WindowEvent e)
          {
              dispose();
          }
      });
  }
}

class HolaSwing
{
    public static void main(String args[])
    {
    Ventana captura = new Ventana();

        captura.setSize(250, 200);
        captura.show();
    }
}