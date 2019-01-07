/* Abc.java

    ACTUALIZACION EN UNA TABLA

2003 07 Jesus Olivares
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

class LeeRegistro extends JFrame
{
int figura ;

// PANEL AJUSTABLE PARA LA PANTALLA
JSplitPane panelAjustable = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

// ETIQUETAS DE LOS CAMPOS DE DATOS
JLabel eRfc = new JLabel("Rfc:" );
JLabel eNombre = new JLabel("Nombre: ");
JLabel eDireccion = new JLabel("Direccion: ");
JLabel eTelefono = new JLabel("Telefono: ");

// CAMPOS DE DATOS
JTextField vRfc = new JTextField(10);
JTextField vNombre = new JTextField(30);
JTextField vDireccion = new JTextField(30);
JTextField vTelefono = new JTextField(20);

// BOTONES DE ACTUALIZACION
JPanel panelBotones = new JPanel(new FlowLayout());

// OBJETO AL QUE SE LE AGREGA EL REGISTRO O EN EL QUE SE HACEN CAMBIOS
DefaultTableModel modelo ;
int renglon ;
boolean hacerCambio ;

JButton salir = new JButton("Cancelar");
ActionListener accionSalir = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      dispose();
  }
};
JButton aceptar = new JButton("Aceptar");
ActionListener accionAceptar = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      if( hacerCambio )
      {
          modelo.setValueAt(vRfc.getText(), renglon ,0);
          modelo.setValueAt(vNombre.getText(), renglon ,1);
          modelo.setValueAt(vDireccion.getText(), renglon ,2);
          modelo.setValueAt(vTelefono.getText(), renglon ,3);
      }
      else
      {
      String info [] = new String[4];

          info[0] = vRfc.getText();
          info[1] = vNombre.getText();
          info[2] = vDireccion.getText();
          info[3] = vTelefono.getText();
          modelo.addRow(info);
      }
   dispose(); // CIERRA LA VENTANA
  }
};

  void construyeVentana()
  {
  Container cont = getContentPane();
  Panel panel = new Panel(new GridLayout(5,2));

      // ASIGNA LOS COMPONENTES EN EL PANEL DE BOTONES
      panel.add(eRfc);
      panel.add(vRfc);
      panel.add(eNombre);
      panel.add(vNombre);
      panel.add(eDireccion);
      panel.add(vDireccion);
      panel.add(eTelefono);
      panel.add(vTelefono);
      panel.add(aceptar);
      panel.add(salir);
      cont.add(panel);

      // ASIGNA LAS ACCIONES
      aceptar.addActionListener(accionAceptar);
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

  // CONSTRUCTOR PARA HACER ALTAS
  public LeeRegistro(DefaultTableModel _modelo)
  {
      modelo = _modelo;
      construyeVentana();
      hacerCambio = false;
  }

  // CONSTRUCTOR PARA HACER CAMBIOS
  public LeeRegistro(DefaultTableModel _modelo ,String []datos ,int _renglon)
  {
      modelo = _modelo;
      construyeVentana();
      renglon = _renglon;

      // ASIGNA LOS COMPONENTES EN EL PANEL DE BOTONES
      vRfc.setText(datos[0]);
      vNombre.setText(datos[1]);
      vDireccion.setText(datos[2]);
      vTelefono.setText(datos[3]);
      hacerCambio = true;
  }
}

class PantallaAbc extends JFrame
{
// PANEL AJUSTABLE PARA LA PANTALLA
JSplitPane panelAjustable = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

// DATOS DE LA TABLA
DefaultTableModel modelo = new DefaultTableModel(0,4);
JTable tabla = new JTable(modelo);
JScrollPane panelScroll = new JScrollPane(tabla);
JPanel marcoTabla = new JPanel(new GridLayout(1,1));

// BOTONES DE ACTUALIZACION
JButton alta = new JButton("ALTA");
ActionListener altaAccion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
  LeeRegistro nuevo = new LeeRegistro(modelo);

      nuevo.setSize(300,200);
      nuevo.show();
  }
};

JButton baja = new JButton("BAJA");
ActionListener bajaAccion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
  int ultimo ,seleccion ;

      ultimo = tabla.getRowCount();
      seleccion = tabla.getSelectedRow();
      if( seleccion != -1 )
          modelo.removeRow(seleccion);
  }
};

JButton cambio = new JButton("CAMBIO");
ActionListener cambioAccion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
  int seleccion ;

      seleccion = tabla.getSelectedRow();
      if( seleccion != -1 )
      {
      String [] datos = new String[4];

          datos[0] = (String)modelo.getValueAt(seleccion ,0);
          datos[1] = (String)modelo.getValueAt(seleccion ,1);
          datos[2] = (String)modelo.getValueAt(seleccion ,2);
          datos[3] = (String)modelo.getValueAt(seleccion ,3);
          LeeRegistro nuevo = new LeeRegistro(modelo, datos ,seleccion);
          nuevo.setSize(300,200);
          nuevo.show();
      }
  }
};

JButton salir = new JButton("SALIR");
ActionListener salirAccion = new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      dispose();
  }
};

// PANEL PARA COLOCAR LOS BOTONES
JPanel panelBotones = new JPanel(new FlowLayout());

  // CONSTRUCTOR
  public PantallaAbc()
  {
  Container cont = getContentPane();

      // COLOCA LOS ENCABEZADOS DE LAS COLUMNAS EN LA TABLA
      tabla.getColumnModel().getColumn(0).setHeaderValue("Rfc");
      tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre");
      tabla.getColumnModel().getColumn(2).setHeaderValue("Direccion");
      tabla.getColumnModel().getColumn(3).setHeaderValue("Telefono");

      // LA SELECCION DE ELEMENTOS ES SIMPLE
      tabla.setSelectionMode((int)ListSelectionModel.SINGLE_SELECTION);

      // ASIGNA LA TABLA EN EL PANEL DE MARCO
      panelScroll.setPreferredSize(new Dimension(400, 300));
      panelScroll.setMinimumSize(new Dimension(300, 200));
      marcoTabla.setBorder(BorderFactory.createTitledBorder("Persona"));
      marcoTabla.add(panelScroll);

      // ASIGNA LOS BOTONES DE ACCION EN EL PANEL DE BOTONES
      panelBotones.add(alta);
      panelBotones.add(baja);
      panelBotones.add(cambio);
      panelBotones.add(salir);

      // COLOCA LOS PANELES EN LA PANTALLA
      panelAjustable.add(marcoTabla);
      panelAjustable.add(panelBotones);

      // AGREGA EL panelAjustable EN EL PANEL PRINCIPAL CENTRADO
      cont.setLayout(new BorderLayout());
      cont.add(panelAjustable, BorderLayout.CENTER);

      // ASIGNA LAS ACCIONES
      alta.addActionListener(altaAccion);
      salir.addActionListener(salirAccion);
      baja.addActionListener(bajaAccion);
      cambio.addActionListener(cambioAccion);

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

class Abc
{
  public static void main(String args[])
  {
  PantallaAbc captura = new PantallaAbc();

      captura.setSize(400, 300);
      captura.show();
  }
}