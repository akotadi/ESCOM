import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

class GUIReactivo extends JFrame implements ActionListener{
	JButton bOk, bClose, bCheck;
	JLabel lPregunta, lOpcionA, lOpcionB, lOpcionC, lOpcionD, lRespuesta, lConnectionText, lTittle;
	JTextField tPregunta, tOpcionA, tOpcionB, tOpcionC, tOpcionD, tRespuesta, tReactivo;
	JPanel p0, p1, p2, p3, p4;
	getConnectionClient connect;
	JRadioButton rbP, rbE;
	ButtonGroup gButtons;
	JComboBox jcMateria;
	int idAdmin;
	Vector<String> vMaterias;

	public GUIReactivo(int idAdmin, getConnectionClient connect){

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Evaluador Examen");
        setResizable(false);
		getContentPane().setLayout(null);
		this.idAdmin = idAdmin;
		this.connect = connect;

		lTittle = new JLabel("Panel Administrador Reactivos");
		getContentPane().add(lTittle);
		lTittle.setBounds(560/2-100, 10, 200, 30);

		jcMateria = new JComboBox(connect.getSubjects());
		getContentPane().add(jcMateria);
		jcMateria.setBounds(30,50,200,30);

		bCheck = new JButton("Consultar Reactivos");			bCheck.addActionListener(this);
		getContentPane().add(bCheck);
		bCheck.setBounds(300,50,200,30);

		gButtons = new ButtonGroup();

		rbP = new JRadioButton("Añadir Reactivo");
		gButtons.add(rbP);
		getContentPane().add(rbP);
		rbP.setBounds(30, 80, 150, 30);
		p0 = new JPanel();
		p0.setBorder(BorderFactory.createTitledBorder("Registro de Reactivo:"));
		p0.setLayout(new GridLayout(11,1));
		lPregunta = new JLabel("Pregunta: ");
		tPregunta = new JTextField(200);		
		lOpcionA = new JLabel("Opcion A: ");
		tOpcionA = new JTextField(100);		
		lOpcionB = new JLabel("Opcion B: ");
		tOpcionB = new JTextField(100);		
		lOpcionC = new JLabel("Opcion C: ");
		tOpcionC = new JTextField(100);		
		lOpcionD = new JLabel("Opcion D: ");
		tOpcionD = new JTextField(100);
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		lRespuesta = new JLabel("Respuesta: ");
		tRespuesta = new JTextField(1);				
		p1.add(lRespuesta);
		p1.add(tRespuesta);
		p0.add(lPregunta);
		p0.add(tPregunta);
		p0.add(lOpcionA);
		p0.add(tOpcionA);
		p0.add(lOpcionB);
		p0.add(tOpcionB);
		p0.add(lOpcionC);
		p0.add(tOpcionC);
		p0.add(lOpcionD);
		p0.add(tOpcionD);
		p0.add(p1);
		getContentPane().add(p0);
		p0.setBounds(30, 110, 500, 300);


		rbE = new JRadioButton("Eliminar Reactivo");
		gButtons.add(rbE);
		getContentPane().add(rbE);
		rbE.setBounds(30,420, 150, 30);
		p2 = new JPanel();
		p2.setLayout(new GridLayout(1,1));
		p2.setBorder(BorderFactory.createTitledBorder("Ingresa el ID del Reactivo:"));
		tReactivo = new JTextField(50);
		p2.add(tReactivo);
		getContentPane().add(p2);
		p2.setBounds(30, 450, 500, 50);

		lConnectionText = new JLabel(" ");
		bOk = new JButton("Confirmar");			bOk.addActionListener(this);
		bClose = new JButton("Close");			bClose.addActionListener(this);
		p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(lConnectionText, BorderLayout.NORTH);
		p3.add(bOk, BorderLayout.WEST);
		p3.add(bClose, BorderLayout.EAST);
		getContentPane().add(p3);
		p3.setBounds(560/2-120, 520, 240, 50);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-560)/2, (screenSize.height-600)/2, 560, 600);
	}

	public static void main(String[] args) {
		new GUIReactivo(4,new getConnectionClient("localhost",1025)).setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if(selected == bOk && (rbE.isSelected() || rbP.isSelected())){
			if(rbE.isSelected()){
				connect.deleteReactivo(Integer.valueOf(tReactivo.getText()));
				tReactivo.setText("");
			}
			else if (rbP.isSelected()) {
				Reactivo r = new Reactivo(tPregunta.getText(),tOpcionA.getText(),tOpcionB.getText(),tOpcionC.getText(),tOpcionD.getText(),tRespuesta.getText());
				connect.nuevoReactivo(r,(String)jcMateria.getSelectedItem());
				tPregunta.setText("");
				tOpcionA.setText("");
				tOpcionB.setText("");
				tOpcionC.setText("");
				tOpcionD.setText("");
				tRespuesta.setText("");
			}
		}
		else if (selected == bCheck) {
			Vector<Reactivo> vReactivo;
			vReactivo = connect.checkReactivo((String)jcMateria.getSelectedItem());
			String sCheck = "";
			if (vReactivo.size()>10) {
				int j = 0;
				for (;j<vReactivo.size()/10; j++) {
					sCheck = "";
					for (int i = 10*j; i<10*(j+1); ++i) {
						sCheck = sCheck+"ID: "+vReactivo.elementAt(i).getID()+"\nPregunta: "+vReactivo.elementAt(i).getPregunta()+"\n\n";
					}
					JOptionPane.showMessageDialog(null,sCheck);
				}/*
				sCheck = "";
				for (int i = 10*j; i < vReactivo.size(); ++i) {
					sCheck = sCheck+"ID: "+vReactivo.elementAt(i).getID()+"\nPregunta: "+vReactivo.elementAt(i).getPregunta()+"\n\n";
				}
				JOptionPane.showMessageDialog(null,sCheck);*/
			}
			else{
				for (int i = 0; i<vReactivo.size(); ++i) {
					sCheck = sCheck+"ID: "+vReactivo.elementAt(i).getID()+"\nPregunta: "+vReactivo.elementAt(i).getPregunta()+"\n\n";
				}
				JOptionPane.showMessageDialog(null,sCheck);
			}
			/*for (int i = 0; i<vReactivo.size(); ++i) {
				sCheck = sCheck+"ID: "+vReactivo.elementAt(i).getID()+"\nPregunta: "+vReactivo.elementAt(i).getPregunta()+"\n\n";
			}
			JOptionPane.showMessageDialog(null,sCheck);*/
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