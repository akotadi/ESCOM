/*
CREATE TABLE  PerroImg (
         nombre VARCHAR(100),
         raza VARCHAR(100),
	 edad VARCHAR(3),
         genero VARCHAR(10),
         imagen blob
);
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertImageTest {

	/**
	 * This is used to get the Connection
	 * 
	 * @return
	 */
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

	/***
	 * Execute Program
	 * 
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		InsertImageTest imageTest = new InsertImageTest();
		imageTest.insertImage();
	}

}
