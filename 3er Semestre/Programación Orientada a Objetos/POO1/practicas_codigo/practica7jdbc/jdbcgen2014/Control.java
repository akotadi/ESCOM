import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;
import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;
import java.lang.ClassNotFoundException;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
//javac -cp .:../lib/mysqlcon.jar:../lib/servlet-api.jar:../lib/beanutils.jar *.java
class Control extends AbstractTableModel {
   String driverClassName = "com.mysql.jdbc.Driver";
   String conexionUrl = "jdbc:mysql://localhost:3306/Perracos";
   String dbUser = "root";
   String dbPwd = "";

   Connection conexion = null;
   PreparedStatement stmt = null;

   private Statement stat;
   private ResultSet rs;               
   private ResultSetMetaData rsmd;     // Necesario para nombres de columnas
   private String mensaje_error = null;// Registro de mensajes de error
   private String sentencia;           // Ultima sentencia ejecutada
   int n;
   public Control() { }

   public Control(String driver, String host_BD, String login,String pwd){
      driverClassName = driver;
      conexionUrl = host_BD;
      dbUser = login;
      dbPwd = pwd; 
   }
   public Connection getConexion(){
      Connection conn;
   try {
     conn = ConnectionFactory.getInstance(driverClassName, conexionUrl, dbUser, dbPwd )
.getConexion();
   } catch (SQLException e) {
		e.printStackTrace();
		conn = null;
   }
   return conn;
}
public boolean cargarDatos( String tabla ) {    
	try {
            conexion = getConexion();
            stat = conexion.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
            boolean resultado = actualizar( tabla );
	    if ( resultado )
		rsmd = rs.getMetaData();  
	    return resultado;
	}
	catch (SQLException e) {
	    mensaje_error = new String( e.getMessage() );
	    return false;
	}
}
public String obt_mensaje_error() { return mensaje_error; }
public boolean actualizar( String tabla ) {
	try {
	    sentencia = "SELECT * FROM " + tabla;
            System.out.println("11111cmd=("+sentencia+")2222");
	    if (rs != null)
		rs.close();
            /*rs=consulta(sentencia);
            fireTableStructureChanged();*/
             if ( stat != null ) {
		rs = stat.executeQuery(sentencia); // Ejecutar la consulta
		fireTableStructureChanged();       // Ordena a la JTable que se actualice
		return true;
	    }
	    mensaje_error = new String( "No se pueden cargar los datos. Probablemente no hay conexión" );
	    return false;
	}
	catch (SQLException e) {
	    mensaje_error = new String( "No se pueden cargar los datos. " + e.getMessage() );
	    return false;
	
    }
 }
public void insertar__(Object obj, String tabla) throws DAOException{  
    insertar(obj, "perro") ;
}
public String getColumnName( int c ) {
       try {
	   if ( rsmd != null )
	       return rsmd.getColumnName(c + 1);
	   return "";
      }
       catch(SQLException e) {  e.printStackTrace();  return ""; }
}/*
    public Class getColumnClass( int col )  {
        try {
            String nombreClase = rsmd.getColumnClassName( col + 1 );
            return Class.forName( nombreClase );
        } catch ( Exception excepcion ) {
            excepcion.printStackTrace();
        }
        return Object.class;
    }*/
 public Class<?> getColumnClass(int col) {
            String namec = getColumnName( col );
	    if(namec.equals("imagen"))
            	return ImageIcon.class;
            return Object.class;
        
    }
public int getColumnCount() {
       try {
	   if ( rsmd != null )
	       return rsmd.getColumnCount();
	   return 0;
       }
       catch(SQLException e) {  e.printStackTrace();  return 0;  }
}
public int getRowCount() {
	try {
	    if ( rs != null ) {
		rs.last(); // Nos situamos en la última fila
		return rs.getRow(); // Devolvemos el número de la fila
	    }
	    return 0;
	}
	catch(SQLException e) {  e.printStackTrace();  return 0;  }
}
public ResultSet getResultSet() { return rs; }
public Object getValueAt( int fila, int col ) {
   BufferedImage img;
   try {
      if ( rs != null ) {
	 rs.absolute( fila + 1 );
	 String namec = getColumnName( col );
         if(namec.equals("imagen")){
	     Blob blob = rs.getBlob("imagen");
             int blobLength = (int) blob.length();  

             byte[] bytes = blob.getBytes(1, blobLength);
             blob.free();
	     try {
                img = ImageIO.read(new ByteArrayInputStream(bytes));
	     } catch(IOException e) {  e.printStackTrace();  return "";  }
             ImageIcon icon = new ImageIcon(img);  
	     icon= new ImageIcon(
             icon.getImage().getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH));
	     return icon;
	 }		
	 return rs.getObject( col + 1 );
      }  
      return "";
   } catch(SQLException e) {  e.printStackTrace();  return null;  }
}
protected void finalize() {
	try {
	    if (rs != null)
		rs.close();
	    if (rsmd != null)
		rs.close();
	    if (conexion != null)
		rs.close();
	    if (stat != null)
		stat.close();
	}
	catch(SQLException e) {  e.printStackTrace();  }
}
//================================================================================
public void crear(String nombre){
   Statement stmt;
   String qryStr;
   try{
      conexion = getConexion(); 
      Class c=Class.forName(nombre);
      Object obj=c.getDeclaredConstructor().newInstance(); 
           qryStr="create table "+ c.getSimpleName()+" ( ";
           BeanInfo bI = Introspector.getBeanInfo(obj.getClass());
           PropertyDescriptor[] pds = bI.getPropertyDescriptors();
           int i = 1;
           for (PropertyDescriptor pd : pds) {
              String propName = pd.getName();
              Class aType = pd.getPropertyType();
              //
              if(propName.substring(0,1).equals("id") || aType == int.class ){
                qryStr = qryStr+propName+" int , ";
                 continue;
              }
              if( aType == double.class ){
                qryStr = qryStr+propName+" double , ";
                 continue;
              }
              if(propName.equals("class"))
	         continue;
	      if(propName.equals("imagen"))
		qryStr = qryStr+propName+" longblob, ";
	      else
              	qryStr = qryStr+propName+" varchar(50), ";
              i++; 
           }
	   qryStr =qryStr.substring(0,qryStr.length()-2);
	   qryStr = qryStr+")";
           System.err.println("Consulta <"+qryStr);
	   stmt= conexion.createStatement();							
	   stmt.executeUpdate(qryStr/*createString*/);
           stmt.close();
	   conexion.close();
	} catch(Exception ex) {
			System.err.println("SQLException: " + ex.getMessage()+"ex "+ex);
	}
}
public void insertar(Object obj, String tabla) throws DAOException {
   String qryStr = "INSERT INTO "+ tabla + "(";
   try{
   conexion = getConexion();    
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
      //== Class c = pd.getPropertyType();
      if(propName.equals("class"))
	 continue;
      System.out.println("i="+"("+i+") "+propName);      
      if(propName.equals("imagen")){
	 try {       
	    System.out.println("Try i="+"("+i+") "/*+ii.toString()*/);
	    Object instancia=PropertyUtils.getProperty(obj, propName);
	    if(instancia instanceof String){
		String fileName = (String)instancia;         
		//System.out.println("en try file = "+fileName+" i= "+i);
		File fimage = new File(fileName);
		//System.out.println("en try size = "+fimage.length()+" i= "+i);
		InputStream inputImage = new FileInputStream(fimage);
    		stmt.setBinaryStream(i, inputImage, (int)(fimage.length()));
	    }
	    if(instancia instanceof ImageIcon){
	       ImageIcon ii = (ImageIcon)instancia;                              
               BufferedImage bImage=getBufImage(ii);
	       ByteArrayOutputStream out = new ByteArrayOutputStream();
               ImageIO.write(bImage,"jpeg",out);
               byte[] buf = out.toByteArray();
               ByteArrayInputStream inStream = new ByteArrayInputStream(buf);
       	       stmt.setBinaryStream(i, inStream,inStream.available());	
	    }
	 } catch (Exception e) {
			System.out.println("if Imagen Exception: " + e);
	 } 
      } else {
      	try {
      		stmt.setObject(i, PropertyUtils.getProperty(obj, propName));
      	} catch (Exception e) {e.printStackTrace();}
      }
      i++;
      //System.out.println("obj prop "+getProperty(pb,propertyName));   
   }
   n=stmt.executeUpdate();
   //cerrar?
   } catch(SQLException sqle){
	sqle.printStackTrace();
	throw new DAOException("");
   }
   catch(IntrospectionException ie){
	ie.printStackTrace();
	throw new DAOException("");
   }
   finally {
	try {
		conexion.close();
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException Finally: - " + e);
	}
   }
}

public void eliminar(String key, String valor, String tabla) throws SQLException{
   String qryStr = "DELETE FROM "+ tabla +" WHERE ";
   qryStr =qryStr + key+" = ?";
   System.out.println("eliminar "+qryStr);
   conexion = getConexion();
   stmt = conexion.prepareStatement(qryStr);
   stmt.setString(1, valor);	     
   stmt.executeUpdate();
   stmt.close();
   conexion.close();
}

public void actualizar(Object obj, String key, String tabla) throws Exception {
 String qryStr = "UPDATE "+tabla+" SET ";
   try{
     conexion = getConexion();
     BeanInfo bI = Introspector.getBeanInfo(obj.getClass());
     PropertyDescriptor[] pds = bI.getPropertyDescriptors();
    int i = 1;
    for (PropertyDescriptor pd : pds) {
      String propName = pd.getName();
      if(propName.equals("class") || 
	 propName.equals(key) )
		continue;
      System.out.println("i="+"("+i+") "+propName);
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
      if(propName.equals("imagen")){
	 try {       
	    System.out.println("Try i="+"("+i+") "/*+ii.toString()*/);
	    Object instancia=PropertyUtils.getProperty(obj, propName);
	    if(instancia instanceof String){
                String fileName = (String)instancia;         
		//System.out.println("en try file = "+fileName+" i= "+i);
		File fimage = new File(fileName);
		//System.out.println("en try size = "+fimage.length()+" i= "+i);
		InputStream inputImage = new FileInputStream(fimage);
    		stmt.setBinaryStream(i, inputImage, (int)(fimage.length()));
	    }
          } catch (Exception e) {
	     System.out.println("if Imagen Exception: " + e);
	  } 
      } else {
         try {
            //stmt.setBlob(i, is);
            stmt.setObject(i,PropertyUtils.getProperty(obj, propName));   
         } catch (Exception e) {e.printStackTrace();} 
      }
      i++;
    }   
    System.out.println("j="+"("+(i+1)+") "+key);
    try {
      stmt.setObject(i,PropertyUtils.getProperty(obj, key));
    }
    catch (Exception e) {e.printStackTrace();}   
                                                                                                                                                                                                                                                
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
public void actualiza(String comando) throws Exception{
   Statement st;
   System.out.println(comando); 
   try {
      conexion = getConexion();
      st = conexion.createStatement();	
      st.execute(comando);
   } catch(SQLException sqle){
	sqle.printStackTrace();
	throw new DAOException("");
   }
   st.close();
   conexion.close();	 
}
public ResultSet consulta(String qryStr){
   Statement stmt;
   ResultSet result=null;
   System.out.println("consulta "+qryStr);
   //String queryString ="select * from perro where Nombre like '"+nombre+"'";
   try {
	conexion = getConexion();
        stmt = conexion.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
        if ( stmt != null ) {
	    result = stmt.executeQuery(qryStr);
            //displayResults(result);
        }
        return result;
   } catch (SQLException e) {
		e.printStackTrace();
		return null;
   }
}

/*
        case Types.TINYINT:
        case Types.SMALLINT:
        case Types.INTEGER:
            return Integer.class;
*/
public ArrayList<Object> propertyChange11(String tabla, ResultSet r) throws DAOException{
   ArrayList<Object> listAux = new ArrayList<Object>();
   try {
      String dato=null;
      ResultSetMetaData rmeta = r.getMetaData();
      int numColumns=rmeta.getColumnCount();    
      Class c=Class.forName(tabla);
      Object bean; 
      //BeanInfo beanInfo = Introspector.getBeanInfo(c);
      int type, val= 0;
      //PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
      while(r.next()){
       bean=c.getDeclaredConstructor().newInstance(); 
       for(int i=1;i <= numColumns;++i) {
          type = rmeta.getColumnType(i);
          if(type== Types.TINYINT ||  type== Types.SMALLINT || type== Types.INTEGER){
             val= r.getInt(i);
             PropertyUtils.setProperty(bean, rmeta.getColumnName(i), val);	
	  } else { 
             dato=r.getString(i);
	     if(dato!=null){
                System.out.println("dato= "+dato);
	        PropertyUtils.setProperty(bean, rmeta.getColumnName(i), dato);	
	     }
          }
       }
       listAux.add(bean);
    }  
    return listAux;        
   } 
   catch(SQLException sqle){
		sqle.printStackTrace();
		throw new DAOException("");
   }
   catch (ClassNotFoundException ex){ ex.printStackTrace(); }
   catch (InstantiationException ex){ ex.printStackTrace(); }
   catch (IllegalAccessException ex){ ex.printStackTrace(); } 
   catch (NoSuchMethodException ex) { ex.printStackTrace(); }  
   catch (InvocationTargetException ex){ ex.printStackTrace(); } 
   //catch (IntrospectionException ex){ ex.printStackTrace(); }
   return null;
}
/*public void propertyChange(){
   try {
      BeanInfo info = Introspector.getBeanInfo(bean.getClass());
      PropertyDescriptor[] descriptors 
            = (PropertyDescriptor[])info.getPropertyDescriptors().clone(); 
      for (int i = 0; i < descriptors.length; i++){
         final Method setter = descriptor.getWriteMethod();
                     setter.invoke(bean, 
                        new Object[] { editor.getValue() });   
      }             
   } 
   catch (IllegalAccessException ex){ ex.printStackTrace(); } 
   catch (InvocationTargetException ex){ ex.printStackTrace(); } 
   catch (IntrospectionException ex){ ex.printStackTrace(); }
}*/
public ArrayList<Object> consultaGen(String tabla, String qryStr){
   Statement stmt;
   ResultSet result=null;
   ArrayList<Object> list=null;
   //String queryString ="select * from perro where Nombre like '"+nombre+"'";
   try {
	conexion = getConexion();
        stmt = conexion.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
        if ( stmt != null ) {
	    result = stmt.executeQuery(qryStr);
            list = propertyChange11(tabla, result);
        }     
        return list;
   } catch (Exception e) {
		e.printStackTrace();
		return null;
   }
}
public ResultSet consulta_(String qryStr){
   Statement stmt;
   boolean hasResults = false;
   //String queryString ="select * from perro where Nombre like '"+nombre+"'";
   try {
	conexion = getConexion();
        stmt = conexion.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
        if ( stmt != null ) 
	   return stmt.executeQuery(qryStr);
        return null;
/*      hasResults = statement.execute(qryStr);
	if(hasResults){
		ResultSet result;
		result = statement.getResultSet();
		if(result!=null)	
		   displayResults(result);
	} else 
	    System.out.println("no hay resultados");*/
   } catch (SQLException e) {
		e.printStackTrace();
		return null;
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
BufferedImage getBufImage(ImageIcon ii){
    BufferedImage bi = new BufferedImage(ii.getIconWidth(), ii.getIconHeight(),
    BufferedImage.TYPE_INT_RGB);
    Graphics g = bi.createGraphics();
    ii.paintIcon(null, g, 0,0);
    g.dispose();
    return bi;
}
}
		
		
