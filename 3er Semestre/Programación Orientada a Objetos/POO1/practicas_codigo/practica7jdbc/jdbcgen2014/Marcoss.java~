
//javac -cp .:beanutils.jar:commons-logging.jar  Marcoss.java
//java -cp .:beanutils.jar:commons-logging.jar:mysqlcon.jar  Marcoss PerroBean
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class Marcoss implements ActionListener{
static Button binsert;
static Control control;
static Object obj;
public static void main(String[] s) throws Exception {
    Class c=Class.forName(s[0]);
    obj=c.getDeclaredConstructor().newInstance();
     
    System.out.println(obj); 
    Marcoss ms=new Marcoss();
    control = new Control("com.mysql.jdbc.Driver", 
              "jdbc:mysql://localhost:3306/test","root","gatito");
    JFrame f=new JFrame("Propiedades");   
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.add("South", binsert=new Button("inserta"));
    f.add("North", new PropertySheet(obj));
    binsert.addActionListener(ms);
    f.setSize(300, 300);
    f.setVisible(true);  
  }
  public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        try {
		System.err.println("Perro ins "+obj);
       		control.insertar(obj, obj.getClass().getName()) ;
		//control.consulta("*");
	} catch (DAOException daoe){
	}
  }
}
