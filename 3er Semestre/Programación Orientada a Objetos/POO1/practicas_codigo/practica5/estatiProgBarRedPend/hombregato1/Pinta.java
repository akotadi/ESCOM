
import java.io.Serializable;

class Pinta implements Serializable {
	String programa;
	String nick;
	Dibuja dibu;
        
	public Pinta(String programa, String nick, Dibuja dibu){
		this.programa=programa;
		this.nick=nick;
		this.dibu=dibu;
	}
	public String getPrograma(){
               return programa;
	}
	public String getNick(){
               return nick;
	}
	public Dibuja getDibuja(){
	       return dibu;
	}
}
