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

public class InsertImageTest extends Frame implements ActionListener {
        JLabel jlNombre=new JLabel("Nombre");
	JLabel jlRaza=new JLabel("Raza");
	JLabel jlEdad=new JLabel("Edad");
	JLabel jlGenero=new JLabel("Genero");
	JLabel jlImagen=new JLabel("Imagen");
        JTextField jtNombre=new JTextField(15);
        JTextField jtRaza=new JTextField(15);
        JTextField jtEdad=new JTextField(15);
        JTextField jtGenero=new JTextField(15);
        JTextField jtImagen=new JTextField(15);
	JButton jbcon=new JButton("Conectar"); 
        JButton jbinsert=new JButton("Insertar");
        JPanel jp=new JPanel();
	public InsertImageTest(){
		jp.setLayout(new GridLayout(6,2));
		jp.add(jlNombre);jp.add(jtNombre);
		jp.add(jlRaza);jp.add(jtRaza);
		jp.add(jlEdad);jp.add(jtEdad);
		jp.add(jlGenero);jp.add(jtGenero);
		jp.add(jlImagen);jp.add(jtImagen);
		jp.add(jbcon);jp.add(jbinsert);
		add(jp);
		setSize(400,300);
		setVisible(true);
	}
	public Connection getConnection() {
		Connection connection = null;
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "classicmodels";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url+dbName, "root", "gatito" );
		} catch (Exception e) {
			System.out.println("Error Occured While Getting the Connection: - "
					+ e);
		}
		return connection;
	}

	/**
	 * Insert Image
	 */
	public void insertImage() {
		Connection connection = null;
		PreparedStatement statement = null;
		FileInputStream inputStream = null;

		try {
			File image = new File("dalmata.jpg");
			inputStream = new FileInputStream(image);
			connection = getConnection();
			statement = connection
					.prepareStatement("insert into PerroImg(nombre, raza, edad, genero, imagen) "
							+ "values(?,?,?,?,?)");
			statement.setString(1, "Percy");
			statement.setString(2, "Dalmata");
			statement.setString(3, "2");
			statement.setString(4, "Macho");
			statement.setBinaryStream(5, (InputStream) inputStream,
					(int) (image.length()));

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
