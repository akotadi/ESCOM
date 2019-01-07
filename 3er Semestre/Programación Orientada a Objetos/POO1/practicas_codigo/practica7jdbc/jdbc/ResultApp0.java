//ResultApp.java
import java.sql.*;
import java.util.*;

class ResultApp {
	public static void main (String args [ ]) {
	try { 
//Cargar el controlador IDS
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");          		//String url="jdbc:ids://cx122974-a.cu1.sdca.home.com:80/";
          		//url +="conn?dbtype=odbc&dsn=´IDSExamples´";
                String url= "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=f:\\jdbc\\verduleros.MDB";
//Conectar con la base de datos
Connection connection=DriverManager.getConnection(url);
Statement statement=connection.createStatement();
String sql="SELECT * FROM productos";
//Ejecutar instrucciones SQL y recuperar conjuntos de resultados
ResultSet result=statement.executeQuery(sql);
displayResults(result);
connection.close( );
	}
	catch (Exception ex){
		System.out.println(ex);
		System.exit(0);

	}
}

static void displayResults(ResultSet r) throws SQLException {
// obtiene los meta datos del conjunto de resultados

ResultSetMetaData rmeta = r.getMetaData();


//Usar los metadatos para determinar el numero de columnas
//de cada fila del conjunto de resultados

int numColumns=rmeta.getColumnCount();

//Imprimir valores de cada columna
 	for (int i = 1; i <= numColumns; ++i ) {
		if(i < numColumns)
			System.out.print(rmeta.getColumnName( i )+"  ");	
else
			System.out.println(rmeta.getColumnName( i ));
}
while(r.next()) {
		for(int i = 1;  i <= numColumns; ++i){
			if( i < numColumns)				System.out.print(r.getString( i )+"  ");
			else
				System.out.println(r.getString( i ).trim());
		}//for
	}//while
}//displayResults
}//ResultApp
