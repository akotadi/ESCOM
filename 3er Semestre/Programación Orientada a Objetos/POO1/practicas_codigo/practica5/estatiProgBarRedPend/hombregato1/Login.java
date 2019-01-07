import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

public class Login implements Serializable {
	String programa;
	String nick;
	boolean rep;

	public Login(String programa, String nick, boolean rep)
	{
		this.programa=programa;
		this.nick=nick;
		this.rep=rep;
	}
        public String getPrograma(){
               return programa;
	}
	public String getNick(){
		return nick;
	}
	public boolean getRep(){
		return rep;
	}
}


