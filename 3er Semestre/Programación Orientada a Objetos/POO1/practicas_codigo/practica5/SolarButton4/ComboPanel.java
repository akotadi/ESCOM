
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class ComboPanel extends JPanel implements ActionListener {
 JComboBox jcb;
 AccionString as; 
 public ComboPanel(String opcs[], AccionString as){
    this.as = as;
    Vector combo=new Vector();
    for(int i = 0; i < opcs.length; i++){
    	combo.addElement(opcs[i]);  	
    }
    jcb=new JComboBox(combo);
    jcb.addActionListener(this);
    add(jcb);  
 }
 public void actionPerformed(ActionEvent e){
    int aux;
    Object botones= e.getSource();
    if(botones instanceof JComboBox){
		JComboBox jcb= (JComboBox) e.getSource();
		String nombre=(String)jcb.getSelectedItem();
		System.out.println("Se eligio ("+nombre +")");
                as.accion(nombre);
    }
 }
}
