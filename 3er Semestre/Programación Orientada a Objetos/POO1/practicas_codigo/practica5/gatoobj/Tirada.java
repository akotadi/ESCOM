import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

public class Tirada implements Serializable {
	String programa;
	String nick;
        int posicion;

	public Tirada(String programa, String nick, int posicion)
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
        public int getPosicion(){
        	return posicion;
	}
}


