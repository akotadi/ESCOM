import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer {
    //ArrayList<PrintWriter> clientOutputStreams;
    ArrayList<ObjectOutputStream> clientObjectOutputStreams;
    ArrayList<UserSession> us;
  
 
    public class ClientHandler implements Runnable {
         //PrintWriter writer;
         ObjectOutputStream writer;
	 //BufferedReader reader;
         ObjectInputStream reader;
         Socket sock;

         public ClientHandler(Socket clientSocket, ObjectOutputStream writer) {
           try {
             this.writer= writer;
             sock = clientSocket;
             reader = new ObjectInputStream(sock.getInputStream());      
           } catch(Exception ex) {
		System.out.println("Exce Servidor reader " + ex);
             	ex.printStackTrace();
            }
          } // close constructor

        public void run() {
	   //Object obj;
           
           try {
              while (true) {
                UserSession obj = (UserSession) reader.readObject();
                System.out.println("read " + obj.getUser());
		System.out.println("read " + obj.getLoginTime());
                us.add(obj);
                for(int i=0;i<us.size();i++){
                      obj=us.get(i);
                      System.out.println("ARRread " + obj.getUser());
		      System.out.println("ARRread " + obj.getLoginTime());
                }
                tellEveryone(obj, writer);
             }
           } catch(Exception ex) {ex.printStackTrace();}
       } // close run
   } // close inner class
     public static void main (String[] args) {
         new VerySimpleChatServer().go();
    }
     public void go() {
       //clientOutputStreams = new ArrayList<PrintWriter>();
       clientObjectOutputStreams = new ArrayList<ObjectOutputStream>();
       us=new ArrayList<UserSession>();
       try {
       ServerSocket serverSock = new ServerSocket(5000);

       while(true) {
          Socket clientSocket = serverSock.accept();
          //PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());         
          //clientOutputStreams.add(writer);
          ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());        
          clientObjectOutputStreams.add(writer);
       Thread t = new Thread(new ClientHandler(clientSocket, writer));
       t.start();
       System.out.println("got a conexion");
     }
       // now if I get here I have a connection          
      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }
   public void tellEveryone(Object obj, ObjectOutputStream writerp) {
      //Iterator it = clientOutputStreams.iterator();
      Iterator it = clientObjectOutputStreams.iterator();
      while(it.hasNext()) {
        try {
           //PrintWriter writer = (PrintWriter) it.next();
           ObjectOutputStream writer = (ObjectOutputStream) it.next();
	   if(!writer.equals(writerp)){
           	//writer.println(message);
                writer.writeObject(obj);
           	writer.flush();
	   }
         } catch(Exception ex) {
              ex.printStackTrace();
         }
      
       } // end while 
   } // close tellEveryone
}
