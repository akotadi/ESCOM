import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
public class DibujaFig3 extends Applet implements LeeRed, ActionListener{
        JButton bcrea;
        TextField t;
        Red r;
        private static int size=300;
public void init(){
        add(t=new TextField(20));
        r=new Red(this);
}
public static void main(String args[]){
	DibujaFig3 df=new DibujaFig3();
	df.init();
	df.start();
	JFrame f=new JFrame("Dibuja");  
	f.add("Center",df);  
        f.add("South", df.bcrea=new JButton("crea"));         
        df.bcrea.addActionListener(df); 
        f.setSize(400, 300);                 
	f.setVisible(true);
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
}
public void leeRed(Object obj){
	System.out.println("recibi clase "+ obj.getClass().getName());
	if( obj instanceof JPanel){
		JPanel jp=(JPanel)obj;
                //c=(JPanel)Class.forName(c.getClass().getName()).newInstance();
		add(jp);
                repaint(); 
         }
}
public void actionPerformed(ActionEvent e) {
	JButton btn=(JButton)e.getSource();
	crea(t.getText());
}
void crea(String nombre){
        JPanel d; 
        try {   
        	d =(JPanel)Class.forName(nombre).newInstance();
		add(d);
        	repaint();
        	System.out.println("clase "+d.getClass().getName());    
        	r.escribeRed(d); 
	} catch (Throwable e1) {
            System.err.println(e1);
        }
}
}                                                                                    
