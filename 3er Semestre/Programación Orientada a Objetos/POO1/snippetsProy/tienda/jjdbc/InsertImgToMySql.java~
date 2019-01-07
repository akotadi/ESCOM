/*CREATE TABLE  PerroImg (
         nombre VARCHAR(100),
         raza VARCHAR(100),
	 edad VARCHAR(3),
         genero VARCHAR(10),
         imagen blob
);*/
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertImgToMySql  {
  public static void main(String[] args) throws Exception, IOException, SQLException {
String url = "jdbc:mysql://localhost:3306/", dbName = "classicmodels";
    Class.forName("com.mysql.jdbc.Driver");   
Connection conex=DriverManager.getConnection(url+dbName, "root", "gatito" );
   String INSERT_PICTURE = "insert into PerroImg(nombre, raza, edad, genero, imagen) "
							+ "values(?,?,?,?,?)";
    FileInputStream fis = null;
    PreparedStatement ps = null;
    try {
      conex.setAutoCommit(false);
      File file = new File("conejo.jpg");
      fis = new FileInputStream(file);
      ps = conex.prepareStatement(INSERT_PICTURE);
      ps.setString(1, "Bunny");
      ps.setString(2, "Dalmata");
      ps.setString(3, "2");
      ps.setString(4, "Macho");
      ps.setBinaryStream(5, fis, (int) file.length());
      ps.executeUpdate();
      conex.commit();
    } finally {
      ps.close();
      fis.close();
    }
  }
}
