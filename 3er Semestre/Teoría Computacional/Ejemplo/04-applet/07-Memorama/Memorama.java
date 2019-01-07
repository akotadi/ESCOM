import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Memorama extends JApplet
{   
Image ai[] = new Image[11];
int x[] = {0 ,10  ,95  ,180 ,265 ,350 
             ,10  ,95  ,180 ,265 ,350  
             ,10  ,95  ,180 ,265 ,350  
             ,10  ,95  ,180 ,265 ,350     
          };
          
int y[] = {0 ,10  ,10  ,10  ,10  ,10 
             ,140 ,140 ,140 ,140 ,140
             ,270 ,270 ,270 ,270 ,270
             ,400 ,400 ,400 ,400 ,400             
          };

int valor[] = {0 ,-5  ,-2  ,-4  ,-10 ,-7 
                 ,-4  ,-3  ,-9  ,-8  ,-1
                 ,-7  ,-6  ,-9  ,-2  ,-6 
                 ,-1  ,-8  ,-10 ,-3  ,-5
          }; 
int contador = 0;// INDICA EL NUMERO DE IMAGENES VOLTEADAS
int anterior = 0;
int actual = 0;
int buenas = 0;

  MouseListener eventoRaton = new MouseListener() 
  {
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mousePressed(MouseEvent e) 
    {
    int xR ,yR // COORDENADAS DONDE EL USUARIO HIZO CLICK
    ,cualN ; // ES EL INDICE DE valor Y DE x Y y 
    
        // COORDENADAS DEL RATON DONDE SE HIZO CLICK      
        xR = e.getX();
        yR = e.getY();
        
//      if( e.getClickCount() != 2 )
//      return;

       // SUPONEMOS QUE NO HAY IMAGEN SELECCIONADA
       cualN = 0;
         
       for( int n=1; n <= 20; n++ )
           // SELECCIONA EN CUAL IMAGEN HIZO click EL USUARIO    
           if( xR >= x[n] && xR <= (x[n]+75)
            
           && yR >= y[n] && yR <= (y[n]+120) )
           
           {
              cualN = n;
              break;
           }
 
       // SI HIZO CLICK FUERA DE ALGUNA IMAGEN regresa
        if(contador == 2 )  // CONTADOR vale 2
        // REVISA SI LAS IMAGENES ANTERIORES SE REVOLTEAN O SE QUEDAN DE FRENTE
        {
            contador = 0;  // YA NO TENGO IMAGENES VOLTEADAS
            // SI ES LA MISMA IMAGEN, QUEDAN DE FRENTE 
            if( valor[anterior] == valor[actual] )
            {
                buenas++;
                if( buenas == 10 )
                    JOptionPane.showMessageDialog(null, "GANASTE");
                System.out.println("actual == anterior");
            }
            else
            // SE REVOLTEAN LAS IMAGENES HACIENDOLAS NEGATIVAS
            {
               valor[actual] = - valor[actual];
                valor[anterior] = - valor[anterior];
              }
              repaint();
              return;
           }
           
       if( cualN < 1)
          return;
 
       // SI LA IMAGEN DONDE HIZO CLICK YA ESTA VOLTEADA (POSITIVO) regresa
       if( valor[cualN] > 0 )
          return;
 
       // SI ES LA PRIMERA IMAGEN VOLTEADA, LA GUARDA COMO ANTERIOR
       if( contador == 0 )
       {
             contador = 1;// YA TENGO UNA IMAGEN VOLTEADA
             anterior = cualN; // GUARDO EL INDICE DE LA IMAGEN VOLTEADA
             valor[cualN] = - valor[cualN];
         }
         else
           // SI ES LA SEGUNDA IMAGEN VOLTEADA, LA GUARDA COMO ACTUAL
           if( contador == 1 )
           {
               contador = 2;// YA TENGO DOS IMAGENES VOLTEADAS
               actual = cualN; // GUARDO EL INDICE DE LA SEGUNDA IMAGEN VOLTEADA
               valor[cualN] = - valor[cualN];
           }
          
       repaint();      
    }
  };
  
    public void init()
    {
        setBackground(Color.white);
        this.addMouseListener(eventoRaton);
        
// SE CARGAN IMAGENES QUE SE UTILIZARAN DURANTE EL JUEGO 
        for( int n=0; n < 11; n++ )
          ai[n] = getImage(getCodeBase(),"f"+n+".jpg");
    }

    // EL METODO PAINT SE LLAMA AUTOMATICAMENTE
    public void paint(Graphics g)
    {        
      for( int n=1; n <= 20; n++ )
        if( valor[n] < 0 )
          g.drawImage(ai[0] ,x[n] ,y[n] ,75 ,120, this);
        else        
          g.drawImage(ai[valor[n]] ,x[n] ,y[n] ,75 ,120, this); 
    }
}