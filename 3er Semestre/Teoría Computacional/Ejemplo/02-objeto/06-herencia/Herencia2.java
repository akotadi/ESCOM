class Persona
{
String nombre ;

    Persona(String _nombre)
    {
        nombre = _nombre;
    }
    String indicaNombre()
    {
        return nombre;
    }
}

class Alumno extends Persona
{
int calificacion ;

    Alumno(String _nombre)
    {
        super(_nombre);
        nombre = "ALUMNO**************" + nombre;
    }
    void asignaCalificacion(int _calificacion)
    {
        calificacion = _calificacion;
    }
    String indicaDatos()
    {
        return "Alumno: " + super.indicaNombre() + " calificacion: "
          + calificacion;
    }
}

class Profesor extends Persona
{
    Profesor(String _nombre)
    {
        super(_nombre);
    }
    String indicaDatos()
    {
        return "Profesor: " + super.indicaNombre();
    }
}

class Herencia
{
  public static void main(String args[])
  {
  Alumno emilio = new Alumno("Emilio");
  Profesor pedro = new Profesor("Pedro");

      emilio.asignaCalificacion(10);
      System.out.println(emilio.indicaDatos());
      System.out.println(pedro.indicaDatos());
  }
}


