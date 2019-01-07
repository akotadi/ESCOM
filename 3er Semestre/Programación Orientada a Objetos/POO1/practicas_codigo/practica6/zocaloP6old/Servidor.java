import java.io.* ;
import java.net.* ;

class Servidor {
static final int PUERTO=5000;
/*
static String[][] KnowledgeBase = {
		{"COMO TE LLAMAS", 
		 "MI NOMBRE ES TAMAGOCHI"
		},		
		{"DONDE VIVES", 
		 "EN LA COMPU",
		}
};*/
static String preguntas []= { "COMO_TE_LLAMAS","DONDE_VIVES"};
static String respuestas []= {"MI NOMBRE ES TAMAGOCHI","EN LA COMPU"};
private Socket cliente;  
private ServerSocket servidor;
private ObjectOutputStream writer;
private ObjectInputStream reader;

public Servidor( ) {
Object ci=null, co=null;
int j;
try {

	servidor = new ServerSocket( PUERTO );

System.out.println("Escucho el puerto " + PUERTO );

for ( int numCli = 0; numCli < 3; numCli++) {

	cliente = servidor.accept(); // Crea objeto

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
                        ci=reader.readObject();	
    		} catch (IOException e) {
			System.err.println("IO ex"+e);
         		j=1;
                } catch (ClassNotFoundException ex) {
                     	System.err.println("Class no found"+ex);
			j=1;
		} 
    		if (j==0) {
 		        if(ci instanceof String) 
                           //System.out.println("llego "+(String)ci);
                           co=new String(findMatch((String)ci));
                }

try {
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
new Servidor();
}

static String findMatch(String str) {
		String result = "";
/*		for(int i = 0; i < KnowledgeBase.length; ++i) {
			if(KnowledgeBase[i][0].equalsIgnoreCase(str)) {
				result = KnowledgeBase[i][1];
				break;
			}
		} */
		for(int i = 0; i < preguntas.length; ++i) {
			if(preguntas[i].equalsIgnoreCase(str)) {
				result = respuestas[i];
				break;
			}
		} 
		return result;
}
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
}

