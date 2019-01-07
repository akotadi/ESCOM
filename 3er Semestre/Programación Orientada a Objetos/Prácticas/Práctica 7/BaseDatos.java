import java.sql.*;
import java.util.*;
import java.lang.reflect.Field;
class BaseDatos {
	static Map map;
public static void main (String args [ ]) {
  	String url = "jdbc:mysql://localhost:3306/";
  	String dbName = "classicmodels";
  	String driver = "com.mysql.jdbc.Driver";
  	String userName = "root"; 
  	String password = "77Manuel95:";
	try { 
		Class.forName(driver);          		
                //String url= "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=f:\\jdbc\\verduleros.MDB";
		Connection connection=DriverManager.getConnection(url+dbName, userName, password );
		displayTables(connection);
		Statement statement=connection.createStatement();
		String sql="SELECT * FROM customers";
		ResultSet result=statement.executeQuery(sql);
		displayResults(result);
		connection.close( );
	}
	catch (Exception ex){ System.out.println(ex); System.exit(0);}
}
static void displayTables(Connection conexion) throws SQLException {
        DatabaseMetaData dmeta = conexion.getMetaData();
	String[] types = {"TABLE"};
  	ResultSet rs = dmeta.getTables(null,null,"%",types);
  	System.out.println("nombre de tabla:");
  	while (rs.next())
  		System.out.println(rs.getString("TABLE_NAME"));
}
static void displayResults(ResultSet r) throws SQLException {
ResultSetMetaData rmeta = r.getMetaData();
String s="";
int numColumns=rmeta.getColumnCount();
for (int i = 1; i <= numColumns; ++i ) {    
	if(i < numColumns)
		System.out.print(rmeta.getColumnName( i )+" "+getJdbcTypeName(rmeta.getColumnType(i))+"  ");
	else
		System.out.println(rmeta.getColumnName( i )+" "+getJdbcTypeName(rmeta.getColumnType(i)));
}
for (int i = 1; i <= numColumns; ++i ) {
	if(i < numColumns)
		System.out.print(rmeta.getColumnName( i )+"  ");	
	else
		System.out.println(rmeta.getColumnName( i ));
}
while(r.next()) {
	for(int i = 1;  i <= numColumns; ++i){                        
                try{
switch (rmeta.getColumnType(i)){	
		case Types.TINYINT: s=""+r.getByte(i);
			break;
		case Types.SMALLINT: s=""+r.getShort(i);
			break;
		case Types.INTEGER: s=""+r.getInt(i);
			break;
		case Types.BIGINT: s=""+r.getLong(i);
			break;
		case Types.REAL: s=""+r.getFloat(i);
			break;
		case Types.FLOAT: s=""+r.getDouble(i);
			break;
		case Types.DOUBLE:s=""+r.getDouble(i);
			break;
		case Types.DECIMAL: s=""+r.getBigDecimal(i);
			break;
		case Types.NUMERIC: s=""+r.getBigDecimal(i);
			break;
		case Types.BIT: s=""+r.getBoolean(i);
			break;
		case Types.CHAR: s=""+r.getString(i).trim();
			break;
		case Types.VARCHAR: s=""+r.getString(i);
			break;
		case Types.DATE: s=""+r.getDate(i);
			break;
		case Types.TIME: s=""+r.getTime(i);
			break;
		case Types.TIMESTAMP: s=""+r.getTimestamp(i);		
			break;
		default:
			break;
			}	
		}	
		catch(Exception e){
			System.err.print("**ERROR:  "+ e + "\n");
		}
		if( i < numColumns)		
			System.out.print(s+"  ");
		else
			System.out.println(s.trim());
		}//for
	}//while
}//displayResults
public static String getJdbcTypeName(int jdbcType) {
    if (map == null) {
        map = new HashMap();
        Field[] fields = java.sql.Types.class.getFields();
        for (int i=0; i<fields.length; i++) {
            try {
                String name = fields[i].getName();
                Integer value = (Integer)fields[i].get(null);
                map.put(value, name);
            } catch (IllegalAccessException e) {
            }
        }
    }
    return (String)map.get(new Integer(jdbcType));
}
}//BaseDatos
