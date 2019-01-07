
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.awt.*;
import java.sql.*;
// mysql --password=gatito --user=root

public class CreaGen {
	String url = "jdbc:mysql://localhost:3306/";
        String dbName = "test";	  
	Connection con;
    	String driverClassName = "com.mysql.jdbc.Driver";
/*      String createString = "create table perro (Nombre varchar(50), " +
			"Raza varchar(50), Edad int, Genero varchar(15))";*/
	Statement stmt;
        String qryStr;	
public CreaGen(String nombre) {
	try {
		Class.forName(driverClassName);
	} catch(java.lang.ClassNotFoundException e) {
        	System.err.print("ClassNotFoundException: "); 
		System.err.println(e.getMessage());
	}
	try {
           con=DriverManager.getConnection(url+dbName, "root", "gatito" );
           Class c=Class.forName(nombre);
           Object obj=c.getDeclaredConstructor().newInstance(); 
           qryStr="create table "+ obj.getClass().getName()+" (";
           BeanInfo bI = Introspector.getBeanInfo(obj.getClass());
           PropertyDescriptor[] pds = bI.getPropertyDescriptors();
           int i = 1;
           for (PropertyDescriptor pd : pds) {
              String propName = pd.getName();
              if(propName.equals("class"))
	         continue;
              qryStr = qryStr+propName+" varchar(50), ";
              i++; 
           }
	   qryStr =qryStr.substring(0,qryStr.length()-2);
	   qryStr = qryStr+")";
           System.err.println("Consulta <"+qryStr);
	   stmt= con.createStatement();							
	   stmt.executeUpdate(qryStr/*createString*/);
           stmt.close();
	   con.close();
	} catch(Exception ex) {
			System.err.println("SQLException: " + ex.getMessage()+"ex "+ex);
	}
}
public static void main(String s[]) {		  
	new CreaGen(s[0]);
}
}

