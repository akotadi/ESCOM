import java.awt.*;
import java.applet.*;
import java.util.*;

public class DibujaFig1 extends Applet {
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
	}
}
