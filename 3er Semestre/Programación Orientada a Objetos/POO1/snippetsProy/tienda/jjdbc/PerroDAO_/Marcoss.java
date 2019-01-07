
//javac -cp beanutils.jar:commons-logging.jar:. PropGrafi.java
//java -cp beanutils.jar:commons-logging.jar:. PropGrafi java.awt.Button
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.awt.event.*;
import java.awt.*;

public class Marcoss implements ActionListener{
static Button binsert;
static Control control;
static PerroBean pb;
public static void main(String[] s) throws Exception {
    /*Class c=Class.forName(s[0]);
    Object o=c.getDeclaredConstructor(new Class[]{String.class}).newInstance("objeto1");
     
    System.out.println(o); */
    Marcoss ms=new Marcoss();
    control = new Control();
    pb= new PerroBean("fido","fox",2,"M", Color.red);
    Frame f=new Frame("Propiedades");   
    PanelHojaProp psp=new PanelHojaProp(f); 
    psp.setTarget(pb);
    f.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent winEvent) {
          System.exit(0);
       }
   	 });
    f.add("South", binsert=new Button("inserta"));
    binsert.addActionListener(ms);
    f.setSize(300, 300);
    f.setVisible(true);  
  }
  public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        try {
		System.err.println("Perro ins "+pb);
       		control.insertar(pb, "perro") ;
		control.consulta("*");
	} catch (DAOException daoe){
	}
  }
}
