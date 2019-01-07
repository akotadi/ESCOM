import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends Ventana implements ActionListener{

	public static String clientPath = "Archivos_Cliente";
	public static String sep = "/";
	public static int buf_size = 1024;

	public static java.util.List<String> pendingDownloads = new ArrayList<>();
	public static Scanner sc = new Scanner(System.in);

	public Client(Socket client, DataInputStream dis, DataOutputStream dos){
		super(client, dis, dos);
	}

	private void sendFile(File file, String name) throws Exception {
		if(!file.exists()) return;
		long size = file.length();
		DataInputStream fileInput = new DataInputStream(new FileInputStream(file.getAbsolutePath()));
		System.out.println("Enviando " + name + " de " + size + " bytes:");
		long sent = 0;
		int read = 0;
		double percent = 0;
		dos.writeUTF(name);
		dos.flush();
		dos.writeLong(size);
		dos.flush();
		byte buffer[] = new byte[buf_size];
		while (sent < size) {
			read = fileInput.read(buffer);
			dos.write(buffer, 0, read);
			dos.flush();
			sent += read;
			percent = (double) sent / (double) size * 100.0;
			System.out.print(String.format("\rEnviado el %.2f%% del archivo. ", percent));
		}
		fileInput.close();
		System.out.println("\nArchivo " + name + " enviado.\n");
	}

	private boolean receiveFile(String rootPath) throws Exception {
		String name = dis.readUTF();
		if(name.compareTo(sep) == 0) return false;
		long size = dis.readLong();
		String newDirectory = rootPath + name.substring(0, name.lastIndexOf(sep));
		newDirectory = newDirectory.replace(sep, File.separator);
		new File(newDirectory).mkdirs();

		String newName = rootPath + name.replace(sep, File.separator);
		DataOutputStream outputFile = new DataOutputStream(new FileOutputStream(newName));
		System.out.println("Recibiendo " + name + " de " + size + " bytes:");
		int read = 0;
		long received = 0;
		double percent = 0;
		byte buffer[] = new byte[buf_size];
		while (received < size) {
			read = dis.read(buffer, 0, (int) Math.min(buf_size, size - received));
			outputFile.write(buffer, 0, read);
			outputFile.flush();
			received += read;
			percent = (double) received / (double) size * 100.0;
			System.out.print(String.format("\rRecibido el %.2f%% del archivo.", percent));
		}
		outputFile.close();
		System.out.println("\nArchivo " + name + " recibido.\n");
		return true;
	}

	public void sendFolder(File folder, String relativePath) throws Exception {
		for (File file : folder.listFiles()) {
			if (file.isFile())
				sendFile(file, relativePath + sep + file.getName());
			else if (file.isDirectory())
				sendFolder(file, relativePath + sep + file.getName());
		}
	}

	public void sendContent() throws Exception {
		for (File f : filesToSend)
			if (f.isFile())
				sendFile(f, sep + f.getName());
			else if (f.isDirectory())
				sendFolder(f, sep + f.getName());
		dos.writeUTF(sep);
		dos.flush();
		filesToSend = new ArrayList<>();
	}

	public void receiveContentInfo() throws Exception {
		serverContent.clear();
		while (true) {
			String name = dis.readUTF();
			if (name.compareTo(sep) == 0)
				break;
			serverContent.add(name);
			System.out.println(name);
		}
	}

	public void downloadPendingFiles() throws Exception {
		for (String f : lServerFiles.getSelectedValuesList()) {
			dos.writeUTF(f);
			dos.flush();
		}
		dos.writeUTF(sep);
		dos.flush();
		while(receiveFile(clientPath));
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if (selected == bAdd) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setMultiSelectionEnabled(true);
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setDialogTitle("Escoge archivos y carpetas para enviar");
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				File[] files = chooser.getSelectedFiles();
				for(File f : files){
					clientListItems.addElement(f.getAbsolutePath());
				}
			}
		}
		if (selected == bErase) {
			clientListItems.clear();
		}
		if (selected == bSend) {
			for(Object str : clientListItems.toArray()){
				File f = new File((String)str);
				if(f.exists()) filesToSend.add(f);
			}
			try{
				dos.writeInt(0);
				dos.flush();
				sendContent();
				clientListItems.clear();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		if (selected == bActualizate) {
			try{
				dos.writeInt(1);
				dos.flush();
				serverContent.clear();
				receiveContentInfo();
				serverListItems.clear();
				for(String f : serverContent){
					serverListItems.addElement(f);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		if (selected == bDownload) {
			try{
				dos.writeInt(2);
				dos.flush();
				downloadPendingFiles();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		if (selected == bExit){
			int x = JOptionPane.showConfirmDialog(null,"Estas seguro?","Ventana de confirmacion",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				try{
					dos.writeInt(3);
					dos.flush();
					dos.close();
					dis.close();
					client.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
				System.exit(0);
			}
		}
	}
}