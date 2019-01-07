import java.awt.*;
import java.io.Serializable;

public class Linea implements Dibujable , Serializable {
	private int x1=0, y1=0;
	private int x2=0, y2=0;
	protected Color color;
	public void ponColor(Color c){
		color=c;
	}
	public Linea(int x1, int y1, int x2, int y2){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
	public void dibuja(Graphics g){
		g.setColor(color);
		g.drawLine(x1,y1,x2,y2);
	}
}
