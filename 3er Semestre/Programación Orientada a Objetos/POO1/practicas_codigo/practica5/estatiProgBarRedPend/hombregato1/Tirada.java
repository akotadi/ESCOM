import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

public class Tirada implements Serializable {
	String programa;
	String nick;
        byte posicion;

	public Tirada(String programa, String nick, byte posicion)
	{
		this.programa=programa;
		this.nick=nick;
                this.posicion=posicion;
		
	}
        public String getPrograma(){
               return programa;
	}
	public String getNick(){
		return nick;
	}
        public byte getPosicion(){
        	return posicion;
	}
}


