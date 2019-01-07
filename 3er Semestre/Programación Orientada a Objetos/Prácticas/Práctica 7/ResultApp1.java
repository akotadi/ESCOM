import java.sql.*;
import java.util.*;
class ResultApp {
public static void main (String args [ ]) {
	String url = "jdbc:mysql://localhost:3306/", dbName = "classicmodels";
//String url= "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=f:\\jdbc\\verduleros.MDB";
	try { 
		Class.forName("com.mysql.jdbc.Driver");          		
		Connection conex=DriverManager.getConnection(url+dbName, "root", "gatito" );
		displayTables(connection);
		Statement statement=conex.createStatement();
		ResultSet result=statement.executeQuery("SELECT * FROM customers");
		displayResults(result);
		connection.close( );
	}
	catch (Exception ex){ System.out.println(ex); System.exit(0); }
}
static void displayResults(ResultSet r) throws SQLException {
	ResultSetMetaData rmeta = r.getMetaData();
	int numColumns=rmeta.getColumnCount();
 	for (int i = 1; i <= numColumns; ++i )
		if(i < numColumns)
			System.out.print(rmeta.getColumnName( i )+"  ");	
		else
			System.out.println(rmeta.getColumnName( i ));
	while(r.next())
		for(int i = 1;  i <= numColumns; ++i)
			if( i < numColumns)				
				System.out.print(r.getString( i )+"  ");
			else
				System.out.println(r.getString( i ).trim());
}//displayResults
}//ResultApp
