//mysql --password=gatito --user=root
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
class ResultApp extends JFrame {
static ImageIcon icon;
static JLabel lPhoto;
static JPanel pnl;
public static void main (String args [ ]) {
	String url = "jdbc:mysql://localhost:3306/", dbName = "classicmodels";
//String url= "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=f:\\jdbc\\verduleros.MDB";
ResultApp ra=new ResultApp();
	try { 
                pnl = (JPanel)ra.getContentPane();
	        pnl.setLayout(new FlowLayout());
		Class.forName("com.mysql.jdbc.Driver");          		
		Connection conex=DriverManager.getConnection(url+dbName, "root", "gatito" );
		Statement statement=conex.createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM MyPictures1");
                while(rs.next()) 
            {
            byte[] imagedata = rs.getBytes("photo") ;
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            icon =new ImageIcon(img);
            lPhoto = new JLabel(icon) ;
             pnl.add(lPhoto); 
            }        
		conex.close( );
            ra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    	    //ra.pack(); 
            ra.setVisible(true);
	}
	catch (Exception ex){ System.out.println(ex); System.exit(0); }
}

}//ResultApp
