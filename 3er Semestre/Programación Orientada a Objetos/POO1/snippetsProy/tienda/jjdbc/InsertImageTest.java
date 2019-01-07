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
			insertImage(jtNombre.getText(), jtRaza.getText(), 
                        jtEdad.getText(), jtGenero.getText(), jtImagen.getText());
		}
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
	public void insertImage(String nombre, String raza, String edad, 
		String genero, String imagen) {
		Connection connection = null;
		PreparedStatement statement = null;
		FileInputStream inputStream = null;

		try {
			File fimage = new File(imagen);
			inputStream = new FileInputStream(fimage);
			connection = getConnection();
			statement = connection
					.prepareStatement("insert into PerroImg(nombre, raza, edad, genero, imagen) "
							+ "values(?,?,?,?,?)");
			statement.setString(1, nombre);
			statement.setString(2, raza);
			statement.setString(3, edad);
			statement.setString(4, genero);
			statement.setBinaryStream(5, (InputStream) inputStream,
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
