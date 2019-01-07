/* Opcion.java

    MANEJO DE MENUS Y DESPLIEGUE DE IMAGENES

2003 07 Jesus Olivares
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

class Grafica extends Canvas
{
int figura;

  public void asignaFigura(int _figura)
  {
      figura = _figura;
  }
  public void paint(Graphics g)
  {
      g.setColor(Color.white);
      g.fillRect(0,0,400,300);
      if( figura == 1 )
      {
          g.setColor(Color.blue);
          g.fillRect(30,30,50,50);
      }
      else
      {
          g.setColor(Color.red);
          g.fillOval(30,30,50,50);
      }
  }
  public void desplega(String dato)
  {
  Graphics g = getGraphics();

      g.setColor(Color.white);
      g.fillRect(0,100,400,300);
      g.setColor(Color.blue);
      g.drawString("Area = " + dato ,20 ,110);
  }
}

class CalculaArea extends JFrame
{
int figura ;

// PANEL AJUSTABLE PARA LA PANTALLA
JSplitPane panelAjustable = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
Grafica grafica = new Grafica();
JScrollPane panelScroll = new JScrollPane(grafica);
JLabel letrero = new JLabel();;
JTextField parametro = new JTextField(10);
ActionListener accionParametro = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
  double valor ;
  Double valorDouble = new Double(parametro.getText());

      valor = valorDouble.doubleValue();
      if( figura == 1 )
          grafica.desplega("" + (valor * valor));
      else
          grafica.desplega("" + (3.1416 * valor * valor) );
  }
};

// BOTONES DE ACTUALIZACION
JPanel panelBotones = new JPanel(new FlowLayout());
JButton area = new JButton("AREA");
ActionListener accionArea = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
  double valor ;
  Double valorDouble = new Double(parametro.getText());

      valor = valorDouble.doubleValue();
      if( figura == 1 )
          grafica.desplega("" + (valor * valor));
      else
          grafica.desplega("" + (3.1416 * valor * valor) );
  }
};

JButton salir = new JButton("SALIR");
ActionListener accionSalir = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      dispose();
  }
};

  // CONSTRUCTOR
  public CalculaArea(int _figura)
  {
  Container cont = getContentPane();

      figura = _figura;
      grafica.asignaFigura(figura);
      if( figura == 1 )
          letrero.setText("Lado: ");
      else
          letrero.setText("Radio: ");

      // ASIGNA LOS COMPONENTES EN EL PANEL DE BOTONES
      panelBotones.add(letrero);
      panelBotones.add(parametro);
      panelBotones.add(area);
      panelBotones.add(salir);

      // COLOCA LOS PANELES EN LA PANTALLA
      panelScroll.setPreferredSize(new Dimension(400, 300));
      panelScroll.setMinimumSize(new Dimension(300, 200));
      panelAjustable.add(panelScroll);
      panelAjustable.add(panelBotones);

      // AGREGA EL panelAjustable EN EL PANEL PRINCIPAL CENTRADO
      cont.setLayout(new BorderLayout());
      cont.add(panelAjustable, BorderLayout.CENTER);

      // ASIGNA LAS ACCIONES
      parametro.addActionListener(accionParametro);
      area.addActionListener(accionArea);
      salir.addActionListener(accionSalir);

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

class Estadistico extends JFrame
{
int arreglo[] = {8 ,10 ,9 ,7 ,10};
JLabel datos = new JLabel();
JLabel mediaE = new JLabel();
JLabel varianzaE = new JLabel();
JPanel panelComponentes = new JPanel(new BorderLayout());
int i ;
Vector vector = new Vector();
Estadistica estadistica ;
StringBuffer cadena = new StringBuffer(50);

  // CONSTRUCTOR
  public Estadistico()
  {
  Container cont = getContentPane();

      // ASIGNA LOS COMPONENTES EN EL PANEL DE BOTONES
      panelComponentes.setPreferredSize(new Dimension(400, 300));
      panelComponentes.setMinimumSize(new Dimension(300, 200));
      panelComponentes.add(datos ,BorderLayout.NORTH);
      panelComponentes.add(mediaE ,BorderLayout.CENTER);
      panelComponentes.add(varianzaE ,BorderLayout.SOUTH);
      cont.setLayout(new BorderLayout());
      cont.add(panelComponentes, BorderLayout.CENTER);
      for( int i = 0; i < arreglo.length; i++ )
      {
          vector.add((Object)new Integer(arreglo[i]));
          cadena.append("    ");
          cadena.append(arreglo[i]);
      }
      estadistica = new Estadistica(vector);
      datos.setText("DATOS: " + cadena.toString());
      mediaE.setText("Media = " + estadistica.media());
      varianzaE.setText("Varianza = " + estadistica.varianza());

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

class Principal extends JFrame
{
CalculaArea calculaArea;

// COMPONENTES DEL MENU
JMenuBar barra = new JMenuBar();
JMenu area = new JMenu("Areas");
JMenuItem cuadrado = new JMenuItem("Cuadrado");
ActionListener areaCuadrado = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      calculaArea = new CalculaArea(1);
      calculaArea.setSize(400, 300);
      calculaArea.show();
  }
};

JMenuItem circulo = new JMenuItem("Circulo");
ActionListener areaCirculo = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      calculaArea = new CalculaArea(0);
      calculaArea.setSize(400, 300);
      calculaArea.show();
  }
};

JMenuItem estadistica = new JMenuItem("Estadistica");
ActionListener calculaEstadistica = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
  Estadistico ventanaE = new Estadistico();

      ventanaE.setSize(400 ,300);
      ventanaE.show();
  }
};

JMenuItem salir = new JMenuItem("Salir");
ActionListener accionSalir = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      System.exit(0);
  }
};

  // CONSTRUCTOR
  public Principal()
  {
  Container cont = getContentPane();

      // ASIGNA UNA DISTRIBUCION DE COMPONENTES
      cont.setLayout(new BorderLayout());

      // CONSTRUYE EL MENU
      area.add(cuadrado);
      area.add(circulo);
      barra.add(area);
      barra.add(estadistica);
      barra.add(salir);

      // ASIGNA LAS ACCIONES A LAS OPCIONES DEL MENU FINALES
      cuadrado.addActionListener(areaCuadrado);
      circulo.addActionListener(areaCirculo);
      estadistica.addActionListener(calculaEstadistica);
      salir.addActionListener(accionSalir);

      // COLOCA EL MENU EN LA PANTALLA
      cont.add(barra ,BorderLayout.NORTH);

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

class Menu
{
    public static void main(String args[])
    {
    Principal pantalla  = new Principal();

        pantalla.setSize(400, 300);
        pantalla.show();
    }
}