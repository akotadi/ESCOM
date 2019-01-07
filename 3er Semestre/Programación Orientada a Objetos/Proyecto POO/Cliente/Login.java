// LIBRERÍAS
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{

	// VARIABLES
	JButton bLogin, bClose; // Botones de la ventana
	JLabel lUsuario, lContraseña, lConnectionText, lTittle; // Etiquetas para colocar texto
	JTextField tUsuario, tContraseña; // Campos de entrada para leer texto
	JPanel p1,p2; // Paneles para contener otros elementos
	getConnectionClient connect; // Conexión con el servidor

	// CONSTRUCTOR
	public Login(getConnectionClient connect){

		// INICIALIZACIÓN PARA LA VENTANA
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Añade el cierre predeterminado de la ventana
        setTitle("Evaluador Examen"); // Coloca título a la ventana
        setResizable(false); // Evita que el usuario cambie el tamaño predeterminado
		getContentPane().setLayout(null); // Inicializa el contenedor sin layout permitiéndonos trabajar libremente
		this.connect = connect; // Inicializa la conexión de la clase por medio de la recibida mediante el constructor
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // Tomamos las medidas de la pantalla del usuario
        setBounds((screenSize.width-300)/2, (screenSize.height-250)/2, 300, 250); // Colocamos la ventana en el centro

        // INICIALIZACIÓN DE COMPONENTES

		lTittle = new JLabel("Iniciar Sesión"); // Inicia una etiqueta con un texto predeterminado
		getContentPane().add(lTittle); // Añadimos la etiqueta a la ventana
		lTittle.setBounds(300/2-50, 20, 150, 30); // Colocamos la etiqueta en el lugar deseado de la ventana
		
		p1 = new JPanel(); // Inicializa un panel donde agregaremos los campos de entrada
		p1.setLayout(new GridLayout(4,1)); // Establecemos un diseño predeterminado
		lUsuario = new JLabel("Usuario: "); // Inicializamos una etiqueta para el usuario con un texto predeterminado
		tUsuario = new JTextField(30); // Inicializamos un campo de entrada para el usuario
		lContraseña = new JLabel("Contraseña: "); // Inicializamos una etiqueta para la contraseña con un texto predeterminado
		tContraseña = new JTextField(20); // Inicializamos un campo de entrada para la contraseña
		p1.add(lUsuario);						p1.add(tUsuario); // Añadimos los campos referentes al usuario al panel
		p1.add(lContraseña);					p1.add(tContraseña); // Añadimos los campos referentes a la contraseña al panel
		getContentPane().add(p1); // Añadimos el panel a la ventana
		p1.setBounds(30, 60, 240, 100); // Colocamos el panel en el lugar deseado de la ventana

		p2 = new JPanel(); // Inicializa un panel donde agregaremos los campos de entrada
		p2.setLayout(new BorderLayout()); // Establecemos un diseño predeterminado
		lConnectionText = new JLabel(" "); // Inicializa una etiqueta en blanco por si necesitásemos mostrar texto
		bLogin = new JButton("Login");			bLogin.addActionListener(this); // Inicializa un botón con un texto predeterminado y se añade el método de escucha
		bClose = new JButton("Close");			bClose.addActionListener(this); // Inicializa un botón con un texto predeterminado y se añade el método de escucha
		p2.add(lConnectionText, BorderLayout.NORTH); // Dentro del diseño predeterminado, colocamos la etiqueta en la parte norte
		p2.add(bLogin, BorderLayout.WEST); // Dentro del diseño predeterminado, colocamos la etiqueta en la parte oeste
		p2.add(bClose, BorderLayout.EAST); // Dentro del diseño predeterminado, colocamos la etiqueta en la parte este
		getContentPane().add(p2); // Añadimos el panel a la ventana
		p2.setBounds(300/2-120, 180, 240, 40); // Colocamos el panel en el lugar deseado de la ventana

	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if(selected == bLogin){
			String usuario, contraseña;
			usuario = tUsuario.getText();
			contraseña = tContraseña.getText();
			if (usuario.length()>2 && contraseña.length()>2) {
				int i = 0;
				i = connect.LoginAdmin(usuario,contraseña);
				if (i == -1) {
					System.out.println("Admin not connected");
					i = connect.LoginUser(usuario, contraseña);
					if (i == -1) {
						System.out.println("User not connected");
					}
					else{
						GUIChoose choose = new GUIChoose(i,connect);
						this.dispose();
						choose.setVisible(true);
					}
				}
				else{
					GUIAdmin admin = new GUIAdmin(i,connect);
					this.dispose();
					admin.setVisible(true);
				}
			}
			else
				System.out.println("Please fill all the fields.\n");		
		}
		else if (selected == bClose) {
			int x = JOptionPane.showConfirmDialog(null,"¿Estás seguro?","Ventana de Confirmación",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				connect.closeConnectionClient();
				System.exit(0);
			}
		}
	}
}