import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*; 
import java.lang.Thread.*;
import java.lang.*;


public class CuentaSegundos extends JFrame implements Runnable
{
	Panel p;
	Thread hilo;
	int contador, limite_cont;
	JLabel etiqueta;

	public CuentaSegundos(int limite)
	{
		p = new Panel();
		contador = 0;
		limite_cont = limite;
		etiqueta = new JLabel("0");
	
		add(p,"Center");
		p.add(etiqueta);
		setSize(500, 500);
	    setVisible(true);

		hilo = new Thread(this);
		hilo.start();
	}

	public void run()
	{
		while(contador <= limite_cont)
		{
			try
			{
				hilo.sleep(1000);
				etiqueta.setText(""+contador);
			}
			catch(InterruptedException e)
			{
				return;
			}

			contador++;
		}
	}

	public static void main (String s[]) 
	{
		new CuentaSegundos(7);
	}
}