import java.net.*;
import java.io.*;
import java.util.*;
import java.io.Serializable;

public class funciones{
	public static int buffer_size = 60000;
	public static int part_size = 7;

	public static byte[] objectToByteArray(Object obj) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.flush();
		return baos.toByteArray();
	}

	public static Object byteArrayToObject(byte b[]) throws Exception{
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
	}

	public static void sendParts(byte data[], DatagramSocket socket, InetAddress dst, int port) throws Exception{
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		int sent = 0;
		int size = data.length;
		int read = 0;
		int cnt = 0;
		int parts = data.length / part_size;
		if(data.length % part_size > 0) parts++;
		while(sent < size){
			byte tmp[] = new byte[Math.min(part_size, size - sent)];
			read = bais.read(tmp);
			Dato d = new Dato(tmp, sent, cnt++, size);
			sent += read;
			byte b[] = objectToByteArray(d);
			DatagramPacket p = new DatagramPacket(b, b.length, dst, port);
			socket.send(p);
			System.out.println("Datagrama #" + cnt + "/" + parts + " enviado");
		}
		bais.close();
	}

	public static Object[] receiveParts(DatagramSocket socket) throws Exception{
		DatagramPacket p = new DatagramPacket(new byte[buffer_size], buffer_size);
		socket.receive(p);
		System.out.println("Datagrama recibido desde " + p.getAddress() + ":" + p.getPort() + ", reconstruyendo partes...");
		InetAddress sourceAddress = p.getAddress();
		int sourcePort = p.getPort();
		Dato d = (Dato)byteArrayToObject(p.getData());
		int received_parts = 1;
		int size = d.size;
		int parts = size / part_size;
		if(size % part_size > 0) parts++;
		boolean visited[] = new boolean[parts];
		visited[d.id] = true;
		byte data[] = new byte[size];
		System.out.println("Se han recibido " + received_parts + "/" + parts + " partes");
		System.arraycopy(d.data, 0, data, d.offset, d.data.length);
		while(received_parts < parts){
			p = new DatagramPacket(new byte[buffer_size], buffer_size);
			socket.receive(p);
			d = (Dato)byteArrayToObject(p.getData());
			if(!visited[d.id]){
				visited[d.id] = true;
				received_parts++;
			}
			System.out.println("Se han recibido " + received_parts + "/" + parts + " partes");
			System.arraycopy(d.data, 0, data, d.offset, d.data.length);
		}
		return new Object[] {new String(data, 0, data.length), sourceAddress, sourcePort};
	}
}