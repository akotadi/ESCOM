import java.rmi.*;

public interface ChatClient extends Remote
{
void receive (String s) throws RemoteException;
}
