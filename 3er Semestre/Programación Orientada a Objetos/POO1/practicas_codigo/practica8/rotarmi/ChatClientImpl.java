import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Runnable
{
private ChatServer mycs;

public ChatClientImpl (ChatServer cs) throws RemoteException
{
mycs=cs;
mycs.register(this);
}

public synchronized void receive (String s) throws RemoteException
{
System.out.println("Message: "+s);
}
public void run ()
{
Scanner in=new Scanner(System.in);
String msg;

while(true)
{
try
{
msg=in.nextLine();
mycs.broadcast(msg);
}
catch(Exception e)
{
System.err.println("Problem….");
}
}
}

public static void main (String[] args)
{
String url = "rmi://localhost/ChatServer";
try
{
ChatServer cs= (ChatServer) Naming.lookup(url);
new Thread(new ChatClientImpl(cs)).start();
}
catch (Exception e)
{
System.err.println("Problem….") ;
}
}
}
