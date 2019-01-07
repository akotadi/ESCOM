import java.awt.*;
import java.util.*;

//C:\Program Files\Java\jdk1.6.0_21\bin\javac
public class Movil extends Canvas  {
     	int x, i;

	public Movil(int i, int x){
           	this.i=i;
 		if(i==0)		
		 	setBackground(Color.blue);
		if(i==1)
		   	setBackground(Color.green);
		if(i==2)
		   	setBackground(Color.red);
		setSize(25,10);
           	setLocation(x,40);
	     	this.x=x;	
	}
	
	public Dimension getMinimumSize()
	{
		return new Dimension(150,20);
	}
	public void handleSplash(WaterEventObject e){
           	if(i==0)		
		 setBackground(Color.blue);
	      	if(i==1)
		   setBackground(Color.green);
		if(i==2)
		   setBackground(Color.red);
                System.out.println("x=("+x+")"+i);
		x=x+30;
		setLocation(x,40);
		repaint();
		//splash();
	}
}
