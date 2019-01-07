import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.media.j3d.*;
//sudo javac -cp .:MS3DLoader.jar:portfolio.jar Tamagotchi.java
//sudo java -cp .:MS3DLoader.jar:portfolio.jar Tamagotchi
public class Tamagotchi extends JFrame {
   private Container frameContainer;
   private Canvas3D canvas3D;
   private JButton bcomer;
   private JLabel jl, jlh;
   private JProgressBar progressBar = new JProgressBar();
   private EventHandler eh; 
   Espacio espacio;
   Audio audio;
   int val;
   int turno=0;
   
   public Tamagotchi(){
      frameContainer=getContentPane();
      frameContainer.setLayout(null);
      espacio=new Espacio(this); 
      //audio=new Audio();

      eh = new EventHandler();
      bcomer=new JButton("Comer");
      bcomer.addActionListener(eh);
      bcomer.setLocation(20, 20);
      bcomer.setSize(200, 300); 
      frameContainer.add(bcomer);
//BARRA DE PROGRESO
      progressBar.setMinimum(0);
      progressBar.setMaximum(100);
      progressBar.setValue(0);
      progressBar.setBounds(370,500,100,20);
      frameContainer.add(progressBar);
//ETIQUETA
      jl=new JLabel("Nombre");
      jl.setBounds(20,500,100,20);
      frameContainer.add(jl);
      jlh=new JLabel("Hambre");
      jlh.setBounds(250,500,100,20);
      frameContainer.add(jlh);
//SE AÑADE LA ESCENA EN 3D
      canvas3D=espacio.getCanvas();
      canvas3D.setBounds(250,10,700,450);
      frameContainer.add(canvas3D);
      ((JPanel) getContentPane()).setOpaque(true);
      ImageIcon imagen = new ImageIcon(this.getClass().getResource("Fondo.jpg"));
      JLabel fondo = new JLabel();
      fondo.setIcon(imagen);
      getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
      fondo.setBounds(0, 0, imagen.getIconWidth(), imagen.getIconHeight());
      this.add(fondo, BorderLayout.CENTER);
      
      setResizable(false);
      setVisible(true);
      setIconImage(new ImageIcon("pacman.jpg").getImage());
      setTitle("TAMAGOTCHI");
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setSize(1000,700);
   }
   public static void main(String args[]){ Tamagotchi t=new Tamagotchi(); }
   class EventHandler implements ActionListener {  
         public void actionPerformed(ActionEvent e1) {  
            JButton btn=(JButton)e1.getSource();
            if(btn==bcomer){ 
	       val=100-val;
               turno=1-turno;
               System.out.println("Tamagochi come 2#### "+turno);    
               espacio.comer(turno);
               progressBar.setValue(val);
               
           }
        }
   }
}
