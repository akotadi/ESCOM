//javac -cp beanutils.jar:commons-logging.jar:. Propiedad.java
//java -cp beanutils.jar:commons-logging.jar:. Propiedad java.awt.TextArea
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.*;
import java.awt.*;

public class Propiedad {
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
            PropertyUtils.setProperty(o, "background", Color.red);
	System.out.println("background: "+PropertyUtils.getProperty(o, "background"));
            PropertyUtils.setProperty(o, "name", "Requiem for a dream");
            //PropertyUtils.setProperty(f, "price", Double.valueOf(18.0));
    } catch(Exception ex){ ex.printStackTrace(); } 
    System.out.println(o);    
  }
}
