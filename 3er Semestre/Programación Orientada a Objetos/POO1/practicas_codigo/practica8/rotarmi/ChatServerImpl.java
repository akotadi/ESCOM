import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer
{
private LinkedList<ChatClient> myclients;

public ChatServerImpl() throws RemoteException
{
myclients = new LinkedList<ChatClient>();
}

public synchronized void register (ChatClient c) throws RemoteException
{
myclients.add(c);
}

public synchronized void broadcast (String s) throws RemoteException
{
for(int i=0;i< myclients.size();i++)
{
myclients.get(i).receive(s);
}
}

public static void main (String[] args)
{
try
{
Naming.rebind("ChatServer", new ChatServerImpl());
System.out.println("servidor listo");
}
catch(Exception e)
{
System.err.println("Problemn rebind…."+e) ;
}
}
}

