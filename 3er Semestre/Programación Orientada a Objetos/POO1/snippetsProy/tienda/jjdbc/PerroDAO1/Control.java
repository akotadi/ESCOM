
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.text.*;
import java.sql.*;

class Control {
Connection conexion = null;
PreparedStatement stmt = null;

private Connection getConnection() throws SQLException {
	Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
}
public void insertar(Object obj, String tabla) throws DAOException{
   String qryStr = "INSERT INTO "+ tabla + "(";
   try{
   conexion = getConnection();    
  BeanInfo bI = Introspector.getBeanInfo(obj.getClass());
   PropertyDescriptor[] pds = bI.getPropertyDescriptors();
   int i = 1;
   for (PropertyDescriptor pd : pds) {
      String propName = pd.getName();
      if(propName.equals("class"))
	 continue;
      //System.out.println("i="+"("+i+") "+propName);
         qryStr = qryStr+propName+", ";
         i++; 
   }
   qryStr =qryStr.substring(0,qryStr.length()-2);
   //System.out.println("obj prop "+qryString);
   qryStr = qryStr+") VALUES(";
   for(i=0; i< pds.length-1;  i++)
      qryStr = qryStr+"?,";
   qryStr = qryStr.substring(0,qryStr.length()-1);
   qryStr = qryStr+")";
   System.out.println("obj fin "+qryStr);
   stmt = conexion.prepareStatement(qryStr);
   i = 1;
   for (PropertyDescriptor pd : pds) {
      String propName = pd.getName();
      if(propName.equals("class"))
	 continue;
      System.out.println("i="+"("+i+") "+propName);
      stmt.setObject(i,getProperty(obj, propName));
      i++;
      //System.out.println("obj prop "+getProperty(pb,propertyName));   
   }
   stmt.executeUpdate();
   //cerrar?
   } catch(SQLException sqle){
	sqle.printStackTrace();
	throw new DAOException("");
   }
   catch(IntrospectionException ie){
	ie.printStackTrace();
	throw new DAOException("");
   }
}

public void eliminar(String key, String valor, String tabla) throws SQLException{
   String qryStr = "DELETE FROM "+ tabla +" WHERE ";
   qryStr =qryStr + key+" = ?";
   conexion = getConnection();
   stmt = conexion.prepareStatement(qryStr);
   stmt.setString(1, valor);	     
   stmt.executeUpdate();
   stmt.close();
   conexion.close();
}

public void actualizar(Object obj, String key, String tabla) throws Exception
{
 String qryStr = "UPDATE "+tabla+" SET ";
  try{
	conexion = getConnection();
BeanInfo bI = Introspector.getBeanInfo(obj.getClass());
    PropertyDescriptor[] pds = bI.getPropertyDescriptors();
    int i = 1;
    for (PropertyDescriptor pd : pds) {
      String propName = pd.getName();
      if(propName.equals("class") || 
	 propName.equals(key) )
		continue;
      //System.out.println("i="+"("+i+") "+propertyName);
     qryStr = qryStr+propName+" = ?, ";
      i++;
      
    }
    qryStr =qryStr.substring(0,qryStr.length()-2);
    System.out.println("obj prop "+qryStr);
    qryStr = qryStr+" WHERE "+key+" = ?";
    System.out.println("obj fin "+qryStr);
    stmt = conexion.prepareStatement(qryStr);
    int j=-1;
    String prop=null;
    i = 1;
    for (PropertyDescriptor pd : pds) {
      String propName = pd.getName();
      if(propName.equals("class")|| 
	 propName.equals(key)){
		continue;
       }      
      stmt.setObject(i,getProperty(obj, propName));
      i++;   
    }   
    System.out.println("j="+"("+(i+1)+") "+key);
    stmt.setObject(i, getProperty(obj, key)); 
	  stmt.executeUpdate();
	} catch(SQLException sqle){
		sqle.printStackTrace();
		throw new DAOException("");
	}
	catch(IntrospectionException ie){
		ie.printStackTrace();
		throw new DAOException("");
	}
	stmt.close();
	conexion.close();	
}


public void consulta(String nombre){
   Statement statement;
   boolean hasResults = false;
   //String queryString ="select * from perro where Nombre like '"+nombre+"'";
   String queryString ="select * from perro";
   try {
	conexion = getConnection();
	statement = conexion.createStatement();
	hasResults = statement.execute(queryString);
	if(hasResults){
		ResultSet result;
		result = statement.getResultSet();
		if(result!=null)	
			displayResults(result);
		} else System.out.println("no hay resultados");
   } catch (SQLException e) {
		e.printStackTrace();
   }
}
void displayResults_(ResultSet r) throws SQLException {
   String dato=null;
   ResultSetMetaData rmeta = r.getMetaData();
   int numColumns=rmeta.getColumnCount();
   String text="";
   for(int i=1;i<=numColumns;++i) {
	  if(i<numColumns)
	    	text+=rmeta.getColumnName(i)+" | ";
	   else
	    	text+=rmeta.getColumnName(i);
   }
   text+="\n";
   while(r.next()){
	  for(int i=1;i<=numColumns;++i) {
		dato=r.getString(i);
		if(dato!=null){
	    		if(i<numColumns) //{
	     			text+=r.getString(i)+" | ";
	      				//System.out.println("["+r.getString(i)+"]");
	    			//}
	    		else
	     			text+=r.getString(i).trim();
		}
	  }
	   text+="\n";
   }
   System.out.println(text);
}
static void displayResults(ResultSet r) throws SQLException {
        ResultSetMetaData rmeta = r.getMetaData();
        int numCols = rmeta.getColumnCount();
        System.out.println("ncols"+numCols);
 	for (int i = 1; i <= numCols; ++i ){
		//System.out.println("i "+i);
		if(i < numCols)
			System.out.print(rmeta.getColumnName( i )+"  ");	
		else
			System.out.println(rmeta.getColumnName( i ));
        }
	while(r.next())
	    for(int i = 1;  i <= numCols; ++i){
                 Object value = r.getObject(i);
                 String s=value.toString().trim();
                 if( i < numCols)	       	
			  System.out.print(s+"  ");
		 else
			  System.out.println(s);
            }
}//displayResults
public static Object getProperty(Object o, String propertyName)
{
   if (o == null ||
       propertyName == null ||
       propertyName.length() < 1)
   {
      return null;
   }
   // --- Based on the property name build the getter method name ---
   String methodName = "get" +
                      propertyName.substring(0,1).toUpperCase() +
                      propertyName.substring(1);
   Object property = null;
   try {
      java.lang.Class c = o.getClass();
      java.lang.reflect.Method m = c.getMethod(methodName, null);
      property = m.invoke(o, null);
   } catch (NoSuchMethodException e) {
     // --- Handle exception --
   }  catch (SecurityException e) {
     // --- No permission; Handle exception --
   }
    catch (IllegalAccessException e) {
     // --- No permission; Handle exception --
   }
   catch (InvocationTargetException e) {
     // --- No permission; Handle exception --
   }
return property;
}
}
		
		
