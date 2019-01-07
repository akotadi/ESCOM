
//javac -cp beanutils.jar:commons-logging.jar:. PropGrafi.java
//java -cp beanutils.jar:commons-logging.jar:. PropGrafi java.awt.Button
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.*;
import java.awt.event.*;
import java.awt.*;

public class GUIBean {
public static void main(String[] s) throws Exception {
    Class c=Class.forName(s[0]);
    Object o=c.getDeclaredConstructor(new Class[]{String.class}).newInstance("Solovino");
    BeanInfo beanInfo = Introspector.getBeanInfo(c);
    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    
    
    System.out.println(o);  
    Frame f=new Frame("GUIBean");
    Panel p=new Panel();
    f.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent winEvent) {
          System.exit(0);
       }
   	 });
    p.setLayout(new GridLayout(pds.length,2));
    for (PropertyDescriptor pd : pds) {
      String propertyName = pd.getName();
      //System.out.println(propertyName);
      p.add(new Label(propertyName));
      System.out.println(propertyName+": "+PropertyUtils.getProperty(o, propertyName));
      Object o1=PropertyUtils.getProperty(o, propertyName);
      String s1;
      if(o1==null)
         s1="";
      else
	 s1=o1.toString().trim();
      p.add(new TextField(s1));
    }
    f.add("Center", p);
    f.setSize(300, 300);
    f.setVisible(true);  
  }
}
