import java.util.Date;
import java.lang.Math;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ComandoBD extends JApplet
{
TextField comando  = new TextField(80);
JTextArea resultado = new JTextArea("" ,20 ,80);
Label eComando = new Label("Comando:");
Label eResultado = new Label("RESULTADO");
JButton enviar = new JButton("Envia");
ActionListener accion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
    try
    {
        envia();
    }
    catch(IOException ex)
    {
        resultado.insert("FALLO LA CONEXION CON EL SERVIDOR" ,0);
    } 
  }
};

    public void init()
    {
    Container c = getContentPane();
    
      c.setLayout(null);
      c.add(eComando);
      eComando.setBounds(10 ,20 ,70 ,20);
      eComando.show();
      
      c.add(comando);
      comando.setBounds(90 ,20 ,500 ,20);
      comando.show();
      
      c.add(enviar);
      enviar.setBounds(600 ,20 ,80 ,20);
      enviar.show();
      
      c.add(eResultado);
      eResultado.setBounds(300 ,70 ,100 ,20);
      eResultado.show();
      
      c.add(resultado);
      resultado.setBounds(10 ,100 ,600 ,300);
      resultado.show();
      
      // ASIGNA LA ACCION AL BOTON ENVIA
      enviar.addActionListener(accion);
      comando.addActionListener(accion);
    }
    public void envia() throws IOException
    {
    Socket conexion = null;
    DataInputStream is ;
    PrintStream os ;
    String entra ,sale ;

      try
      {
        conexion = new Socket("160.1.1.70" ,7777);
        is = new DataInputStream(conexion.getInputStream());
        os = new PrintStream(conexion.getOutputStream());
        os.println(comando.getText());
        os.flush();
        String respuesta = is.readLine();
        resultado.insert(respuesta ,0);
        conexion.close(); 
      }
      catch(IOException e)
      {
        resultado.insert("FALLO EL SERVIDOR" ,0);
      }
    }
}
