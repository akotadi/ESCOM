import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ChatBotImp extends UnicastRemoteObject implements ChatBotI {

    public ChatBotImp() throws RemoteException {
        super();
    }

    public String responde(String pregunta) throws RemoteException {
        
        String respuesta = "LO SIENTO, NO TE ENTIENDO";

        HashMap<String,String> map = new HashMap<>();
        map.put("HOLA" , "HOLA, MUCHO GUSTO");
        map.put("ADIOS" , "ADIOS, FUE UN PLACER");
        map.put("COMO TE LLAMAS?" , "ME LLAMO MANUEL");
        map.put("CUANTOS AÑOS TIENES?" , "22");
        map.put("DONDE ESTUDIAS?" , "EN ESCOM");
        map.put("QUE CLASE ES ESTA?" , "PROGRAMACION ORIENTADA A OBJETOS");
        map.put("QUE LENGUAJE UTILIZAS?" , "JAVA");
        map.put("COLOR FAVORITO?" , "ROJO");
        map.put("QUE MUSICA ESCUCHAS?" , "ROCK");
        map.put("A QUE HORA SALES?" , "3 DE LA TARDE");
        map.put("DONDE NACISTE?" , "CIUDAD DE MEXICO");
        map.put("COMIDA FAVORITA?" , "MOLE CON POLLO");

        if(map.containsKey(pregunta.toUpperCase())){
            respuesta = map.get(pregunta.toUpperCase());
        }

        return respuesta;
    }
}