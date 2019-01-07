import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Topos2 extends Panel implements ActionListener, Runnable
{
	ImageIcon topo, topo_enojado, agujero;
	JButton zona_juego[]; 
	JLabel score;
	int puntuacion;
	
	Thread hilo;

	public Topos2 ()
	{
		puntuacion = 0;

		topo = new ImageIcon("topo.png");
		topo_enojado = new ImageIcon("enojado.jpg");
		agujero = new ImageIcon("agujero.png");
		
		setLayout(new GridLayout(6,4,15,20));
		zona_juego = new JButton[20];
		score = new JLabel("Puntuacion = "+puntuacion);

		for (int i = 0; i < 20; i++) 
		{
			double x = Math.random();

		 	if (x <= 0.5)
		 		zona_juego[i] = new JButton (topo);

		 	else
		 		zona_juego[i] = new JButton (agujero);

		 	add(zona_juego[i]);
		 	zona_juego[i].addActionListener(this);
		}
		
		add(score);
		hilo = new Thread(this);
		hilo.start();
	}

	public static void main(String s[])
	{
		Frame f=new Frame("Golpea a los topos");
		f.add("Center",new Topos2());
		f.setSize (1000,1000);
		f.setVisible(true);	
	}

	public void run()
	{
		while(true)
		{
			try
			{
				hilo.sleep(2000);
				for (int i = 0; i < 20; i++) 
			{
				double x = Math.random();

				if (x <= 0.5)
					zona_juego[i].setIcon(topo);
				
				else
					zona_juego[i].setIcon(agujero);
			}
			}

			catch(Exception e)
			{
				return;
			}
			
		}
		
	}

	public void actionPerformed (ActionEvent e)
	{
		JButton zona_juego = (JButton)e.getSource();
		if(zona_juego.getIcon() == topo)
		{
			zona_juego.setIcon(topo_enojado);
		 	puntuacion++;
		}
		score.setText("Puntuacion = "+ puntuacion);

	}

}
