import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Gato extends Panel implements ActionListener, Runnable,   Serializable{
	
Socket cliente;  
private ObjectInputStream oisNet;
private ObjectOutputStream oosNet;
Thread thread;
int puerto;
//Label etiq;
Button botones[];
Button conecta;
Panel p1, p2;
boolean turno;

public Gato(){
	turno=true;
	puerto=5000;
	p1=new Panel();
        p2=new Panel();
	p1.setLayout(new GridLayout(3,3));
	botones=new Button[9];
	for(int i=0; i< botones.length ; i++){
		p1.add(botones[i]=new Button(""+i));
                botones[i].setEnabled(false);
		botones[i].addActionListener(this);
	}
	p2.setLayout(new BorderLayout());
        conecta=new Button("conecta");
	conecta.addActionListener(this);       
        p2.add(p1,BorderLayout.NORTH);
	p2.add(conecta,BorderLayout.SOUTH);
        add(p2);
/*
		int i=0;
   		while(i==0)
   		{
    			i=1;
    			System.out.println("Gato Esperando por el servidor . . .");
    			try {
				cliente=new Socket( "localhost", puerto);
    			} catch ( IOException e) {
				System.out.println("Fallo creacion Socket");
            			i=0;
   			}
   		}
   		System.out.println("Gato Connectado al servidor.");
   		try {
			oisNet = getOISNet(cliente.getInputStream());
            		oosNet = getOOSNet(cliente.getOutputStream()); 
   		} catch ( IOException e) {
         		System.out.println("ERROR(2)");
   		}
   		thread = new Thread (this);
   		thread.start ();
*/

}       
public void run(){
	String message;
	int j;
	Object c=null;
   	for(;;)
   	{
    		j=0;
                try {
                        c=oisNet.readObject();	
    		} catch ( IOException e) {
			System.out.println("IO ex"+e);
         		j=1;
                } catch (ClassNotFoundException ex) {
                     	System.out.println("Class no found"+ex);
			j=1;
		} 
    		if (j==0) {
                        Tirada t=(Tirada)c;
                        //System.out.println("prog=("+t.getPrograma()+")");
			if(t.getPrograma().equals("Gato")){
                        	byte k=t.getPosicion(); 
				String s="";
                             	if(botones[k].isEnabled()){
					if(turno) s="X"; else s="O";
					botones[k].setLabel(s);
				//etiq.setText("Es el turno de: "+s);
					botones[k].setEnabled(false);
					turno=!turno;
			     	}
                         }
		}
	}
}
public void actionPerformed(ActionEvent e) {
	String s="";
        Object c=null;
	Button btn=(Button)e.getSource();
	//String s="";
        if(btn==conecta){
           	conectar();
	}//if con
        else {
		if(turno) s="X"; else s="O";
		btn.setLabel(s);
		btn.setEnabled(false);
		turno=!turno;                 
		for(byte i=0;i<9;i++)
			if(botones[i]==btn){
				System.out.println("e i("+i+")");
				try {
                                	c=new Tirada("Gato","ju",i); 
          				oosNet.writeObject(c);
     				} catch (IOException ex) {
          				ex.printStackTrace();
     				}
                	}
	}
}
void conectar(){
     for(int i=0; i< botones.length ; i++)
     	botones[i].setEnabled(true);
	int i=0;
   	while(i==0)
   	{
    		i=1;
    		System.out.println("Gato Esperando por el servidor . . .");
    		try {
			cliente=new Socket( "localhost", puerto);

    		} catch ( IOException ioe) {
			System.out.println("Fallo creacion Socket");
            		i=0;
   		}
   	}
   	System.out.println("Gato Connectado al servidor.");
   		try {
			oisNet = getOISNet(cliente.getInputStream());
            		oosNet = getOOSNet(cliente.getOutputStream()); 
   		} catch ( IOException ioe) {
         		System.out.println("ERROR(2)");
   		}
                System.out.println("flujos obtenidos.");
                thread = new Thread (this);
   		thread.start ();
}
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
}
