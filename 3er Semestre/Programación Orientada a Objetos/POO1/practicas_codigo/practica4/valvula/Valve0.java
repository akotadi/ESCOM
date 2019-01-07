import java.awt.*;
import java.util.*;
import WaterListener;

public class Valve extends Canvas implements WaterListener,
	Runnable {
	private Vector waterListeners = new Vector();
	private WaterEventObject lastWaterEvent;
	private boolean open=true;
	Thread thread;

	public Valve(){
		setBackground(Color.green);
		resize(30,30);
		thread= new Thread(this);
		thread.start();
	}
	
	public boolean isOpen(){
		return open;
	}
	public void setOpen(boolean x){
		open=x;
	}
	public Dimension getMinimumSize()
	{
		return new Dimension(20,30);
	}
	public void handleSplash(WaterEventObject e){
		lastWaterEvent=e;
		if(isOpen()){
			setBackground(Color.blue);
			repaint();
			splash();
		}
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
				if(dt > 2000 || (!isOpen())){
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