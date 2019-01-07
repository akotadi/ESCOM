import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

class SignIn extends JFrame implements ActionListener{
	JButton bSignIn, bClose;
	JLabel lUsuario, lContraseña, lEmail, lConnectionText, lTittle;
	JTextField tUsuario, tContraseña, tEmail;
	JPanel p1,p2,p3;
	JRadioButton rbA, rbU;
	ButtonGroup gButtons;
	getConnectionClient connect;

	public SignIn(getConnectionClient connect){

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Evaluador Examen");
        setResizable(false);
		getContentPane().setLayout(null);
		this.connect = connect;

		lTittle = new JLabel("Registro");
		getContentPane().add(lTittle);
		lTittle.setBounds(400/2-35, 20, 60, 30);
		
		lUsuario = new JLabel("Usuario: ");
		tUsuario = new JTextField(20);
		lEmail = new JLabel("Email: ");
		tEmail = new JTextField(30);
		lContraseña = new JLabel("Contraseña: ");
		tContraseña = new JTextField(20);
		lConnectionText = new JLabel(" ");
		p1 = new JPanel();
		p1.setLayout(new GridLayout(6,1));
		p1.add(lUsuario);						p1.add(tUsuario);
		p1.add(lEmail);							p1.add(tEmail);
		p1.add(lContraseña);					p1.add(tContraseña);
		getContentPane().add(p1);
		p1.setBounds(10, 60, 200, 150);

		rbA = new JRadioButton("Administrador");
		rbU = new JRadioButton("Usuario");
		gButtons = new ButtonGroup();
		gButtons.add(rbA);
		gButtons.add(rbU);
		p3 = new JPanel();
		p3.setBorder(BorderFactory.createTitledBorder("Tipo de Usuario:"));
		p3.setLayout(new GridLayout(2,1));
		p3.add(rbA);
		p3.add(rbU);
		getContentPane().add(p3);
		p3.setBounds(220,95,170,90);

		bSignIn = new JButton("Sign In");			bSignIn.addActionListener(this);
		bClose = new JButton("Close");			bClose.addActionListener(this);
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(lConnectionText, BorderLayout.NORTH);
		p2.add(bSignIn, BorderLayout.WEST);
		p2.add(bClose, BorderLayout.EAST);
		getContentPane().add(p2);
		p2.setBounds(400/2-120, 230, 240, 40);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-300)/2, 400, 300);
	}

	/*
	Descripción: Método encargado de escuchar las acciones en los distintos botones
	Recibe: ActionEvent e (Acción que se realizó en la ventana) 
	Devuelve: 
	Observaciones: 
	*/
	public void actionPerformed(ActionEvent e){

		// Transformamos la acción escuchada en un botón que podamos comparar
		JButton selected = (JButton)e.getSource();

		// En caso de haberse seleccionado el botón de registrar
		if(selected == bSignIn){

			String usuario, email, contraseña;
			usuario = tUsuario.getText(); // Iniciamos la variable usuario tomando el texto introducido en la ventana
			email = tEmail.getText(); // Iniciamos la variable email tomando el texto introducido en la ventana
			contraseña = tContraseña.getText(); // Iniciamos la variable contraseña tomando el texto introducido en la ventana
			
			// Verificamos que el usuario haya introducido valores mínimos en cada campo
			if (usuario.length()>2 && email.length()>2 && contraseña.length()>2) {

				// En caso de haber seleccionado el tipo de usuario "Administrador"
				if (rbA.isSelected()) {
					connect.SignInNewUser("Administrador",usuario,email,contraseña);
					Inicio start = new Inicio(connect);
					this.dispose();
					start.setVisible(true);
				}

				// En caso de haber seleccionado el tipo de usuario "Usuario"
				else if (rbU.isSelected()) {
					connect.SignInNewUser("Usuario",usuario,email,contraseña);
					Inicio start = new Inicio(connect);
					this.dispose();
					start.setVisible(true);
				}

				// En caso de no haber seleccionado tipo de usuario
				else
					System.out.println("Please choose a type of user.\n");
			}
			else
				System.out.println("Please fill all the fields.\n");
		}

		// En caso de haberse seleccionado el botón de cerrar
		else if (selected == bClose) {
			int x = JOptionPane.showConfirmDialog(null,"¿Estás seguro?","Ventana de Confirmación",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				connect.closeConnectionClient();
				System.exit(0);
			}
		}
	}
}