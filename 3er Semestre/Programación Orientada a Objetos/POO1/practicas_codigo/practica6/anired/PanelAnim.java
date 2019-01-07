import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class PanelAnim extends JPanel implements Animable , Serializable, ActionListener {

  	JButton bStart;
        JButton bStop;
	IconAnim ia;
	
	public PanelAnim(){
                setLayout(new BorderLayout());
		bStart=new JButton("Start");      
                bStart.addActionListener(this);       
        	bStop=new JButton("Stop");         
        	bStop.addActionListener(this);   
		ia=new IconAnim();
		add("West", bStart);  
		add("East", bStop);  
		add("Center", ia);       
	}
	public void actionPerformed(ActionEvent e) 
	{
		JButton btn=(JButton)e.getSource();
		if(btn==bStart){
			start();
		}
		if(btn==bStop){
			stop();
		}
	}
	public void start(){    
		ia.start();	
	}
	public void stop(){
		ia.stop();
	}
}
