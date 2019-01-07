import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

public class Icono implements Serializable {
	String programa;    
	int turno;
	ImageIcon im;
	public Icono(String programa, int turno, String im){
		this.programa=programa;
		this.turno=turno;
                this.im=new ImageIcon(im);	
	}
        public String getPrograma(){
               return programa;
	}
	public int getTurno(){
        	return turno;
	}
	 public ImageIcon getIcon(){
        	return im;
	}
}


