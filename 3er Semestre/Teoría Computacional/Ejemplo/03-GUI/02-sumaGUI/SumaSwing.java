/* SumaSwing.java

    SUMA DOS NUMEROS MEDIANTE LA INTERFASE CON COMPONENTES SWING

2003 07 Jesus Olivares
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Ventana extends JFrame
{
JLabel etiqueta = new JLabel("Numeros:");
JTextField datoA = new JTextField(5);
JTextField datoB = new JTextField(5);

DefaultListModel modelo = new DefaultListModel();
JList lista = new JList(modelo);

JLabel resultado = new JLabel("= ____");
ActionListener accion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      resultado.setText("= " + (
        Integer.parseInt(datoA.getText()) +
        Integer.parseInt(datoB.getText())) );
  }
};
JButton sumar = new JButton("SUMAR");

  // CONSTRUCTOR
  public Ventana()
  {
  Container cont = getContentPane();

      cont.setLayout(new FlowLayout());
      cont.add(etiqueta);
      cont.add(datoA);
      cont.add(datoB);
      cont.add(resultado);
      sumar.addActionListener(accion);
      cont.add(sumar);
modelo.add(0,"HOLA");
modelo.add(1,"AMIGOS");
      cont.add(lista);

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

class SumaSwing
{
    public static void main(String args[])
    {
    Ventana captura = new Ventana();

        captura.setSize(250, 200);
        captura.show();
    }
}