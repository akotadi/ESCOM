import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Dibuja extends JPanel implements Runnable, ActionListener, Serializable,  AccionInt {
        String opciones[]={" Cir ", " Lin ", " Rec ", " dis "};
	private Vector<Dibujable> v=new Vector<Dibujable>();
        Socket cliente;
        ObjectInputStream oisNet;
        ObjectOutputStream oosNet;
	Thread hilo;
	JButton bColor, bConecta;
	JButton bCrea;
	TextField t;
        Paleta pal;
	Point anchor;
	JPanel p;
	Color c;
	String host;
        int sel;
        int puerto;
        //private transient EventHandler eh; 
	public Dibuja(String host){
		 //String host = JOptionPane.showInputDialog("Escriba dir.IP", "localhost");
                this.host = host; puerto=5000;
                p=new JPanel();	
                setLayout(new BorderLayout());
		bColor=new JButton("Color");
		bColor.addActionListener(this);
		bCrea=new JButton("crea");         
        	bCrea.addActionListener(this); 
                bConecta=new JButton("conecta");
		bConecta.addActionListener(this);    
                //eh = new EventHandler();  
		pal=new Paleta(opciones, new FlowLayout(), this);
                p.add(pal); p.add(bConecta); p.add(bColor); p.add(bCrea);
		p.add(t=new TextField(10));
		add("South", p);
		c = Color.BLACK;	
	}
	public void paint(Graphics g){
		paintComponents(g);
		for(int i=0;i<v.size();i++)
                      if(v.elementAt(i)!=null)
			((Dibujable)v.elementAt(i)).dibuja(g);
	}
	public static void main(String args[]){
                Frame f=new Frame("Pinta");
                f.add("Center", new Dibuja("localhost"));
		f.setSize(400,400);
		f.setVisible(true);
		//f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public void run() {
        	System.out.println("run!!!");
        	while (true) {     
        		try {
		    		v.addElement((Dibujable) (oisNet.readObject()));
				repaint();
                	} catch (ClassNotFoundException ex) {
                     		System.out.println("nofound"+ex);
			} catch (IOException e) {
            	     		System.out.println("IO ex"+e);
                	}	
        	}
	}
void crea(String nombre){
	Dibujable d;

	try {
            d =(Dibujable)Class.forName(nombre).newInstance();
	    v.addElement(d);
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
void conectar(){
	int i=0;
   	while(i==0)
   	{
    		i=1;
    		System.out.println("Dibuja Esperando por el servidor . . .");
    		try {
			cliente=new Socket( host, puerto);

    		} catch ( IOException ioe) {
			System.out.println("Fallo creacion Socket"+ioe);
            		i=0;
   		}
   	}
   	System.out.println("Dibuja Connectado al servidor.");
   	try {
		oisNet = getOISNet(cliente.getInputStream());
            	oosNet = getOOSNet(cliente.getOutputStream()); 
   	} catch ( IOException ioe) {
		ioe.printStackTrace();
         	System.out.println("Error al crear los fujos de objeto"+ioe);
   	}
        System.out.println("flujos obtenidos.");
        hilo = new Thread(this);
        hilo.start();
}
	public void actionPerformed(ActionEvent e){
		JButton btn=(JButton)e.getSource();
		if(btn == bColor){
		c = JColorChooser.showDialog( this, "Seleccione un color", c );
                	if ( c == null )
                		c = Color.BLACK;
		}
		if(btn == bCrea){
			crea(t.getText());
		}    
		if(btn== bConecta){
                        addMouseListener(new MouseEventHandler()); 
                	addMouseMotionListener(new MouseMotionEvtHandler());
           		conectar();
		}
	}
        class MouseMotionEvtHandler extends MouseMotionAdapter {  
             public void mouseDragged( MouseEvent e ){
			//int sel; 
			//sel=pal.getSeleccion();  
                        /*if(sel == 0){
				v.addElement(
				new Circulo((int)e.getPoint().getX(), 
					    (int)e.getPoint().getY(),50));
			}*/
			if(sel == 1){
				int x = e.getX();
        			int y = e.getY();
				Graphics g=getGraphics();
				g.drawLine((int)anchor.getX(), (int)anchor.getY(), x, y);
				g.dispose();
			} 
			if(sel == 2){
				int x = e.getX();
        			int y = e.getY();
				int ancho, alto;
				ancho=Math.abs((int)(anchor.getX()-e.getPoint().getX()));
			        alto=Math.abs((int)(anchor.getY()-e.getPoint().getY()));
				Graphics g=getGraphics();
				g.drawRect((int)anchor.getX(), (int)anchor.getY(), 
						ancho, alto);
				g.dispose();	
			} 
      			repaint(); 
             }
        }   
 	class MouseEventHandler extends MouseAdapter { 
   		public void mousePressed(MouseEvent e)  { 
			 anchor = new Point(e.getX(), e.getY());        
    		}
    		public void mouseReleased(MouseEvent e) { 
			Dibujable d=null;
			//int sel; 
			//sel=pal.getSeleccion();  
			if(sel == 1){
				Linea l=new Linea("Piolin", (int)anchor.getX(), 
					   (int)anchor.getY(),
					   (int)e.getPoint().getX(), 
					    (int)e.getPoint().getY()
					);
				l.ponColor(c);
				d=l;
				
			}
			if(sel == 2){
				int ancho, alto;
				ancho=Math.abs((int)(anchor.getX()-e.getPoint().getX()));
			       alto=Math.abs((int)(anchor.getY()-e.getPoint().getY()));	
				Rectangulo r=new Rectangulo("Piolin", (int)anchor.getX(), 
					    (int)anchor.getY(),
					    ancho, alto);
				r.ponColor(c);
				d=r;
			} 
			v.addElement(d);
      			repaint(); 
			try {
            			oosNet.writeObject(d);
        		} catch (IOException ex) {
            			ex.printStackTrace();
        		}
    		}
  	}	
public void accion(int n){ sel = n; }
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
}
