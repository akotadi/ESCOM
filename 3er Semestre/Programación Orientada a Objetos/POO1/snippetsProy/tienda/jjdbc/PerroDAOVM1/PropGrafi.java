
//javac -cp beanutils.jar:commons-logging.jar:. PropGrafi.java
//java -cp beanutils.jar:commons-logging.jar:. PropGrafi java.awt.Button
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.*;
import java.awt.event.*;
import java.awt.*;

public class PropGrafi{
public static void main(String[] s) throws Exception {
    Class c=Class.forName(s[0]);
    Object o=c.getDeclaredConstructor(new Class[]{String.class}).newInstance("objeto1");
    BeanInfo beanInfo = Introspector.getBeanInfo(c);
    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    for (PropertyDescriptor pd : pds) {
      String propertyName = pd.getName();
      //System.out.println(propertyName);
      System.out.println(propertyName+": "+PropertyUtils.getProperty(o, propertyName));
    }
    try {
            PropertyUtils.setProperty(o, "background", Color.blue);
	System.out.println("background: "+PropertyUtils.getProperty(o, "background"));
            PropertyUtils.setProperty(o, "name", "Requiem for a dream");
            //PropertyUtils.setProperty(f, "price", Double.valueOf(18.0));
    } catch(Exception ex){ ex.printStackTrace(); } 
    System.out.println(o);  
    Frame f=new Frame("Gato");
    f.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent winEvent) {
          System.exit(0);
       }
   	 });
    f.add("Center", (Component)o);
    f.setSize(300, 300);
    f.setVisible(true);  
  }
}
