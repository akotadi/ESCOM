
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.table.*;
import java.sql.*;
//import javax.swing.table.DefaultTableCellRenderer;

public class Vista extends JApplet implements java.awt.event.ActionListener {
    PerroBean pb;
    JPanel pnlRaiz;                // Panel principal
    JTabbedPane pnlTab;            // Panel contenedor de pestañas

    //// Panel de formulario
    JPanel pnlFormulario;
    JComboBox cbbDriver;
    JComboBox cbbHost;
    
    JTextField txtBasedatos;
    JTextField txtTabla;
    JTextField txtLogin;
    JTextField txtPwd;
    JButton btnConexion;
    String BOTON_CONEXION = "conexion";

    //// Panel de consulta de datos
    JPanel pnlConsulta;
    JPanel pnlAdd;
    JPanel pnlBorra;
    JPanel pnlTablas;
    JTable tabla;
    JButton btnActualizar;
    String BOTON_ACTUALIZAR = "Actualizar"; 

    JButton btnInsertar;
    String BOTON_INSERTAR = "Insertar"; 
    
    JTextField txtBorra;
    JButton btnBorrar;
    String BOTON_BORRAR = "Borrar"; 

    JComboBox cbbTablas;
    static JFrame f;
    modelo mod;    // modelo de datos
    JTableHeader header;
    ImageIcon imageIcon;
    PanelHojaProp psp;
    public void init() {
       //f.addWindowListener(new WindowHandler());
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       try {
	    pnlRaiz = (JPanel)this.getContentPane();
	    pnlRaiz.setLayout(new BorderLayout());

	    pnlTab = new JTabbedPane();
	    pnlRaiz.add(pnlTab, BorderLayout.CENTER);

	    //// Crea un modelo vacio (no hay conexión a la base de datos todavía)
	    mod = new modelo();

	    pnlFormulario = crearPanelFormulario();
	    pnlTab.addTab( "Conexión", pnlFormulario );

	    pnlConsulta = crearPanelConsulta( mod );
	    pnlTab.addTab( "Consulta", pnlConsulta );

            pnlAdd = crearPanelAdd( );
	    pnlTab.addTab( "Add", pnlAdd );
	   
	    pnlBorra = crearPanelBorra( );
	    pnlTab.addTab( "Borra", pnlBorra );
	}
	catch ( Exception e ) {
	    e.printStackTrace();
	}
    }

    private  JPanel crearPanelFormulario() {
	JPanel pnlF = new JPanel();
	pnlF.setLayout( new BorderLayout() );

	JPanel pnlSuperior = new JPanel();
	pnlSuperior.setLayout( new BoxLayout( pnlSuperior, BoxLayout.PAGE_AXIS));

	JPanel pnlFila1 = new JPanel( new FlowLayout( FlowLayout.LEFT));
	JLabel lblDriver = new JLabel( "Driver:" );
	Vector drivers = new Vector();
	drivers.add("com.mysql.jdbc.Driver");     // Local
	drivers.add("org.gjt.mm.mysql.Driver");   // Remoto
	cbbDriver = new JComboBox( drivers );
	pnlFila1.add( lblDriver );
	pnlFila1.add( cbbDriver );
	pnlSuperior.add( pnlFila1 );

	JPanel pnlFila2 = new JPanel(new FlowLayout( FlowLayout.LEFT));
	JLabel lblHost = new JLabel( "Host:" );
	Vector hosts = new Vector();
        hosts.add("jdbc:mysql://localhost:3306/");     // Local
        hosts.add("jdbc:mysql:///");     // Local
	hosts.add("jdbc:mysql://proactiva-calidad.com:3306/");   // Remoto
	cbbHost = new JComboBox( hosts );
	pnlFila2.add( lblHost );
	pnlFila2.add( cbbHost );
	pnlSuperior.add( pnlFila2 );

	JPanel pnlFila3 = new JPanel(new FlowLayout( FlowLayout.LEFT));
JLabel lblBasedatos = new JLabel( "Base de datos:" );
	txtBasedatos = new JTextField("classicmodels");
	pnlFila3.add( lblBasedatos );
	pnlFila3.add( txtBasedatos );
	pnlSuperior.add( pnlFila3 );

	JPanel pnlFila4 = new JPanel(new FlowLayout( FlowLayout.LEFT));
	JLabel lblTabla = new JLabel( "Tabla:" );
	txtTabla = new JTextField(10);
	txtTabla.setText("customers");
	pnlFila4.add( lblTabla );
	pnlFila4.add( txtTabla );
	pnlSuperior.add( pnlFila4 );

	JPanel pnlFila5 = new JPanel(new FlowLayout( FlowLayout.LEFT));
	JLabel lblLogin = new JLabel( "Login:" );
	txtLogin = new JTextField("root");
	pnlFila5.add( lblLogin );
	pnlFila5.add( txtLogin );
	pnlSuperior.add( pnlFila5 );

	JPanel pnlFila6 = new JPanel(new FlowLayout( FlowLayout.LEFT));
	JLabel lblPwd = new JLabel( "Password:" );
	txtPwd = new JTextField("gatito");


	pnlFila6.add( lblPwd );
	pnlFila6.add( txtPwd );
	pnlSuperior.add( pnlFila6 );

	btnConexion = new JButton( "Pulse aquí para la conexion" );
	btnConexion.setActionCommand( BOTON_CONEXION );
	btnConexion.addActionListener( this );

	pnlF.add( pnlSuperior, BorderLayout.CENTER );
	pnlF.add( btnConexion, BorderLayout.SOUTH );
	return pnlF;
    }

    private JPanel crearPanelConsulta( modelo mod) {
	JPanel pnlCon = new JPanel(new BorderLayout());
	tabla = new JTable( mod);
	JScrollPane pnlScroll = new JScrollPane( tabla );
	btnActualizar = new JButton( "Pulse aquí para actualizar la vista");
	btnActualizar.setActionCommand( BOTON_ACTUALIZAR );
	btnActualizar.addActionListener( this );

	pnlCon.add( pnlScroll, BorderLayout.CENTER);
	pnlCon.add( btnActualizar, BorderLayout.SOUTH);
	return pnlCon;
    }
    private JPanel crearPanelAdd() {
	JPanel pnl_Add = new JPanel(new BorderLayout());
	psp=new PanelHojaProp(f); 
        pb= new PerroBean("firulais","fox",2,"M");
    	psp.setTarget(pb);
	pnl_Add.add( psp, BorderLayout.CENTER);
        btnInsertar = new JButton(BOTON_INSERTAR);
	btnInsertar.setActionCommand( BOTON_INSERTAR );
	btnInsertar.addActionListener( this );
	pnl_Add.add( btnInsertar, BorderLayout.SOUTH );
	return pnl_Add;
    }
    private JPanel crearPanelBorra() {
	JPanel pnl_Borra = new JPanel();
        JPanel pnl1 = new JPanel(new FlowLayout( FlowLayout.LEFT));
	pnl_Borra.setLayout( new BorderLayout() );
        txtBorra = new JTextField(20);
        pnl1.add( new JLabel("Nombre:"));
	pnl1.add( txtBorra);
        pnl_Borra.add( pnl1, BorderLayout.CENTER);
        btnBorrar = new JButton(BOTON_BORRAR);
	btnBorrar.setActionCommand( BOTON_BORRAR );
	btnBorrar.addActionListener( this );
	pnl_Borra.add( btnBorrar, BorderLayout.SOUTH );
	return pnl_Borra;
    }
    public void actionPerformed(ActionEvent e) {

	//// Botón de conexión
	if ( e.getActionCommand().compareTo( BOTON_CONEXION ) == 0 ) {
	    String hostYbase = (String)cbbHost.getSelectedItem() + txtBasedatos.getText();
	    if ( !mod.conexion( (String)cbbDriver.getSelectedItem(), hostYbase, txtTabla.getText(),
				txtLogin.getText(), txtPwd.getText() ) ) {
		JOptionPane.showMessageDialog( null, mod.obt_mensaje_error(),"Error", JOptionPane.ERROR_MESSAGE);
	    }
	    //// Si la conexión ok, cambio a panel de consulta
	    else {
		mod.fireTableStructureChanged();
		pnlTab.setSelectedComponent( pnlConsulta );
                tabla.setRowHeight(40);
                
    //tabla.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
                imageIcon = new ImageIcon("java.png");
		SetIcon(tabla, 2, imageIcon,"Home");
                imageIcon = new ImageIcon("database.png");
		SetIcon(tabla, 1, imageIcon,"Home");
		imageIcon = new ImageIcon("php.jpg");
		SetIcon(tabla, 0, imageIcon,"Name");
		header = tabla.getTableHeader();
		header.setBackground(Color.yellow);                


               // pnlTablas = crearPanelTablas( mod );
	       // pnlTab.addTab( "Tablas", pnlTablas );
	    }
	}
	//// Botón de actualizar
	if ( e.getActionCommand().compareTo( BOTON_ACTUALIZAR ) == 0 ) {
	    if ( !mod.actualizar( txtTabla.getText() ) )
		JOptionPane.showMessageDialog( null, mod.obt_mensaje_error(),"Error", JOptionPane.ERROR_MESSAGE);
	}
        if ( e.getActionCommand().compareTo( BOTON_INSERTAR ) == 0 ) {
		try {
                
		System.err.println("Perro ins "+pb);
       		mod.insertar(pb, "perro") ;
		//control.consulta("*");
		} catch (DAOException daoe){}
        }
        if ( e.getActionCommand().compareTo( BOTON_BORRAR ) == 0 ) {
		try {
                
		System.err.println("Perro borra "+txtBorra.getText());
		pnlTab.setSelectedComponent( pnlConsulta );
       		mod.eliminar("nombre", txtBorra.getText(), "perro") ;
		//control.consulta("*");
		} catch (SQLException daoe){}
        }
    }
public class WindowHandler extends WindowAdapter {
   public void windowClosing(WindowEvent e) {
      System.exit(0);
   }
}
public static void main(String args[]){
	f=new JFrame("JDBC TABLE EXAMPLE");
	Vista vista=new Vista();
	vista.init();
	vista.start();
        	
	f.getContentPane().add(vista, BorderLayout.CENTER);
	f.setSize(700, 500);
	f.setVisible(true);
}
public void SetIcon(JTable table, int col_index, ImageIcon icon,String name){
		table.getTableHeader().getColumnModel().getColumn(col_index).setHeaderRenderer(new iconRenderer());
		table.getColumnModel().getColumn(col_index).setHeaderValue(new txtIcon(name, icon));
	}
/*
class ImageRenderer extends DefaultTableCellRenderer {
  JLabel lbl = new JLabel();

  ImageIcon icon = new ImageIcon(getClass().getResource("rings.jpg"));

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column) {
    lbl.setText((String) value);
    lbl.setIcon(icon);
    return lbl;
  }
*/
public class iconRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object obj,boolean isSelected, boolean hasFocus, int row, int column) {
		txtIcon i = (txtIcon)obj;
            	setIcon(i.imageIcon); setText(i.txt);
            	setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            	setHorizontalAlignment(JLabel.CENTER);
            	return this;
        }
    }
public class txtIcon {
	String txt;
	ImageIcon imageIcon;
	txtIcon(String text, ImageIcon icon) {
		txt = text;
            	imageIcon = icon;
        }
  }
}
