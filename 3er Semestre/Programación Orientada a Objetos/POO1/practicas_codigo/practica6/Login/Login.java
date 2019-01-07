import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Login extends JPanel implements LeeRed, ActionListener {	
JButton bconecta, benvia;
JTextField tf;
Red r;
public Login(){
        JPanel p1, p2;
	p1=new JPanel(); p2=new JPanel();
	p1.setLayout(new GridLayout(3,3));
	p2.setLayout(new BorderLayout());	
        bconecta=new JButton("Conecta");
	bconecta.addActionListener(this);  
        benvia=new JButton("Envia");
	benvia.addActionListener(this); 
        tf=new JTextField(15);    
        p2.add(bconecta, BorderLayout.NORTH);
        p2.add(benvia, BorderLayout.CENTER);
	p2.add(tf, BorderLayout.SOUTH);
        add(p2);
} 
public static void main(String args[]){
	JFrame f=new JFrame("Login");
	f.add("Center", new Login());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        f.setSize(250, 250); f.setVisible(true);	
}      
public void leeRed(Object obj){
	if(obj!=null){
		UserSession u=(UserSession)obj;
        	System.out.println("Cli read " + u.getUser());
		System.out.println("Cli read " + u.getLoginTime());  
	}
        else System.out.println("obj nullo ");              	
}
public void actionPerformed(ActionEvent e) {
	JButton btn=(JButton)e.getSource();
        if(btn==bconecta) r=new Red(this);
	else { 
                if(!e.getActionCommand().equals("Conecta")){      
                	UserSession c=new UserSession();
			c.setUser(tf.getText());
                    	c.setLoginTime(new Date());                         
			r.escribeRed(c);
     		}          
	}
}
}
