import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DibujaFig3 extends Frame implements ActionListener{
        String opciones[]={" Cir ", " Lin ", " Rec ", " dis "};
	private Vector v=new Vector();
	JButton b;
        Paleta pal;
	Point anchor;
	JPanel p;
	Color c;

	public DibujaFig3(){
		super("Paint");
                p=new JPanel();
		addMouseListener(new MouseEventHandler()); 
                addMouseMotionListener(new MouseMotionEvtHandler());
		b=new JButton("Color");
		b.addActionListener(this);
		pal=new Paleta(opciones, new FlowLayout());
                p.add(pal); p.add(b);
		add("South", p);
		c = Color.BLACK;
	}
	public void paint(Graphics g){
		paintComponents(g);
		for(int i=0;i<v.size();i++)
			((Forma)v.elementAt(i)).dibuja(g);
	}
	public static void main(String args[]){
		DibujaFig3 d=new DibujaFig3();
		d.setSize(800,400);
		d.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		c = JColorChooser.showDialog( this, "Seleccione un color", c );
                if ( c == null )
                	c = Color.BLACK;
	}
        class MouseMotionEvtHandler extends MouseMotionAdapter {  
             public void mouseDragged( MouseEvent e ){
			int sel; 
			sel=pal.getSeleccion();  
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
			int sel; 
			sel=pal.getSeleccion();  
			if(sel == 1){
				Linea l=new Linea((int)anchor.getX(), 
					   (int)anchor.getY(),
					   (int)e.getPoint().getX(), 
					    (int)e.getPoint().getY()
					);
				l.ponColor(c);
				v.addElement(l);
				
			}
			if(sel == 2){
				int ancho, alto;
				ancho=Math.abs((int)(anchor.getX()-e.getPoint().getX()));
			       alto=Math.abs((int)(anchor.getY()-e.getPoint().getY()));	
				Rectangulo r=new Rectangulo((int)anchor.getX(), 
					    (int)anchor.getY(),
					    ancho, alto);
				r.ponColor(c);
				v.addElement(r);
			} 
      			repaint(); 
    		}
  	}
}
