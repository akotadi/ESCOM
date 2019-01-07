//javac -cp beanutils.jar:commons-logging.jar:. Propiedad.java
//java -cp beanutils.jar:commons-logging.jar:. Propiedad
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.*;
import java.awt.*;

public class Propiedad {
public static void main(String[] argv) throws Exception {
    
    BeanInfo beanInfo = Introspector.getBeanInfo(Button.class);
    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    Button b=new Button("Test");
    for (PropertyDescriptor pd : pds) {
      String propertyName = pd.getName();
      System.out.println(propertyName+": "+PropertyUtils.getProperty(b,propertyName));
    }
    try {
            //PropertyUtils.setProperty(f, "id", Long.valueOf(10));
            PropertyUtils.setProperty(b, "name", "Requiem for a dream");
            //PropertyUtils.setProperty(f, "price", Double.valueOf(18.0));
    } catch(Exception ex){ ex.printStackTrace(); } 
    System.out.println("Boton = " + b);    
  }
}
