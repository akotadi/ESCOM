import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

class GUIChoose extends JFrame implements ActionListener{
	JButton bCheck, bNew, bContinue, bClose, bDelete;
	JLabel lTittle;
	JPanel p1,p2,p3;
	getConnectionClient connect;
	int idUsuario = 4;
	JComboBox cNewExam, cContinueExam, cCheckExam;
	Vector vNewExam, vContinueExam;

	public GUIChoose(int idUsuario, getConnectionClient connect){

		this.connect = connect;
		this.idUsuario = idUsuario;

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Evaluador Examen");
        setResizable(false);
		getContentPane().setLayout(null);

		lTittle = new JLabel("Panel Usuario");
		getContentPane().add(lTittle);	
		lTittle.setBounds(400/2-60, 30, 150, 30);

		bDelete = new JButton("Eliminar Cuenta");			bDelete.addActionListener(this);
		getContentPane().add(bDelete);
		bDelete.setBounds(400/2-90, 60, 150, 30);
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(3,1,20,20));
		bCheck = new JButton("Revisar Examen");				bCheck.addActionListener(this);
		bNew = new JButton("Nuevo Examen");					bNew.addActionListener(this);
		bContinue = new JButton("Continuar Examen");		bContinue.addActionListener(this);
		p2.add(bCheck);
		p2.add(bNew);
		p2.add(bContinue);
		getContentPane().add(p2);
		p2.setBounds(30, 100, 165, 120);

		vContinueExam = connect.getExamsUser(Integer.toString(idUsuario));
		cCheckExam = new JComboBox(vContinueExam);
		if (vContinueExam.size() == 0) {
			bCheck.setEnabled(false);
		}
		vNewExam = connect.getExams();
		String s, q;
		for (int i = 0; i<vNewExam.size(); ++i) {
			if (vContinueExam.contains(vNewExam.elementAt(i))) {
				vNewExam.removeElementAt(i);
				i--;
			}
		}
		vContinueExam = connect.getExamsUser(""+Integer.toString(idUsuario)+" and eu.ultimaPregunta < 10");
		if (vContinueExam.size() == 0) {
			bContinue.setEnabled(false);
		}
		cContinueExam = new JComboBox(vContinueExam);
		cNewExam = new JComboBox(vNewExam);
		if (vNewExam.size() == 0) {
			bNew.setEnabled(false);
		}
		p3 = new JPanel();
		p3.setLayout(new GridLayout(3,1,20,20));
		p3.add(cCheckExam);
		p3.add(cNewExam);
		p3.add(cContinueExam);
		getContentPane().add(p3);
		p3.setBounds(200,100,150,120);

		bClose = new JButton("Close");						bClose.addActionListener(this);
		getContentPane().add(bClose);
		bClose.setBounds(400/2-50,230,100,30);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-270)/2, 400, 270);
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if (selected == bNew) {

			String sTitulo = "Examen "+(String)cNewExam.getSelectedItem();
			Vector v = connect.nuevoExamen(sTitulo,(String)cNewExam.getSelectedItem(),idUsuario);
			/*JOptionPane.showMessageDialog(null,"Las preguntas del examen '"+sTitulo+"' son: \n\n"
				+"\t- "+v.elementAt(0)
				+"\t\n- "+v.elementAt(1)
				+"\t\n- "+v.elementAt(2)
				+"\t\n- "+v.elementAt(3)
				+"\t\n- "+v.elementAt(4)
				+"\t\n- "+v.elementAt(5)
				+"\t\n- "+v.elementAt(6)
				+"\t\n- "+v.elementAt(7)
				+"\t\n- "+v.elementAt(8)
				+"\n\t- "+v.elementAt(9));
			//tTitulo.setText("");

			//String sTitulo = (String)cNewExam.getSelectedItem();*/
			System.out.println("GUI CHOOSE 1");
			Examen ex = new Examen(sTitulo,idUsuario,connect.getValuesExam((String)cNewExam.getSelectedItem(),idUsuario),(String)cNewExam.getSelectedItem());
			System.out.println("GUI CHOOSE 2");
			GUIExam exam = new GUIExam(idUsuario,connect,ex);
			this.dispose();
			exam.setVisible(true);
		}
		else if(selected == bContinue){
			String sTitulo = (String)cContinueExam.getSelectedItem();
			Examen ex = connect.getExam(sTitulo,idUsuario);
			GUIExam exam = new GUIExam(idUsuario,connect,ex);
			this.dispose();
			exam.setVisible(true);
		}
		else if(selected == bCheck){
			String sTitulo = (String)cCheckExam.getSelectedItem();
			Examen ex = connect.getExam(sTitulo,idUsuario);
			String sCheck = "Pregunta "+1+": "+ex.Questions.elementAt(0).getPregunta()+"\n"
			+"Respuesta: "+ex.Questions.elementAt(0).getTextoRespuesta()+"\n"
			+"\tRespondiste: "+ex.Questions.elementAt(0).getTexto(ex.vAnswers.elementAt(0).toString().charAt(0))+"\n\n";
			for (int i = 1; i<ex.vAnswers.size(); ++i) {
				sCheck = sCheck+"Pregunta "+(i+1)+": "+ex.Questions.elementAt(i).getPregunta()+"\n"
				+"Respuesta: "+ex.Questions.elementAt(i).getTextoRespuesta()+"\n"
				+"\tRespondiste: "+ex.Questions.elementAt(i).getTexto(ex.vAnswers.elementAt(i).toString().charAt(0))+"\n\n";
			}
			JOptionPane.showMessageDialog(null,"Titulo del Examen: "+ex.titulo+"\n\n"+sCheck+"\nCalificación: "+ex.calificacion);
		}
		else if (selected == bDelete) {
			int x = JOptionPane.showConfirmDialog(null,"¿Estás seguro que quieres eliminar la cuenta?","Ventana de Confirmación",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				connect.deleteUser(idUsuario);
				Inicio start = new Inicio(connect);
				this.dispose();
				start.setVisible(true);
			}
		}
		else if (selected == bClose) {
			int x = JOptionPane.showConfirmDialog(null,"¿Estás seguro?","Ventana de Confirmación",JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				connect.closeConnectionClient();
				System.exit(0);
			}
		}
	}
}