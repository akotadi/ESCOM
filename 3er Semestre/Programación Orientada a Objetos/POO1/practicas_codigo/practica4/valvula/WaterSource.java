import java.awt.*;
import java.util.*;

public class WaterSource extends Canvas implements Runnable {
	private Vector waterListeners = new Vector();
	Thread thread;
	
	public WaterSource(){
		setBackground(Color.blue);
		setSize(30,50);
		thread= new Thread(this);
		thread.start();
	}

	public Dimension getMinimumSize()
	{
		return new Dimension(15,15);
	}
	public void run(){
		while(true){
			splash();
			try {
				thread.sleep(1000);
			}
			catch (Exception e){}
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
