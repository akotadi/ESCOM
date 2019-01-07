import java.awt.*;
import java.applet.*;
import java.util.*;

public class DibujaFig1 extends Applet {
	int SMALL_OFFSET=700, BIG_OFFSET=700;
	private Vector v=new Vector();
	public void init(){
		v.addElement(new Circulo(50,50,50));
		v.addElement(new Circulo(20,20,50));
		v.addElement(new Linea(0,0,75,75));
		v.addElement(new Rectangulo(10,10,30,90));
	}
	public void paint(Graphics g){
		for(int i=0;i<v.size();i++)
			((Forma)v.elementAt(i)).dibuja(g);
		spray(g, 40,40);	
		
	}
	public void spray(Graphics g, int x, int y){
		int tempx ,tempy;
		for (int i=0; i<35; i++){
			// use static final ints now
			tempx = (x + (int) Math.round(2*SMALL_OFFSET*(Math.random() -0.5)));
			tempy = (y + (int) ( ((Math.random()-0.5)*2) * Math.sqrt(
				(SMALL_OFFSET * SMALL_OFFSET) - ((x - tempx) * (x - tempx)))));
			g.drawLine(tempx, tempy, tempx+30, tempy+30);
		}
		
		for (int i=0; i<12; i++){
			tempx = (x + (int) Math.round(2*BIG_OFFSET*(Math.random() -0.5)));
			tempy = (y + (int) ( ((Math.random()-0.5)*2) * Math.sqrt(
				(BIG_OFFSET * BIG_OFFSET) - ((x - tempx) * (x - tempx)))));
			g.drawLine(tempx, tempy, tempx+30, tempy+30);
		}
		//repaint();
	}
}
