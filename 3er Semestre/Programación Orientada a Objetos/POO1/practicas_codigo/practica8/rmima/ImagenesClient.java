import java.rmi.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;


//java -Djava.security.policy=no.policy ImagenesClient
public class ImagenesClient extends JFrame implements ActionListener {
   
   JLabel jimg;
   JComboBox jbbn;
   Container c;
   static ImageIcon img = null;
   static Imagenes i;
   Vector <String> nombres=new Vector<String>();

   public ImagenesClient(){
          try {
          	nombres=i.getNombres();
	  } 
         catch (RemoteException re) {
                System.out.println("Exception in HelloClient.constru: " + re);
      //re.printStackTrace();
         }
          jbbn=new JComboBox(nombres);
          jbbn.addActionListener(this);
          jimg=new JLabel(img); 
          c=getContentPane();
          c.setLayout(new GridLayout(2,1));
  	  c.add(jbbn);
	  c.add(jimg);
	  setVisible(true);
         
  }
  public void actionPerformed(ActionEvent e){
	  String nomimg=(String)jbbn.getSelectedItem();
          try{
		img = i.getImage(nomimg);
	}catch(Exception ex){
        System.out.println("falla imagen");
	System.out.println(ex.toString());
	}
        jimg.setIcon(img);
          
  }
  public static void main(String args[]) {
        
    System.setSecurityManager(new RMISecurityManager());

    try {

      i = (Imagenes) Naming.lookup("imagenes");
      
	try{
		img = i.getImage("gato.jpg");
	}catch(Exception ex){
        System.out.println("falla imagen");
	System.out.println(ex.toString());
	}
      System.out.println("ImagenesClient: ");
    } 
    catch (Exception e) {
      System.out.println("Exception in main: " + e);
    }
    ImagenesClient hc=new ImagenesClient();
  }

}

