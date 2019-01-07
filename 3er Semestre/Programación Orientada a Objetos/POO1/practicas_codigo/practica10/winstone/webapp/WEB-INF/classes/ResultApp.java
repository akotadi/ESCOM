//mysql --password=gatito --user=root
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
//javac -cp .:beanutils.jar:commons-logging.jar ResultApp.java
//java -cp .:mysqlcon.jar:beanutils.jar:commons-logging.jar  ResultApp
class ResultApp extends JFrame {
static ImageIcon icon;
static JLabel lPhoto;
static JPanel pnl;
public static void main (String args [ ]) {
        String url = "jdbc:mysql://localhost:3306/";
    	String dbName = "Perracos";
	String driverClassName = "com.mysql.jdbc.Driver";
//String url= "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=f:\\jdbc\\verduleros.MDB";
	ResultApp ra=new ResultApp();
        JPanel jp= new JPanel();
        jp.setLayout(new FlowLayout());
        JScrollPane src_pane = new JScrollPane (jp);
        pnl = (JPanel)ra.getContentPane();
	try { 
           Class.forName("com.mysql.jdbc.Driver");          		
           Connection conex=DriverManager.getConnection(url+dbName, "root", "" );
           Statement statement=conex.createStatement();
          ResultSet rs=statement.executeQuery("SELECT * FROM fotoPerro");      
          while(rs.next()) {
            //System.out.println(rs.getString("nombre"));
            byte[] imagedata = rs.getBytes("foto") ;
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            icon =new ImageIcon(img);
            lPhoto = new JLabel(icon) ;
             jp.add(lPhoto); 
            }  
            pnl.add(src_pane);     
	    //conex.close( );
            ra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    	    //ra.pack(); 
            ra.setVisible(true);
	}
	catch (Exception ex){ System.out.println(ex); System.exit(0); }
}

}//ResultApp
