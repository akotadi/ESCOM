import java.awt.*;
import javax.swing.*;

class Hola extends JApplet
{
    public void paint( Graphics g )
    {
        g.drawString("Hola amigos", 5, 25 );  // X Y
    }
}

public class Hola2
{
    public static void main(String args[])
    {
        Console.run(new Hola(), 200 ,100);
    }
}


class Console {
  // Create a title string from the class name:
  public static String title(Object o) {
    String t = o.getClass().toString();
    // Remove the word "class":
    if(t.indexOf("class") != -1)
      t = t.substring(6);
    return t;
  }
  public static void setupClosing(JFrame frame) {
    // The JDK 1.2 Solution as an
    // anonymous inner class:
/*
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    */
    // The improved solution in JDK 1.3:
     frame.setDefaultCloseOperation(2); // 2= EXIT_ON_CLOSE
  }
  public static void
  run(JFrame frame, int width, int height) {
    setupClosing(frame);
    frame.setSize(width, height);
    frame.setVisible(true);
  }
  public static void
  run(JApplet applet, int width, int height) {
    JFrame frame = new JFrame(title(applet));
    setupClosing(frame);
    frame.getContentPane().add(applet);
    frame.setSize(width, height);
    applet.init();
    applet.start();
    frame.setVisible(true);
  }
  public static void
  run(JPanel panel, int width, int height) {
    JFrame frame = new JFrame(title(panel));
    setupClosing(frame);
    frame.getContentPane().add(panel);
    frame.setSize(width, height);
    frame.setVisible(true);
  }
} ///:~