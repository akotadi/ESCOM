import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

public class Rotacion implements Serializable {
	String programa;
	String nick;
        double angulo;

	public Rotacion(String programa, String nick, double angulo)
	{
		this.programa=programa;
		this.nick=nick;
                this.angulo=angulo;
		
	}
        public String getPrograma(){
               return programa;
	}
	public String getNick(){
		return nick;
	}
        public double getAngulo(){
        	return angulo;
	}
}


