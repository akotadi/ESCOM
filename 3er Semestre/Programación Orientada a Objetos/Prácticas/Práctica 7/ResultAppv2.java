import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

class ResultApp extends JPanel implements ActionListener{
	
	static Connection conex;
	JButton bConnect, bInsert, bShow, bClose;
	JLabel lName, lRace, lAge, lGender;
	JTextField tName, tRace, tAge, tGender;
	JPanel p1,p2;
	boolean stateConnection = false;

	public ResultApp(){
		bConnect = new JButton("Connect");		bConnect.addActionListener(this);
		bInsert = new JButton("Insert");		bInsert.addActionListener(this);
		bShow = new JButton("Show");			bShow.addActionListener(this);
		bClose = new JButton("Close");			bClose.addActionListener(this);
		lName = new JLabel("Name: ");
		tName = new JTextField(20);
		lRace = new JLabel("Race: ");
		tRace = new JTextField(20);
		lAge = new JLabel("Age: ");
		tAge = new JTextField(2);
		lGender = new JLabel("Gender: ");
		tGender = new JTextField(1);
		p1 = new JPanel();
		p2 = new JPanel();
		p1.setLayout(new GridLayout(3,3));
		p2.setLayout(new BorderLayout());
		add(lName);						add(tName);
		add(lRace);						add(tRace);
		add(lAge);						add(tAge);
		add(lGender);					add(tGender);
		p2.add(bConnect, BorderLayout.NORTH);
		p2.add(bInsert, BorderLayout.WEST);
		p2.add(bShow, BorderLayout.EAST);
		p2.add(bClose, BorderLayout.SOUTH);
		add(p2);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Dog Registration");
		f.add("Center", new ResultApp());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        f.setSize(250, 250); f.setVisible(true);
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
			String sName = tName.getText().toUpperCase();
			String sRace = tRace.getText().toUpperCase();
			int nAge = Integer.parseInt(tAge.getText());
			char cGender = tGender.getText().toUpperCase().charAt(0);
			String sqlInsert="INSERT INTO Dog VALUES('"+sName+"','"+sRace+"',"+nAge+",'"+cGender+"')";
			try{
				int i;
				Statement statement=conex.createStatement();
				i=statement.executeUpdate(sqlInsert);
				System.out.println("QUERY OK, "+i+" row affected.");
			} catch (Exception ex){ System.out.println(ex); System.exit(0); }
		}
		else if(selected == bShow){
			String sqlConsult="SELECT * FROM Dog";
			try{
				Statement statement=conex.createStatement();
				ResultSet result=statement.executeQuery(sqlConsult);
				displayResults(result);
			} catch (Exception ex){ System.out.println(ex); System.exit(0); }
		}
		else if (selected == bClose) {
			try{
				conex.close();
				System.out.println("Connection closed.");
				System.exit(0);
			} catch (Exception ex){ System.out.println(ex); System.exit(0); }
		}
	}

	static void displayResults(ResultSet r) throws SQLException{
		ResultSetMetaData rmeta = r.getMetaData();
		int numColumns = rmeta.getColumnCount();
		System.out.print("\n");
		for (int i = 1; i <= numColumns; ++i)
			System.out.print(rmeta.getColumnName(i)+"\t\t");
		System.out.println("\n");
		while(r.next()){
			for (int i = 1; i <= numColumns; ++i) {
				if (i <= numColumns)
					System.out.print(r.getString(i)+"\t\t");
			}
			System.out.print("\n");
		}
	}
}