class Automovil2
{
String color ;

    Automovil2(String _color)
    {
        color = _color;
    }
    void asignaColor(String _color)
    {
        color = _color;
    }
    String indicaColor()
    {
        return color;
    }
}

class Autos2
{
  public static void main(String args[])
  {
  Automovil2 autoDeportivo = new Automovil2("azul");
  Automovil2 camioneta = new Automovil2("rojo");

      System.out.println("autoDeportivo color " + autoDeportivo.indicaColor());
      System.out.println("camioneta color " + camioneta.indicaColor());
  }
}


