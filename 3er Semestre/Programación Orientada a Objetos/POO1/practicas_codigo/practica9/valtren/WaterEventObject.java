import java.util.*;

public class WaterEventObject extends EventObject {
	long timeOfEvent;
	
	public WaterEventObject(Object o){
		super(o);
		timeOfEvent=System.currentTimeMillis();
	}
	public long getTimeOfEvent(){
		return timeOfEvent;
	}
}

