import java.awt.*;
import java.applet.*;
import java.util.*;

public class DibujaFig1 extends Applet {
	private Vector<Dibujable> v=new Vector<Dibujable>();
	public void init(){
		v.addElement(new Circulo(70,70,120));
		v.addElement(new Circulo(20,20,50));
		v.addElement(new Linea(0,0,75,75));
		v.addElement(new Rectangulo(1,1,75,150));
                //v.addElement(new Cuadrado(75,75,20));	
	}
	public void paint(Graphics g){
		System.out.println("size ("+v.size()+")");
                //g.drawString("size ("+v.size()+")",20,50);
		for(int i=0;i<v.size();i++)
			((Dibujable)v.elementAt(i)).dibuja(g);
		
	}
}
