import java.io.* ;
import java.net.* ;
import javax.swing.*;
class ServIma {
static final int PUERTO=5000;

private Socket cliente;  
private ServerSocket ServIma;
private ObjectOutputStream writer;
private ObjectInputStream reader;
String nombres[]={"perro.jpg","conejo.jpg"};
int cta=0;
public ServIma( ) {
Object ci=null, co=null;
int j;
try {

	ServIma = new ServerSocket( PUERTO );

System.out.println("Escucho el puerto " + PUERTO );

for ( int numCli = 0; numCli < 10; numCli++) {

	cliente = ServIma.accept(); // Crea objeto

System.out.println("Sirvo al cliente " + numCli);
try {
	    
            writer = getOOSNet(cliente.getOutputStream());
            writer.flush(); 
            reader = getOISNet(cliente.getInputStream());
   } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Serv Error al crear los fujos de objeto"+e);
   }
j=0;
try {
	      co=new ImageIcon(nombres[++cta%2]);
              writer.writeObject(co);
     } catch (IOException ex) {
              ex.printStackTrace();
     }  

cliente.close();

}

System.out.println("Demasiados clientes por hoy");

} catch( Exception e ) {

System.out.println( e.getMessage() );

}

}

public static void main( String[] arg ) {
new ServIma();
}

ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
}

