// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VerySimpleChatServer.java

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class VerySimpleChatServer
{
    public class ClientHandler
        implements Runnable
    {

        public void run()
        {
            try
            {
                while(true) 
                {
                    Object obj = reader.readObject();
                    tellEveryone(obj, writer);
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }

        ObjectOutputStream writer;
        ObjectInputStream reader;
        Socket sock;
        final VerySimpleChatServer this$0;

        public ClientHandler(Socket socket, ObjectOutputStream objectoutputstream)
        {
            this$0 = VerySimpleChatServer.this;
            super();
            try
            {
                writer = objectoutputstream;
                sock = socket;
                reader = new ObjectInputStream(sock.getInputStream());
            }
            catch(Exception exception)
            {
                System.out.println((new StringBuilder()).append("Exce Servidor reader ").append(exception).toString());
                exception.printStackTrace();
            }
        }
    }


    public VerySimpleChatServer()
    {
    }

    public static void main(String args[])
    {
        (new VerySimpleChatServer()).go();
    }

    public void go()
    {
        clientObjectOutputStreams = new ArrayList();
        try
        {
            ServerSocket serversocket = new ServerSocket(5000);
            do
            {
                Socket socket = serversocket.accept();
                ObjectOutputStream objectoutputstream = new ObjectOutputStream(socket.getOutputStream());
                clientObjectOutputStreams.add(objectoutputstream);
                Thread thread = new Thread(new ClientHandler(socket, objectoutputstream));
                thread.start();
                System.out.println("got a conexion");
            } while(true);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void tellEveryone(Object obj, ObjectOutputStream objectoutputstream)
    {
        Iterator iterator = clientObjectOutputStreams.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            try
            {
                ObjectOutputStream objectoutputstream1 = (ObjectOutputStream)iterator.next();
                if(!objectoutputstream1.equals(objectoutputstream))
                {
                    objectoutputstream1.writeObject(obj);
                    objectoutputstream1.flush();
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        } while(true);
    }

    ArrayList clientObjectOutputStreams;
}
