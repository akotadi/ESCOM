import java.util.*;


public interface WaterListener extends EventListener {
	void handleSplash(WaterEventObject weo);
}