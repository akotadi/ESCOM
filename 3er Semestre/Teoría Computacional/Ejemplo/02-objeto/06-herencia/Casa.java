class Foco
{
int estado ;
String nombre ;

    // CONSTRUCTORES DE LA CLASE Foco
    Foco(String _nombre)
    {
        nombre = _nombre;
    }
    Foco(int _estado ,String _nombre)
    {
        estado = _estado;
        nombre = _nombre;
    }

    // METODOS QUE FORMAN LA INTERFASE DE LA CLASE Foco (API)
    void on()  // void INDICA QUE NO DEVUELVE NINGUN VALOR
    {
        estado = 1;
    }
    void off()
    {
        estado = 0;
    }
    int avisame()
    {
        if( estado == 1 )
            System.out.println(nombre + " Encendido");
        else
            System.out.println(nombre + " Apagado");
        return estado; 
    }
}

class Navidad extends Foco
{
    Navidad(String _nombre)
    {
        super("Navidad " + _nombre);
    }
    int avisame()
    {
        System.out.println(nombre + " estado = " + estado);
        return estado; 
    }

}

class Casa
{

    public static void main(String a[])
    {
    Foco azul, rojo = new Foco("Rojo");
    Navidad verde = new Navidad("Verde");

        azul = new Foco(1 ,"Azul");
        verde.avisame();
        rojo.avisame();
        azul.avisame();
        verde.avisame();


    }
}
