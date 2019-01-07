/* Vigilante.java

    AGENTE REACTIVO

2003 07 Jesus Olivares
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Grafica extends Canvas
{
int yRaton ;
int xRaton ;

    MouseMotionListener eventoRatonMueve = new MouseMotionListener()
    {
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e)
    {
    Graphics g = getGraphics();

        xRaton = e.getX();
        yRaton = e.getY();
    }
  };

   int getXRaton()
   {
        return xRaton;
   }

   int getYRaton()
   {
        return yRaton;
   }

    // EL METODO PAINT SE LLAMA AUTOMATICAMENTE
    public void paint(Graphics g)
    {
        addMouseMotionListener(eventoRatonMueve);
        setBackground(Color.white);
        g.setColor(Color.blue);
        g.drawRect(100,20,100,100);
    }
    public void dibuja()
    {
    Graphics g = getGraphics();

        g.setColor(Color.red);
        g.drawString("Llego un cliente" ,0 ,19);
    }
     public void borra()
    {
    Graphics g = getGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,99,19);
    }
}

class Agente extends JFrame implements Runnable
{
Grafica grafica = new Grafica();
Thread hilo ;
int xRaton ,yRaton ;
JButton iniciar = new JButton("INICIAR");
JButton detener = new JButton("DETENER");
JButton salir = new JButton("SALIR");

// PANELES PARA LA PANTALLA
JScrollPane panelScroll = new JScrollPane(grafica);
JPanel panelBotones = new JPanel(new FlowLayout());
JSplitPane panelAjustable = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

// ACCIONES
ActionListener accion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
        if(e.getActionCommand().equals("INICIAR"))
        {
            hilo.start();
        }
        if(e.getActionCommand().equals("DETENER"))
        {
            hilo.stop();
        }
        if(e.getActionCommand().equals("SALIR"))
        {
            System.exit(0);
        }
  }
};

    // CONSTRUCTOR
    public Agente()
    {
    Container cont = getContentPane();

      hilo = new Thread(this);

      // ASIGNA LOS COMPONENTES EN EL PANEL DE BOTONES
      panelBotones.add(iniciar);
      panelBotones.add(detener);
      panelBotones.add(salir);

      // COLOCA LOS PANELES EN LA PANTALLA
      panelScroll.setPreferredSize(new Dimension(400, 400));
      panelScroll.setMinimumSize(new Dimension(300, 300));
      panelAjustable.add(panelScroll);
      panelAjustable.add(panelBotones);

      // AGREGA EL panelAjustable EN EL PANEL PRINCIPAL CENTRADO
      cont.setLayout(new BorderLayout());
      cont.add(panelAjustable, BorderLayout.CENTER);

      // ASIGNA LAS ACCIONES
      iniciar.addActionListener(accion);
      detener.addActionListener(accion);
      salir.addActionListener(accion);

      // ACCION PARA CUANDO SE PIDE EL CIERRE DE LA VENTANA
      addWindowListener(new WindowAdapter()
      {
          public void windowClosing(WindowEvent e)
          {
              dispose();
          }
      });
  }
    public void run()
    {
        System.out.println("inicio al Agente");
        while(true)
        {
            xRaton = grafica.getXRaton();
            yRaton = grafica.getYRaton();
            try
            {
                Thread.sleep(10);
                if( xRaton > 100 && xRaton < 200
                  && yRaton > 20 && yRaton < 120 )
                {
                    grafica.dibuja();
                }
                else
                {
                    grafica.borra();
                }
            }
            catch(Exception e)
            {}
        }
    }
}


class Vigilante
{
    public static void main(String args[])
    {
    Agente agente = new Agente();

        agente.setSize(350, 400);
        agente.show();
    }
}
