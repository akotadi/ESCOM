import java.util.Date;
import java.lang.Math;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

public class ApCliente extends java.applet.Applet
{
TextField telefono  = new TextField(30);
TextField direccion  = new TextField(30);
TextField nombre   = new TextField(30);
TextField rfc = new TextField(30);
Label e1 = new Label("Rfc:");
Label e2 = new Label("Nombre:");
Label e3 = new Label("Direccion:");
Label e4 = new Label("Telefono:");

    public void init()
    {
    Panel p1 = new Panel();
    
      p1.setLayout(new GridLayout(5, 2 ,0 ,0)); // renglones,columnas
      p1.add(e1); 
      p1.add(rfc);
      p1.add(e2);
      p1.add(nombre);
      p1.add(e3);
      p1.add(direccion);
      p1.add(e4);
      p1.add(telefono);
      p1.add(new Label(" "));            
      p1.add(new Button("Buscar"));
      add(p1);
      nombre.hide();
      direccion.hide();
      telefono.hide();
    }
    public void envia() throws IOException
    {
    Socket conexion = null;
    DataInputStream is ;
    PrintStream os ;
    String entra ,sale ;

      try
      {
        conexion = new Socket("148.204.45.7" ,7777);
        is = new DataInputStream(conexion.getInputStream());
        os = new PrintStream(conexion.getOutputStream());
        os.println(rfc.getText());
        os.flush();
        String sNombre = is.readLine();
        String sDireccion = is.readLine();
        String sTelefono = is.readLine();
        nombre.show();
        direccion.show();
        telefono.show();
        nombre.setText (sNombre);
        direccion.setText(sDireccion);
        telefono.setText(sTelefono);
        conexion.close(); 
      }
      catch(IOException e){}
    }
    public boolean action(Event ev, Object arg)
    {
        if (ev.target instanceof Button)
        {
            String ident = (String)arg;
            if (ident.equals("Buscar") == true)
            {
                try
                {
                    envia();
                }
                catch(Exception e){}
            }
        }
        return  true;
    }
}
