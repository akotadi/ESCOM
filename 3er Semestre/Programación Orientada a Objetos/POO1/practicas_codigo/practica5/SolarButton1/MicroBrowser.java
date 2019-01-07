import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.net.*;
import java.io.*;
public class MicroBrowser implements HyperlinkListener{
	JEditorPane jt;
	public MicroBrowser(JEditorPane jt) {
		this.jt = jt;
	}
	public void hyperlinkUpdate(final HyperlinkEvent e) {
        if (e.getEventType() == 
            HyperlinkEvent.EventType.ACTIVATED) {
          SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              Document doc = jt.getDocument();
              try {
                URL url = e.getURL();
                System.out.println("URL valido");
                jt.setPage(url);
              } catch (IOException io) {
                System.out.println("URL no valido");
                jt.setDocument (doc);
              }
            }
          });
        }
      }
}
