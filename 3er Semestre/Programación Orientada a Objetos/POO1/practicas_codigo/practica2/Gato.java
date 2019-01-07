import java.awt.event.*;
import java.awt.*;
public class Gato extends Frame implements ActionListener {
	Button botones[]=new Button[9];
	String jugadores[]={"X", "0"};
	int turno=0;
	public Gato(){
		setLayout(new GridLayout(3,3));
		for(int i=0; i<9; i++){
			add(botones[i]=new Button(""+i));
			botones[i].addActionListener(this);
		}
                setSize(300, 300); setVisible(true);    
	}
	public void actionPerformed(ActionEvent e) {
		Button btn=(Button)e.getSource();	
		btn.setLabel(jugadores[turno]);
		btn.setEnabled(false); turno=1-turno;                    
	}
	public static void main(String s[]){ new Gato(); }
}
