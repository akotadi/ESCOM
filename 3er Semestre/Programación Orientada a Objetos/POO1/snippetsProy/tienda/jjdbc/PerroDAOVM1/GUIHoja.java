
//javac -cp beanutils.jar:commons-logging.jar:. PropGrafi.java
//java -cp beanutils.jar:commons-logging.jar:. PropGrafi java.awt.Button
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class GUIHoja {

public static void main(String[] s) throws Exception {
    JPanel pnlRaiz; 
    Class c=Class.forName(s[0]);
    Object o=c.getDeclaredConstructor(new Class[]{String.class}).newInstance("Solovino");
    BeanInfo beanInfo = Introspector.getBeanInfo(c);
    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
      
    System.out.println(o);  
    JFrame f=new JFrame("GUIBean");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pnlRaiz = (JPanel)f.getContentPane();
    pnlRaiz.setLayout(new BorderLayout());
    PanelHojaProp psp=new PanelHojaProp(f); 
    PerroBean pb= new PerroBean("firulais","fox",2,"M");
    psp.setTarget(pb);
    pnlRaiz.add(psp, BorderLayout.CENTER);
    //pnl_Add.add( psp, BorderLayout.CENTER);
    f.setSize(300, 300);
    f.setVisible(true);  
  }
}
