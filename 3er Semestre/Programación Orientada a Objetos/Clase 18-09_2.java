import Java.awt.*;

public abstract class figura{
	private Color color;
	public abstract void dibuja(Graphics g);
}

public class Punto extends figura{
	private int x,y;
	public Punto(){
		x=0;
		y=0;
	}
	public Punto(int x, int y){
		this.x=x;
		this.y=y;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public void setx(int x){
		this.x=x;
	}
	public void sety(int y){
		this.y=y;
	}
	public void dibujar(graphics g){
		g.drawLine(x,y,this.x,this.y);
	}
}

public class Linea extends figura{
	int x1, x2, y1, y2;
	public void dibuja(Graphics g){
		g.drawLine(x1,y1,x2,y2);
	}
	public Linea(int x1, int y1, int x2, int y2){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
}

public class Circulo extends figura{
	int r, x, y;
	public void dibuja(Graphics g){
		g.drawOval(x,y,r,r);
	}
	public Circulo(int r, int x, int y){
		this.r=r;
		this.x=x;
		this.y=y;
	}
}