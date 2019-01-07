import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Gato extends Applet implements Runnable, ActionListener{
	Socket cliente;  
  	OutputStream salida;
  	InputStream entrada;
  	Thread thread;
  	int puerto;
	Label etiq;
	Button botones[];
	boolean turno;

	public void init(){
		turno=true;
		puerto=5000;
		setLayout(new GridLayout(3,3));
		botones=new Button[9];
		for(int i=0; i<9; i++){
			add(botones[i]=new Button(""+i));
			botones[i].addActionListener(this);
		}
		int i=0;
   		while(i==0)
   		{
    			i=1;
    			System.out.println("Esperando por el servidor . . .");
    			try {
         			//cliente=new Socket( getCodeBase().getHost(),puerto);
				cliente=new Socket( "localhost", puerto);
    			} catch ( IOException e) {
            			System.out.println("ERROR(1)");
            			i=0;
   			}
   		}
   		System.out.println("Connectado al servidor.");
   		try {
        		salida=cliente.getOutputStream();
   		} catch ( IOException e) {
       			System.out.println("ERROR(2)");
   		}
   		try {
        		entrada=cliente.getInputStream();
   		} catch ( IOException e) {
         		System.out.println("ERROR(2)");
   		}
   		thread = new Thread (this);
   		thread.start ();
	}

	public void run()
  	{
		int j,k=0;
   		for(;;)
   		{
    			j=0;
    			try {
         			k=entrada.read();
    			} catch ( IOException e) {
         			j=1;
    			}
    			if (j==0) {
				String s="";
				if(turno) s="X"; else s="O";
				botones[k].setLabel(s);
				etiq.setText("Es el turno de: "+s);
				botones[k].setEnabled(false);
				turno=!turno;
			}
		}
	}

	public void actionPerformed(ActionEvent e) 
  	{
		Object obj=e.getSource();
		
		if(obj instanceof Button)
		{
			String s="";
			Button btn=(Button)obj;
			if(turno) s="X"; else s="O";
			btn.setLabel(s);
			etiq.setText("Es el turno de: "+s);
			btn.setEnabled(false);
			turno=!turno;         
			for(int i=0;i<9;i++)
				if(botones[i]==btn)
					try {
						salida.write(i);
					} catch ( IOException ee) {}
		}
	}
	public static void main(String args[])
	{
		Gato g=new Gato();
		g.init();
		g.start();
		Frame f=new Frame("Gato");
		f.add("Center",g);
		f.add("South", g.etiq=new Label("Es el turno de: la equis"));                                 
		f.setVisible(true);
	}
}
