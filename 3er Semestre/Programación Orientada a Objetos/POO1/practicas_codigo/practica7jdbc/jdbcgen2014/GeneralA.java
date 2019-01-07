import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.table.*;
//javac -cp  .:beanutils.jar:commons-logging.jar  GeneralA.java
//java -cp  .:beanutils.jar:commons-logging.jar:mysqlcon.jar  GeneralA
public class GeneralA extends JApplet implements java.awt.event.ActionListener {
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
    JTextField txtCrea;
    JButton btnConexion;
    String BOTON_CONEXION = "conexion";
    JPanel pnlAdd;
    JPanel pnlConsulta;
    JPanel pnlCrea;
    JButton btnInsertar;
    String BOTON_INSERTAR = "insertar"; 
    String BOTON_CREA = "crear"; 
    static JFrame f;
    JTable tabla;
    JButton btnActualizar;
    JButton btnCrea;
    String BOTON_ACTUALIZAR = "actualizar"; 
    Control control;
    Object obj;
    JTableHeader header;
    public void init() {
	try {

	    //// Obtengo y configuro layout de panel raiz
	    pnlRaiz = (JPanel)this.getContentPane();
	    pnlRaiz.setLayout(new BorderLayout());

	    //// Le asigno un TABBEDPANE
	    pnlTab = new JTabbedPane();
	    pnlRaiz.add(pnlTab, BorderLayout.CENTER);

	    //// Crea un modelo vacio (no hay conexión a la base de datos todavía)

            //// Creo el panel de formulario y lo asigno al Tabbed
	    pnlFormulario = crearPanelFormulario();
	    pnlTab.addTab( "Conexión", pnlFormulario );
            String hostYbase = (String)cbbHost.getSelectedItem() + txtBasedatos.getText();
            control = new Control((String)cbbDriver.getSelectedItem(), 
                        hostYbase, txtLogin.getText(),  txtPwd.getText());
	    
            //pnlAdd = crearPanelAdd( );
	    //pnlTab.addTab( "Add", pnlAdd );    
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
	cbbHost = new JComboBox( hosts );
	pnlFila2.add( lblHost );
	pnlFila2.add( cbbHost );
	pnlSuperior.add( pnlFila2 );

	JPanel pnlFila3 = new JPanel(new FlowLayout( FlowLayout.LEFT));
        JLabel lblBasedatos = new JLabel( "Base de datos:" );
	txtBasedatos = new JTextField("test");
	pnlFila3.add( lblBasedatos );
	pnlFila3.add( txtBasedatos );
	pnlSuperior.add( pnlFila3 );

	JPanel pnlFila4 = new JPanel(new FlowLayout( FlowLayout.LEFT));
	JLabel lblTabla = new JLabel( "Tabla:" );
	txtTabla = new JTextField(10);
	txtTabla.setText("PerroBean");
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
    private JPanel crearPanelConsulta(Control control) {
	JPanel pnlCon = new JPanel();
	pnlCon.setLayout( new BorderLayout() );

	tabla = new JTable(control);
        tabla.setRowHeight(40);
        header = tabla.getTableHeader();
	header.setBackground(Color.yellow);    
	JScrollPane pnlScroll = new JScrollPane( tabla );
	btnActualizar = new JButton( "Pulse aquí para actualizar la vista!");
	btnActualizar.setActionCommand( BOTON_ACTUALIZAR );
	btnActualizar.addActionListener( this );

	pnlCon.add( pnlScroll, BorderLayout.CENTER);
	pnlCon.add( btnActualizar, BorderLayout.SOUTH);  
        
	return pnlCon;
    }
    
    private JPanel crearPanelCrea() {
	JPanel pnlCrea = new JPanel();
	pnlCrea.setLayout( new BorderLayout() );  
        JLabel lblCrea = new JLabel( "tabla:" );
	txtCrea = new JTextField("  ");
        pnlCrea.add( lblCrea , BorderLayout.WEST);
	pnlCrea.add( txtCrea , BorderLayout.CENTER);
        btnCrea = new JButton( "Pulse aquí para crear tabla");
	btnCrea.setActionCommand( BOTON_CREA );
	btnCrea.addActionListener( this );
	pnlCrea.add( btnCrea, BorderLayout.SOUTH);  
        return pnlCrea;
    }
    private JPanel crearPanelAdd() {
	JPanel pnl_Add = new JPanel();
	pnl_Add.setLayout( new BorderLayout() );
	try {
        Class c=Class.forName(txtTabla.getText());
    	obj=c.getDeclaredConstructor().newInstance();  
    	System.out.println(obj); 
        } catch (Exception ex){
	}
        //obj= new PerroBean("fido","fox",2,"M", Color.red);
    	//psp.setTarget(obj);
	pnl_Add.add( new PropertySheet(obj)/*psp*/, BorderLayout.CENTER);
        btnInsertar = new JButton(BOTON_INSERTAR);
	btnInsertar.setActionCommand( BOTON_INSERTAR );
	btnInsertar.addActionListener( this );
	pnl_Add.add( btnInsertar, BorderLayout.SOUTH );
	return pnl_Add;
    }
    public void actionPerformed(ActionEvent e) {
	if ( e.getActionCommand().compareTo( BOTON_CONEXION ) == 0 ) {		
                try {
                        pnlCrea = crearPanelCrea( );
	                pnlTab.addTab( "Crea", pnlCrea );  
                        pnlConsulta = crearPanelConsulta( control );
	    		pnlTab.addTab( "Consulta", pnlConsulta );
                        pnlAdd = crearPanelAdd( );
	                pnlTab.addTab( "Add", pnlAdd );  
                        pnlTab.setSelectedComponent( pnlAdd );                  
                } catch (Exception ex){
		}
	}
        if ( e.getActionCommand().compareTo( BOTON_INSERTAR ) == 0 ) {
		try {
                	System.err.println("Perro ins "+obj);
       			control.insertar(obj, obj.getClass().getName()) ;
			//control.consulta("*");	
		} catch (DAOException daoe){}
        } 
        if ( e.getActionCommand().compareTo( BOTON_ACTUALIZAR ) == 0 ) {
	    if ( !control.cargarDatos(txtTabla.getText()))
		JOptionPane.showMessageDialog( null, control.obt_mensaje_error(),"Error", JOptionPane.ERROR_MESSAGE);
	}
        if ( e.getActionCommand().compareTo( BOTON_CREA ) == 0 ) {
                   control.crear(txtCrea.getText());
        }
    }
    public static void main(String s[]){
	f=new JFrame("JDBC TABLE EXAMPLE");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	GeneralA g=new GeneralA();
	g.init();
	g.start();	
	f.add(g, BorderLayout.CENTER);
	f.setSize(700, 500);
	f.setVisible(true);
     }
}
