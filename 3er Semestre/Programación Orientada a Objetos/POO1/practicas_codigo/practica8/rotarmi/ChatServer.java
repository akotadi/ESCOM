import java.rmi.*;

public interface ChatServer extends Remote
{
void register(ChatClient c) throws RemoteException;
void broadcast(String s) throws RemoteException;
}

