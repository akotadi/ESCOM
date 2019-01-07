import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;
public class RompeCabezas extends Applet  {
	JButton boton[];
	int v, h;				// Se divide la imagen V x H celdas
        int iw, ih;				// Ancho y Alto de la imagen
        int cw, ch;				// Ancho y Alto de una celda
	int last;				// Ultima celda (negra)
	int numCeldas;			// Numero de celdas
	Image[] Celda;			// Arreglo de imagenes
	int[] Orden;			// Orden de las imagenes
	Image Imagen;	
        public RompeCabezas(){
		init(); start();
		JFrame f=new JFrame("Rompecabezas");
		f.add("Center", this);
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
                f.setSize(400, 300);
		f.setVisible(true);
        }
	public void init(){
		numCeldas = 0;
                Imagen = (new ImageIcon("dalmata.jpg")).getImage();
		iw = Imagen.getWidth(null); ih = Imagen.getHeight(null);
		v=4;h=4;
		cw = iw / h;  ch = ih / v;            
                numCeldas = v*h;
		last = numCeldas - 1;
		System.out.println("iw= "+iw+"ih= "+ih+" last= "+last);     
		Celda = new Image[numCeldas];
		Orden = new int[numCeldas];
		CropImageFilter f;
		FilteredImageSource fis;	
		boton=new JButton[numCeldas];			
		for (int y=0; y < v; y++){
			for (int x=0; x < h; x++){
				f = new CropImageFilter(cw*x, ch*y, cw, ch);
				fis = new FilteredImageSource(Imagen.getSource(), f);
				int i = y*h+x;
				Orden[i] = i;
				Celda[i] = createImage(fis);
			}
		}
                for (int i=0; i < numCeldas; i++)
			add(boton[i]=new JButton(
                                    new ImageIcon(Celda[Orden[i]])));
		dibuja();
	}
	private void dibuja(){
		for (int i=0; i < numCeldas; i++)
			if ( Orden[i] != last) 
				boton[i].setIcon(new ImageIcon(Celda[Orden[i]])) ;
                        /*else
				boton[i].setIcon(new ImageIcon("labrador.jpg"));*/
	}
	public static void main(String args[]){  new RompeCabezas();	}
}
