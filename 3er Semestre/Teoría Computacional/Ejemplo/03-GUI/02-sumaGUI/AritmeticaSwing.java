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
JLabel resultado = new JLabel("= ____");
JButton suma = new JButton("+");
JButton resta = new JButton("-");
JButton multiplica = new JButton("*");
JButton division = new JButton("/");

ActionListener accion = new ActionListener()
{
    public void actionPerformed(ActionEvent e)
    {
    int aTemp ,bTemp ;

        aTemp = Integer.parseInt(datoA.getText());
        bTemp = Integer.parseInt(datoB.getText());
        if(e.getActionCommand().equals("+"))
        {
             resultado.setText("= " + (aTemp + bTemp));
        }
        if(e.getActionCommand().equals("-"))
        {
            resultado.setText("= " + (aTemp - bTemp));
        }
        if(e.getActionCommand().equals("*"))
        {
            resultado.setText("= " + (aTemp * bTemp));
        }
        if(e.getActionCommand().equals("/"))
        {
            resultado.setText("= " + (aTemp / bTemp));
        }
    }
};


  // CONSTRUCTOR
  public Ventana()
  {
  Container cont = getContentPane();

      cont.setLayout(new FlowLayout());
      cont.add(etiqueta);
      cont.add(datoA);
      cont.add(datoB);
      cont.add(resultado);
      suma.addActionListener(accion);
      division.addActionListener(accion);
      multiplica.addActionListener(accion);
      resta.addActionListener(accion);
      cont.add(suma);
      cont.add(resta);
      cont.add(multiplica);
      cont.add(division);

      // ACCION PARA CUANDO SE PIDE EL CIERRE DE LA VENTANA
      addWindowListener(new WindowAdapter()
      {
          public void windowClosing(WindowEvent e)
          {
              dispose();//cerrar ventana
          }
      });
  }
}

class AritmeticaSwing
{
    public static void main(String args[])
    {
    Ventana captura = new Ventana();

        captura.setSize(250, 200);
        captura.show();
    }
}