import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

class GUIAdmin extends JFrame implements ActionListener{
	JButton bMateria, bReactivo, bClose;
	JLabel lUsuario, lContraseña, lConnectionText, lTittle, lInstruction;
	JTextField tUsuario, tContraseña;
	JPanel p1,p2;
	getConnectionClient connect;
	int idAdmin;

	public GUIAdmin(int idAdmin, getConnectionClient connect){

		bMateria = new JButton("Materia");					bMateria.addActionListener(this);
		bReactivo = new JButton("Reactivo");		bReactivo.addActionListener(this);
		bClose = new JButton("Close");						bClose.addActionListener(this);
		lTittle = new JLabel("Panel Administrador");
		

		this.connect = connect;
		this.idAdmin = idAdmin;

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Evaluador Examen");
        setResizable(false);
		getContentPane().setLayout(null);

		
		getContentPane().add(lTittle);	
		lTittle.setBounds(300/2-70, 30, 150, 30);

		p2 = new JPanel();
		p2.setBorder(BorderFactory.createTitledBorder("Qué desea modificar:"));
		p2.setLayout(new BorderLayout());
		p2.add(bMateria, BorderLayout.WEST);
		p2.add(bReactivo, BorderLayout.EAST);
		getContentPane().add(p2);
		p2.setBounds(40,80,220,50);

		getContentPane().add(bClose);	
		bClose.setBounds(300/2-60, 150, 100, 30);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-300)/2, (screenSize.height-200)/2, 300, 200);
	}

	public static void main(String[] args) {
		new GUIAdmin(7,new getConnectionClient("localhost",1025)).setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if (selected == bMateria) {
			GUIMateria materia = new GUIMateria(connect,idAdmin);
			this.dispose();
			materia.setVisible(true);
		}
		else if(selected == bReactivo){
			GUIReactivo reactivo = new GUIReactivo(idAdmin,connect);
			this.dispose();
			reactivo.setVisible(true);
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