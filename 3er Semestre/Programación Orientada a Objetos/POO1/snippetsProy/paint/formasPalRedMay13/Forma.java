import java.awt.*;

public abstract class Forma {
	protected Color color;
	public void ponColor(Color c){
		color=c;
	}
	public abstract void dibuja(Graphics g);
}
