import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

class GUIMateria extends JFrame implements ActionListener{
	JButton bClose, bOk;
	JRadioButton rbN, rbD;
	ButtonGroup group;
	JLabel lConnectionText, lTittle;
	JTextField tMateria;
	JPanel p0,p1,p2;
	getConnectionClient connect;
	int idAdmin;
	JComboBox jcMateria;

	public GUIMateria(getConnectionClient connect, int idAdmin){

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Evaluador Examen");
        setResizable(false);
		getContentPane().setLayout(null);
		this.connect = connect;
		this.idAdmin = idAdmin;

		lTittle = new JLabel("Panel Administrador Materias");
		getContentPane().add(lTittle);
		lTittle.setBounds(400/2-100, 20, 200, 30);

		group = new ButtonGroup();
		rbD = new JRadioButton("Eliminar Materia");
		rbD.setSelected(true);
		rbN = new JRadioButton("Nueva Materia");
		group.add(rbD);
		group.add(rbN);

		getContentPane().add(rbD);
		rbD.setBounds(40,55,200,15);

		p0 = new JPanel();
		p0.setBorder(BorderFactory.createTitledBorder("Eliminar"));
		p0.setLayout(new BorderLayout());
		jcMateria = new JComboBox(connect.getSubjects());
		p0.add(jcMateria);
		getContentPane().add(p0);
		p0.setBounds(50,75,300,50);

		getContentPane().add(rbN);
		rbN.setBounds(40,140,200,15);

		p1 = new JPanel();
		p1.setBorder(BorderFactory.createTitledBorder("Agregar"));
		p1.setLayout(new BorderLayout());
		tMateria = new JTextField(50);
		p1.add(tMateria);
		getContentPane().add(p1);
		p1.setBounds(50,160,300,50);



		lConnectionText = new JLabel("");
		bClose = new JButton("Close");						bClose.addActionListener(this);
		bOk = new JButton("Confirmar");						bOk.addActionListener(this);
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(lConnectionText, BorderLayout.NORTH);
		p2.add(bOk, BorderLayout.WEST);
		p2.add(bClose, BorderLayout.EAST);
		getContentPane().add(p2);
		p2.setBounds(400/2-120, 240, 240, 40);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-350)/2, 400, 350);
	}

	public static void main(String[] args) {
		new GUIMateria(new getConnectionClient("localhost",1025),7).setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if(selected == bOk){
			if (rbD.isSelected()) {
				connect.deleteMateria((String)jcMateria.getSelectedItem());
				jcMateria.removeItemAt(jcMateria.getSelectedIndex());
			}
			else if (rbN.isSelected() && tMateria.getText().length()>2) {
				connect.nuevaMateria(tMateria.getText());
				jcMateria.addItem(tMateria.getText());
				tMateria.setText("");
			}
		}
		else if (selected == bClose) {
			int x = JOptionPane.showConfirmDialog(null,"¿Estás seguro?","Ventana de Confirmación",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				GUIAdmin admin = new GUIAdmin(idAdmin,connect);
				this.dispose();
				admin.setVisible(true);
			}
		}
	}
}