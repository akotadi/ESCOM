import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.net.*;
import java.io.*;

public abstract class Ventana extends JFrame implements ActionListener{
	JPanel pClient, pServer;
	JButton bExit;
	JLabel lTittle, lClient, lServer;

	private javax.swing.JList<String> lClientFiles;
	DefaultListModel clientListItems = new DefaultListModel();
	JScrollPane jScrollPane1;
	JPanel pButtonsClient;
	JButton bSend, bErase, bAdd;

	public static javax.swing.JList<String> lServerFiles;
	DefaultListModel serverListItems = new DefaultListModel();
	JScrollPane jScrollPane2;
	JPanel pButtonsServer;
	JButton bActualizate, bDownload;

	public DataInputStream dis = null;
	public DataOutputStream dos = null;
	public Socket client = null;
	public Client cl;

	public static java.util.List<String> serverContent = new ArrayList<>();
	public static java.util.List<File> filesToSend = new ArrayList<>();

	public Ventana(Socket client, DataInputStream dis, DataOutputStream dos){

		this.client = client;
		this.dis = dis;
		this.dos = dos;
		// cl = new Client(client, dis, dos);
		// cl = new Client();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Practica 1");
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent evt) {
				formWindowOpened(evt);
			}
		});
		getContentPane().setLayout(null);

		lTittle = new JLabel("Transferencia de archivos",SwingConstants.CENTER);
		getContentPane().add(lTittle);	
		lTittle.setBounds(700/2-(150/2), 10, 150, 30);

		/*	-- Send -- */
		lClient = new JLabel("Archivos a subir:");
		getContentPane().add(lClient);
		lClient.setBounds(30,60,150,20);

		lClientFiles = new javax.swing.JList<>();
		lClientFiles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		lClientFiles.setEnabled(true);
		jScrollPane1 = new JScrollPane(lClientFiles);
		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(350/2-(300/2),80,300,250);

		pButtonsClient = new JPanel();
		pButtonsClient.setLayout(new GridLayout(1,3));
		bSend = new JButton("Enviar");
		bSend.addActionListener(this);
		bErase = new JButton("Borrar");
		bErase.addActionListener(this);
		bAdd = new JButton("Anadir");
		bAdd.addActionListener(this);
		pButtonsClient.add(bAdd);
		pButtonsClient.add(bErase);
		pButtonsClient.add(bSend);
		getContentPane().add(pButtonsClient);
		pButtonsClient.setBounds(350/2-(250/2),350,250,20);

		/*	-- Download -- */
		lServer = new JLabel("Archivos disponibles:");
		getContentPane().add(lServer);
		lServer.setBounds(30+330,60,150,20);

		lServerFiles = new javax.swing.JList<>();
		lServerFiles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		lServerFiles.setEnabled(true);
		jScrollPane2 = new JScrollPane(lServerFiles);
		getContentPane().add(jScrollPane2);
		jScrollPane2.setBounds(350/2-(300/2)+330,80,300,250);

		pButtonsServer = new JPanel();
		pButtonsServer.setLayout(new GridLayout(1,2));
		bActualizate = new JButton("Actualizar");
		bActualizate.addActionListener(this);
		bDownload = new JButton("Descargar");
		bDownload.addActionListener(this);
		pButtonsServer.add(bDownload);
		pButtonsServer.add(bActualizate);
		getContentPane().add(pButtonsServer);
		pButtonsServer.setBounds(350/2-(250/2)+330,350,250,20);

		bExit = new JButton("Salir");
		bExit.addActionListener(this);
		getContentPane().add(bExit);
		bExit.setBounds(700/2-(100/2),390,100,20);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocation(150, 150);
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width-700)/2, (screenSize.height-450)/2, 700, 450);
		setVisible(true);
	}

	public abstract void actionPerformed(ActionEvent e);

	private void formWindowOpened(java.awt.event.WindowEvent evt) {
		setLocationRelativeTo(null);
		new FileDrop(lClientFiles, new FileDrop.Listener(){
			public void filesDropped(File[] files) {
				for (File file : files) {
					clientListItems.addElement(file.getAbsolutePath());
				}
			}
		});
		lServerFiles.setModel(serverListItems);
		lClientFiles.setModel(clientListItems);
	}
}