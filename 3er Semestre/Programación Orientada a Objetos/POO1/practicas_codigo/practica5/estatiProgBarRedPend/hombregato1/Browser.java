import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Browser extends JPanel implements HyperlinkListener, 
                                               ActionListener {
  public static void main(String[] args) {
    if (args.length == 0)
      new Browser("http://www.apl.jhu.edu/~hall/");
    else
      new Browser(args[0]);
  }

  private JButton homeButton;
  private JTextField urlField;
  private JEditorPane htmlPane;
  private String initialURL;

  public Browser(String initialURL) {
    this.initialURL = initialURL;
    setLayout(new BorderLayout());
    JPanel topPanel = new JPanel();
    topPanel.setBackground(Color.lightGray);
    homeButton = new JButton("home");
    homeButton.addActionListener(this);
    JLabel urlLabel = new JLabel("URL:");
    urlField = new JTextField(30);
    urlField.setText(initialURL);
    urlField.addActionListener(this);
    topPanel.add(homeButton);
    topPanel.add(urlLabel);
    topPanel.add(urlField);
    add(topPanel, BorderLayout.NORTH);

    try {
        htmlPane = new JEditorPane(initialURL);
        htmlPane.setEditable(false);
        htmlPane.addHyperlinkListener(this);
        JScrollPane scrollPane = new JScrollPane(htmlPane);
        add(scrollPane, BorderLayout.CENTER);
    } catch(IOException ioe) {
       warnUser("Can't build HTML pane for " + initialURL 
                + ": " + ioe);
    }

/*
    Dimension screenSize = getToolkit().getScreenSize();
    int width = screenSize.width * 8 / 10;
    int height = screenSize.height * 8 / 10;
    setBounds(width/8, height/8, width, height);*/
  }

  public void actionPerformed(ActionEvent event) {
    String url;
    if (event.getSource() == urlField) 
      url = urlField.getText();
    else  // Clicked "home" button instead of entering URL
      url = initialURL;
    try {
      htmlPane.setPage(new URL(url));
      urlField.setText(url);
    } catch(IOException ioe) {
      warnUser("Can't follow link to " + url + ": " + ioe);
    }
  }

  public void hyperlinkUpdate(HyperlinkEvent event) {
    if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      try {
        htmlPane.setPage(event.getURL());
        urlField.setText(event.getURL().toExternalForm());
      } catch(IOException ioe) {
        warnUser("Can't follow link to " 
                 + event.getURL().toExternalForm() + ": " + ioe);
      }
    }
  }

  private void warnUser(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", 
                                  JOptionPane.ERROR_MESSAGE);
  }
}
    
    
