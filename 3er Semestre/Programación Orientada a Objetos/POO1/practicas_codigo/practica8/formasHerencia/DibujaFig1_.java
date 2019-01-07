import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DibujaFig1_ extends Frame implements ActionListener{
	private Forma c;
	private Forma l;
	private Forma r;
	Vector points = new Vector(); 
	Button b;
	Image img;

	public DibujaFig1_()
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
		 for (int i = 0; i < points.size(); i++) // for last pt to all new pts
      		{ 
			Point pt = (Point)points.elementAt(i);    // retrieve a point pt   
         			g.drawOval(pt.x, pt.y,50,50); // connect current to pt
         		}
	}
	public static void main(String args[]){
		DibujaFig1_ d=new DibujaFig1_();
		d.setSize(200,200);
		d.setVisible(true);
		//d.paint();
	}
	public void actionPerformed(ActionEvent e){
		repaint();
	}
 class MouseEventHandler extends MouseAdapter    // mouse event handler
  { 
   public void mousePressed(MouseEvent evt)      // handles mouse presses
    { 
      //g.drawString("Dibuja todo",50,50);
     points.addElement(evt.getPoint());          
      repaint(); // and scheduling an update
    }

    public void mouseReleased(MouseEvent evt)     // handles mouse releases
    { 
      repaint(); // and scheduling an update
    }
  }
}
