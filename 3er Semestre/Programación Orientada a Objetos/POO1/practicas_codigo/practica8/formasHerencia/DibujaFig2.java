import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DibujaFig2 extends Frame implements ActionListener{
	private Forma c, l, r;
	Vector points = new Vector(); 
	Button b;
	Image img;

	public DibujaFig2()
	{
		super("Dibuja");
		addMouseListener(new MouseEventHandler()); 
		c= new Circulo(50,50,30);
		l= new Linea(0,0,75,75);
		r= new Rectangulo(10,10,90,90);
		//img=getImage("/","Duke.gif");
		b=new Button("dibuja");
		b.addActionListener(this);
		add("South",b);
	}
	public void paint(Graphics g)
	{
		//Graphics g=getGraphics();
		c.ponColor(new Color(1,0,0));
		g.setColor(Color.blue);
		g.drawString("Dibuja todo",50,50);
     		//g.drawImage(img,50,50,this);
		c.dibuja(g);
		l.dibuja(g);
		r.dibuja(g);
		 for (int i = 0; i < points.size(); i++) { 
			Point pt = (Point)points.elementAt(i);      
         		g.drawOval(pt.x, pt.y,50,50); 
         	}
	}
	public static void main(String args[]){
		DibujaFig2 d=new DibujaFig2();
		d.setSize(200,200);
		d.setVisible(true);
		//d.paint();
	}
	public void actionPerformed(ActionEvent e){
		repaint();
	}
 	class MouseEventHandler extends MouseAdapter { 
   		public void mousePressed(MouseEvent evt)  { 
      		//g.drawString("Dibuja todo",50,50);
     			points.addElement(evt.getPoint());          
      			repaint(); 
    		}
    		public void mouseReleased(MouseEvent evt) { 
      			repaint(); 
    		}
  	}
}
