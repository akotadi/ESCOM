import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Valve extends Panel implements WaterListener, ActionListener,
	Runnable {
	private Vector waterListeners = new Vector();
	private WaterEventObject lastWaterEvent;
	private boolean open=true;
	private Canvas cnv;
	private JButton bAbre;
	Thread thread;

	public Valve(){
		setLayout(new BorderLayout());
		setSize(90,20);
		setFont(new Font("Helvetica", Font.PLAIN,8));
		cnv=new Canvas();
		cnv.setBackground(Color.blue);
		cnv.setSize(20,20);
		add("East",cnv);
		add("West",bAbre=new JButton("Open"));
		bAbre.addActionListener(this);
		//bAbre.setSize(30,30);
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
			cnv.setBackground(Color.blue);
			repaint();
			splash();
		}
	}

	public void actionPerformed(ActionEvent e) 
  	{
		if(isOpen())
		{
			setOpen(false);
			bAbre.setLabel("Closed");
		}
		else {
			setOpen(true);
			bAbre.setLabel("Open");
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
					cnv.setBackground(Color.white);
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
