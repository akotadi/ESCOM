import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
        String driverClassName = "com.mysql.jdbc.Driver";
        String conexionUrl = "jdbc:mysql://localhost:3306/classicmodels";
        String dbUser = "root";
        String dbPwd = "gatito";

        private static ConnectionFactory connectionFactory = null;

private ConnectionFactory(String driver, String host_BD, String login,String pwd) {
    driverClassName = driver;
    conexionUrl = host_BD;
    dbUser = login;
    dbPwd = pwd;            
}

public Connection getConexion() throws SQLException {
   try {
       Class.forName(driverClassName);
   } catch (ClassNotFoundException e) {
       e.printStackTrace();
   }
   Connection conn = null;
   conn = DriverManager.getConnection(conexionUrl, dbUser, dbPwd);
   return conn;
}

public static ConnectionFactory getInstance( String driver, String host_basedatos, String login, String pwd ) {
   if (connectionFactory == null) {
      connectionFactory = new ConnectionFactory(driver, host_basedatos,login, pwd);
   }
   return connectionFactory;
}
}
