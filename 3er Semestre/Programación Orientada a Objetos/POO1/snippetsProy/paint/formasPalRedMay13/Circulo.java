import java.awt.*;
import java.io.Serializable;

public class Circulo implements Dibujable, Serializable {
	private int x=0, y=0, r=0;
	protected Color color;
	public void ponColor(Color c){
		color=c;
	}
	public Circulo(int x, int y, int r){
		this.x=x;
		this.y=y;
		this.r=r;
	}
	public void dibuja(Graphics g){
		g.setColor(color);
		g.drawOval(x,y,r,r);
	}
}


