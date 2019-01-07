class Persona implements Cloneable
{
String nombre ;
String direccion ;

    public Persona(String _nombre ,String _direccion)
    {
        nombre = _nombre;
        direccion = _direccion;
    }
    public Object clone()
    {
    Cloneable copia = new Persona(nombre ,direccion);

        return copia;
    }
    void asignaDireccion(String _direccion)
    {
        direccion = _direccion;
    }
    void despliega()
    {
        System.out.println("Nombre: " + nombre + " Dirección: " + direccion);
    }
}

class Clon
{
    public static void main(String arg[])
    {
    Persona juan = new Persona("Juan" ,"Lomas"); // SE CREA EL OBJETO juan

        juan.despliega(); // SE DESPLIEGAN LOS DATOS DE juan
        System.out.println("Clonando a Juan");

    Persona copiaJuan = (Persona)juan.clone(); // SE CLONA EL OBJETO juan
    Persona otroApuntadorJuan = juan;  // OTRO OBJETO APUNTA A juan

        copiaJuan.asignaDireccion("Iztapalapa"); // CAMBIO DE DIRECCION AL CLON
        juan.despliega(); // DESPLIEGA LOS DATOS DE juan
        copiaJuan.despliega(); // DESPLIEGA LOS DATOS DEL CLON
        otroApuntadorJuan.despliega(); // DESPLIEGA LOS DATOS DEL OTRO APUNTADOR

        otroApuntadorJuan.asignaDireccion("C.U."); // CAMBIO DE DIRECCION A juan
        juan.despliega(); // DESPLIEGA LOS DATOS DE juan
        otroApuntadorJuan.despliega(); // DESPLIEGA LOS DATOS DEL OTRO APUNTADOR

        // LOS ARREGLOS SON CLONABLES AUTOMÁTICAMENTE
    int []arreglo = new int[] {1, 2, 3};
    int []clonArreglo = (int[])arreglo.clone();

        for(int i = 0; i < arreglo.length; i++ )
            System.out.print(""+arreglo[i]+" "+clonArreglo[i]+"  ");
        System.out.println();
    }
}
