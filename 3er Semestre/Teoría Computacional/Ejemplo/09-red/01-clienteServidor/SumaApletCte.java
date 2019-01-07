/* SumaApletCte.java

    SUMA UTILIZANDO CLIENTE SERVIDOR

2003 07 Jesus Olivares
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class SumaApletCte extends JApplet
{
JLabel etiqueta = new JLabel("Numeros:");
JTextField datoA = new JTextField(5);
JTextField datoB = new JTextField(5);
JLabel resultado = new JLabel("= ____");
ActionListener accion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
  Socket conexion ;
  DataInputStream is ;
  PrintStream os ;
 
   try
   {
       conexion = new Socket("localhost" ,7777);
       is = new DataInputStream(conexion.getInputStream());
       os = new PrintStream(conexion.getOutputStream());
       os.println(datoA.getText());
       os.flush();
       os.println(datoB.getText());
       os.flush();
       resultado.setText("= " + is.readLine());
       conexion.close();
   }
   catch(IOException ex)
   { }
  }
};
JButton sumar = new JButton("SUMAR");

    // CONSTRUCTOR
    public void init()
    {
    Container cont = getContentPane();

        cont.setLayout(new FlowLayout());
        cont.add(etiqueta);
        cont.add(datoA);
        cont.add(datoB);
        cont.add(resultado);
        sumar.addActionListener(accion);
        cont.add(sumar);
    }
}
