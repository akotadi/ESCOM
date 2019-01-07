import java.io.*;
import java.net.Socket;

class proceso extends Thread{

    
    final InputStreamReader entrada; 
    final DataOutputStream salida;
    final BufferedReader reader;
    final Socket socket;
    final String dir;    
    

    proceso(Socket socket) throws Exception{

        this.socket = socket;
        this.entrada = new InputStreamReader(socket.getInputStream());
        this.reader = new BufferedReader(this.entrada);
        this.salida = new DataOutputStream(socket.getOutputStream());
        this.dir = socket.getInetAddress().toString().substring(1);                

    }
    
    @Override
    public void run(){

        try{

            while(true){

                String line = this.reader.readLine();

                int tam = 0;
                String[] primera = line.split(" ");                

                String tipo = primera[0];
                String nombre = primera[1].substring(1);



                while (!line.isEmpty()) {

                    String[] act = line.split(":");
                    if(act[0].equals("Content-Length")){
                        tam = Integer.parseInt(act[1].substring(1));
                    }

                    //System.out.println(line);
                    line = this.reader.readLine();
                }

                String propiedades = "";                                
                for(int i = 0; i < tam; i ++)                    
                    propiedades += (char)this.reader.read();


                
                procesador p = new procesador();

                byte[] respuesta;                            

                if(tipo.equals("GET")) respuesta = p.Get(nombre, dir);
                else if(tipo.equals("POST")) respuesta = p.Post(nombre, propiedades, dir);
                else if(tipo.equals("DELETE")) respuesta = p.Delete(nombre, dir);
                else if(tipo.equals("HEAD")) respuesta = p.Head(nombre, dir);
                else continue;

                this.salida.write(respuesta);
                                                
                System.out.println("Petición terminada!");

                break;

            }

            reader.close(); 
            entrada.close();                
            salida.close();
            socket.close();

        } catch(Exception ex){

        }
    } 

       

}