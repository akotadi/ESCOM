import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class FlappyPan extends JPanel {
   int posXave=0, posYave=200;
   int posXtubo=700, posXtubo2=1250;
   int r1,r2,r3,r4;
   JLabel Imagen;
   JLabel tubos[];  
   int frame;
   ImageIcon iconos[];
   Thread hilo;
   public FlappyPan(){
      iconos=new ImageIcon[3];
      tubos= new JLabel[4];
      for(int i=0; i <iconos.length;i++)
	 iconos[i] =new ImageIcon("ave"+(i+1)+".png");
      setVisible(true);
      setBounds(0,0,1000,705);	
      setLayout(null);
      add(Imagen =new JLabel(iconos[0]));
      for(int i=0; i <4;i++)
         add(tubos[i] =new JLabel(new ImageIcon("tubo.png")));
      Imagen.setBounds(posXave,posYave,100,50);
      r1= (int)(Math.random()*300);
      r2= r1+150;
      r3= (int)(Math.random()*300);
      r4= r3+150;	
      tubos[0].setBounds(posXtubo,0,200,r1);
      tubos[1].setBounds(posXtubo,r2,200,653-r2);
      tubos[2].setBounds(posXtubo2,0,200,r3);
      tubos[3].setBounds(posXtubo2,r4,200,653-r4);
      paintComponents(getGraphics());
   } 		
}
