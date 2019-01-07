import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class DibujaAnim extends Applet implements ActionListener{
        TextField t;
	private IconAnim ia;

	public void init(){
                
		add(t=new TextField("holilla", 20));
                ia=new IconAnim();
                add(ia);
		//t.addActionListener(this);
                //ia.dibuja(g);
	}
/*
        public void paint(Graphics g){
                if (d!=null)
			d.dibuja(g);	
	}*/
        public void actionPerformed(ActionEvent e) 
  	{	
		
	}
}
