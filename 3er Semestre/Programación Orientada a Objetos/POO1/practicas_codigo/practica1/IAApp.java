import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class IAApp extends Applet implements ActionListener {
        Button b;
        TextField t;
	Label l;
        public void init(){
                t=new TextField(10); add(t);
                b=new Button("Muestra"); add(b);
                b.addActionListener(this);
		l=new Label("_______________"); add(l);
        }
        public void actionPerformed(ActionEvent e){
                l.setText(t.getText());
        }
}
