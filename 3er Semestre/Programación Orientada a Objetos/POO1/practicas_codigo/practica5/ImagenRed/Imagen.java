import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

public class Imagen implements Serializable {
	String programa;
	String nick;
        ImageIcon im;

	public Imagen(String programa, String nick, String im)
	{
		this.programa=programa;
		this.nick=nick;
                this.im=new ImageIcon(im);
		
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
}


