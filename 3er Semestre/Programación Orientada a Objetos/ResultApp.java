import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class ResultApp extends JFrame implements ActionListener{
	static Connection conex;
	JButton bConnect, bInsert;
	JLabel lRegistry,lName, lRace, lAge, lGender, lEmpty1, lEmpty2;
	JTextField tName, tRace, tAge, tGender;
	JPanel p1,p2,p3;
	boolean stateConnection = false;

	public ResultApp(){
		bConnect = new JButton("Connect");		bConnect.addActionListener(this);
		bInsert = new JButton("Insert");		bInsert.addActionListener(this);
		lRegistry = new JLabel("Dog Registration");
		lEmpty1 = new JLabel("");
		lEmpty2 = new JLabel("");
		lName = new JLabel("Name: ");
		tName = new JTextField(20);
		lRace = new JLabel("Race: ");
		tRace = new JTextField(20);
		lAge = new JLabel("Age: ");
		tAge = new JTextField(2);
		lGender = new JLabel("Gender: ");
		tGender = new JTextField(1);
		p1 = new JPanel();
		p1.setLayout(new GridLayout(4,2,5,5));
		p1.add(lName);		p1.add(tName);
		p1.add(lRace);		p1.add(tRace);
		p1.add(lAge);		p1.add(tAge);
		p1.add(lGender);	p1.add(tGender);
		p2 = new JPanel();
		p2.setLayout(new GridLayout(1,2,5,5));
		p2.add(bConnect);		p2.add(bInsert);
		p3 = new JPanel();
		p3.setLayout(new GridLayout(1,3,5,5));
		p3.add(lEmpty1);		p3.add(lRegistry);		p3.add(lEmpty2);
		setSize(400,200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		add("North",p3);		add("Center",p1);		add("South",p2);
	}

	public static void main(String[] args) {
		new ResultApp();
	}

	public void actionPerformed(ActionEvent e){
		JButton selected = (JButton)e.getSource();
		if (selected == bConnect && !stateConnection) {
			String url = "jdbc:mysql://localhost:3306/", dbName = "p7";
			try { 
				Class.forName("com.mysql.jdbc.Driver");          		
				conex=DriverManager.getConnection(url+dbName, "root", "77Manuel95:" );
				System.out.println("Connection established.");
				stateConnection = true;
			}
			catch (Exception ex){ System.out.println(ex); System.exit(0); }
		}
		else if (selected != bConnect && !stateConnection) {
			System.out.println("Connection not established yet.");
		}
		else if (selected == bInsert) {
			String sName = tName.getText();
			String sRace = tRace.getText();
			int nAge = Integer.parseInt(tAge.getText());
			char cGender = tGender.getText().charAt(0);
			//System.out.println(sName+"\n"+sRace+"\n"+cGender+"\n"+nAge+"\n");
			try{
				Statement statement=conex.createStatement();
				statement.executeUpdate("INSERT INTO Perro VALUES('"+sName+"','"+sRace+"',"+nAge+",'"+cGender+"')");
			} catch (Exception ex){ System.out.println(ex); System.exit(0); }
		}
	}
}