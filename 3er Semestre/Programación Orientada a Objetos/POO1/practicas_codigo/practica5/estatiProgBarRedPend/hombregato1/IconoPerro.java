import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

public class IconoPerro /*extends ImageIcon*/ implements Dibujable , Serializable {
	String nombre;
        ImageIcon im;
        private Point fPt;
	

        public IconoPerro(){
		fPt = new Point(100, 100);
		this.nombre="perro.jpg";
                im=new ImageIcon(nombre);
	}
	public IconoPerro(Point p1, String nombre){
		fPt = p1;
		this.nombre=nombre;
                im=new ImageIcon(nombre);
	}
	public void dibuja(Graphics g){       
		g.drawImage(im.getImage(), fPt.x, fPt.y, null);
	}
}


