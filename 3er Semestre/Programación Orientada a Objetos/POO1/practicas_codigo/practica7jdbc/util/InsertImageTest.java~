import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InsertImageTest extends JFrame implements ActionListener {
	JLabel jlImagen=new JLabel("Imagen");
        JTextField jtImagen=new JTextField(15);
	JButton jbcon=new JButton("Conectar"); 
        JButton jbinsert=new JButton("Insertar");
        JPanel jp=new JPanel();
	public InsertImageTest(){
		jp.setLayout(new GridLayout(2,2));
		jp.add(jlImagen);jp.add(jtImagen);
		jp.add(jbcon);jp.add(jbinsert);
		jbcon.addActionListener(this);
		jbinsert.addActionListener(this);
		add(jp);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		setSize(400,300);
		setVisible(true);
	}
        public void actionPerformed(ActionEvent e){
		JButton jb=(JButton)e.getSource();
		if(jb==jbcon){
			getConnection() ;
		}
		if(jb==jbinsert){
			insertImage(jtImagen.getText());
		}
	}
	public Connection getConnection() {
		Connection connection = null;
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "Perracos";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url+dbName, "root", "" );
		} catch (Exception e) {
			System.out.println("Error Occured While Getting the Connection: - "
					+ e);
		}
		return connection;
	}

	/**
	 * Insert Image
	 */
	public void insertImage(String imagen) {
		Connection connection = null;
		PreparedStatement statement = null;
		FileInputStream inputStream = null;

		try {
                        String sql="INSERT INTO fotoPerro (foto) values(?)";
			File fimage = new File(imagen);
			inputStream = new FileInputStream(fimage);
			connection = getConnection();
			statement = connection
					.prepareStatement(sql);
			statement.setBinaryStream(1, (InputStream) inputStream,
					(int) (fimage.length()));

			statement.executeUpdate();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: - " + e);
		} catch (SQLException e) {
			System.out.println("SQLException: - " + e);
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println("SQLException Finally: - " + e);
			}
		}

	}

	
	public static void main(String[] args) throws SQLException {
		InsertImageTest imageTest = new InsertImageTest();
		//imageTest.insertImage();
	}

}
