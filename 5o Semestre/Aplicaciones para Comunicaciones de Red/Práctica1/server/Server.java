import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class Server {
	public static DataInputStream dis = null;
	public static DataOutputStream dos = null;
	public static int port = 9999;
	public static String serverPath = "Archivos_Servidor";
	public static String sep = "/";
	public static int buf_size = 1024;

	public static void sendAllFilePathsDFS(File path, String relativePath) throws Exception {
		if (path.isFile()) {
			dos.writeUTF(relativePath);
			dos.flush();
		}
		else if (path.isDirectory())
			for (File file : path.listFiles())
			sendAllFilePathsDFS(file, relativePath + sep + file.getName());
	}

	public static void sendAllFilePaths() throws Exception {
		sendAllFilePathsDFS(new File(serverPath), "");
		dos.writeUTF(sep);
		dos.flush();
	}

	public static void sendFiles() throws Exception {
		List<String> toSend = new ArrayList<>();
		while (true) {
			String path = dis.readUTF();
			if (path.compareTo(sep) == 0)
				break;
			toSend.add(path);
		}
		for(String f : toSend){
			File file = new File(serverPath + f);
			String name = f;

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
		dos.writeUTF(sep);
		dos.flush();
	}

	public static boolean receiveFile(String rootPath, DataInputStream input) throws Exception {
		String name = input.readUTF();
		if(name.compareTo(sep) == 0) return false;
		long size = input.readLong();
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
			read = input.read(buffer, 0, (int) Math.min(buf_size, size - received));
			outputFile.write(buffer, 0, read);
			outputFile.flush();
			received += read;
			percent = (double) received / (double) size * 100.0;
			System.out.print(String.format("\rRecibido el %.2f%% del archivo.", percent));
		}
		outputFile.close();
		System.out.println("\nFinalizo la transferencia de " + name + "\n");
		return true;
	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		ServerSocket server = new ServerSocket(port);
		System.out.println("Conexion establecida, esperando clientes...\n");
		while (true) {
			Socket client = server.accept();
			System.out.println("Llego un cliente desde " + client.getInetAddress() + ":" + client.getPort() + ", esperando su accion.\n");
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			while (true) {
				try{
					int action = dis.readInt();
					if (action == 0)
						while(receiveFile(serverPath, dis));
					else if (action == 1)
						sendAllFilePaths();
					else if (action == 2)
						sendFiles();
					else if (action == 3) {
						dis.close();
						dos.close();
						System.out.println("Se desconecto el cliente " + client.getInetAddress() + ":" + client.getPort() + "\n");
						client.close();
						break;
					}
				}catch(Exception e){
					System.out.println("Ocurrio un error con el cliente, desconectando...");
					e.printStackTrace();
					break;
				}
			}
		}
	}
}