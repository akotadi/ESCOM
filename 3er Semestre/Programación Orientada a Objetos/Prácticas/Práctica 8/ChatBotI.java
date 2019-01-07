import java.rmi.*;

public interface ChatBotI extends Remote {

    public String responde(String pregunta) throws RemoteException;
    
}