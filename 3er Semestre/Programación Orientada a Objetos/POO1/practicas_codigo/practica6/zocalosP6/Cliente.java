import java.io.*;
import java.net.*;

class Cliente {

static final String HOST = "localhost";

static final int PUERTO=5000;
private Socket cliente;  
private ObjectInputStream oisNet;
private ObjectOutputStream oosNet;

public Cliente(String msg ) {
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

try {
              oosNet.writeObject(new String(msg));
     } catch (IOException ex) {
              ex.printStackTrace();
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
 		        if(ci instanceof String) 
                           System.out.println((String)ci);
                }

cliente.close();

} catch( Exception e ) {

System.out.println( e.getMessage() );

}
}

public static void main( String[] arg ) {

new Cliente(arg[0]);

}
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
}
