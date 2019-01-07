import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
public class Local extends JFrame {
 Vector <String> nombres=new Vector<String>();
 Panel p;
 ImageIcon iconos[];
 JLabel jlimg;
 static final String HOST = "localhost";
 static final int PUERTO=5000;
 private Socket cliente;  
 private ObjectInputStream oisNet;
 private ObjectOutputStream oosNet;

 public Local() {
	super("Marco");
    	nombres.addElement("conejo.jpg");
   	p=new Panel(); 
	jlimg=new JLabel("ima", JLabel.CENTER);
        
        int j;
Object ci=null;
try{
cliente = new Socket( HOST , PUERTO );
try {
            oisNet = getOISNet(cliente.getInputStream());
            oosNet = getOOSNet(cliente.getOutputStream()); 
   } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cliente Error al crear los fujos de objeto "+e);
   } 
j=0; 

try {
                        ci=oisNet.readObject();	
    		} catch (IOException e) {
			System.out.println("IO ex"+e);
         		j=1;
                } catch (ClassNotFoundException ex) {
                     	System.out.println("Class no found"+ex);
			j=1;
		} 
    		if (j==0) {
 		        if(ci instanceof ImageIcon) 
                           jlimg.setIcon((ImageIcon)ci);
                }

cliente.close();

} catch( Exception e ) {

System.out.println( e.getMessage() );

}
   	//iconos=new ImageIcon[nombres.size()];
        //iconos[0] =new ImageIcon(nombres.elementAt(0));
   	p.add(jlimg);
    	Container content = getContentPane();
    	content.add(p, BorderLayout.CENTER);
    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        setSize(400, 300);
    	setVisible(true);
 }
 public static void main(String[] args) { new Local(); }
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
}
