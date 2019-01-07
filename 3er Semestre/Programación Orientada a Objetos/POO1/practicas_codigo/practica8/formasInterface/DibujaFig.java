import java.awt.*;
import java.applet.*;

public class DibujaFig extends Applet {
	private Dibujable c, l, r;

	public void init() {
		c= new Circulo(50,50,30);
		l= new Linea(0,0,75,75);
		r= new Rectangulo(10,10,90,90);
	}
	public void paint(Graphics g){
		c.dibuja(g);
		l.dibuja(g);
		r.dibuja(g);
	}
}
