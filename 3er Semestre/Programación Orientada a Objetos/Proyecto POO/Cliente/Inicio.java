import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

class Inicio extends JFrame implements ActionListener{
	JButton bLogin, bSignIn, bClose;
	JLabel lTittle;
	JPanel p1,p2;
	public boolean stateConnection;
	getConnectionClient connect;

	public Inicio(getConnectionClient connect){

		bLogin = new JButton("Login");					bLogin.addActionListener(this);
		bSignIn = new JButton("Sign In");		bSignIn.addActionListener(this);
		bClose = new JButton("Close");						bClose.addActionListener(this);
		lTittle = new JLabel("Evaluador Examen");

		this.connect = connect;

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Evaluador Examen");
        setResizable(false);
		getContentPane().setLayout(null);

		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		getContentPane().add(lTittle);	
		lTittle.setBounds(300/2-60, 30, 150, 30);
		
		p2.add(bLogin, BorderLayout.WEST);
		p2.add(bSignIn, BorderLayout.EAST);
		p2.add(bClose, BorderLayout.SOUTH);
		getContentPane().add(p2);
		p2.setBounds(300/2-70, 100, 150, 60);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-300)/2, (screenSize.height-200)/2, 300, 200);
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if (selected == bLogin) {
			Login log = new Login(connect);
			this.dispose();
			log.setVisible(true);
		}
		else if(selected == bSignIn){
			SignIn sign = new SignIn(connect);
			this.dispose();
			sign.setVisible(true);
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