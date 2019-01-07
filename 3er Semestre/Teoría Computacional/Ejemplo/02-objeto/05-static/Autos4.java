/** Autos4.java

    EJEMPLO DE PROPIEDADES Y METODOS DE CLASE
    Los METODOS DE CLASE se usan sin crear objetos (o con ellos) mientras que
los METODOS DE INSTANCIA requieren de un objeto. Un METODO DE CLASE no puede
accesar propiedades de instancia porque no se sabe a cual se refiere.
    Las PROPIEDADES DE CLASE se accesan desde cualquier metodo y son unicas para
todas las instancias (objetos) creadas.
*/
class Automovil
{
// PROPIEDAD DE INSTANCIA
String color ;

// PROPIEDAD DE CLASE
static int cuenta;

    // CONSTRUCTORES
    Automovil()
    {
        color = "blanco";
        cuenta++; 
    }
    Automovil(String _color)
    {
        color = _color;
        cuenta++;
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

    // METODO DE CLASE
    static void contador()
    {
        System.out.println("Creadas " + cuenta + " instancias");
    }
}

class Autos4
{
  public static void main(String args[])
  {
  Automovil.contador(); // SE INVOCA AL METODO DE CLASE SIN CREAR UN OBJETO
  Automovil autoDeportivo = new Automovil();
  
  // EL METODO DE CLASE SE INVOCA TAMBIEN DESDE LOS OBJETOS
  autoDeportivo.contador(); 
  Automovil camioneta = new Automovil("rojo");
  camioneta.contador();

      System.out.println("autoDeportivo color " + autoDeportivo.indicaColor());
      System.out.println("camioneta color " + camioneta.indicaColor());
      Automovil.contador();
  }
}