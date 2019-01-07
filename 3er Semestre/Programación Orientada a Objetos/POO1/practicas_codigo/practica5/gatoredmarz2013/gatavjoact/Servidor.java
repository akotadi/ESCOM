import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.util.*;


public class Servidor implements Runnable
{
 ServerSocket serv;
 Socket cliente;
 InputStream  entrada;
 OutputStream  salida;
 Thread thread;
 int puerto;
 Vector inputs=new Vector();
 Vector outputs=new Vector();

 transient ActionListener actionListener;

 public Servidor(int p)
 {
  puerto=p;
  init();
 }

 public void init()
 {
  int j=0;
  while(j==0)
  {
   j=1;
   try {
        serv=new ServerSocket(puerto);
       } catch ( IOException e) {
                                 System.out.println("ERROR(1)");
                                 j=0;
                                }
  }
 try {
      serv.setSoTimeout(100);
     } catch(SocketException e){System.out.println("ERROR(2)");}
 System.out.println("Connectado.");
 System.out.println("Esperando Cliente . . .");
 thread = new Thread (this);
 thread.start ();

 }

 public void run()
 {
  int i=0,j,k,l;
  
  for(;;)
  {
  j=1;
  try {
      cliente=serv.accept();
     } catch ( IOException e) {
                               j=0;
                              }
  if(j==1) {
            try {
                 inputs.addElement(cliente.getInputStream());
                 outputs.addElement(cliente.getOutputStream());
                } catch ( IOException e) {}
            System.out.println("Connectado a otro cliente  ");
           }

  for(i=0;i<inputs.size();i++) {
				l=0;
				entrada=(InputStream)inputs.elementAt(i);
				try {
				     j=entrada.read();
				    }catch ( IOException e) {System.out.println("ERROR(5)");l=1;}
                                if(l==0) {
	 				  System.out.println(String.valueOf(j));
					  for(k=0;k<outputs.size();k++) 
					  {
					    salida=(OutputStream)outputs.elementAt(k);
					    try {
					         salida.write(j);
					        }catch ( IOException e) {System.out.println("ERROR(6)");}
					  }
					 }
			       }
  try {
       Thread.sleep (400);
      } catch ( InterruptedException e) {}
  }
 }

 public synchronized void addActionListener(ActionListener l) 
 {
  actionListener = AWTEventMulticaster.add(actionListener, l);
 }

 public static void main (String args[])
 {
  Servidor s=new Servidor(23);
 }

}