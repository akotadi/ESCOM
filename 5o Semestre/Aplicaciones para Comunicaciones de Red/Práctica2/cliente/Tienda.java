import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;

public class Tienda extends JFrame implements ActionListener{
	JPanel pButtons;
	JButton bExit, bBuy;
	JLabel lTittle;
	TableCheckBox tArticulos;
	Cliente cl;

	public Tienda(){

		cl = new Cliente();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tienda");
        setResizable(false);
		getContentPane().setLayout(null);

		lTittle = new JLabel("Compras Online",SwingConstants.CENTER);
		getContentPane().add(lTittle);	
		lTittle.setBounds(700/2-(150/2), 10, 150, 30);

		Object[][] data = cl.getArticulos();
		tArticulos = new TableCheckBox(data);
		getContentPane().add(tArticulos);
		tArticulos.setBounds(700/2-(600/2),50,600,300);

		pButtons = new JPanel();
		pButtons.setLayout(new BorderLayout());
		bExit = new JButton("Salir");
		bExit.addActionListener(this);
		bBuy = new JButton("Comprar");
		bBuy.addActionListener(this);
		pButtons.add(bExit, BorderLayout.WEST);
		pButtons.add(bBuy, BorderLayout.EAST);
		getContentPane().add(pButtons);
		pButtons.setBounds(700/2-(200/2),370,200,50);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocation(150, 150);
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width-700)/2, (screenSize.height-500)/2, 700, 500);
        setVisible(true);
	}

	public static void main(String[] args) {
		Tienda frame = new Tienda();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(150, 150);
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-700)/2, (screenSize.height-500)/2, 700, 500);
        frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if (selected == bBuy) {
			while(!cl.sendBuy(tArticulos.getBuyOrder()))
				continue;
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, cl.getPrice(),"Ventana de compra", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			while(!cl.closeConnection())
				continue;
			System.exit(0);
		}
		else if (selected == bExit) {
			int x = JOptionPane.showConfirmDialog(null,"Estas seguro?","Ventana de confirmacion",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				while(!cl.closeConnection())
					continue;
				System.exit(0);
			}
		}
	}
}