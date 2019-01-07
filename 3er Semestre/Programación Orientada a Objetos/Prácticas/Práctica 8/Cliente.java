import java.util.*;
import java.io.*;
import java.rmi.*;

public class Cliente {

    private ChatBotI remote;
    private BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
    private String ip, respuesta;
    private int puerto;

    public Cliente() {
        try {
            ip = "localhost";
            puerto = 1025;
            remote = (ChatBotI)Naming.lookup("//" + ip + ":" + puerto + "/ChatBot");
        } catch (Exception ex) {
            System.out.println("ERROR " + ex);
        }
        try {
            while (true) {
                System.out.print("\nEscribe tu pregunta: ");
                respuesta = remote.responde(inLine.readLine());
                System.out.println("\nRespuesta: " + respuesta);
            }
        } catch (Exception ex) {
            System.out.println("ERROR " + ex);
        }
    }

    public static void main(String[] s) {
        new Cliente();
    }
}