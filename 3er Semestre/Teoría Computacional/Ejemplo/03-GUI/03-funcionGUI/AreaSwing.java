/* AreaSwing.java

    CALCULO DEL AREA DE UN CIRCULO UTILIZANDO UNA INTERFASE SWING

2003 07 Jesus Olivares
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Grafica extends Canvas
{
    // EL METODO PAINT SE LLAMA AUTOMATICAMENTE
    public void paint(Graphics g)
    {
        setBackground(Color.white);
    }

    public void dibuja(double _radio ,double _area)
    {
    Graphics g = getGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,400,300);
        g.setColor(Color.blue);
        g.drawOval(10 ,20 ,10 + (int)_radio ,20 + (int)_radio);
        g.drawString("Area circulo= " + _area ,10 ,19);
    }
}

class Ventana extends JFrame
{
Grafica grafica = new Grafica();
JLabel etiqueta = new JLabel("Radio=");
JTextField datoRadio = new JTextField(5);
JLabel resultado = new JLabel("Area= ");
JTextField datoArea = new JTextField(15);
JButton botonArea = new JButton("AREA");
JButton botonSalir = new JButton("SALIR");

// PANELES PARA LA PANTALLA
JScrollPane panelScroll = new JScrollPane(grafica);
JPanel panelBotones = new JPanel(new FlowLayout());
JSplitPane panelAjustable = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

// ACCION PARA CALCULAR EL AREA
ActionListener accion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
  double _radio ,_area ;

      _radio = Double.parseDouble(datoRadio.getText());
      _area = Math.PI * _radio * _radio;
      datoArea.setText("" + _area);
      grafica.dibuja(_radio ,_area);
  }
};
// ACCION PARA CERRAR LA VENTANA GRAFICA
ActionListener accionSalir = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      dispose();
  }
};

    // CONSTRUCTOR
    public Ventana()
    {
    Container cont = getContentPane();

      // ASIGNA LOS COMPONENTES EN EL PANEL DE BOTONES
      panelBotones.add(etiqueta);
      panelBotones.add(datoRadio);
      panelBotones.add(resultado);
      panelBotones.add(datoArea);
      panelBotones.add(botonArea);
      panelBotones.add(botonSalir);

      // COLOCA LOS PANELES EN LA PANTALLA
      panelScroll.setPreferredSize(new Dimension(400, 400));
      panelScroll.setMinimumSize(new Dimension(300, 300));
      panelAjustable.add(panelScroll);
      panelAjustable.add(panelBotones);

      // AGREGA EL panelAjustable EN EL PANEL PRINCIPAL CENTRADO
      cont.setLayout(new BorderLayout());
      cont.add(panelAjustable, BorderLayout.CENTER);

      // ASIGNA LAS ACCIONES
      botonArea.addActionListener(accion);
      botonSalir.addActionListener(accionSalir);

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


class AreaSwing
{
    public static void main(String args[])
    {
    Ventana captura = new Ventana();

        captura.setSize(350, 400);
        captura.show();
    }
}
