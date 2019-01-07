
//javac -cp beanutils.jar:commons-logging.jar:. PropGrafi.java
//java -cp beanutils.jar:commons-logging.jar:. PropGrafi java.awt.Button
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class IUCrea implements ActionListener{
static Button binsert;
//static Control control;
static CreaPerros cp;
static PerroBean pb;
public static void main(String[] s) throws Exception {
    /*Class c=Class.forName(s[0]);
    Object o=c.getDeclaredConstructor(new Class[]{String.class}).newInstance("objeto1");
     
    System.out.println(o); */
    IUCrea ms=new IUCrea();
    //control = new Control();
    
    pb= new PerroBean("50","50","5","15"/*, Color.red*/);
    Frame f=new Frame("Propiedades");   
    
    PanelHojaProp psp=new PanelHojaProp(f); 
    cp=new CreaPerros(pb);
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
/*
        try {
		System.err.println("Perro ins "+pb);
       		control.insertar(pb, "Perro") ;
		cp.crear();

		try{
		control.displayResults(control.consulta("select * from Perro"));
		}
		catch(SQLException eq){System.err.println("display");}
	} catch (DAOException daoe){
	}*/
  }
}
