//Punto.java
import java.awt.*;

class Punto {
int x = 0;
int y = 0;

public Punto ( ) {
x=5;
y=5;
}
public Punto (int x, inty ) {
this. x=x;
this. y=y;
}
int obtenX {
      		return x;
}
int obtenY ( ) {
      		return y;
}
void ponX (int x) {
        		this.x=x;
}
void ponY(int y ) {
       	 	this.y=y;
}
public void mueve (int dx, int dy ){
            	x=x+dx;
             	y=y+dy:
}
public void dibuja (Graphics g ){
       		g.drawLine (x, y, x, y );
}
}
