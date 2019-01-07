import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

public class servidor {

    public static void main(String args[]) {

        int puerto;

        System.out.print("Puerto :");

        Scanner scanner = new Scanner(System.in);
        puerto = scanner.nextInt();

        try{

            ServerSocket server = new ServerSocket(puerto);
            System.out.println("Puerto "+puerto+" esperando conexiones....");

            while (true) {
                try {
                    Socket socket = server.accept();
                    Thread p = new proceso(socket);

                    p.start();  

                } catch(Exception ex){

                }
            }
        } catch(Exception ex){

        }
    }

}