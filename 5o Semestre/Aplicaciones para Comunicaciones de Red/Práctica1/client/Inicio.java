import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Inicio extends JFrame implements ActionListener{
	JPanel pButtons, pConnection;
	JTextField tDirection, tPort;
	JButton bConnect, bExit;
	JLabel lDirection, lPort, lTittle;

	public DataInputStream dis = null;
	public DataOutputStream dos = null;
	public Socket client = null;

	public Inicio(){

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Practica 1");
        setResizable(false);
		getContentPane().setLayout(null);

		lTittle = new JLabel("Iniciar Conexion",SwingConstants.CENTER);
		getContentPane().add(lTittle);	
		lTittle.setBounds(700/2-(150/2), 10, 150, 30);

		pConnection = new JPanel();
		pConnection.setLayout(new GridLayout(4,1));
		lDirection = new JLabel("Direccion: ");
		pConnection.add(lDirection);
		tDirection = new JTextField();
		pConnection.add(tDirection);
		lPort = new JLabel("Puerto: ");
		pConnection.add(lPort);
		tPort = new JTextField();
		pConnection.add(tPort);
		getContentPane().add(pConnection);
		pConnection.setBounds(700/2-(600/2),50,600,150);

		pButtons = new JPanel();
		pButtons.setLayout(new BorderLayout());
		bConnect = new JButton("Conectar");
		bConnect.addActionListener(this);
		bExit = new JButton("Salir");
		bExit.addActionListener(this);
		pButtons.add(bConnect, BorderLayout.WEST);
		pButtons.add(bExit, BorderLayout.EAST);
		getContentPane().add(pButtons);
		pButtons.setBounds(700/2-(200/2),230,200,50);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocation(150, 150);
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width-700)/2, (screenSize.height-350)/2, 700, 350);
        setVisible(true);
	}

	public static void main(String[] args) {
		Inicio frame = new Inicio();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(150, 150);
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-700)/2, (screenSize.height-350)/2, 700, 350);
        frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if (selected == bConnect) {
			try{
				client = new Socket(tDirection.getText(), Integer.parseInt(tPort.getText()));
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				Client ventana = new Client(client, dis, dos);
				ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
				ventana.pack();
				ventana.setLocation(150, 150);
				java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				ventana.setBounds((screenSize.width-700)/2, (screenSize.height-450)/2, 700, 450);
				this.setVisible(false);
				ventana.setVisible(true);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Error en la conexion.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if (selected == bExit){
			int x = JOptionPane.showConfirmDialog(null,"Estas seguro?","Ventana de confirmacion",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				System.exit(0);
			}
		}
	}
}