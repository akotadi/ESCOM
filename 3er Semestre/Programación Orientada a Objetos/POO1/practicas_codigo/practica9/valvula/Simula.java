import java.awt.*;


public class Simula extends Frame {
	private WaterSource ws;
	private Valve v;
	private Pipe p;
	public Simula(){
		setLayout(null);   
   		setBackground(new Color(0xbdb9a3));  	     		
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
		Simula s=new Simula();
		s.setSize(350, 200); 		
		s.setVisible(true);
	}
}
