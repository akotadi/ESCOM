import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class PaletaGral extends JPanel implements ActionListener {
	JButton botones[];
	int sel=-1;
        AccionInt actor;
	
	PaletaGral(Object arr[],LayoutManager lm, AccionInt ai){             
		//if(arr instanceof ImageIcon[]		
		actor=ai;
                setLayout(lm);
		botones=new JButton[arr.length];
		for(int i=0; i< botones.length;i++){
			if(arr[i] instanceof ImageIcon)
				botones[i]=new JButton((ImageIcon)arr[i]);
			if(arr[i] instanceof String)
				botones[i]=new JButton((String)arr[i]);
			add(botones[i]);
			botones[i].addActionListener(this);
		}
		setVisible(true);
	}
        public int getSeleccion(){
		return sel;
	}	
	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		for(int i=0; i< botones.length;i++){
			if(b == botones[i]){
				sel=i;
			}
		}
		actor.accion(sel);
	}
}
