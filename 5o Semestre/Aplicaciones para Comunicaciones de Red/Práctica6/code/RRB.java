import java.util.*;
import java.nio.channels.*;
import java.nio.*;
import java.net.*;
import java.io.*;

class RRB implements Runnable{

	public static int pMaster;	
	public static Queue cola = new LinkedList();
	private int cnt ;

	private ServerSocketChannel master;
	private Selector estados;

	private Map<SocketChannel, Socket> todo = new HashMap<>();
	private Map<SocketChannel, Integer> ids = new HashMap<>();

	public RRB() throws Exception{

		cnt  = 0;

		this.master = ServerSocketChannel.open();
		this.master.configureBlocking(false);
		this.master.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		this.master.socket().bind(new InetSocketAddress(pMaster));
		this.estados = Selector.open();
		this.master.register(this.estados, SelectionKey.OP_ACCEPT);
	}

	@Override
	public void run(){
		System.out.println("Esperando conexiones en el puerto "+pMaster+" ....");
		while(!Thread.currentThread().isInterrupted()){
			try{
				estados.select(10000);
				Iterator<SelectionKey> actual = estados.selectedKeys().iterator();
				while(actual.hasNext()){
					SelectionKey estado = (SelectionKey)actual.next();
					actual.remove();
					if(!estado.isValid()) continue;
					
					if(estado.isAcceptable()){

						ServerSocketChannel server = (ServerSocketChannel)estado.channel();
						SocketChannel canal = server.accept();
						canal.configureBlocking(false);
						canal.register(estados, SelectionKey.OP_READ);
						System.out.println("Conexión desde " + canal.socket().getInetAddress());

					} else if(estado.isReadable()){
						SocketChannel canal = (SocketChannel)estado.channel();
						ByteBuffer buffer = ByteBuffer.allocate(4096);

						Socket socket = new Socket();
						socket.connect(new InetSocketAddress("localhost", (Integer) cola.element()), 10000);
						socket.setSoTimeout(10000);

						int sig = (Integer) cola.remove();
						cola.add(sig);

						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

						int read = 0;
						while((read = canal.read(buffer)) > 0){
							buffer.flip();
							//System.out.print(new String(buffer.array(), 0, read));
							dos.write(buffer.array(), 0, read);
							buffer.clear();
						}

						todo.put(canal, socket);
						ids.put(canal, ++cnt);
						estado.interestOps(SelectionKey.OP_WRITE);

						System.out.println("Se atenderá una petición en el puerto "+sig + ", ID "+ cnt);

					} else if(estado.isWritable()){

						SocketChannel canal = (SocketChannel)estado.channel();
						
						Socket socket = todo.get(canal);
						int actId = ids.get(canal);
						DataInputStream dis = new DataInputStream(socket.getInputStream());

						try{

							int read = 0;
							byte buffer[] = new byte[4096];
							while((read = dis.read(buffer, 0, 4096)) > 0)
								canal.write(ByteBuffer.wrap(buffer, 0, read));
							
						}catch(Exception e){
							System.out.println("Se agotó el tiempo");
							estado.interestOps(SelectionKey.OP_READ);
							continue;
						}

						estado.interestOps(SelectionKey.OP_READ);
						System.out.println("Se ha resuelto la peticón hecha desde "+canal.socket().getInetAddress()+" con ID " + actId);

						canal.close();


					}
				}
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
	}


	public static void main(String [] args){



		Scanner scanner = new Scanner(System.in);


		System.out.print("Puerto del master: ");
		pMaster = scanner.nextInt();

		System.out.println("Cantidad de auxiliares: ");
		int n = scanner.nextInt();

		for(int i = 0; i < n; i ++){
			System.out.println("Puerto auxiliar "+i+": ");
			int x = scanner.nextInt();
			cola.add(x);
		}

		try{
		
			Thread R = new Thread(new RRB());
			R.start();
		} catch(Exception ex){

		}

	}

}