/* Polimorfismo.java 

   EJEMPLO DE HERENCIA, SOBREESCRITURA Y POLIMORFISMO 

2003 08 Jesus Olivares 
*/ 
import javax.swing.*; 
import javax.swing.event.*; 
import javax.swing.table.*; 
import java.util.*; 
import java.awt.*; 
import java.awt.event.*; 

// ***************************** MODELO DE CLASES ******************************
class Figura 
{ 
// PROPIEDADES 
int x ,y ; 
Color color ; 

  // CONSTRUCTOR 
  Figura(int _x ,int _y ,Color _color) 
  { 
      x = _x; 
      y = _y; 
      color = _color; 
  } 

  // METODOS 
  void pinta(Graphics g) 
  { 
      g.setColor(color); 
      g.drawString("sin forma" ,x ,y); 
  }
} 

// Circulo ES SUBCLASE DE Figura Y Figura ES SUPERCLASE DE Circulo
class Circulo extends Figura 
{
// PROPIEDADES, LAS QUE HEREDA YA NO SE ESCRIBEN: x y color
double radio ;

   // CONSTRUCTOR 
   Circulo(int _x ,int _y ,double _radio ,Color _color) 
   { 
       super(_x ,_y ,_color); // INVOCA AL CONSTRUCTOR DE SU SUPERCLASE
       radio = _radio; // INICIALIZA SUS PROPIEDADES DE INSTANCIA
   } 

   // METODOS 
   void pinta(Graphics g) // SOBREESCRIBE A pinta() de Figura
   { 
       g.setColor(color); 
       g.fillOval((int)(x - radio) ,(int)(y - radio) 
         ,(int)(2 * radio) ,(int)(2 * radio)); 
   }
}

class Cuadrado extends Figura 
{ 
int lado ; 

  // CONSTRUCTOR
  Cuadrado(int _x ,int _y ,int _lado ,Color _color) 
  { 
      super(_x ,_y ,_color); 
      lado = _lado; 
  } 

   // METODOS 
   void pinta(Graphics g) // SOBREESCRIBE A pinta() de FIGURA
   { 
       g.setColor(color); 
       g.fillRect((int)(x - lado) ,(int)(y - lado) ,lado ,lado); 
   }
} 

class Triangulo extends Figura 
{
   // CONSTRUCTOR
   Triangulo(int _x ,int _y ,Color _color) 
   { 
       super(_x ,_y ,_color); 
   } 
}

class Isoceles extends Triangulo 
{
// PROPIEDADES
int base ,altura ;

   // CONSTRUCTOR
   Isoceles(int _x ,int _y ,int _base ,int _altura ,Color _color)
   { 
       super(_x ,_y ,_color); 
       base = _base;
       altura = _altura;
   }

   // METODOS 
   void pinta(Graphics g) // SOBREESCRIBE A pinta() de Figura Y NO DE Triangulo
   {
   int ladoX[] = {x ,x + base ,x + (int)(base / 2.0) ,x};
   int ladoY[] = {y ,y        ,y - altura            ,y};
   
       g.setColor(color); 
       g.fillPolygon(ladoX ,ladoY ,ladoX.length);
   }
}

// ************************ INTERFASE PARA EL USUARIO **************************
class Grafica extends Canvas 
{ 
  // paint SE INVOCA AUTOMATICAMENTE CUANDO SE CREA UN OBJETO DE Canvas
  public void paint(Graphics g) 
  { 
      setBackground(Color.white); 
  } 
  
  public void dibuja() 
  { 
  Graphics g = getGraphics(); // APUNTA AL CONTEXTO GRAFICO DEL Canvas

  // DECLARA EL ARREGLO DE OBJETOS 
  Figura arreglo[] = new Figura[6]; 

      // DETERMINA QUE OBJETO SE ALMACENA EN CADA ELEMENTO 
      arreglo[0] = new Circulo(20,30,10 ,Color.blue); 
      arreglo[1] = new Triangulo(50,220,Color.red); 
      arreglo[2] = new Circulo(200,100,40 ,Color.cyan); 
      arreglo[3] = new Cuadrado(120,40,30,Color.green); 
      arreglo[4] = new Triangulo(250,70,Color.gray);
      arreglo[5] = new Isoceles(173 ,204 ,50 ,66 ,new Color(0100 ,150 ,0xFF)); 

     //  LLAMA AL METODO pinta PARA CADA ELEMENTO APLICANDO POLIMORFISMO 
     for(int i=0; i < arreglo.length;  i++) 
         arreglo[i].pinta(g); 
  }
}

class Pantalla extends JFrame 
{ 
Grafica grafica = new Grafica(); // LIENZO DONDE SE DIBUJAN LAS FIGURAS
JScrollPane panelScroll = new JScrollPane(grafica);  // SCROLL PARA grafica
JPanel panelMarco = new JPanel(new GridLayout(1,1)); // MARCO PARA panelScroll
JPanel panelBotones = new JPanel(new FlowLayout()); // PANEL PARA LOS BOTONES

// PANEL PARA COLOCAR EL MARCO Y EL PANEL DE BOTONES
JSplitPane panelAjustable = new JSplitPane(JSplitPane.VERTICAL_SPLIT); 

// BOTONES, EN CADA UNO SE INDICA LA ACCION QUE SE EFECTUA CUANDO SE PRESIONA
JButton dibuja = new JButton("DIBUJA"); 
ActionListener dibujaAccion = new ActionListener() 
{ 
  public void actionPerformed(ActionEvent e) 
  { 
       grafica.dibuja(); // SE INVOCA EL DESPLIEGUE DE FIGURAS
  }
}; 

JButton salir = new JButton("SALIR"); 
ActionListener salirAccion = new ActionListener() 
{ 
  public void actionPerformed(ActionEvent e) 
  { 
       System.exit(0); 
  } 
}; 

   // CONSTRUCTOR 
  public Pantalla() 
  { 
  Container cont = getContentPane(); 

      // DIMENSIONA EL MARCO Y COLOCA SU TITULO
      panelMarco.setMinimumSize(new Dimension(400, 300)); 
      panelMarco.setBorder(BorderFactory.createTitledBorder("Herencia y Polimorfismo")); 
       
      // COLOCA EL panelScroll QUE CONTIENE EL AREA DE GRAFICACION EN EL panelMarco
      panelMarco.add(panelScroll); 

      // ASIGNA LOS BOTONES EN EL panelBotones
       panelBotones.add(dibuja); 
       panelBotones.add(salir); 

       // COLOCA EL MARCO Y EL PANEL DE BOTONES EN EL panelAjustable
       panelAjustable.add(panelMarco); 
       panelAjustable.add(panelBotones); 

       // AGREGA EL panelAjustable EN EL CONTENEDOR DEL JFrame
       cont.setLayout(new BorderLayout()); 
       cont.add(panelAjustable, BorderLayout.CENTER); 

       // ASIGNA LAS ACCIONES A LOS BOTONES
       dibuja.addActionListener(dibujaAccion); 
       salir.addActionListener(salirAccion); 

       // ACCION PARA CUANDO SE PIDE EL CIERRE DE LA VENTANA 
       addWindowListener(new WindowAdapter() 
       { 
           public void windowClosing(WindowEvent e) 
           { 
               dispose(); 
           } 
       }
   ); 
 } 
} 

class Polimorfismo 
{ 
  public static void main(String args[]) 
  { 
  Pantalla pantalla = new Pantalla(); 

      pantalla.setSize(400, 400); 
      pantalla.show(); 
  }
}