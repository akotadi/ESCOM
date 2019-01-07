
//javac -cp beanutils.jar:commons-logging.jar:. PropGrafi.java
//java -cp beanutils.jar:commons-logging.jar:. PropGrafi java.awt.Button
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.awt.event.*;
import java.awt.*;

public class Marco {
public static void main(String[] s) throws Exception {
    /*Class c=Class.forName(s[0]);
    Object o=c.getDeclaredConstructor(new Class[]{String.class}).newInstance("objeto1");
     
    System.out.println(o); */
    PerroBean pb= new PerroBean("fido","fox",2,"M");
    Frame f=new Frame("Propiedades");   
    PropertySheetPanel psp=new PropertySheetPanel(f); 
    psp.setTarget(pb);
    f.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent winEvent) {
          System.exit(0);
       }
   	 });
    //f.add("Center", (Component)o);
    f.setSize(300, 300);
    f.setVisible(true);  
  }
}
