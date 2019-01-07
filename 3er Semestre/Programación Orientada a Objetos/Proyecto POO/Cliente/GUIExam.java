import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class GUIExam extends JFrame {
	JRadioButton rbA, rbB, rbC, rbD;
	ButtonGroup gButtons;
	JButton bSend, bClose, bInfo;
	JLabel lQuestion1, lQuestion2;
	JLabel lTittle, lError, lOpcionA, lOpcionB, lOpcionC, lOpcionD;
	JPanel p0,p1,p2;
	int idUser;
	getConnectionClient connect;
	Examen exam;
	boolean newExam = true;

	public GUIExam(int idUser, getConnectionClient connect, Examen exam){

		System.out.println("GUIExam");

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Evaluador Examen");
        setResizable(false);
        getContentPane().setLayout(null);

        System.out.println(1);

        this.connect = connect;
        this.idUser = idUser;
        this.exam = exam;

        System.out.println(2);

        if (exam.ultimaPregunta != 0) {
        	newExam = false;
        }

        System.out.println(3);

        bInfo = new JButton("Acerca de...");
        getContentPane().add(bInfo);
        bInfo.setBounds(5, 5, 150, 20);

        System.out.println(4);

		lTittle = new JLabel("Evaluador Examen");
		getContentPane().add(lTittle);
		lTittle.setBounds(600/2-70, 30, 150, 30);

		System.out.println(5);

		p0 = new JPanel();
		p0.setLayout(new GridLayout(3,1, 10, 10));
		
		String s, s1, s2;
		s = exam.Questions.elementAt(exam.ultimaPregunta).getPregunta();
		System.out.println(s.length());
		if (s.length()>70) {
			s1 = s.substring(0,70);
			s2 = s.substring(70,s.length());
			lQuestion1 = new JLabel(s1);
			lQuestion2 = new JLabel(s2);
		}
		else{
			lQuestion1 = new JLabel(s);
			lQuestion2 = new JLabel("");
		}
		
		p0.add(new JLabel("Question: "));
		p0.add(lQuestion1);
		p0.add(lQuestion2);
		getContentPane().add(p0);
		p0.setBounds(5, 60, 590, 60);

		System.out.println(6);

		rbA = new JRadioButton("A");
		rbB = new JRadioButton("B");
		rbC = new JRadioButton("C");
		rbD = new JRadioButton("D");
		gButtons = new ButtonGroup();
		gButtons.add(rbA);
		gButtons.add(rbB);
		gButtons.add(rbC);
		gButtons.add(rbD);

		System.out.println(7);

		p1 = new JPanel();
		p1.setBorder(BorderFactory.createTitledBorder("Selecciona una respuesta"));
		p1.setLayout(new GridLayout(8,1));
		lOpcionA = new JLabel(exam.Questions.elementAt(exam.ultimaPregunta).getOpcionA());
		p1.add(rbA);
		p1.add(lOpcionA);
		
		lOpcionB = new JLabel(exam.Questions.elementAt(exam.ultimaPregunta).getOpcionB());
		p1.add(rbB);
		p1.add(lOpcionB);
		
		lOpcionC = new JLabel(exam.Questions.elementAt(exam.ultimaPregunta).getOpcionC());
		p1.add(rbC);
		p1.add(lOpcionC);
		
		lOpcionD = new JLabel(exam.Questions.elementAt(exam.ultimaPregunta).getOpcionD());
		p1.add(rbD);
		p1.add(lOpcionD);

		System.out.println(8);
		
		getContentPane().add(p1);
		p1.setBounds(15, 130, 570, 200);

		lError = new JLabel("");
		getContentPane().add(lError);
		lError.setBounds(5, 340, 590, 20);

		System.out.println(9);

		bSend = new JButton("Send");		
		bClose = new JButton("Close");
		getContentPane().add(bSend);
		bSend.setBounds(120, 370, 100, 30);
		getContentPane().add(bClose);
		bClose.setBounds(381, 370, 100, 30);

		System.out.println(10);


		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 420);

        System.out.println(11);

        ControlActionWindow controlAction = new ControlActionWindow(this);

		bInfo.addActionListener(controlAction);
		bSend.addActionListener(controlAction);
		bClose.addActionListener(controlAction);
		rbA.addActionListener(controlAction);
		rbB.addActionListener(controlAction);
		rbC.addActionListener(controlAction);
		rbD.addActionListener(controlAction);
	}
	
}