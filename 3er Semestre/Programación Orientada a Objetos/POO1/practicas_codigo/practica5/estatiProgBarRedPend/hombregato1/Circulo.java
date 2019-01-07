import java.awt.*;
import java.io.Serializable;

public class Circulo implements Dibujable, Serializable {
	private int x=0, y=0, r=0;
	protected Color color;
        String programa;
	public void ponColor(Color c){
		color=c;
	}
	public Circulo()
	{
		this.x= (int) (200 * Math.random());
		this.y= (int) (200 * Math.random());
		this.r= (int) (70 * Math.random());
	}
	public Circulo(String programa, int x, int y, int r){
		this.programa=programa;
		this.x=x;
		this.y=y;
		this.r=r;
	}
	public void dibuja(Graphics g){
		g.drawOval(x,y,r,r);
	}
	 public String getPrograma(){
               return programa;
	}
}


