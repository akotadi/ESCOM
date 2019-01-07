
import java.io.Serializable;

class Juega implements Serializable {
	String programa;
	String nick;
	Gato minino;
        
	public Juega(String programa, String nick, Gato minino){
		this.programa=programa;
		this.nick=nick;
		this.minino=minino;
	}
	public String getPrograma(){
               return programa;
	}
	public String getNick(){
               return nick;
	}
	public Gato getMinino(){
	       return minino;
	}
}
