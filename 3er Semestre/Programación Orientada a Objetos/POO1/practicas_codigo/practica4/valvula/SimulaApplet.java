import java.awt.*;
import java.applet.*;


public class SimulaApplet extends Applet {
	private WaterSource ws;
	private Valve v;
	private Pipe p;
	public SimulaApplet(){
		setLayout(null);   
   		setBackground(new Color(0xbdb9a3));  
   		setSize(350, 200);      		
		ws=new WaterSource();
		v=new Valve();
		p=new Pipe();		
		add(ws);		
		ws.setLocation(50, 70);   		
   		ws.setVisible(true);
		ws.addWaterListener(v);		
		add(v);
                v.setLocation(80,100);
		v.setVisible(true);
		v.addWaterListener(p);
		add(p);		
		p.setLocation(135,100);
		p.setVisible(true);
	}
	public static void main(String ags[])
	{
		Frame f=new Frame("Simula");
		SimulaApplet s=new SimulaApplet();
		f.add(s);
		s.init();
		s.start();
		f.pack();		
		f.show();
	}
}