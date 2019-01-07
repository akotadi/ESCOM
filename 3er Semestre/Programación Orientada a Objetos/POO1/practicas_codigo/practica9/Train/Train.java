import java.applet.Applet;
import java.awt.*;

public class Train extends Applet
{
    
    // create two Compartments and an engine
    private Compartment bogie1, bogie2;
    private Engine eng1;
    private Graphics page;
    private int isleep = 800;
    
    public void init()
    {
       setVisible(true);
       setSize(900,300);
       page = getGraphics();
    }
    
    public void start()
    {
           
        setVisible(true);
        setBackground(Color.green);
        
        bogie1 = new Compartment();
        bogie2 = new Compartment();
        eng1 = new Engine();
        
        page.setColor(Color.red);
        
        // for the movement of the train
        // have a loop
        for (int i=0;i<21;i++)
        {
            
            // clear the part of the screen having the train
            page.clearRect(0,0,400,200);
           
            // draw the tracks
            page.drawLine(0,90,400,90);
                
            // draw the engine
            eng1.draw(190+i*5,40,i%2);
            // the connection between the engine and the first compartment
            page.drawLine(190+i*5,60,180+i*5,60);     
            // the first compartment
            bogie1.draw(10+i*5,40);
            // the connection between the first and second compartments
            page.drawLine(100+i*5,60,90+i*5,60);     
            // the second compartment
            bogie2.draw(100+i*5,40);
            
            // call delay to see the effect of the train's new position
            delay();
        }
               
    }
    
    public void delay()
    {
            try {
                Thread.sleep(isleep);
                }
            catch (Exception e)
                {
                    return;
                }
    }
    
    // class Compartment has a method draw to draw the compartment
    class Compartment
    {
         // takes as parameters two integers that specify
         // the top left corner of the compartment
         public void draw(int xleft, int ytop)
         {
              page.fillOval(xleft+5,ytop+20,30,30);
              page.fillOval(xleft+45,ytop+20,30,30);
              page.fill3DRect(xleft,ytop,80,30,true);
              page.fill3DRect(xleft+15,ytop+5,10,5,true);
              page.fill3DRect(xleft+55,ytop+5,10,5,true);
         }    
    }// end class - Compartment
    
    // class Engine has a method draw to draw the compartment
    class Engine
    {
         // takes as parameters two integers that specify
         // the top left corner of the engine
         public void draw(int xleft, int ytop, int smoke)
         {
              page.fillOval(xleft+5,ytop+20,30,30);
              page.fillOval(xleft+45,ytop+20,30,30);
              page.fill3DRect(xleft,ytop,50,30,true);
              page.fill3DRect(xleft+50,ytop+15,30,15,true);
              page.fill3DRect(xleft+60,ytop-10,10,25,false);
              page.fill3DRect(xleft+30,ytop+10,5,5,true);
              if (smoke==1)
                 page.fillArc(xleft+60,ytop-20,10,10,0,135);
         }
         
    }// end class -Engine
    
    
}// end class Train

