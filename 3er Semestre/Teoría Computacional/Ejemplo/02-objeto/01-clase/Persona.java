class Persona
{
String nombre ;
int edad ;

    void setNombre(String _nombre) // Con esto el usuario almacena el nombre
    {
        nombre = _nombre;
    }
    String getNombre() //Con este m�todo el usuario recupera la propiedad nombre
    {
        return nombre;
    }
    public static void main(String args[])
  {
  Persona hombre = new Persona();
  Persona mujer = new Persona();

      hombre.setNombre("Juan");
      mujer.setNombre("Mar�a");

      System.out.println(hombre.getNombre());
      System.out.println(mujer.getNombre());
  }
}

