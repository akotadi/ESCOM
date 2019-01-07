import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer {

    ArrayList<ObjectOutputStream> clientObjectOutputStreams;
    public class ClientHandler implements Runnable {
    ObjectInputStream reader;
       
   Socket sock;
   

         public ClientHandler(Socket clientSocket) {
           try {
             sock = clientSocket;
             reader = new ObjectInputStream(sock.getInputStream());  
             //System.out.println("Servidor reader (" + reader+")");       
           } catch(Exception ex) {
                System.out.println("Exce Servidor reader " + ex);
                ex.printStackTrace();
             }
          } // close constructor

        public void run() {
           Object obj;
            
           try {
             while (true) {
		obj = (Object) reader.readObject();
                //System.out.println("read " + pub);
                tellEveryone(obj);
             }
           } catch(Exception ex) {ex.printStackTrace();}

       } // close run
   } // close inner class
      



     public static void main (String[] args) {
         new VerySimpleChatServer().go();
    }

     public void go() {
      clientObjectOutputStreams = new ArrayList<ObjectOutputStream>();

       try {
       ServerSocket serverSock = new ServerSocket(5000);

       while(true) {
          Socket clientSocket = serverSock.accept();
          ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());        
          clientObjectOutputStreams.add(writer);

       Thread t = new Thread(new ClientHandler(clientSocket));
       t.start();


       System.out.println("got a connection");
     }
       // now if I get here I have a connection
               
      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }

   public void tellEveryone(Object obj) {
      Iterator it = clientObjectOutputStreams.iterator();
      while(it.hasNext()) {
        try {
           ObjectOutputStream writer = (ObjectOutputStream) it.next();
	   writer.writeObject(obj);
           writer.flush();
         } catch(Exception ex) {
              ex.printStackTrace();
         }
      
       } // end while
       
   } // close tellEveryone


}
       
