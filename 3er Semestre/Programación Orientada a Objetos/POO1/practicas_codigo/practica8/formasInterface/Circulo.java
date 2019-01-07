import java.awt.*;

public class Circulo implements Dibujable {
	private int x=0, y=0, r=0;
	
	public Circulo(int x, int y, int r){
		this.x=x;
		this.y=y;
		this.r=r;
	}
	public void dibuja(Graphics g){
		g.drawOval(x,y,r,r);
	}
}


