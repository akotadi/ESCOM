import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Paleta extends JPanel {
        AccionInt actor;
	JButton botones[];
	int sel=0;

	public Paleta(String captions[], LayoutManager lm, AccionInt ai){
                botones=new JButton[captions.length];
		setLayout(lm);
                actor=ai;
		for(int i=0; i<botones.length; i++){
			botones[i]=new JButton(captions[i]);
			add(botones[i]);
			botones[i].addActionListener(new ManejaBotones());
		}
	}

	int getSeleccion(){
		return sel;
	}

	class ManejaBotones implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JButton j=(JButton)e.getSource();
			for(int i=0; i<botones.length; i++){
				if(botones[i]==j){
					sel=i;
				}
			}
                        actor.accion(sel);
		}	
	}
}
