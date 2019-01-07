import java.io.Serializable;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class MuestraIcon extends Panel implements Serializable {
	JLabel jlimage;
	JLabel jlcoment;        
	public MuestraIcon(String comen, ImageIcon ic){
		setLayout(new BorderLayout()); 
		jlcoment= new JLabel(comen);
		jlimage= new JLabel(ic);
		add(jlcoment, BorderLayout.NORTH);
		add(jlimage, BorderLayout.CENTER);
	}
        void setImage(ImageIcon ii){
		jlimage.setIcon(ii);
        }
	void setComent(String s){
		jlcoment.setText(s);
        }
}
