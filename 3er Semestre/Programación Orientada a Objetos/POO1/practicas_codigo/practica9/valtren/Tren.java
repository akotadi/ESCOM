import java.awt.*;
import java.awt.event.*;

public class Tren extends Frame {
	private WaterSource ws;
	private Movil m1,m2,m3;

	public Tren(){
		setLayout(null);   
   		setBackground(new Color(0x00b9a3));  
   		setSize(350, 200); 
                addWindowListener(new WindowHandler());     	
		ws=new WaterSource();
                m1=new Movil(0,95);	
		m2=new Movil(1,50);
		m3=new Movil(2,5);
		add(ws);		
		ws.setLocation(50, 70);   		
   		ws.setVisible(true);
		//ws.addWaterListener(m1);		
		add(m1);
           //m1.setLocation(95,60);
	        m1.setVisible(true);
	        //m1.addWaterListener(m2);
                add(m2);
           //m2.setLocation(50,60);
	        m2.setVisible(true);
	        //m2.addWaterListener(m3);
	        add(m3);		
		//m3.setLocation(5,60);
		m3.setVisible(true);
	}
	public static void main(String ags[])
	{
		Tren s=new Tren();		
		s.setVisible(true);
	}
	public class WindowHandler extends WindowAdapter {
  		public void windowClosing(WindowEvent e) {
   		System.exit(0);
  		}
 	}
}
