import java.util.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

public class Servidor {

    private String ip;
    private int puerto;

    public Servidor() {
        try {
            ChatBotImp remote = new ChatBotImp();
            ip = "localhost";
            puerto = 1025;
            java.rmi.registry.LocateRegistry.createRegistry(puerto);
            Naming.rebind("//" + ip + ":" + puerto + "/ChatBot", remote);
        } catch (Exception ex) {
            System.out.println("ERROR " + ex);
            System.exit(0);
        }
    }

    public static void main(String[] s) {
        new Servidor();
    }
}
