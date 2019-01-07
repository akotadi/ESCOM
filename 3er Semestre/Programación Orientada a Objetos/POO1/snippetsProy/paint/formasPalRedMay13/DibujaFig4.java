import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
public class DibujaFig4 extends JFrame implements Runnable, ActionListener{
        String opciones[]={" Cir ", " Lin ", " Rec ", " dis "};
	private Vector<Dibujable> v=new Vector<Dibujable>();
        private Socket client;
        private ObjectInputStream oisNet;
        private ObjectOutputStream oosNet;
	private Thread hilo;
	private JButton bColor, bCrea;
        private TextField tfx, tfy;
        private Paleta pal;
	private JPanel p;
	private Color c;
	public DibujaFig4(){
		super("Paint");
		 String host = JOptionPane.showInputDialog("Escriba dir.IP", "localhost");
        	int i=0;
   		while(i==0){
    			i=1;
    			System.out.println("Esperando por el servidor . . .");
        		try {
            			client = new Socket(host, 5000);
        		} catch ( IOException e) {
            			System.out.println("ERROR(1)"); i=0;
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
                p=new JPanel();
		bColor=new JButton("Color");
		bColor.addActionListener(this);
		bCrea=new JButton("Crea");
                tfx= new TextField(5); tfy= new TextField(5);
		bCrea.addActionListener(this);
		pal=new Paleta(opciones, new FlowLayout());
                p.add(pal); p.add(bColor); p.add(bCrea);
		p.add(new JLabel("x"));p.add(tfx);
		p.add(new JLabel("y"));p.add(tfy);
		add("South", p);
		c = Color.BLACK;
		hilo = new Thread(this);
        	hilo.start();
	}
	public void paint(Graphics g){
		paintComponents(g);
		for(int i=0;i<v.size();i++)
			((Dibujable)v.elementAt(i)).dibuja(g);
	}
	public void run() {
        	System.out.println("run!!!");
        	while (true) {     
        		try {
		    		v.addElement((Dibujable) (oisNet.readObject()));
				repaint();
                	} catch (ClassNotFoundException ex) {
                     		System.out.println("Class not found"+ex);
			} catch (IOException e) {
            	     		System.out.println("IO ex"+e);
                	}	
        	}
	}
	public void actionPerformed(ActionEvent e){
		JButton btn=(JButton)e.getSource();
		if(btn==bColor){
		c = JColorChooser.showDialog( this, "Seleccione un color", c );
                	if ( c == null ) c = Color.BLACK;
		}
		if(btn==bCrea){
			Dibujable d=null;
			int sel=pal.getSeleccion(); 
			int x=Integer.parseInt(tfx.getText());
			int y=Integer.parseInt(tfy.getText());
			if(sel == 0){
				Circulo c1=new Circulo(x, y, 70);
				c1.ponColor(c); d=c1;	
			}
			if(sel == 1){
				Linea l=new Linea(x, y,x+70, y+50);
				l.ponColor(c); d=l;	
			}
			if(sel == 2){	
				Rectangulo r=new Rectangulo(x, y,70, 50);
				r.ponColor(c); d=r;
			} 
			v.addElement(d);
      			repaint(); 
			try {
            			oosNet.writeObject(d);
        		} catch (IOException ex) { ex.printStackTrace(); }
		}   
	}
	public static void main(String args[]){
		DibujaFig4 d=new DibujaFig4();
		d.setSize(600,400); d.setVisible(true);
		d.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
		return new ObjectOutputStream(os);
	}
	ObjectInputStream getOISNet(InputStream is) throws IOException {
        	return new ObjectInputStream(is);
	}
}
