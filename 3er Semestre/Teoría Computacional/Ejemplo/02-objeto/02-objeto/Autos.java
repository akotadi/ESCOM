class Automovil
{
String color ;

    void asignaColor(String _color)
    {
        color = _color;
    }
    String indicaColor()
    {
        return color;
    }
}

class Autos
{
  public static void main(String args[])
  {
  Automovil autoDeportivo = new Automovil();
  Automovil camioneta = new Automovil();

      autoDeportivo.asignaColor("azul");
      camioneta.asignaColor("rojo");
      System.out.println("autoDeportivo color " + autoDeportivo.indicaColor());
      System.out.println("camioneta color " + camioneta.indicaColor());
  }
}


