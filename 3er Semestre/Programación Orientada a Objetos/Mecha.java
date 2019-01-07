import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
import java.io.*;
//import java.util.*;
//import java.lang.*;
//import java.lang.Thread.*;

public class Mecha extends Frame implements Runnable
{
	Panel p;
	int contador;
	Label muestra;
	Thread hilo;

	public Mecha()
	{
		p = new Panel();
		contador = 10;
		muestra = new Label("10");
		add(p,"Center");
		p.add(muestra);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		setSize(100,100);
		setVisible(true);
		hilo = new Thread(this);
		hilo.start();

	}

	public void run()
	{
		while(contador > 0)
		{
			try
			{
				muestra.setText(contador+"");
				hilo.sleep(1000);
				contador--;
			}
			catch(InterruptedException e)
			{
				return;
			}
		}
		if(contador == 0)
		{
			muestra.setText("BOOOM!!");
		}
	}

	public static void main(String args[])
	{
		new Mecha();
	}
}
