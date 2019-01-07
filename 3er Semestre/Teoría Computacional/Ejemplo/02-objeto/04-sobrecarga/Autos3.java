class Automovil3
{
String color ;


    // CONSTRUCTORES
    Automovil3()
    {
        color = "blanco";
    }
    Automovil3(String _color)
    {
        color = _color;
    }

    // METODOS DE INSTANCIA
    void asignaColor(String _color)
    {
        color = _color;
    }
    String indicaColor()
    {
        return color;
    }
}

class Autos3
{
  public static void main(String args[])
  {
  Automovil3 autoDeportivo = new Automovil3();
  Automovil3 camioneta = new Automovil3("rojo");

      System.out.println("autoDeportivo color " + autoDeportivo.indicaColor());
      System.out.println("camioneta color " + camioneta.indicaColor());
  }
}


