import java.awt.*;
import java.io.Serializable;

public class Rectangulo implements Dibujable , Serializable {
	private int x1=0, y1=0;
	private int x2=0, y2=0;
	protected Color color;
	String programa;
	public void ponColor(Color c){
		color=c;
	}	
	public Rectangulo()
	{
		this.x1=(int) (200 * Math.random());
		this.y1=(int) (200 * Math.random());
		this.x2=(int) (100 * Math.random());
		this.y2=(int) (100 * Math.random());
	}
	public Rectangulo(String programa, int x1, int y1, int x2, int y2){
		this.programa=programa;
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
	public void dibuja(Graphics g){
		g.setColor(color);
		g.drawRect(x1,y1,x2,y2);
	}
	public String getPrograma(){
               return programa;
	}
}
