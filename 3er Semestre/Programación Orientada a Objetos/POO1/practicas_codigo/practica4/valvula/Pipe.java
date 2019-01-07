import java.awt.*;
import java.util.*;


public class Pipe extends Canvas implements WaterListener,
	Runnable {
	private Vector waterListeners = new Vector();
	private WaterEventObject lastWaterEvent;
	Thread thread;

	public Pipe(){
		setBackground(Color.blue);
		setSize(150,20);
		thread= new Thread(this);
		thread.start();
	}
	
	public Dimension getMinimumSize()
	{
		return new Dimension(150,20);
	}
	public void handleSplash(WaterEventObject e){
		lastWaterEvent=e;		
		setBackground(Color.blue);
		repaint();
		splash();
	}

	public void run(){
		while(true){
			try {
				thread.sleep(1000);
			}
			catch (Exception e){}
			if(lastWaterEvent != null){
				long dt=System.currentTimeMillis()-
					lastWaterEvent.getTimeOfEvent();
				if(dt > 2000){
					setBackground(Color.white);
					repaint();
				}
			}
		}
	}

	public synchronized void addWaterListener(WaterListener l){
		waterListeners.addElement(l);
	}
	public synchronized void removeWaterListener(WaterListener l){
		waterListeners.removeElement(l);
	}
	
	private void splash(){
		Vector l;
		WaterEventObject weo = new WaterEventObject(this);
		synchronized(this){
			l=(Vector)waterListeners.clone();
		}
		for(int i=0; i<l.size();i++){
			WaterListener wl=(WaterListener)l.elementAt(i);
			wl.handleSplash(weo);
		}
	}	
}
