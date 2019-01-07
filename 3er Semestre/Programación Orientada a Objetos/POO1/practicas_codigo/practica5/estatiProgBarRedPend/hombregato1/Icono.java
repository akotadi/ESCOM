import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

public class Icono implements Serializable {
	String programa;
	String nick;
        ImageIcon im;
	int turno;

	public Icono(String programa, String nick, ImageIcon im, int turno)
	{
		this.programa=programa;
		this.nick=nick;
                this.im=im;
		this.turno=turno;	
	}
        public String getPrograma(){
               return programa;
	}
	public String getNick(){
		return nick;
	}
        public ImageIcon getIcon(){
        	return im;
	}
	public int getTurno(){
        	return turno;
	}
}


