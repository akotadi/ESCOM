//import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.applet.*;

public class FairBuilder implements ActionListener{
  private Timer timer;
  protected  JPanel mainPanel;

  protected  JPanel waitPanel;
  protected  Container buttonsAndTarget;
  protected  JPanel fairPanel;
  protected Fair fair;
public static DragSourceJLabel createLabel(String filename, Applet applet) {
  URL url = FindUrl.get(filename, applet);
  ImageIcon icon = new ImageIcon(url,"description");
  DragSourceJLabel imageLabel = new DragSourceJLabel(icon);//("image");
  return imageLabel;
}
   // Possible Look & Feels
    private static final String mac      =
            "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    private static final String metal    =
            "javax.swing.plaf.metal.MetalLookAndFeel";
    private static final String motif    =
            "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    private static final String windows  =
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
public static void main(String[] args) {
  new FairBuilder();
}
public FairBuilder() {
  try {
    UIManager.setLookAndFeel(windows);
  } catch(Throwable thrown){System.out.println(thrown);}

  JFrame topFrame = new JFrame("Build a funfair");
  topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //JTabbedPane buildTab = new JTabbedPane(JTabbedPane.TOP);
  mainPanel = new JPanel();
  new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
  waitPanel = new JPanel();
  waitPanel.add(new JLabel("Building the fair. Please wait..."));
  Box panel = Box.createHorizontalBox(); 
  //panel.setLayout(new BoxLayout());
  new BoxLayout(panel,BoxLayout.X_AXIS);
  panel.setPreferredSize(new Dimension(600,120));
  //panel.setBackground(Color.cyan);
  buttonsAndTarget = Box.createVerticalBox();//new JPanel();
  new BoxLayout(buttonsAndTarget,BoxLayout.Y_AXIS);
  buttonsAndTarget.add(panel);
  fairPanel = new JPanel();
  new BoxLayout(fairPanel,BoxLayout.Y_AXIS);
  fair = new Fair(fairPanel, this);
 
  Applet applet = new Applet();

  DragSourceJLabel imageLabel = createLabel("bigwheelbutton.jpg",applet);
  imageLabel.setToolTipText("A Ferris Wheel");
  imageLabel.setName("ferris");
  panel.add(imageLabel,BoxLayout.X_AXIS);

  DragSourceJLabel twisterLabel = createLabel("twisterbutton.jpg",applet);
  twisterLabel.setToolTipText("Twister - a spinning ride");
  twisterLabel.setName("twister");
  panel.add(twisterLabel);

  DragSourceJLabel carouselLabel = createLabel("merrygoroundbutton.jpg",applet);
  carouselLabel.setToolTipText("A Carousel");
  carouselLabel.setName("carousel");
  panel.add(carouselLabel,BoxLayout.X_AXIS);

  DragSourceJLabel bermudaLabel = createLabel("bermudatrianglebutton.jpg",applet);
  bermudaLabel.setToolTipText("Bermuda Triangle - a spinning ride");
  bermudaLabel.setName("bermuda");
  panel.add(bermudaLabel);
  DrawingBoard target = new DrawingBoard();
  target.setFair(fair);
  target.setBackground(Color.green);
  target.setPreferredSize(new Dimension(300,300));
  JButton button = new JButton("Explore the Fair");
  button.addActionListener(this);
  buttonsAndTarget.add(target);

  //button.setBackground(Color.cyan);
  //Component content=new DragSourceJLabel("hello", DnDConstants.ACTION_MOVE);
  //Container container = new Panel();
  //container.setSize(300,200);
  //container.add(content);
  //content.setLocation(0,0);
  //container.validate();

  topFrame.getContentPane().add(mainPanel);// , BorderLayout.NORTH);
  //mainPanel.add(buildTab, BorderLayout.NORTH);
  Box box = new Box(BoxLayout.X_AXIS);
  box.add(button);
  buttonsAndTarget.add(box);
  mainPanel.add(buttonsAndTarget);

  topFrame.pack();
  topFrame.setVisible(true);
}
public void actionPerformed(ActionEvent e ) {
  if (e.getSource() != timer) {
  System.out.println("Button pressed.");
  mainPanel.removeAll();
  mainPanel.add(waitPanel);
  mainPanel.validate();
  mainPanel.repaint();
  timer = new Timer(80,this);
  timer.start();
} else {

  timer.stop();
  mainPanel.removeAll();
  mainPanel.add(fairPanel);
  try {
    fair.initialize();
  }
  catch(Throwable error) {
    System.out.println("Too much memory used?");
    fair = null;
    System.runFinalization();
    System.out.println(error.getMessage());
    backToTheDrawingBoard();
  }
  mainPanel.validate();
  mainPanel.repaint();
}}
public void backToTheDrawingBoard() {
  mainPanel.removeAll();
  mainPanel.add(buttonsAndTarget);
  mainPanel.repaint();
  //fairPanel.removeAll();
}
} // end of class