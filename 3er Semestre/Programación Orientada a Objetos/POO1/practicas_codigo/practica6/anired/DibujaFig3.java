import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;
public class DibujaFig3 extends Applet implements Runnable, ActionListener{
        Socket client;
        ObjectInputStream oisNet;
        ObjectOutputStream oosNet;
        Thread hilo;
        JButton bcrea;
        TextField t;
        private static int size=300;
public void init(){
        String host = JOptionPane.showInputDialog("Escriba dir.IP", "localhost");
        int i=0;
   	while(i==0)
   	{
    		i=1;
    		System.out.println("Esperando por el servidor . . .");
        	try {
            		client = new Socket(host, 5000);
        	} catch ( IOException e) {
            		System.out.println("ERROR(1)");
            		i=0;
   		}
   	}
        System.out.println("Conectado al servidor==");
        try {
            oisNet = getOISNet(client.getInputStream());
            oosNet = getOOSNet(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al crear los fujos de objeto"+e);
        } 
        add(t=new TextField(20));
        hilo = new Thread(this);
        hilo.start();
}
public static void main(String args[]){
	DibujaFig3 df=new DibujaFig3();
	df.init();
	df.start();
	JFrame f=new JFrame("Dibuja");  
	f.add("Center",df);  
        f.add("South", df.bcrea=new JButton("crea"));         
        df.bcrea.addActionListener(df); 
        f.setSize(400, 300);                 
	f.setVisible(true);
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
}
public void run() {
        System.out.println("run!!!");
        while (true) {     
        	try {      
		    //JPanelAnim c= (JPanelAnim) (oisNet.readObject());
                    Object c= oisNet.readObject();
                    System.out.println("recibi clase "+c.getClass().getName());
		    if( c instanceof JPanel){
			JPanel jp=(JPanel)c;
                    //c=(JPanel)Class.forName(c.getClass().getName()).newInstance();
		    add(jp);
                    repaint();    
		    }
                } catch (ClassNotFoundException ex) {
                     System.out.println("nofound"+ex);
		} catch (IOException e) {
            	     System.out.println("IO ex"+e);
                } 
                return;
        }
}
public void actionPerformed(ActionEvent e) {
	JButton btn=(JButton)e.getSource();
	crea(t.getText());
}
void crea(String nombre){
        JPanel d;     
	try {
	    d =(JPanel)Class.forName(nombre).newInstance();
	    add(d);
            repaint();
            System.out.println("clase "+d.getClass().getName());
            oosNet.writeObject(d);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	catch (Throwable e1) {
            System.err.println(e1);
         }
}
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
}                                                                                    
