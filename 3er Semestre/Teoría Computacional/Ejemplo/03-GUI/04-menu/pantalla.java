import java.awt.*;
import java.lang.Math;

class Ventana extends Frame
{
MenuBar barraMenu = new MenuBar();
Menu archivo = new Menu("Archivo",true);
MenuItem opcionCirculo = new MenuItem("Circulo");
MenuItem opcionCuadrado = new MenuItem("Cuadro");
MenuItem opcionLinea = new MenuItem("Linea");
MenuItem opcionLimpia = new MenuItem("Limpia");
MenuItem opcionRandom = new MenuItem("Random");
MenuItem opcionPoligono = new MenuItem("Poligono");
MenuItem opcionSalir = new MenuItem("Salir");

    public Ventana()
    {
        this.setMenuBar(barraMenu);
        this.barraMenu.add(archivo);
        archivo.add(opcionCuadrado);
        archivo.add(opcionCirculo);
        archivo.add(opcionLinea);
        archivo.add(opcionRandom);
        archivo.add(opcionPoligono);
        archivo.add(opcionLimpia);
        archivo.add(opcionSalir);
    }
      
      
    //HABILITA EL CIERRE DE LA VENTANA CON EL TACHE
    public boolean handleEvent(Event evento)
    {
        if(evento.id == Event.WINDOW_DESTROY)
            System.exit(0);
        return super.handleEvent(evento);
    }
      
      
     public boolean action(Event evento, Object objeto)  
      {
        if(evento.target instanceof MenuItem)
        {                        
           if(objeto.equals("Salir") == true)
           {
              System.exit(0);
           }
           if(objeto.equals("Circulo") == true)
           {
           Graphics g=getGraphics();

               g.translate(0,30); // MUEVE EL ORIGEN DE LA PANTALLA
               g.setColor(Color.red);
               g.fillOval(10,30,50,20);
               System.out.println("CIRCULO");
               g.drawString("Circulo",50,70);
           }
           if(objeto.equals("Cuadro") == true)
           {
           Graphics g = getGraphics();

               g.setColor(Color.blue);
               g.translate(0,40);
               g.fillRect(0,0,50,50);
           }
           if(objeto.equals("Limpia")==true)
           {
           Graphics g = getGraphics();

               g.setColor(Color.white);
               g.fillRect(0,0,300,300);
           }
           if(objeto.equals("Linea")==true)
           {
           Graphics g=getGraphics();

               g.setColor(Color.green);
               g.drawLine(10,10,100,80);
           }
           if(objeto.equals("Poligono")==true)
           {
           Graphics g=getGraphics();
           int poligono_x[] = { 140, 180, 180, 140, 100, 100, 140};
           int poligono_y[] = {   5,  25,  45,  65,  45,  25,   5};

              g.translate(0,40);
              g.fillPolygon(poligono_x, poligono_y, poligono_x.length);
           }
           if(objeto.equals("Random")==true)
           {
           short r,v,a ;
           int cuenta ,talla ,x ,y ,xf ,yf ;
           Graphics g=getGraphics();

              for( cuenta = 0; cuenta < 50; cuenta++ )
              {
                 r = (short)(Math.floor(Math.random() * 256));
                 v = (short)(Math.floor(Math.random() * 256));
                 a = (short)(Math.floor(Math.random() * 256));

                 x = (int)(Math.floor(Math.random() * 290 + 10));
                 y = (int)(Math.floor(Math.random() * 260 + 40));
                 xf = (int)(Math.floor(Math.random() * 290 + 10));
                 yf = (int)(Math.floor(Math.random() * 260 + 40));

                 g.setColor(new Color(r,v,a));
                 g.drawLine(x,y,xf,yf);
             }
            }
         }
        return false;
      }
      
    public void paint(Graphics g)
    {
        g.setColor(Color.red);
        g.drawRect(10,50,200,200);
    }
}
      
class Pantalla
{
    public static void main(String arg[])
    {
    Ventana consulta = new Ventana();

        consulta.resize(300,300);
        consulta.setTitle("Consulta");
        consulta.show();
    }
}
