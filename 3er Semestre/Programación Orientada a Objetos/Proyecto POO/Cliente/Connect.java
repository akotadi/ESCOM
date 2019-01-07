// Librerías
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

class Connect extends JFrame implements ActionListener{

	// Variables del programa
	JButton bConnect, bClose;
	JLabel lIP, lPort, lConnectionText, lTittle;
	JTextField tIP, tPort;
	JPanel p1,p2;
	getConnectionClient connect;

	public Connect(){

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Colocamos el cerrar por default de la ventana
        setTitle("Evaluador Examen"); // Colocamos título a la ventana
        setResizable(false); // Evitamos que el usuario pueda editar el tamaño de texto
		getContentPane().setLayout(null); // Colocamos la ventana para una edición libre dentro de su tamaño

		lTittle = new JLabel("Evaluador de Examen"); // Inicializamos una etiqueta
		getContentPane().add(lTittle); // La agregamos a la ventana
		lTittle.setBounds(300/2-80, 20, 150, 30); // 

		bConnect = new JButton("Connect");			bConnect.addActionListener(this);
		bClose = new JButton("Close");			bClose.addActionListener(this);
		lIP = new JLabel("IP: ");
		tIP = new JTextField(30);
		lPort = new JLabel("Puerto: ");
		tPort = new JTextField(20);
		lConnectionText = new JLabel(" ");
		p1 = new JPanel();
		p2 = new JPanel();
		p1.setLayout(new GridLayout(4,1));
		p2.setLayout(new BorderLayout());
		p1.add(lIP);						p1.add(tIP);
		p1.add(lPort);					p1.add(tPort);
		getContentPane().add(p1);
		p1.setBounds(30, 60, 240, 100);

		p2.add(lConnectionText, BorderLayout.NORTH);
		p2.add(bConnect, BorderLayout.WEST);
		p2.add(bClose, BorderLayout.EAST);
		getContentPane().add(p2);
		p2.setBounds(300/2-120, 180, 240, 40);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-300)/2, (screenSize.height-250)/2, 300, 250);
	}

	public static void main(String[] args) {
		new Connect().setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if(selected == bConnect){

			String ip = tIP.getText();
			int port = Integer.parseInt(tPort.getText());
			
			try{
				connect = new getConnectionClient(ip,port);
				System.out.println("Connection with server established.\n");
				Inicio start = new Inicio(connect);
				this.dispose();
				start.setVisible(true);
			}
			catch (EmptyStackException ems){
				System.out.println("Connection Failed.\n");
			}
		}
		else if (selected == bClose) {
			int x = JOptionPane.showConfirmDialog(null,"¿Estás seguro?","Ventana de Confirmación",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				System.exit(0);
			}
		}
	}
}