import javax.swing.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.lang.*;

public class Tablero extends JFrame {
	private JButton tablero[][];
	private JLabel lista[];

	public Tablero(char[][] letras, String[] palabras){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tablero");
        setResizable(false);
		getContentPane().setLayout(null);

		JPanel p1 = new JPanel(new GridLayout(16,16));
		tablero = new JButton[15][15];
		for (int i = 0; i <= tablero.length; i++) {
			for (int j = 0; j <= tablero.length; j++) {
				if (i == 0 && j == 0) {
					p1.add(new JLabel("y\\x"));
				}else if (i == 0) {
					p1.add(new JLabel(Integer.toString(j-1)));
				}else if(j == 0){
					p1.add(new JLabel(Integer.toString(i-1)));
				}else{
					tablero[i-1][j-1] = new JButton(Character.toString(letras[i-1][j-1]));
					tablero[i-1][j-1].setEnabled(false);
					tablero[i-1][j-1].setBackground(Color.lightGray); // cyan
					p1.add(tablero[i-1][j-1]);
				}
			}
		}
		getContentPane().add(p1);
		p1.setBounds(0, 0, 750, 730);

		JPanel p2 = new JPanel(new GridLayout(16,1));
		lista = new JLabel[15];
		for (int i = 0; i <= palabras.length; i++) {
			if (i == 0) {
				p2.add(new JLabel(" "));
			}else{
				lista[i-1] = new JLabel(palabras[i-1]);
				p2.add(lista[i-1]);
			}
		}
		getContentPane().add(p2);
		p2.setBounds(750, 0, 150, 730);
	}

	public void actualizarTiro(int x1, int y1, int x2, int y2){
		// System.out.println("Entrando a actualizar con "+x1+":"+y1+" - "+x2+":"+y2);
		if (x1 != x2 && y1 != y2) {
			// System.out.println(1);
			int indexX = Math.min(x1,x2);
			int indexY = ((indexX==x1)?y1:y2);
			boolean flag = ((indexX==x1)?y1:y2)>((indexX!=x1)?y1:y2);
			for (int i = 0; i <= Math.abs(x2-x1); i++) {
				// System.out.println(i);
				if (flag) {
					tablero[indexX+i][indexY-i].setBackground(Color.cyan);
				}else{
					tablero[indexX+i][indexY+i].setBackground(Color.cyan);
				}
			}
		}else if (x1 != x2) {
			// System.out.println(2);
			for (int i = ((x1<x2)?x1:x2); i <= ((x1>x2)?x1:x2); i++) {
				// System.out.println(i);
				tablero[i][y1].setBackground(Color.cyan);
			}
		}else{
			// System.out.println(3);
			for (int i = ((y1<y2)?y1:y2); i <= ((y1>y2)?y1:y2); i++) {
				// System.out.println(i);
				tablero[x1][i].setBackground(Color.cyan);
			}
		}
	}

	public void actualizarPalabra(boolean[] find, String[] palabras){
		for (int i = 0; i < lista.length; i++) {
			if (find[i]) {
				lista[i].setText("-- FIND --");
			}else{
				lista[i].setText(palabras[i]);
			}
		}
	}

	public void finalizar(int moves){
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(null, "Ganaste en "+Integer.toString(moves)+" movimientos!", "Confirmacion",JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	}
}